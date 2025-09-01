package vn.iostar.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.UUID;

import vn.iostar.DAO.UserDao;
import vn.iostar.DAO.impl.UserDaoImpl;
import vn.iostar.Models.User;
import vn.iostar.service.UserService;
import vn.iostar.connection.DBconnect;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    @Override
    public User login(String username, String password) {
        User user = this.get(username);
        if (user != null && password.equals(user.getPassWord()))
            return user;
        return null;
    }

    @Override
    public User get(String username) {
        String sql = "SELECT * FROM [User] WHERE username = ? ";
        try {
            conn = new DBconnect().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUserName(rs.getString("username"));
                user.setFullName(rs.getString("fullname"));
                user.setPassWord(rs.getString("password"));
                user.setAvatar(rs.getString("avatar"));
                user.setRoleid(Integer.parseInt(rs.getString("roleid")));
                user.setPhone(rs.getString("phone"));
                user.setCreatedDate(rs.getDate("createdDate"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean register(String username, String password, String email, String fullname, String phone) {
        if (userDao.checkExistUsername(username)) {
            return false;
        }
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        userDao.insert(new User(email, username, fullname, password, null, 1, phone, date));
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }
    
    @Override
    public String setToken(String email) {
    	// 2. Sinh token ngẫu nhiên
        String token = UUID.randomUUID().toString();

        // 3. Đặt thời gian hết hạn token (VD: 30 phút sau hiện tại)
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(30);

        // 4. Lưu token + expiry vào DB
        userDao.updateResetToken(email, token, expiryTime);

    	return token;
    }
    
    @Override
    public User findByToken(String token) {
    	return userDao.findByToken(token);
    }
    
    @Override
    public void setPassword(String username,String password) {
    	userDao.setPassword(username, password);
    }
}
