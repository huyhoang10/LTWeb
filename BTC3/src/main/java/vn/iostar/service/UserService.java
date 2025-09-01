package vn.iostar.service;

import java.time.LocalDateTime;

import vn.iostar.Models.User;

public interface UserService {
	User login(String name,String password);
	User get(String name);
	void insert(User user);
	boolean register(String email, String password, String username, String fullname, String phone);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	String setToken(String email);
	User findByToken(String token);
	void setPassword(String username,String password);
}
