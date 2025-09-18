<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<style>
    img.table-img { max-height: 80px; object-fit: contain; }
</style>
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
                        <c:url value="/image/${cate.cateImage}" var="imgUrl"/>
                        <img src="${imgUrl}" alt="${cate.cateName}" 
                             class="img-thumbnail table-img"/>
                    </td>

                    <!-- Tên danh mục -->
                    <td>${cate.cateName}</td>

                    <!-- Action -->
                    <td class="text-center">
                        <a href="<c:url value='/admin/category/edit?id=${cate.id}'/>" 
                           class="btn btn-sm btn-warning">
                            <i class="bi bi-pencil-square"></i> Sửa
                        </a>
                        <a href="<c:url value='/admin/category/delete?id=${cate.id}'/>" 
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
