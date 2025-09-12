<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Admin Dashboard - Danh mục</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet" />
  <style>
    body { overflow-x: hidden; }
    .sidebar { min-height: 100vh; }
    .sidebar .nav-link.active { background-color: #0d6efd; color: #fff; }
    .content { padding: 20px; }
    img.table-img { max-height: 80px; object-fit: contain; }
  </style>
</head>
<body>
  <!-- Top Navbar -->
  <nav class="navbar navbar-dark bg-primary sticky-top shadow">
    <div class="container-fluid">
      <a class="navbar-brand" href="/">Trang chủ</a>
      <div class="d-flex">
        <a href="/BTC3/logout" class="btn btn-outline-light">Đăng xuất</a>
      </div>
    </div>
  </nav>

  <div class="container-fluid">
    <div class="row">
      <!-- Sidebar -->
      <nav class="col-md-3 col-lg-2 d-md-block bg-dark sidebar collapse">
        <div class="position-sticky pt-3">
          <h5 class="text-white px-3 mb-3">Admin Panel</h5>
          <ul class="nav flex-column">
            <li class="nav-item">
              <a class="nav-link text-white " aria-current="page" href="#dashboard">
                <i class="bi bi-speedometer2"></i> Dashboard
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white" href="#products">
                <i class="bi bi-box-seam"></i> Sản phẩm
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white active" href="#table">
                <i class="bi bi-table"></i> Danh mục
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white" href="#form">
                <i class="bi bi-ui-checks"></i> Form
              </a>
            </li>
          </ul>
        </div>
      </nav>

      <!-- Main Content -->
      <div class="col-md-9 ms-sm-auto col-lg-10 px-md-4 pt-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
          <h2 class="fw-bold">Danh mục</h2>
          <a href="<c:url value='/admin/category/add'/>" class="btn btn-primary">
            <i class="bi bi-plus-circle"></i> Thêm danh mục
          </a>
        </div>

        <div class="table-responsive shadow-sm rounded-3">
          <table class="table table-bordered table-hover align-middle">
            <thead class="table-dark text-center">
              <tr>
                <th scope="col">#</th>
                <th scope="col">Hình ảnh</th>
                <th scope="col">Tên danh mục</th>
                <th scope="col">Hành động</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach items="${cateList}" var="cate" varStatus="stt">
                <tr>
                  <!-- STT -->
                  <td class="text-center">${stt.index + 1}</td>

                  <!-- Hình ảnh -->
                  <td class="text-center">
                    <c:url value="/image/${cate.icon}" var="imgUrl"/>
                    <p>${imgUrl}</p>
                    <img src="${imgUrl}" alt="${cate.catename}" class="img-thumbnail table-img"/>
                  </td>

                  <!-- Tên danh mục -->
                  <td>${cate.catename}</td>

                  <!-- Action -->
                  <td class="text-center">
                    <a href="<c:url value='/admin/category/edit?id=${cate.cateid}'/>" 
                       class="btn btn-sm btn-warning">
                      <i class="bi bi-pencil-square"></i> Sửa
                    </a>
                    <a href="<c:url value='/admin/category/delete?id=${cate.cateid}'/>"
                       onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này?');"
                       class="btn btn-sm btn-danger">
                      <i class="bi bi-trash"></i> Xóa
                    </a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
