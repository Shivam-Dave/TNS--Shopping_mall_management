package com.mallmanagement.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for managing mall admins.
 * Handles all CRUD operations for the MallAdmin resource.
 */
@RestController
@RequestMapping("/api/admin") // The correct URL path
public class MallAdminController {

    @Autowired
    private MallAdminService service;

    /**
     * Handles GET requests to retrieve all admins.
     * @return A list of all MallAdmin objects.
     */
    @GetMapping
    public List<MallAdmin> listAll() {
        return service.listAll();
    }

    /**
     * Handles GET requests to retrieve a single admin by their ID.
     * @param id The ID of the admin.
     * @return A ResponseEntity containing the admin or a NOT_FOUND status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MallAdmin> get(@PathVariable Integer id) {
        try {
            MallAdmin mallAdmin = service.get(id);
            return new ResponseEntity<>(mallAdmin, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles POST requests to create a new admin.
     * @param mallAdmin The admin data from the request body.
     * @return A ResponseEntity with the created admin and a CREATED status.
     */
    @PostMapping
    public ResponseEntity<MallAdmin> add(@RequestBody MallAdmin mallAdmin) {
        MallAdmin savedAdmin = service.save(mallAdmin);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    /**
     * Handles PUT requests to update an existing admin.
     * @param mallAdmin The updated admin data from the request body.
     * @param id The ID of the admin to update.
     * @return A ResponseEntity with an OK status or NOT_FOUND if the admin doesn't exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody MallAdmin mallAdmin, @PathVariable Integer id) {
        try {
            MallAdmin existingAdmin = service.get(id);

            // Update fields from the request body
            existingAdmin.setUsername(mallAdmin.getUsername());
            existingAdmin.setEmail(mallAdmin.getEmail());

            // Optionally handle password updates securely.
            // In a real application, you would hash the password here.
            if (mallAdmin.getPassword() != null && !mallAdmin.getPassword().isEmpty()) {
                existingAdmin.setPassword(mallAdmin.getPassword());
            }

            service.save(existingAdmin);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles DELETE requests to remove an admin.
     * @param id The ID of the admin to delete.
     * @return A ResponseEntity with an OK status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            // First, check if the admin exists to provide a proper 404 response.
            service.get(id);
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
