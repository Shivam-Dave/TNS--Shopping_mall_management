package com.mallmanagement.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Service class for handling business logic related to MallAdmins.
 */
@Service
public class MallAdminService {

    @Autowired
    private MallAdminRepository repo;

    /**
     * Retrieves all admins from the database.
     * @return a list of all admins.
     */
    public List<MallAdmin> listAll() {
        return repo.findAll();
    }

    /**
     * Saves a new admin or updates an existing one.
     * Sets the last login time if it's not already set.
     * @param mallAdmin the admin object to be saved.
     * @return the saved admin object.
     */
    public MallAdmin save(MallAdmin mallAdmin) {
        if (mallAdmin.getLastLoginTime() == null) {
            mallAdmin.setLastLoginTime(LocalDateTime.now());
        }
        return repo.save(mallAdmin);
    }

    /**
     * Retrieves a single admin by their ID.
     * @param id the ID of the admin to retrieve.
     * @return the admin object.
     * @throws NoSuchElementException if no admin is found with the given ID.
     */
    public MallAdmin get(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Admin not found with ID: " + id));
    }

    /**
     * Deletes an admin by their ID.
     * @param id the ID of the admin to delete.
     * @throws NoSuchElementException if no admin is found with the given ID.
     */
    public void delete(Integer id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException("Admin not found with ID: " + id);
        }
        repo.deleteById(id);
    }
}
