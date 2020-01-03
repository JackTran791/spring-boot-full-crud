package org.luv2code.cmr.repository;

import org.luv2code.cmr.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jack Tran
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
