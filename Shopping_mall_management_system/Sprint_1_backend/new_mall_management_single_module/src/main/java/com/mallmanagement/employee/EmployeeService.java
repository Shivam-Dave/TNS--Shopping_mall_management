package com.mallmanagement.employee;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for handling business logic related to Employees.
 */
@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;

	/**
	 * Retrieves all employees from the database.
	 * @return a list of all employees.
	 */
	public List<Employee> listAll() {
		return repo.findAll();
	}

	/**
	 * Saves a new employee or updates an existing one.
	 * @param employee the employee object to be saved.
	 */
	public void save(Employee employee) {
		repo.save(employee);
	}

	/**
	 * Retrieves a single employee by their ID.
	 * @param id the ID of the employee to retrieve.
	 * @return the employee object.
	 */
	public Employee get(Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found for id: " + id));
	}

	/**
	 * Deletes an employee by their ID.
	 * @param id the ID of the employee to delete.
	 */
	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
