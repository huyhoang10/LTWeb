<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container mt-4">
    <div class="card shadow-lg">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">Chỉnh sửa danh mục</h4>
        </div>
        <div class="card-body">
            <c:url value="/admin/category/edit" var="edit"></c:url>
            <form role="form" action="${edit}" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
                
                <!-- Hidden ID -->
                <input type="hidden" name="id" value="${category.id}" />

                <!-- Tên danh mục -->
                <div class="form-group mb-3">
                    <label for="name" class="form-label fw-bold">Tên danh mục:</label>
                    <input type="text" class="form-control" id="name" name="name" value="${category.name}" required>
                    <div class="invalid-feedback">Vui lòng nhập tên danh mục.</div>
                </div>

                <!-- Ảnh hiện tại -->
                <div class="form-group mb-3">
                    <label class="form-label fw-bold">Ảnh hiện tại:</label>
                    <c:url value="/image?fname=${category.icon}" var="imgUrl"></c:url>
                    <div>
                        <img class="img-thumbnail mb-2" width="120px" src="${imgUrl}" alt="Ảnh hiện tại">
                    </div>
                </div>

                <!-- Upload ảnh mới -->
                <div class="form-group mb-3">
                    <label for="icon" class="form-label fw-bold">Chọn ảnh mới:</label>
                    <input type="file" class="form-control" id="icon" name="icon" accept="image/*" onchange="previewImage(event)">
                    <div class="mt-2">
                        <img id="preview" class="img-thumbnail" style="display:none;" width="120px" alt="Xem trước ảnh mới">
                    </div>
                </div>

                <!-- Buttons -->
                <div class="d-flex justify-content-end mt-4">
                    <button type="submit" class="btn btn-success me-2">
                        <i class="bi bi-check-circle"></i> Cập nhật
                    </button>
                    <button type="reset" class="btn btn-secondary">
                        <i class="bi bi-arrow-counterclockwise"></i> Reset
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
