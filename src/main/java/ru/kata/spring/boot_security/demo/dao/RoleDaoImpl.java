package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
