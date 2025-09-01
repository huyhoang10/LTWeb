package vn.iostar.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.DAO.UserDao;
import vn.iostar.DAO.impl.UserDaoImpl;
import vn.iostar.Models.User;
import vn.iostar.service.UserService;
import vn.iostar.service.impl.UserServiceImpl;
import vn.iostar.utils.EmailUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Servlet implementation class ForgetPasswordController
 */
@WebServlet("/forgot-password")
public class ForgetPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");

        // 1. Tìm user theo email
        //User user = userDao.findByEmail(email);

        if (!userService.checkExistEmail(email)) {
            req.setAttribute("message", "Email không tồn tại trong hệ thống!");
            req.getRequestDispatcher("/views/forgetPassword.jsp").forward(req, resp);
            return;
        }

        // 5. Tạo link reset password
        String token = userService.setToken(email);
        String resetLink = req.getRequestURL().toString()
                                .replace("forgot-password", "reset-password?token=" + token);

        // 6. Gửi email
        String subject = "Yêu cầu đặt lại mật khẩu";
        String body = "Xin chào,\n\n"
                + "Bạn vừa yêu cầu đặt lại mật khẩu cho tài khoản của mình.\n"
                + "Vui lòng nhấn vào liên kết sau để đặt lại mật khẩu:\n\n"
                + resetLink + "\n\n"
                + "Lưu ý: Liên kết chỉ có hiệu lực trong vòng 30 phút.\n";
        try {
            EmailUtil.sendEmail(email,subject,body);

            req.setAttribute("message", "Vui lòng kiểm tra email để đặt lại mật khẩu!");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "Không thể gửi email: " + e.getMessage());
        }

        // 7. Forward về trang thông báo
        req.getRequestDispatcher("/views/forgetPassword.jsp").forward(req, resp);
    }
}
