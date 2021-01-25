package ru.nadir.springdemo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.nadir.springdemo.entity.Customer;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDAO {

    //need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
        //get the current hibernate session
        Session session = sessionFactory.getCurrentSession();
        //create a query ... sort by last name
        Query<Customer> theQuery = session.createQuery("from Customer order by lastName", Customer.class);
        //execute query and get result list
        List<Customer> customers = theQuery.getResultList();
        //return the results
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        //save/update the customer
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int theId) {
        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //now retrieve/read from database using the primary key
        Customer theCustomer = currentSession.get(Customer.class, theId);

        //return current customer
        return theCustomer;
    }

    @Override
    public void deleteCustomer(int theId) {
        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //delete object by primary key
        Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
        theQuery.setParameter("customerId", theId);
        theQuery.executeUpdate();
    }
}