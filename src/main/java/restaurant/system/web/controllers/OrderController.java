package restaurant.system.web.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import restaurant.system.db.enteties.TableBillEntity;
import restaurant.system.service.OrderRedisService;
import restaurant.system.service.OrderService;
import restaurant.system.web.controllers.dto.BillDto;
import restaurant.system.web.controllers.dto.OrderDto;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired OrderService orderService;
    @Autowired OrderRedisService redisService;

    @PostMapping("/create-bill")
    public ResponseEntity<String> createBill(){
        TableBillEntity bill = orderService.createBill();

        return  ResponseEntity.ok(bill.getId().toString());
    }
    @GetMapping("/active-bills")
    public ResponseEntity<List<BillDto>> getAllBills() {
      return ResponseEntity.ok(orderService.getAllActiveBills());

    }
//    @GetMapping("/paid-bills")
//    public ResponseEntity<List<BillDto>> getpaidBills(){
//        orderService.getPaidBills()
//    }
//    @CrossOrigin(origins = "http://localhost:8181")
    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto, HttpSession session) {
        try {
            redisService.saveOrder(orderDto,String.valueOf(orderDto.getOrderNumber()));
//            session.setAttribute("orderDto-"+orderDto.getOrderNumber(), orderDto);
            // Call the order service to create the order

            return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return new ResponseEntity<>("Failed to create order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update/{orderNumber}")
    public ResponseEntity<String> updateOrder(@PathVariable int orderNumber, @RequestBody OrderDto updatedOrderDto,
            HttpSession session) {
        try {
            redisService.updateOrder(updatedOrderDto);
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return new ResponseEntity<>("Failed to update order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable int orderNumber){
        return ResponseEntity.ok(redisService.getOrder(orderNumber));
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String,OrderDto>> getAllOrders(){
        return ResponseEntity.ok(redisService.getAllOrders());
    }
}
