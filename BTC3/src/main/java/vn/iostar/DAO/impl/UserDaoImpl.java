package vn.iostar.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import vn.iostar.DAO.UserDao;
import vn.iostar.Models.User;
import vn.iostar.connection.DBconnect;

public class UserDaoImpl implements UserDao {

    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

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
    public void insert(User user) {
        String sql = "INSERT INTO [User](email, username, fullname, password, avatar, roleid, phone, createddate) "
                   + "VALUES (?,?,?,?,?,?,?,?)";
        try {
            conn = new DBconnect().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getPassWord());
            ps.setString(5, user.getAvatar());
            ps.setInt(6, user.getRoleid());
            ps.setString(7, user.getPhone());
            ps.setDate(8, user.getCreatedDate());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        boolean duplicate = false;
        String query = "SELECT * FROM [User] WHERE email = ?";
        try {
            conn = new DBconnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                duplicate = true;
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return duplicate;
    }

    @Override
    public boolean checkExistUsername(String username) {
        boolean duplicate = false;
        String query = "SELECT * FROM [User] WHERE username = ?";
        try {
            conn = new DBconnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                duplicate = true;
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return duplicate;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        boolean duplicate = false;
        String query = "SELECT * FROM [User] WHERE phone = ?";
        try {
            conn = new DBconnect().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if (rs.next()) {
                duplicate = true;
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return duplicate;
    }
    
    @Override
    public boolean checkExistToken(String token) {
    	boolean isExist = false;
    	String query = "Select * from [User] where token = ?";
    	try {
    		 conn = new DBconnect().getConnection();
             ps = conn.prepareStatement(query);
             ps.setString(1, token);
             rs = ps.executeQuery();
             if (rs.next()) {
                 isExist = true;
             }
             ps.close();
             conn.close();
    	}
    	catch(Exception e) {}
    	return isExist;
    }
    
    @Override
    public User findByToken(String token) {
    	String sql = "SELECT * FROM [User] WHERE token = ?";
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;

        try {
            conn = new DBconnect().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, token);
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
                user.setToken(rs.getString("token"));
                user.setExpiry_token(rs.getTimestamp("expiry_token"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return null;
    }
    
    @Override
    public void setPassword(String username,String password) {
    	String sql = "UPDATE [User] SET password = ?, token = NULL, expiry_token = NULL WHERE username = ?";
    	try {
    		Connection conn = new DBconnect().getConnection();
    		PreparedStatement ps = conn.prepareStatement(sql);
    		 ps.setString(1,password);
             ps.setString(2, username);
    		ResultSet rs = ps.executeQuery();
    	}
    	catch(Exception e) {}
    }
    @Override
    public void updateResetToken(String email, String token, LocalDateTime expiryTime) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new DBconnect().getConnection();
            String sql = "UPDATE [User] SET token = ?, expiry_token = ? WHERE email = ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, token);
            ps.setTimestamp(2, Timestamp.valueOf(expiryTime)); // LocalDateTime -> Timestamp
            ps.setString(3, email);

            int rows = ps.executeUpdate();
            conn.close();
            if (rows > 0) {
                System.out.println("Cập nhật reset token thành công cho email: " + email);
            } else {
                System.out.println("Không tìm thấy user với email: " + email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

}
