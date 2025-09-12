package vn.iotstar.dao.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.config.JPAConfig;
import vn.iotstar.dao.UserDao;
import vn.iotstar.entity.UserEntity;

import java.sql.ResultSet;
import java.util.List;

public class UserDaoImpl implements UserDao {
    UserEntity userEntity = new UserEntity();
    @Override
    public void insert(UserEntity userEntity){
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.persist(userEntity);
            trans.commit();
        }catch(Exception e){
            trans.rollback();
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public void update(UserEntity userEntity){
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.merge(userEntity);
            trans.commit();
        }catch(Exception e){
            trans.rollback();
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public void delete(UserEntity userEntity){
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.remove(userEntity);
            trans.commit();
        }catch(Exception e){
            trans.rollback();
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public List<UserEntity> findAll(){
        String jpql = "select u from UserEntity u";
        EntityManager em = JPAConfig.getEntityManager();
        TypedQuery<UserEntity> query = em.createQuery(jpql, UserEntity.class);
        return query.getResultList();
    }
    @Override
    public List<UserEntity> findAll(int page, int pageSize){
        String jpql = "select u from UserEntity u";
        EntityManager em = JPAConfig.getEntityManager();
        TypedQuery<UserEntity> query = em.createQuery(jpql, UserEntity.class);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public List<UserEntity> findByName(String userName){
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "select u from UserEntity u where u.userName like :userName";
        TypedQuery<UserEntity> query = em.createQuery(jpql, UserEntity.class);
        query.setParameter("userName", "%" + userName + "%");
        return query.getResultList();
    }
    @Override
    public UserEntity findById(int Id){
        EntityManager em = JPAConfig.getEntityManager();
        UserEntity userEntity = em.find(UserEntity.class, Id);
        return userEntity;
    }
}
