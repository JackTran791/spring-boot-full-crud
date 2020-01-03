package org.luv2code.cmr.dao;

import org.luv2code.cmr.entity.Employee;

import java.util.List;

/**
 * @author Jack Tran
 */
public interface EmployeeDAO {

    public List<Employee> findAll();

    public Employee findById(int theId);

    public void save(Employee employee);

    public void deleteById(int theId);
}
