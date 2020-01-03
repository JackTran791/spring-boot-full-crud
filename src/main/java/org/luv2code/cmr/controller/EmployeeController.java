package org.luv2code.cmr.controller;


import lombok.AllArgsConstructor;
import org.luv2code.cmr.dao.EmployeeDAO;
import org.luv2code.cmr.entity.Employee;
import org.luv2code.cmr.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Jack Tran
 */
@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/hello")
    public String testAPI() {
        return "Hello World!!!";
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // Add mapping for GET /employees/{employeeId}
    @GetMapping("employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);

        if(employee == null) throw new RuntimeException("Employee not found - " + employeeId);

        return employee;
    }

    // Add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        // Also just in case they pass an ID in JSON ... set ID to 0
        // This is to force a save of new item ... instead of update

        employee.setId(0);
        employeeService.save(employee);
        return employee;
    }

    // Add mapping for PUT /employees- update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
        return employee;
    }

    // Add mapping for DELETE /employees- delete existing employee
    @DeleteMapping("/employees/{employeeId}")
    public String updateEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);
        if(tempEmployee == null) throw new RuntimeException("Employee not found - " + employeeId);
        employeeService.deleteById(employeeId);
        return "Delete employee ID - " + employeeId;
    }
}
