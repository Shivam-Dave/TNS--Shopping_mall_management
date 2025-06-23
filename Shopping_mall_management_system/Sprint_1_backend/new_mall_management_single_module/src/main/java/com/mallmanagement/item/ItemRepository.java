    package com.mallmanagement.item;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    import java.util.List;

    @Repository
    public interface ItemRepository extends JpaRepository<Item, Long> {
        // Custom method to find all items belonging to a specific shop owner
        List<Item> findByShopOwnerId(Long shopOwnerId);
    }
    