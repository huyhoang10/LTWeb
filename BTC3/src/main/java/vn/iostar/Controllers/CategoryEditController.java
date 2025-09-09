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

@WebServlet("/admin/category/edit")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1,   // 1MB
    maxFileSize = 1024 * 1024 * 10,        // 10MB
    maxRequestSize = 1024 * 1024 * 15      // 15MB
)
public class CategoryEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        Category category = cateService.get(Integer.parseInt(id));
        req.setAttribute("category", category);
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/admin/category/edit-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        try {
            Category category = new Category();

            // Lấy id và name từ input text
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");

            category.setCateid(id);
            category.setCatename(name);

            // Lấy file upload (nếu có)
            Part filePart = req.getPart("icon");
            if (filePart != null && filePart.getSize() > 0) {
                // Lấy category cũ từ DB
                Category oldCate = cateService.get(id);

                // Xóa icon cũ nếu tồn tại
                if (oldCate.getIcon() != null) {
                    String oldFilePath = Constant.DIR + File.separator + oldCate.getIcon();
                    File oldFile = new File(oldFilePath);
                    if (oldFile.exists()) {
                        oldFile.delete();
                    }
                }

                // Xử lý file upload mới
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

                // Lưu file mới
                filePart.write(uploadPath + File.separator + fileName);

                // Cập nhật đường dẫn icon mới
                category.setIcon("category/" + fileName);

            } else {
                // Nếu không upload file mới → giữ nguyên icon cũ
                Category oldCate = cateService.get(id);
                category.setIcon(oldCate.getIcon());
            }


            // Cập nhật DB
            cateService.edit(category);
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Update failed: " + e.getMessage());
        }
    }
}
