package restaurant.system.web.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import restaurant.system.service.OrderRedisService;
import restaurant.system.service.OrderService;
import restaurant.system.web.controllers.dto.OrderDto;

@RestController
@RequestMapping("/bar")
//@PreAuthorize("hasAuthority('BARMAN', 'ADMIN')")
public class BarController {

    @Autowired OrderService orderService;

    @GetMapping
    @RequestMapping("/orders")
    public ResponseEntity<Map<String, OrderDto>> getActiveOrders() {
        Map<String, OrderDto> allActiveOrdersForTheBar = orderService.getAllActiveOrdersForTheBar();
        return ResponseEntity.ok(allActiveOrdersForTheBar);
    }

    @PostMapping
    @RequestMapping("/finish")
    public ResponseEntity<String> finishOrder(
            @PathVariable int orderNumber) {
        String s = orderService.finishBarOrder(orderNumber);
        return ResponseEntity.ok(s);
    }

}
