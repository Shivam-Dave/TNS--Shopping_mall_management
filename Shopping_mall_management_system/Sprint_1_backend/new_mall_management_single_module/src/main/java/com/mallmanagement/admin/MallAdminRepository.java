package com.mallmanagement.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MallAdmin entity.
 */
@Repository
public interface MallAdminRepository extends JpaRepository<MallAdmin, Integer> {
}
