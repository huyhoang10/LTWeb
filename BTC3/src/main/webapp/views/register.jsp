<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký tài khoản</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body {
            background: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .register-box {
            width: 400px;
            margin: 40px auto;
            padding: 25px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0px 2px 8px rgba(0,0,0,0.1);
        }
        .login-input {
            margin-bottom: 15px;
            display: block;
        }
        .btn-register {
            width: 100%;
            background: #3498db;
            color: white;
            font-weight: bold;
        }
        .btn-register:hover {
            background: #2980b9;
        }
        .text-center {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="register-box">
    <form action="${pageContext.request.contextPath}/register" method="post">
        <h2 class="text-center">Tạo tài khoản mới</h2>

        <!-- Hiện thông báo nếu có -->
        <c:if test="${alert != null}">
            <h3 class="alert alert-danger">${alert}</h3>
        </c:if>

        <!-- Username -->
        <section>
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <input type="text" placeholder="Tài khoản" name="username" class="form-control" required>
                </div>
            </label>
        </section>

        <!-- Fullname -->
        <section>
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-id-card"></i></span>
                    <input type="text" placeholder="Họ tên" name="fullname" class="form-control" required>
                </div>
            </label>
        </section>

        <!-- Email -->
        <section>
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                    <input type="email" placeholder="Nhập Email" name="email" class="form-control" required>
                </div>
            </label>
        </section>

        <!-- Phone -->
        <section>
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-phone"></i></span>
                    <input type="text" placeholder="Số điện thoại" name="phone" class="form-control" required>
                </div>
            </label>
        </section>

        <!-- Password -->
        <section>
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                    <input type="password" placeholder="Mật khẩu" name="password" class="form-control" required>
                </div>
            </label>
        </section>

        <!-- Confirm Password -->
        <section>
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                    <input type="password" placeholder="Nhập lại mật khẩu" name="confirmPassword" class="form-control" required>
                </div>
            </label>
        </section>

        <!-- Button -->
        <section>
            <button type="submit" class="btn btn-register">Tạo tài khoản</button>
        </section>

        <!-- Login link -->
        <p class="text-center" style="margin-top:15px;">
            Nếu bạn đã có tài khoản? <a href="login.jsp">Đăng nhập</a>
        </p>
    </form>
</div>
</body>
</html>
