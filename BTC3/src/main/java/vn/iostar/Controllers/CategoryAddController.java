package vn.iostar.Controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iostar.Models.Category;
import vn.iostar.service.CategoryService;
import vn.iostar.service.impl.CategoryServiceImpl;
import vn.iostar.utils.Constant;

import java.io.File;
import java.io.IOException;

/**
 * Servlet implementation class CategoryAddController
 */
@WebServlet("/admin/category/add")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1,  // 1MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 15     // 15MB
)
public class CategoryAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/admin/category/add-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        try {
            Category category = new Category();

            // Lấy text field
            String name = req.getParameter("name");
            category.setCatename(name);

            // Lấy file field
            Part filePart = req.getPart("icon");
            if (filePart != null && filePart.getSize() > 0) {
                String originalFileName = filePart.getSubmittedFileName();
                String ext = "";

                int index = originalFileName.lastIndexOf(".");
                if (index > 0) {
                    ext = originalFileName.substring(index + 1);
                }

                String fileName = System.currentTimeMillis() + "." + ext;
                String uploadPath = Constant.DIR + File.separator + "category";

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                // Ghi file
                filePart.write(uploadPath + File.separator + fileName);

                // Lưu đường dẫn tương đối
                category.setIcon("category/" + fileName);
            }

            // Lưu category
            cateService.insert(category);

            resp.sendRedirect(req.getContextPath() + "/admin/category/list");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Upload failed: " + e.getMessage());
        }
    }
}
