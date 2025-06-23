package com.mallmanagement.shopowner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ShopOwnerService {

    @Autowired
    private ShopOwnerRepository repo;

    public List<ShopOwner> listAll() {
        return repo.findAll();
    }

    public ShopOwner save(ShopOwner shopOwner) {
        if (shopOwner.getRegistrationDate() == null) {
            shopOwner.setRegistrationDate(LocalDate.now());
        }
        return repo.save(shopOwner);
    }

    public ShopOwner get(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("ShopOwner not found with ID: " + id));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("ShopOwner not found with ID: " + id);
        }
        repo.deleteById(id);
    }
}
