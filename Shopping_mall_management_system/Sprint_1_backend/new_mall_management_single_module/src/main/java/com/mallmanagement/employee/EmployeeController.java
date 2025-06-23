package com.mallmanagement.employee;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing employees.
 * Handles all CRUD operations for the Employee resource.
 */
@RestController
@RequestMapping("/api/employee") // Standardized API endpoint path
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * Handles GET requests to retrieve all employees.
	 * @return A list of all employees.
	 */
	@GetMapping
	public List<Employee> listAll() {
		return employeeService.listAll();
	}

	/**
	 * Handles GET requests to retrieve an employee by their ID.
	 * @param id The ID of the employee.
	 * @return A ResponseEntity containing the employee or a NOT_FOUND status.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Employee> get(@PathVariable Integer id) {
		try {
			Employee employee = employeeService.get(id);
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Handles POST requests to create a new employee.
	 * @param employee The employee data from the request body.
	 * @return A ResponseEntity with a CREATED status.
	 */
	@PostMapping
	public ResponseEntity<?> add(@RequestBody Employee employee) {
		employeeService.save(employee);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Handles PUT requests to update an existing employee.
	 * @param employee The updated employee data from the request body.
	 * @param id The ID of the employee to update.
	 * @return A ResponseEntity with an OK status or NOT_FOUND if the employee doesn't exist.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Employee employee, @PathVariable Integer id) {
		try {
			// Ensure we are updating the correct employee
			Employee existingEmployee = employeeService.get(id);
			existingEmployee.setName(employee.getName());
			existingEmployee.setPosition(employee.getPosition());
			existingEmployee.setSalary(employee.getSalary());
			employeeService.save(existingEmployee);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Handles DELETE requests to remove an employee.
	 * @param id The ID of the employee to delete.
	 * @return A ResponseEntity with an OK status.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			employeeService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
			// Catches errors if the ID does not exist
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}
