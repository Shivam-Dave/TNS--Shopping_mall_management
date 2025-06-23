package com.mallmanagement.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Employee entity.
 * This interface will be automatically implemented by Spring into a Bean.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
