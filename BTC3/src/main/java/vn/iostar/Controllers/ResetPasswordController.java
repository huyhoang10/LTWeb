package vn.iostar.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.DAO.impl.UserDaoImpl;
import vn.iostar.Models.User;
import vn.iostar.service.UserService;
import vn.iostar.service.impl.UserServiceImpl;
import vn.iostar.DAO.UserDao;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
	
/**
 * Servlet implementation class ResetPasswordController
 */
@WebServlet("/reset-password")
public class ResetPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private UserDao userDao = new UserDaoImpl();
	private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String token = req.getParameter("token"); // nếu reset bằng email thì lấy token

        // 1. Check password == confirmPassword
        if (!password.equals(confirmPassword)) {
            req.setAttribute("message", "Mật khẩu xác nhận không khớp!");
            req.getRequestDispatcher("reset-password.jsp").forward(req, resp);
            return;
        }

        // 2. Tìm user theo token hoặc username
        User user = null;
        if (token != null && !token.isEmpty()) {
            //user = userDao.findByToken(token);
            user = userService.findByToken(token);
        }

        if (user == null) {
            req.setAttribute("message", "Không tìm thấy tài khoản hoặc token không hợp lệ!");
            req.getRequestDispatcher("/views/resetPassword.jsp").forward(req, resp);
            return;
        }

        // 3. Kiểm tra token còn hạn nếu có
        if (user.getExpiry_token() != null && user.getExpiry_token().before(new Timestamp(System.currentTimeMillis()))) {
            req.setAttribute("message", "Token đã hết hạn, vui lòng yêu cầu lại!");
            req.getRequestDispatcher("/views/resetPassword.jsp").forward(req, resp);
            return;
        }

        // 4. Cập nhật mật khẩu mới
//        userDao.setPassword(user.getUserName(), password);
        userService.setPassword(user.getUserName(), password);

        // 5. Gửi thông báo thành công
        req.setAttribute("message", "Đổi mật khẩu thành công! Vui lòng đăng nhập lại.");
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String token = req.getParameter("token");

        if (token == null || token.isEmpty()) {
            resp.getWriter().println("Invalid reset link!");
            return;
        }

        // TODO: kiểm tra token trong DB
        // Nếu hợp lệ → forward sang trang nhập mật khẩu mới
        req.setAttribute("token", token);
        req.getRequestDispatcher("/views/resetPassword.jsp").forward(req, resp);
    }

}
