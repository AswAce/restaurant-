package restaurant.system.db.init;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import restaurant.system.db.enteties.UserEntity;
import restaurant.system.db.enums.*;
import restaurant.system.db.repo.UserRepository;
import restaurant.system.service.ItemService;
import restaurant.system.service.OrderRedisService;
import restaurant.system.web.controllers.dto.OrderDto;
import restaurant.system.web.controllers.dto.OrderItemDto;

@Component public class DbInit implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ItemService itemService;

    private final OrderRedisService redisService;
    private final PasswordEncoder encoder;

    public DbInit(UserRepository userRepository, ItemService itemService, OrderRedisService redisService,
            PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.itemService = itemService;
        this.redisService = redisService;
        this.encoder = encoder;
    }

    @Override public void run(String... args) throws Exception {
        addUsers();
        itemService.createBeers(BeerEnum.ARIANA, 2.0);
        itemService.createBeers(BeerEnum.STELLA, 3.0);
        itemService.createBeers(BeerEnum.ZAGORKA, 2.0);
        itemService.createBeers(BeerEnum.HEINEKEN, 3.0);
        itemService.createVodka(VodkaEnum.RUSKI_STANDART, 5.0);
        itemService.createVodka(VodkaEnum.FINLANDIA, 5.0);
        itemService.createWhiskey(WhiskeyEnum.BUSH, 5.0);
        itemService.createWhiskey(WhiskeyEnum.JACK, 5.0);
        itemService.createWhiskey(WhiskeyEnum.PADDY, 5.0);
        itemService.createWhiskey(WhiskeyEnum.RED_LABEL, 5.0);

        itemService.createBurger(BurgerEnum.CHEESEBURGER, 15.0, "Melted cheese");
        itemService.createAppetizers(AppetizersEnum.FRENCH_FRIES, 10.0, "Melted cheese");
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderNumber(1);
        orderDto.setTableNumber(1);
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setItemOrderQuantity(1);
        orderItemDto.setPrice(10);
        orderItemDto.setTypes(ItemTypes.FOOD.name());
        orderItemDto.setSpecialType(ItemTypesSpecifications.BURGERS.name());
        orderItemDto.setName(BurgerEnum.CHEESEBURGER.name());
        List<OrderItemDto> orderDtoList = new ArrayList<>();
        orderDtoList.add(orderItemDto);
        orderDto.setOrderItems(orderDtoList);
        redisService.saveOrder(orderDto,"1");
    }

    public void addUsers() {
        createUser("1234", UserEntity.Role.ADMIN);

        createUser("4444", UserEntity.Role.BARMAN);

        createUser("5555", UserEntity.Role.CHEF);
    }

    private void createUser(String password, UserEntity.Role roleTypes) {
        if (userRepository.findByRole(roleTypes).orElse(null) == null) {
            UserEntity user = new UserEntity(roleTypes.name(), encoder.encode(password), roleTypes);
            userRepository.save(user);
        }
    }

}
