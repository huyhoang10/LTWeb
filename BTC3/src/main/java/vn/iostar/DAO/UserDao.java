package vn.iostar.DAO;

import java.time.LocalDateTime;

import vn.iostar.Models.User;

public interface UserDao {
	User get(String username);
	void insert(User user);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	boolean checkExistToken(String token);
	User findByToken(String token);
	void setPassword(String username,String password);
	void updateResetToken(String email, String token, LocalDateTime expiryTime);
}
