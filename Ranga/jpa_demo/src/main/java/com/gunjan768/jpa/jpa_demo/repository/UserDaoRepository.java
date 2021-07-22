package com.gunjan768.jpa.jpa_demo.repository;

import com.gunjan768.jpa.jpa_demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional  // Each method of this class works in a transaction
public class UserDaoRepository {

    // EntityManager only tracks those objects which are persisted through PersistenceContext. Basically when we call entityManager.persist(user)
    // user object is saved to db and now managed by EntityManager and also starts tracking it in the PersistenceContext i.e. now user object
    // is inside the PersistenceContext. Any changes made to user object will be tracked by PersistenceContext. EntityManger will only track
    // those objects which are in the PersistenceContext (i.e. which are saved to db using entityManager.persist())
    @PersistenceContext
    private EntityManager entityManager;

    public long insert(User user) {
        entityManager.persist(user);
        return user.getId();
    }
}
