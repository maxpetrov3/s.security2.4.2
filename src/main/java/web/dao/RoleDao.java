package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveRole(Role role) {
        entityManager.persist(role);
        entityManager.flush();
    }

    @Transactional
    public List<Role> getAllRoles() {
        Query query = entityManager.createQuery("select r from Role r", Role.class);

        return query.getResultList();
    }
}
