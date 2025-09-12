package vn.iotstar.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import vn.iotstar.util.Constant;
import vn.iotstar.entity.CategoryEntity;
import vn.iotstar.service.Impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {
        "/admin/list-category",
        "/admin/category/add",
        "/admin/category/edit",
        "/admin/category/delete"
})
@MultipartConfig
public class CategoryController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final CategoryServiceImpl cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String url = req.getRequestURL().toString();

        if (url.contains("edit")) {
        	req.setAttribute("category", cateService.findById(Integer.parseInt(req.getParameter("id"))));
            req.getRequestDispatcher("/admin/edit-category.jsp").forward(req, resp);
        } else if (url.contains("add")) {
            req.getRequestDispatcher("/admin/add-category.jsp").forward(req, resp);
        } else if(url.contains("delete")) {
        	CategoryEntity category = cateService.findById(Integer.parseInt(req.getParameter("id")));
        	if (category != null && category.getCateImage() != null) {
                File file = new File(Constant.DIR, category.getCateImage());
                if (file.exists()) {
                    file.delete(); // xóa file vật lý
                }
            }
        	cateService.delete(Integer.parseInt(req.getParameter("id")));
        	resp.sendRedirect(req.getContextPath() + "/admin/list-category");
        }
        else {
        	req.setAttribute("cateList", cateService.findAll());
            req.getRequestDispatcher("/admin/list-category.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String url = req.getRequestURL().toString();

        if (url.contains("add")) {
            insert(req, resp);
        }
        
    }

    protected void insert(HttpServletRequest req, HttpServletResponse resp) {
        try {
            CategoryEntity category = new CategoryEntity();

            // Lấy text field
            String name = req.getParameter("name");
            category.setCateName(name);

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

                // ⚠️ Chỗ này bạn đang setCateImage(name) → nên là fileName để lưu ảnh
                category.setCateImage("category/"+fileName);
            }

            // Lưu category
            cateService.insert(category);

            resp.sendRedirect(req.getContextPath() + "/admin/list-category");

        } catch (Exception e) {
            e.printStackTrace();
            try {
                resp.getWriter().println("Upload failed: " + e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
