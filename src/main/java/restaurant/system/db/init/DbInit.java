package restaurant.system.db.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import restaurant.system.db.enteties.ItemEntity;
import restaurant.system.db.repo.UserRepository;
import restaurant.system.service.SupplyService;
import restaurant.system.service.ItemService;

@Component
public class DbInit implements CommandLineRunner {
   @Autowired
    private final UserRepository userRepository;
   @Autowired
    private final ItemService itemService;

   @Autowired
    private final SupplyService dailySupplieService;

    public DbInit(UserRepository userRepository, ItemService itemService, SupplyService dailySupplieService) {
        this.userRepository = userRepository;
        this.itemService = itemService;
        this.dailySupplieService = dailySupplieService;
    }

    @Override
    public void run(String... args) throws Exception {
    }


//    public void addUsers() {
//        createUser(1234, RoleTypes.ADMIN);
//
//        createUser(4444, RoleTypes.BARMAN);
//
//        createUser(5555, RoleTypes.CHEF);
//        }
//    private void createUser(int password, RoleTypes roleTypes) {
//        if (userRepository.findByRole(roleTypes).orElse(null) == null){
//            UserEntity user = new UserEntity(password,roleTypes);
//            userRepository.save(user);
//        }
//    }
//

}