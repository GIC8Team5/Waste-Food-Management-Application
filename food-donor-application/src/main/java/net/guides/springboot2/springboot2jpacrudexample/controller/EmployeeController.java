package net.guides.springboot2.springboot2jpacrudexample.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
import net.guides.springboot2.springboot2jpacrudexample.model.LoginUser;
import net.guides.springboot2.springboot2jpacrudexample.model.Product;
import net.guides.springboot2.springboot2jpacrudexample.model.Updatedetails;
import net.guides.springboot2.springboot2jpacrudexample.repository.EmployeeRepository;
import net.guides.springboot2.springboot2jpacrudexample.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		System.out.println("=========>getEmployeeById<==========");
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/getLoginDetails")
	public ResponseEntity<Employee> getLoginDetails(@RequestBody LoginUser loginUser) throws ResourceNotFoundException {
		System.out.println("=========>getEmployeeById<==========");
		Employee employee = employeeRepository.findByEmail(loginUser.getUserId());

		if (employee == null || !employee.getPassword().equals(loginUser.getPassword())) {
			throw new ResourceNotFoundException("Employee not found for this id :: " + loginUser.getUserId());
		}

		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		System.out.println("=========>getEmployeeById<==========");
		return employeeRepository.save(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		System.out.println("=========>getEmployeeById<==========");
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		System.out.println("=========>getEmployeeById<==========");
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	// -----------Product details--------------------------

	@PostMapping("/donor")
	public Product createProductDetails(@Valid @RequestBody Product product) {
		System.out.println("=========>111111createProductDetails<==========> " + product);
		Product productResponse = null;
		try {

			productResponse = productRepository.save(product);
		} catch (Exception ex) {
			System.out.println(ex);
		}

		System.out.println("=========>22222222createProductDetails<==========> " + productResponse);

		return productResponse;
	}

	@GetMapping("/donorDetails")
	public List<Product> getAllDonorDetails() {
		System.out.println("=========>getAllDonorDetails<==========");
		return productRepository.findAll();
	}

	@DeleteMapping("/donor/{id}")
	public Map<String, Boolean> deleteDonorDetails(@PathVariable(value = "id") Long donorId)
			throws ResourceNotFoundException {

		Product product = productRepository.findById(donorId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + donorId));

		productRepository.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		System.out.println("=========>deleteDonorDetails<==========" + response + "; donorId:====> " + donorId);
		return response;
	}

	@PutMapping("/donor/{id}")
	public ResponseEntity<Product> updateDonorStatus(@PathVariable(value = "id") Long donorId,
			@Valid @RequestBody Updatedetails updatedetails) throws ResourceNotFoundException {
		System.out.println("=========>111111111updateDonorStatus<==========" + updatedetails);
		Product product = productRepository.findById(donorId)
				.orElseThrow(() -> new ResourceNotFoundException("Donor not found for this id :: " + donorId));
		System.out.println("=========>222222222222updateDonorStatus<==========" + updatedetails);
		product.setStatus(updatedetails.getStatus());
		final Product updateProduct = productRepository.save(product);
		System.out.println("=========>333333333updateDonorStatus<==========" + updateProduct);
		return ResponseEntity.ok(updateProduct);
	}

	@GetMapping("/donor/{emailId}")
	public ResponseEntity<List<Product>> getDonorDetails(@PathVariable(value = "emailId") String emailId)
			throws ResourceNotFoundException {

		List<Product> donorList = productRepository.findByEmailList(emailId);

		System.out.println("=========>getDonorDetails<==========" + donorList);
		return ResponseEntity.ok().body(donorList);
	}

	@PostMapping("/getDonortailsByEmail")
	public ResponseEntity<Product> getDonoreDtailsByEmail(@PathVariable(value = "email") String email)
			throws ResourceNotFoundException {

		Product donor = productRepository.findByEmail(email);

		if (donor == null || !donor.getEmailId().equals(email)) {
			throw new ResourceNotFoundException("Donor not found for this id :: " + email);
		}
		System.out.println("=========>getDonoreDtailsByEmail<==========> " + donor);
		return ResponseEntity.ok().body(donor);
	}
}
