package vn.iotstar.dao;

import vn.iotstar.entity.UserEntity;

import java.beans.JavaBean;
import java.util.List;

public interface UserDao{
    void insert(UserEntity userEntity);
    void update(UserEntity userEntity);
    void delete(UserEntity userEntity);
    List<UserEntity> findAll();
    List<UserEntity> findAll(int page, int pageSize);
    List<UserEntity> findByName(String userName);
    UserEntity findById(int Id);
}
