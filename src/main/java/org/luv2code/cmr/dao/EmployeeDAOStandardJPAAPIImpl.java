package org.luv2code.cmr.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.luv2code.cmr.dao.EmployeeDAO;
import org.luv2code.cmr.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Jack Tran
 */
@Repository
public class EmployeeDAOStandardJPAAPIImpl implements EmployeeDAO {

    // Define filed for entityManager
    private EntityManager entityManager;

    // Set uo constructor injection
    @Autowired
    public EmployeeDAOStandardJPAAPIImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
//        Native Hibernate API
//        // get the current Hibernate session
//        Session currentSession = entityManager.unwrap(Session.class);
//
//        // Create a query
//        Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
//
//        // Execute query and return result list
//        return theQuery.getResultList();

        // Standard JPA API
        Query query = (Query) entityManager.createQuery("from Employee", Employee.class);

        return query.getResultList();
    }

    @Override
    public Employee findById(int theId) {
//        Native Hibernate API
//        Session currentSession = entityManager.unwrap(Session.class);
//        return currentSession.get(Employee.class, theId);

        // Standard JPA API
        return entityManager.find(Employee.class, theId);
    }

    @Override
    public void save(Employee employee) {
//        Native Hibernate API
//        Session currentSession = entityManager.unwrap(Session.class);
//        currentSession.saveOrUpdate(employee);

        // Standard JPA API
        // Save or update the employee
        Employee dbEmployee = entityManager.merge(employee);

        // Update with ID from db ... so we can get generated ID fro save/insert
        employee.setId(dbEmployee.getId());
    }

    @Override
    public void deleteById(int theId) {
//        Native Hibernate API
//        Session currentSession = entityManager.unwrap(Session.class);
//        Query query = currentSession.createQuery("delete from Employee where id=:employeeId");
//        query.setParameter("employeeId", theId);
//        query.executeUpdate();

        // Standard JPA API
        Query query = (Query) entityManager.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", theId);
        query.executeUpdate();
    }
}
