package vn.iostar.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.Models.Category;
import vn.iostar.service.CategoryService;
import vn.iostar.service.impl.CategoryServiceImpl;
import vn.iostar.utils.Constant;

import java.io.File;
import java.io.IOException;

/**
 * Servlet implementation class CategoryDeleteController
 */
@WebServlet("/admin/category/delete")
public class CategoryDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    CategoryService cateService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        int cateId = Integer.parseInt(id);

        // Lấy category trước khi xóa
        Category category = cateService.get(cateId);
        if (category != null && category.getIcon() != null) {
            File file = new File(Constant.DIR, category.getIcon());
            if (file.exists()) {
                file.delete(); // xóa file vật lý
            }
        }

        // Xóa DB
        cateService.delete(cateId);

        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
