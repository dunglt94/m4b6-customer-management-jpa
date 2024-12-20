package com.example.customermanagementjpa.repository;

import com.example.customermanagementjpa.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class CustomerRepository implements ICustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(int id) {
        TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c where c.id=:id",
                Customer.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Customer customer) {
        if (customer.getId() != 0) {
            entityManager.merge(customer);
        } else {
            entityManager.persist(customer);
        }
    }

    @Override
    public void remove(int id) {
        Customer customer = findById(id);
        if (customer != null) {
            entityManager.remove(customer);
        }
    }

    public boolean saveWithStoredProcedure(Customer customer) {
        String sql = "CALL insert_customer(:name, :email, :address)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("name", customer.getName());
        query.setParameter("email", customer.getEmail());
        query.setParameter("address", customer.getAddress());
        return query.executeUpdate() != 0;
    }
}
