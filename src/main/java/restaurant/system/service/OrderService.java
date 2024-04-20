package restaurant.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restaurant.system.controller.dto.OrderEntityDto;
import restaurant.system.controller.dto.OrderItemDto;
import restaurant.system.db.enteties.OrderEntity;
import restaurant.system.db.enteties.OrderItemEntity;
import restaurant.system.db.enums.OrderStatus;
import restaurant.system.db.repo.ItemRepository;
import restaurant.system.db.repo.OrderRepository;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final ItemRepository itemRepository;

    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    // Convert OrderEntity to OrderEntityDto
    private OrderEntityDto convertToOrderDto(OrderEntity order) {
        List<OrderItemDto> orderItemDtos = order.getOrderItems().stream()
                .map(this::convertToOrderItemDto)
                .collect(Collectors.toList());

        return new OrderEntityDto(order.getId(), order.getFinalPrice(), order.getOrderStatus().name(),
                order.getPhoneNumber(), order.getDeliveryAddress(), order.getOrderDate(), orderItemDtos);
    }

    // Convert OrderItem to OrderItemDto
    private OrderItemDto convertToOrderItemDto(OrderItemEntity orderItem) {
        return new OrderItemDto(orderItem.getId(), orderItem.getItem().getId(), orderItem.getQuantity());
    }

    // Convert OrderEntityDto to OrderEntity
    private OrderEntity convertToOrderItemEntity(OrderEntityDto orderDto) {
        OrderEntity order = new OrderEntity();
        // Set properties and convert order items
        order.setId(orderDto.getId());  // Ensure that ID handling is correct based on context (new vs update)
        order.setFinalPrice(orderDto.getFinalPrice());
        order.setOrderStatus(OrderStatus.valueOf(orderDto.getOrderStatus()));
        order.setPhoneNumber(orderDto.getPhoneNumber());
        order.setDeliveryAddress(orderDto.getDeliveryAddress());
        order.setOrderDate(orderDto.getOrderDate());
        order.setOrderItems(orderDto.getOrderItems().stream()
                .map(dto -> convertToOrderItemEntity(dto, order))
                .collect(Collectors.toList()));
        return order;
    }

    // Convert OrderItemDto to OrderItem
    private OrderItemEntity convertToOrderItemEntity(OrderItemDto dto, OrderEntity order) {
        OrderItemEntity item = new OrderItemEntity();
        item.setId(dto.getId());
        item.setOrderEntity(order);
        item.setItem(itemRepository.findById(dto.getItemId()).orElseThrow(() -> new IllegalArgumentException("Invalid item ID")));
        item.setQuantity(dto.getQuantity());
        return item;
    }

    @Transactional
    public OrderEntityDto createOrder(OrderEntityDto orderDto) {
        OrderEntity order = convertToOrderItemEntity(orderDto);
        order = orderRepository.save(order);
        return convertToOrderDto(order);
    }

    @Transactional
    public void updateOrderStatus(long orderId, OrderStatus status) {
        OrderEntity existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        existingOrder.setOrderStatus(status);
        orderRepository.saveAndFlush(existingOrder);

    }

    @Transactional
    public OrderEntityDto updateOrder(OrderEntityDto orderDto) {
        OrderEntity existingOrder = orderRepository.findById(orderDto.getId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Update the necessary fields
        existingOrder.setFinalPrice(orderDto.getFinalPrice());
        existingOrder.setPhoneNumber(orderDto.getPhoneNumber());
        existingOrder.setDeliveryAddress(orderDto.getDeliveryAddress());
        existingOrder.setOrderDate(orderDto.getOrderDate());
        existingOrder.setOrderStatus(OrderStatus.valueOf(orderDto.getOrderStatus()));

        // Assume that order items are correctly handled either here or in another method
        OrderEntity finalExistingOrder = existingOrder;
        existingOrder.setOrderItems(orderDto.getOrderItems().stream()
                .map(dto -> convertToOrderItemEntity(dto, finalExistingOrder))
                .collect(Collectors.toList()));

        existingOrder = orderRepository.save(existingOrder);
        return convertToOrderDto(existingOrder);
    }


    public List<OrderEntityDto> findAllOrdersByStatus(String orderStatus) {
        return null;
    }
}
