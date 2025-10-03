package com.challenge.api.service;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeDto;
import java.util.*;
import java.util.concurrent.*;
import org.springframework.stereotype.Service;

/**
 * Service layer for employee logic
 * With mock data helper as detailed in EmployeeController.java
 */
@Service
public class EmployeeService {
    // a map seems most practical for storing mock data
    // concurrent hashmap for thread safety
    private final Map<UUID, Employee> employeeData = new ConcurrentHashMap<>();

    public EmployeeService() {
        initMockData();
    }

    // get all employees
    public List<Employee> getAllEmployees() {
        return List.copyOf(employeeData.values());
    }

    // get employees by UUID
    public Employee getEmployeeByUuid(UUID uuid) {
        return employeeData.get(uuid);
    }

    // create a new employee
    public Employee createEmployee(
            String firstName, String lastName, Integer salary, Integer age, String jobTitle, String email) {
        EmployeeDto newEmployee = new EmployeeDto(firstName, lastName, salary, age, jobTitle, email);
        employeeData.put(newEmployee.getUuid(), newEmployee);
        return newEmployee;
    }

    //use immutable Set.of(). for creation, and forEach to populate the concurrent hash map
    private void initMockData() {
        Set.of(
                        new EmployeeDto("Ethan", "Anderson", 65000, 20, "Systems Engineer", "ethan.anderson@email.com"),
                        new EmployeeDto("Bob", "Myers", 70000, 28, "Software Engineer", "bob.myers@email.com"),
                        new EmployeeDto("Alice", "Chains", 82000, 34, "Security Analyst", "alice.chains@email.com"),
                        new EmployeeDto("Ozzy", "Osbourne", 93000, 20, "Network Engineer", "ozzy.osbourne@email.com"))
                .forEach(e -> employeeData.put(e.getUuid(), e));
    }
}
