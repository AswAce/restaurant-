package restaurant.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant.system.db.repo.SupplyRepository;

@Service
public class SupplyService {

    @Autowired
    private final SupplyRepository dailySupplieRepository;

    public SupplyService(SupplyRepository dailySupplieRepository) {
        this.dailySupplieRepository = dailySupplieRepository;
    }


//    public DailySupplieEntity saveDailySupplier( int quantity) {
//
//            DailySupplieEntity dailySupplieEntity = new DailySupplieEntity( quantity,
//                    Date.valueOf(LocalDate.now()));
//         return dailySupplieRepository.save(dailySupplieEntity);
//    }

}
