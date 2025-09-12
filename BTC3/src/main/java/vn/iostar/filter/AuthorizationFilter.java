package vn.iostar.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*") // Áp dụng cho tất cả URL
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo nếu cần
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    		
    	
    	
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI().substring(req.getContextPath().length());

        if (uri.equals("/views/login.jsp") 
        		|| uri.startsWith("/login") 
        		|| uri.startsWith("/logout") 
                || uri.startsWith("/register") 
                || uri.startsWith("/waiting") 
                || uri.startsWith("/public") 
                || uri.endsWith(".css") 
                || uri.endsWith(".js") 
                || uri.endsWith(".png") 
                || uri.endsWith(".jpg")) {
            chain.doFilter(request, response);
            return;
        }


        // Lấy roleId từ session (Integer)
        Integer roleId = (Integer) session.getAttribute("RoleId");
        // Cho phép luôn truy cập trang public hoặc login
        if (uri.startsWith(req.getContextPath() 	+ "/public") || uri.endsWith("login.jsp")) {
            chain.doFilter(request, response);
            return;
        }

        // Nếu chưa login
        if (roleId == null) {
            res.sendRedirect(req.getContextPath() + "/views/login.jsp");
            return;
        }

        // Phân quyền theo roleId
        switch (roleId) {
            case 1: // ADMIN
                if (!uri.startsWith("/admin")) {
                    res.sendError(HttpServletResponse.SC_FORBIDDEN, "ADMIN chỉ được vào /admin!");
                    return;
                }
                break;

            case 2: // MANAGER
                if (!uri.startsWith("/manager")) {
                    res.sendError(HttpServletResponse.SC_FORBIDDEN, "MANAGER chỉ được vào /manager!");
                    return;
                }
                break;

            case 3: // USER
                if (!uri.startsWith("/user")) {
                    res.sendError(HttpServletResponse.SC_FORBIDDEN, "USER chỉ được vào /user!");
                    return;
                }
                break;

            default:
                res.sendError(HttpServletResponse.SC_FORBIDDEN, "Vai trò không hợp lệ!");
                return;
        }

        // Nếu hợp lệ → cho đi tiếp
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Dọn dẹp nếu cần
    }
}
