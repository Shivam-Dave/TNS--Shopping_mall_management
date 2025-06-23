    package com.mallmanagement.item;

    import com.mallmanagement.shopowner.ShopOwner;
    import com.mallmanagement.shopowner.ShopOwnerRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import java.util.List;
    import java.util.NoSuchElementException;

    @Service
    public class ItemService {

        @Autowired
        private ItemRepository itemRepo;

        @Autowired
        private ShopOwnerRepository shopOwnerRepo;

        public List<Item> getAllItems() {
            return itemRepo.findAll();
        }

        public List<Item> getItemsByShopOwner(Long shopOwnerId) {
            return itemRepo.findByShopOwnerId(shopOwnerId);
        }

        public Item addItem(Long shopOwnerId, Item item) {
            ShopOwner shopOwner = shopOwnerRepo.findById(shopOwnerId)
                .orElseThrow(() -> new NoSuchElementException("ShopOwner not found"));
            item.setShopOwner(shopOwner);
            return itemRepo.save(item);
        }

        public void deleteItem(Long itemId) {
            itemRepo.deleteById(itemId);
        }
    }
    