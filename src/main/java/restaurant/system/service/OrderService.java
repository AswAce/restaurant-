package restaurant.system.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restaurant.system.db.enteties.ItemEntity;
import restaurant.system.db.enteties.TableBillEntity;
import restaurant.system.db.enums.ItemTypes;
import restaurant.system.db.repo.BillRepository;
import restaurant.system.web.controllers.dto.BillDto;
import restaurant.system.web.controllers.dto.ItemDto;
import restaurant.system.web.controllers.dto.OrderDto;
import restaurant.system.web.controllers.dto.OrderItemDto;

@Service public class OrderService {

    @Autowired OrderRedisService redisService;
    @Autowired BillRepository billRepository;

    public Map<String, OrderDto> getAllActiveOrdersForTheKitchen() {
        Map<String, OrderDto> kitchenOrders = new LinkedHashMap<>();
        Map<String, OrderDto> allOrders = redisService.getAllOrders();
        for (OrderDto orderDto : allOrders.values()) {
            OrderDto kitchenOrderDto = departmentOrder(orderDto, ItemTypes.FOOD);
            if (kitchenOrderDto != null) {
                kitchenOrders.putIfAbsent(String.valueOf(orderDto.getOrderNumber()), kitchenOrderDto);
            }
        }
        return kitchenOrders;
    }

    public Map<String, OrderDto> getAllActiveOrdersForTheBar() {
        Map<String, OrderDto> kitchenOrders = new LinkedHashMap<>();
        Map<String, OrderDto> allOrders = redisService.getAllOrders();
        for (OrderDto orderDto : allOrders.values()) {
            OrderDto kitchenOrderDto = departmentOrder(orderDto, ItemTypes.DRINKS);
            if (kitchenOrderDto != null) {
                kitchenOrders.putIfAbsent(String.valueOf(orderDto.getOrderNumber()), kitchenOrderDto);
            }
        }
        return kitchenOrders;
    }

    public String finishKitchenOrder(int orderNumber) {
        OrderDto order = redisService.getOrder(orderNumber);
        if (order == null) {
            return "Order is not found";
        }
        order.setOrderStatusKitchen(Boolean.TRUE);
        redisService.updateOrder(order);
        return "Order is done";
    }

    public String finishBarOrder(int orderNumber) {
        OrderDto order = redisService.getOrder(orderNumber);
        if (order == null) {
            return "Order is not found";
        }
        order.setOrderStatusBar(Boolean.TRUE);
        redisService.updateOrder(order);
        return "Order is done";
    }

    private OrderDto departmentOrder(OrderDto orderDto, ItemTypes department) {
        boolean orderStatus;
        if (department.equals(ItemTypes.FOOD)) {
            orderStatus = orderDto.getOrderStatusKitchen();
        } else {
            orderStatus = orderDto.getOrderStatusBar();
        }
        if (!orderStatus) {
            OrderDto kitchenOrderDto = new OrderDto();
            kitchenOrderDto.setOrderNumber(orderDto.getOrderNumber());
            kitchenOrderDto.setTableNumber(orderDto.getTableNumber());
            List<OrderItemDto> orderItems = orderDto.getOrderItems();
            List<OrderItemDto> kitchenItems = new ArrayList<>();
            for (OrderItemDto orderItem : orderItems) {
                if (orderItem.getTypes().equalsIgnoreCase(String.valueOf(department))) {
                    kitchenItems.add(orderItem);
                }
            }
            kitchenOrderDto.setOrderItems(kitchenItems);
            return kitchenOrderDto;
        }
        return null;
    }

    public TableBillEntity createBill() {
        TableBillEntity tableBillEntity = new TableBillEntity();
        tableBillEntity.setDateTimeOfTheBill(LocalDateTime.now());
        return billRepository.saveAndFlush(tableBillEntity);
    }

    public List<BillDto> getAllActiveBills() {
        List<TableBillEntity> all = billRepository.findAll();
        return mapBillEntity(all);
    }

    private List<BillDto> mapBillEntity(List<TableBillEntity> bills) {
        List<BillDto> billDtos = new ArrayList<>();
        Map<String, OrderDto> allOrders = redisService.getAllOrders();
        for (TableBillEntity bill : bills) {
            List<OrderDto> orderDtos = new ArrayList<>();

////            List<ItemEntity> items = bill.getItems();
//            for (ItemEntity item : items) {
////                ItemDto itemDto = ItemService.mapDTO(item);
//            }

            BillDto billDto = new BillDto();
            billDto.setDateTimeOfTheBill(bill.getDateTimeOfTheBill());
            billDto.setId(Math.toIntExact(bill.getId()));
            billDtos.add(billDto);
        }
        for (BillDto billDto : billDtos) {
            int id = billDto.getId();
            for (OrderDto orderDto : allOrders.values()) {
                if (orderDto.getBillID() == id) {
                    billDto.setTableNumber(orderDto.getTableNumber());
                    billDto.getOrders().add(orderDto);
                }
            }
        }
        return billDtos;
    }
//    private OrderDto mapDTO(ItemEntity itemEntity) {
//        OrderDto orderDto = new OrderDto();
//        orderDto.set(itemEntity.getTypes().name());
//        orderDto.setSpecialType(itemEntity.getSpecialType().name());
//        orderDto.setPrice(itemEntity.getPrice());
//        orderDto.setDescription(itemEntity.getDescription());
//        return orderDto;

}
