<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<h2>Danh sách danh mục</h2>
<a href="<c:url value='/admin/category/add'/>">Thêm danh mục</a>

<table border="1" cellspacing="0" cellpadding="8" width="100%">
    <thead>
        <tr style="background-color:#f2f2f2; text-align:center;">
            <th>STT</th>
            <th>Hình ảnh</th>
            <th>Tên danh mục</th>
            <th>Hành động</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${cateList}" var="cate" varStatus="stt">
            <tr>
                <!-- Số thứ tự -->
                <td style="text-align:center;">${stt.index + 1}</td>

                <!-- Hình ảnh -->
                <c:url value="/image?fname=${cate.icon}" var="imgUrl" />
                <td style="text-align:center;">
                    <img src="${imgUrl}" alt="${cate.catename}" height="100" width="150"/>
                </td>

                <!-- Tên danh mục -->
                <td>${cate.catename}</td>

                <!-- Link sửa / xóa -->
                <td style="text-align:center;">
                    <a href="<c:url value='/admin/category/edit?cateid=${cate.cateid}'/>">Sửa</a> | 
                    <a href="<c:url value='/admin/category/delete?cateid=${cate.cateid}'/>"
                       onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này?');">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
