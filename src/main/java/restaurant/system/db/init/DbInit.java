package restaurant.system.db.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import restaurant.system.db.enteties.UserEntity;
import restaurant.system.db.enums.RoleTypes;
import restaurant.system.db.repo.UserRepository;

@Component
public class DbInit implements CommandLineRunner {
    private final UserRepository userRepository;

    public DbInit( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        addUsers();
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