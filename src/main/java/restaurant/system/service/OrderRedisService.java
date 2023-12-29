package restaurant.system.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;

import restaurant.system.web.controllers.dto.OrderDto;

@Service
public class OrderRedisService {

    private final HashOperations<String, String, OrderDto> hashOperations;
    private static final String ORDER_HASH_KEY = "orders"; // Redis key for storing orders
    private final ApplicationEventPublisher eventPublisher;

    public OrderRedisService(RedisTemplate<String, OrderDto> redisTemplate, ApplicationEventPublisher eventPublisher) {
        this.hashOperations = redisTemplate.opsForHash();
        this.eventPublisher = eventPublisher;
    }

    public void saveOrder(OrderDto orderDto,String orderNumber) {
        hashOperations.put(ORDER_HASH_KEY, orderNumber, orderDto);
    }

    public OrderDto getOrder(int orderNumber) {
        return hashOperations.get(ORDER_HASH_KEY, String.valueOf(orderNumber));
    }

    public Map<String, OrderDto> getAllOrders() {
        return hashOperations.entries(ORDER_HASH_KEY);
    }

    public void deleteOrder(int orderNumber) {
        OrderDto order = getOrder(orderNumber);
        hashOperations.delete(ORDER_HASH_KEY, String.valueOf(orderNumber));
    }
    public void updateOrder(OrderDto updatedOrderDto) {
        hashOperations.put(ORDER_HASH_KEY, String.valueOf(updatedOrderDto.getOrderNumber()), updatedOrderDto);
    }
    
}

