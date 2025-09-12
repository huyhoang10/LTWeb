package vn.iotstar.dao.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.config.JPAConfig;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.entity.CategoryEntity;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public void insert(CategoryEntity cateEntity){
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(cateEntity);
            trans.commit();
        }
        catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void update(CategoryEntity cateEntity){
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(cateEntity);
            trans.commit();
        }
        catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void delete(int cateId){
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        CategoryEntity cateEntity = em.find(CategoryEntity.class,cateId);
        try {
            trans.begin();
            em.remove(cateEntity);
            trans.commit();
        }
        catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<CategoryEntity> findAll(){
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "select c from CategoryEntity c";
        TypedQuery<CategoryEntity> query = em.createQuery(jpql, CategoryEntity.class);
        return query.getResultList();
    }

    @Override
    public List<CategoryEntity> findAll(int page, int pageSize){
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "select c from CategoryEntity c";
        TypedQuery<CategoryEntity> query = em.createQuery(jpql, CategoryEntity.class);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public CategoryEntity findById(int id){
        EntityManager em = JPAConfig.getEntityManager();
        CategoryEntity categoryEntity = em.find(CategoryEntity.class, id);
        return categoryEntity;
    }

    @Override
    public List<CategoryEntity> findByName(String cateName){
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "select c from CategoryEntity c where c.cateName like :cateName";
        TypedQuery<CategoryEntity> query = em.createQuery(jpql, CategoryEntity.class);
        query.setParameter("cateName", cateName);
        return query.getResultList();
    }
}
