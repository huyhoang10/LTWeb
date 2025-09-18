<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet"/>
</head>
<body>
    <!-- Topbar -->
    <jsp:include page="/commons/admin/topbar.jsp"/>

    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar -->
            <jsp:include page="/commons/admin/sidebar.jsp"/>

            <!-- Nội dung chính -->
            <div class="col-md-9 ms-sm-auto col-lg-10 px-md-4 pt-4">
			    <sitemesh:write property="body"/>
			</div>
        </div>
    </div>
	<jsp:include page="/commons/admin/footer.jsp"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
