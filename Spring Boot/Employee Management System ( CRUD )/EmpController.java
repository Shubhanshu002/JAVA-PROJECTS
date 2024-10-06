package com.Employee.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmpController {

    @Autowired
    private EmployeeService employeeService; // Use dependency injection

    // Endpoint to get all employees
    @GetMapping // Remove @PathVariable here
    public List<Employee> getAllEmployees() {
        return employeeService.readEmployees(); // Correct method call
    }

    // Endpoint to create a new employee
    @PostMapping
    public String createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    // Endpoint to delete an employee by ID
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        if (employeeService.deleteEmployee(id)) {
            return "Deleted Successfully";
        }
        return "Not Found";
    }

    // Endpoint to update an employee by ID
    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }
}
