package restaurant.system.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant.system.controller.dto.*;
import restaurant.system.db.enteties.SupplyEntity;
import restaurant.system.db.enteties.ItemEntity;
import restaurant.system.db.enums.ItemTypesSpecifications;
import restaurant.system.db.repo.ItemRepository;
import restaurant.system.db.repo.SupplyRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private final ItemRepository itemRepository;

    @Autowired
    private final SupplyRepository supplyRepository;

    @Autowired
    private final SupplyService dailySupplieService;

    public ItemService(ItemRepository itemRepository, SupplyRepository supplyRepository, SupplyService dailySupplieService) {
        this.itemRepository = itemRepository;
        this.supplyRepository = supplyRepository;
        this.dailySupplieService = dailySupplieService;
    }

    public ItemEntityDto createItem(CreateItemDto createItemDto) {
        ItemEntity sashimi = new ItemEntity(createItemDto.getItemTypes(),
                createItemDto.getItemTypesSpecifications(), createItemDto.getName(), createItemDto.getPrice(), createItemDto.getDescription(), createItemDto.getImageURL(), createItemDto.getPieces(), createItemDto.getGrams());
        return getItemEntityDto(itemRepository.save(sashimi));
    }

    public ItemEntityDto updateItem(ItemEntityDto itemEntityDto) {
        ItemEntity itemEntity = itemRepository.findItemEntityByName(itemEntityDto.getName())
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));

        // Update fields
        itemEntity.setTypes(itemEntityDto.getItemTypes());
        itemEntity.setSpecialType(itemEntityDto.getItemTypesSpecifications());
        itemEntity.setName(itemEntityDto.getName());
        itemEntity.setDescription(itemEntityDto.getDescription());
        itemEntity.setImageURL(itemEntityDto.getImageURL());
        itemEntity.setPrice(itemEntityDto.getPrice());
        itemEntity.setPieces(itemEntityDto.getPieces());
        itemEntity.setGrams(itemEntityDto.getGrams());
        // Handle updates for supplyEntities if needed

        ItemEntity updatedItem = itemRepository.save(itemEntity);

        // Convert updatedItem to DTO and return
        return getItemEntityDto(updatedItem);  // Assume you have a method to convert an entity to its DTO
    }

    public ItemEntityDto findByItemNameDto(String itemName) {
        ItemEntity itemEntity = itemRepository.findItemEntityByName(itemName).orElse(null);
        if (itemEntity == null) {
            return null;
        } else {
            return getItemEntityDto(itemEntity);
        }

    }

    public ItemEntity findByItemName(String itemName) {
        return itemRepository.findItemEntityByName(itemName).orElse(null);

    }

    public List<ItemEntityDto> findAllByItemTypesSpecifications(ItemTypesSpecifications specifications) {
        List<ItemEntity> allBySpecialType = itemRepository.findAllBySpecialType(specifications);
        return allBySpecialType.stream().map(this::getItemEntityDto).collect(Collectors.toList());
    }

    public List<ItemClientEntityDto> forClientFindAllByItemTypesSpecifications(ItemTypesSpecifications specifications) {
        List<ItemEntity> allBySpecialType = itemRepository.findAllBySpecialTypeAndTodaySupply(specifications);
        System.out.println("Size is" + allBySpecialType.size());
        return allBySpecialType.stream().map(this::getClientItemEntityDto).collect(Collectors.toList());
    }

    public List<ItemEntityDto> addALlDailySupplier(List<AddDailySupplierDto> addDailySupplierDtos) {
        List<ItemEntityDto> all = new ArrayList<>();
        for (AddDailySupplierDto addDailySupplierDto : addDailySupplierDtos) {
            ItemEntityDto itemEntityDto = addDailySupplier(addDailySupplierDto);
            all.add(itemEntityDto);
        }
        return all;
    }

    public ItemEntityDto addDailySupplier(AddDailySupplierDto addDailySupplierDto) {
        SupplyEntity dailySupplieEntity = new SupplyEntity();
        dailySupplieEntity.setDate(Date.valueOf(LocalDate.now()));
        dailySupplieEntity.setQuantity(addDailySupplierDto.getQuantity());
        dailySupplieEntity.setLeftover(addDailySupplierDto.getQuantity());
        dailySupplieEntity.setSold(0);
        ItemEntity salmon = this.findByItemName(addDailySupplierDto.getItemName());
        if (salmon == null) {
            return null;
        } else {
            dailySupplieEntity.setItemEntity(salmon);
            SupplyEntity save = supplyRepository.save(dailySupplieEntity);
            salmon.addDailySupplier(save);
            return getItemEntityDto(salmon);
        }

    }

    private ItemEntityDto getItemEntityDto(ItemEntity itemEntity) {
        ItemEntityDto dto = new ItemEntityDto();
        dto.setItemTypes(itemEntity.getTypes());
        dto.setItemTypesSpecifications(itemEntity.getSpecialType());
        dto.setId(Math.toIntExact(itemEntity.getId()));
        dto.setName(itemEntity.getName());
        dto.setDescription(itemEntity.getDescription());
        dto.setImageURL(itemEntity.getImageURL());
        dto.setPrice(itemEntity.getPrice());
        dto.setPieces(itemEntity.getPieces());
        dto.setGrams(itemEntity.getGrams());

        List<SupplyEntityDto> supplyDtos = itemEntity.getSupplyEntities().stream()
                .map(this::convertToDto)
                .toList();

        dto.setSupplyEntities(supplyDtos);

        return dto;
    }

    private ItemClientEntityDto getClientItemEntityDto(ItemEntity itemEntity) {
        ItemClientEntityDto dto = new ItemClientEntityDto();
        dto.setItemTypes(itemEntity.getTypes());
        dto.setItemTypesSpecifications(itemEntity.getSpecialType());
        dto.setName(itemEntity.getName());
        dto.setDescription(itemEntity.getDescription());
        dto.setImageURL(itemEntity.getImageURL());
        dto.setPrice(itemEntity.getPrice());
        dto.setPieces(itemEntity.getPieces());
        dto.setGrams(itemEntity.getGrams());
        SupplyEntityDto supplyEntityDto = convertToDto(itemEntity.getSupplyEntities().get(0));
        dto.setDailySupplyEntities(supplyEntityDto);

        return dto;
    }

    private SupplyEntityDto convertToDto(SupplyEntity supplyEntity) {
        SupplyEntityDto dto = new SupplyEntityDto();
        dto.setQuantity(supplyEntity.getQuantity());
        dto.setDate(supplyEntity.getDate());
        dto.setSold(supplyEntity.getSold());
        dto.setLeftover(supplyEntity.getLeftover());

        return dto;
    }

    public List<ItemEntityDto> findAllItems() {
        return itemRepository.findAll().stream().
                map(this::getItemEntityDto).toList();
    }

    public List<ItemQuantityDto> findAllItemsDailyQauntity() {
        return itemRepository.findAll().stream().
                map(this::convertToItemQuantity).toList();

    }

    private ItemQuantityDto convertToItemQuantity(ItemEntity itemEntity) {
        ItemQuantityDto itemQuantityDto = new ItemQuantityDto(Math.toIntExact(itemEntity.getId()),
                itemEntity.getName(),
                itemEntity.getSpecialType(),
                itemEntity.getTypes());
        List<SupplyEntity> supplyEntities = itemEntity.getSupplyEntities();
        int currentQuantity = 0;
        for (SupplyEntity supplyEntity : supplyEntities) {
            Date date = supplyEntity.getDate();
            System.out.println("Date 185:" + date);
            if (date.after(getYestarday())) {
                currentQuantity += supplyEntity.getQuantity();
            }
        }
        itemQuantityDto.setCurrentQuantity(currentQuantity);
        return itemQuantityDto;
    }

    private Date getYestarday() {
        Calendar calendar = Calendar.getInstance();  // Get the current date and time.

        // Subtract one day to set the calendar to yesterday
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println("getYestarday" + new Date(calendar.getTimeInMillis()));
        // Create a java.sql.Date from the time in milliseconds of the calendar instance
        return new Date(calendar.getTimeInMillis());
    }
}
