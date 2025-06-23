package com.mallmanagement.shopowner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/shopowner")
public class ShopOwnerController {

    @Autowired
    private ShopOwnerService service;

    @GetMapping
    public List<ShopOwner> listAll() {
        return service.listAll();
    }

    @PostMapping
    public ResponseEntity<ShopOwner> add(@RequestBody ShopOwner shopOwner) {
        ShopOwner savedOwner = service.save(shopOwner);
        return new ResponseEntity<>(savedOwner, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopOwner> get(@PathVariable Long id) {
        try {
            ShopOwner shopOwner = service.get(id);
            return new ResponseEntity<>(shopOwner, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ShopOwner shopOwner, @PathVariable Long id) {
        try {
            ShopOwner existingOwner = service.get(id);
            existingOwner.setName(shopOwner.getName());
            existingOwner.setEmail(shopOwner.getEmail());
            existingOwner.setShopName(shopOwner.getShopName());
            service.save(existingOwner);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
