package com.Employee.demo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // Marks this class as a service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        return "Saved Successfully";
    }

    @Override
    public Employee readEmployee(Long id) {
        // Use Optional to handle the case where the employee might not be found
        Optional<EmployeeEntity> optionalEmp = employeeRepository.findById(id);
        if (optionalEmp.isPresent()) {
            Employee employee = new Employee();
            BeanUtils.copyProperties(optionalEmp.get(), employee);
            return employee;
        }
        return null; // or throw an exception, depending on your design choice
    }

    @Override
    public List<Employee> readEmployees() {
        List<EmployeeEntity> employeesList = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeesList) {
            Employee emp = new Employee();
            BeanUtils.copyProperties(employeeEntity, emp); // Copy all properties to Employee
            employees.add(emp);
        }
        return employees;
    }

    @Override
    public boolean deleteEmployee(long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id); // Use the repository to delete the employee
            return true;
        }
        return false;
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        Optional<EmployeeEntity> optionalExistingEmployee = employeeRepository.findById(id);
        if (optionalExistingEmployee.isPresent()) {
            EmployeeEntity existingEmployee = optionalExistingEmployee.get();
            // Update only the fields that need to be changed
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setName(employee.getName());
            existingEmployee.setPhone(employee.getPhone());
            employeeRepository.save(existingEmployee);
            return "Updated Successfully";
        }
        return "Employee not found"; // Return a message if employee does not exist
    }
}
