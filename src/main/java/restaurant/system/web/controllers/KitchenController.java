package restaurant.system.web.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import restaurant.system.service.OrderRedisService;
import restaurant.system.service.OrderService;
import restaurant.system.web.controllers.dto.OrderDto;

@RestController @RequestMapping("/kitchen")
//@PreAuthorize("hasAuthority('CHEF', 'ADMIN')")
public class KitchenController {

    @Autowired OrderService orderService;
    @Autowired OrderRedisService redisService;

    @GetMapping
    @RequestMapping("/orders")
    public ResponseEntity<Map<String, OrderDto>> getActiveOrders() {
        Map<String, OrderDto> allActiveOrdersForTheKitchen = orderService.getAllActiveOrdersForTheKitchen();
        return ResponseEntity.ok(allActiveOrdersForTheKitchen);
    }

    @PostMapping
    @RequestMapping("/finish")
    public ResponseEntity<String> finishOrder(
            @PathVariable int orderNumber) {
        String s = orderService.finishKitchenOrder(orderNumber);
        return ResponseEntity.ok(s);
    }

}
