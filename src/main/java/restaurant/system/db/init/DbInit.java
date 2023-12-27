package restaurant.system.db.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import restaurant.system.db.enteties.UserEntity;
import restaurant.system.db.enums.BeerEnum;
import restaurant.system.db.enums.RoleTypes;
import restaurant.system.db.repo.UserRepository;
import restaurant.system.service.ItemService;

import java.sql.SQLIntegrityConstraintViolationException;

@Component
public class DbInit implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ItemService itemService;

    public DbInit(UserRepository userRepository, ItemService itemService) {
        this.userRepository = userRepository;
        this.itemService = itemService;
    }

    @Override
    public void run(String... args) throws Exception {
        addUsers();
        itemService.createBeers(BeerEnum.ARIANA,2.0);
        itemService.createBeers(BeerEnum.STELLA,3.0);
        itemService.createBeers(BeerEnum.ZAGORKA,2.0);
        itemService.createBeers(BeerEnum.HEINEKEN,3.0);

    }



    public void addUsers() {
        createUser(1234, RoleTypes.ADMIN);

        createUser(4444, RoleTypes.BARMAN);

        createUser(5555, RoleTypes.CHEF);
        }
    private void createUser(int password, RoleTypes roleTypes) {
        if (userRepository.findByRole(roleTypes).orElse(null) == null){
            UserEntity user = new UserEntity(password,roleTypes);
            userRepository.save(user);
        }
    }


    }