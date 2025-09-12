<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm danh mục</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" 
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-lg rounded-3">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">Thêm danh mục mới</h4>
        </div>
        <div class="card-body">

            <form action="${pageContext.request.contextPath}/admin/category/add" method="post" enctype="multipart/form-data">

                <!-- Tên danh mục -->
                <div class="mb-3">
                    <label class="form-label fw-bold">Tên danh mục:</label>
                    <input type="text" class="form-control" 
                           name="name" placeholder="Nhập tên danh mục" required>
                </div>

                <!-- Ảnh đại diện -->
                <div class="mb-3">
                    <label class="form-label fw-bold">Ảnh đại diện:</label>
                    <input type="file" class="form-control" name="icon" required>
                </div>

                <!-- Nút bấm -->
                <div class="d-flex justify-content-end">
                    <button type="reset" class="btn btn-secondary me-2">Hủy</button>
                    <button type="submit" class="btn btn-success">Thêm</button>
                </div>
            </form>

        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
