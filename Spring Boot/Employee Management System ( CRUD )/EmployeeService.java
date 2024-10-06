package com.Employee.demo;

import java.util.List;

public interface EmployeeService {
    String createEmployee(Employee employee);

    List<Employee> readEmployees();

    Employee readEmployee(Long id);

    boolean deleteEmployee(long id);

    String updateEmployee(Long id, Employee employee);
}
