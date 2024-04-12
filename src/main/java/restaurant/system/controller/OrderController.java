package restaurant.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.system.db.enums.OrderStatus;
import restaurant.system.service.OrderService;
import restaurant.system.controller.dto.OrderEntityDto;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Endpoint to update the order status
    @PostMapping("/update-status/{orderId}")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable long orderId, @RequestBody OrderStatus status) {
        try {
            orderService.updateOrderStatus(orderId, status);
            return ResponseEntity.ok().build();  // HTTP 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // HTTP 404 Not Found
        }
    }

    // Endpoint to create a new order
    @PostMapping("/create")
    public ResponseEntity<OrderEntityDto> createOrder(@RequestBody OrderEntityDto orderDto) {
        try {
            OrderEntityDto createdOrder = orderService.createOrder(orderDto);
            return ResponseEntity.created(URI.create("/orders/" + createdOrder.getId())).body(createdOrder);  // HTTP 201 Created
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();  // HTTP 400 Bad Request if something goes wrong
        }
    }

    @GetMapping("/order-status/{orderStatus}")
    public  ResponseEntity<List<OrderEntityDto>> getOrdersBtstatys(@RequestBody String orderStatus){

       List<OrderEntityDto> ordersByStatus = orderService.findAllOrdersByStatus(orderStatus);

    }
}
