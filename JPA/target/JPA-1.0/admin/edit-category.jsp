<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa danh mục</title>

    <!-- Bootstrap & Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">

<div class="container my-5 d-flex justify-content-center">
    <div class="card shadow-lg rounded-4 border-0" style="max-width: 700px; width: 100%;">
        <!-- Header -->
        <div class="card-header bg-gradient bg-primary text-white d-flex align-items-center rounded-top-4 py-3">
            <i class="bi bi-pencil-square me-2 fs-5"></i>
            <h4 class="mb-0 fw-bold">Chỉnh sửa danh mục</h4>
        </div>

        <!-- Body -->
        <div class="card-body p-4">
            <c:url value="/admin/category/edit" var="edit"></c:url>
            <form role="form" action="${edit}" method="post" enctype="multipart/form-data" 
                  class="needs-validation" novalidate>

                <!-- Hidden ID -->
                <input type="hidden" name="id" value="${category.id}" />

                <!-- Tên danh mục -->
                <div class="mb-4">
                    <label for="name" class="form-label fw-semibold">Tên danh mục</label>
                    <input type="text" class="form-control form-control-lg rounded-3 shadow-sm" 
                           id="name" name="name" 
                           value="${category.cateName}" 
                           placeholder="Nhập tên danh mục..." required>
                    <div class="invalid-feedback">Vui lòng nhập tên danh mục.</div>
                </div>

                <!-- Ảnh hiện tại & Ảnh mới -->
                <div class="row g-4">
                    <!-- Ảnh hiện tại -->
                    <div class="col-md-6 text-center">
                        <label class="form-label fw-semibold">Ảnh hiện tại</label>
                        <c:url value="/image/${category.cateImage}" var="imgUrl"></c:url>
                        <div class="border rounded-3 p-2 bg-light shadow-sm">
                            <img class="img-fluid rounded-3" 
                                 style="max-height: 180px; object-fit: contain;" 
                                 src="${imgUrl}" alt="Ảnh hiện tại">
                        </div>
                    </div>

                    <!-- Ảnh mới -->
                    <div class="col-md-6 text-center">
                        <label for="icon" class="form-label fw-semibold">Ảnh mới</label>
                        <input type="file" class="form-control shadow-sm" id="icon" 
                               name="icon" accept="image/*" onchange="previewImage(event)">
                        <div class="mt-3 border rounded-3 p-2 bg-light shadow-sm">
                            <img id="preview" class="img-fluid rounded-3" 
                                 style="display:none; max-height: 180px; object-fit: contain;" 
                                 alt="Xem trước ảnh mới">
                        </div>
                    </div>
                </div>

                <!-- Buttons -->
                <div class="d-flex justify-content-end mt-5 gap-3">
                    <button type="submit" class="btn btn-success px-4 shadow-sm">
                        <i class="bi bi-check-circle me-1"></i> Cập nhật
                    </button>
                    <button type="reset" class="btn btn-outline-secondary px-4 shadow-sm">
                        <i class="bi bi-arrow-counterclockwise me-1"></i> Reset
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Script Preview ảnh -->
<script>
function previewImage(event) {
    var output = document.getElementById('preview');
    output.style.display = 'block';
    output.src = URL.createObjectURL(event.target.files[0]);
}
</script>
</body>
</html>
