package org.luv2code.cmr.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.luv2code.cmr.dao.EmployeeDAO;
import org.luv2code.cmr.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Jack Tran
 */
@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    // Define filed for entityManager
    private EntityManager entityManager;

    // Set uo constructor injection
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
        // get the current Hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // Create a query
        Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);

        // Execute query and return result list
        return theQuery.getResultList();
    }

    @Override
    public Employee findById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Employee.class, theId);
    }

    @Override
    public void save(Employee employee) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(employee);
    }

    @Override
    public void deleteById(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", theId);
        query.executeUpdate();
    }
}
