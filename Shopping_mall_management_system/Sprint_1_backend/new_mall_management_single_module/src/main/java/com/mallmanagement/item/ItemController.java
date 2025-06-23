    package com.mallmanagement.item;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import java.util.List;

    @RestController
    @RequestMapping("/api")
    public class ItemController {

        @Autowired
        private ItemService itemService;

        // Endpoint for customers to browse all items from all shops
        @GetMapping("/items")
        public List<Item> getAllItems() {
            return itemService.getAllItems();
        }

        // Endpoint for a shop owner to see their own items
        @GetMapping("/shopowner/{shopOwnerId}/items")
        public List<Item> getItemsByShopOwner(@PathVariable Long shopOwnerId) {
            return itemService.getItemsByShopOwner(shopOwnerId);
        }

        // Endpoint for a shop owner to add a new item
        @PostMapping("/shopowner/{shopOwnerId}/items")
        public Item addItem(@PathVariable Long shopOwnerId, @RequestBody Item item) {
            return itemService.addItem(shopOwnerId, item);
        }

        // Endpoint for a shop owner to delete an item
        @DeleteMapping("/items/{itemId}")
        public void deleteItem(@PathVariable Long itemId) {
            itemService.deleteItem(itemId);
        }
    }
    