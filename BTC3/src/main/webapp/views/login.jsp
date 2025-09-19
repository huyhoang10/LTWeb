<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" 
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" 
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .login-container {
            width: 400px;
            margin: 60px auto;
            background: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0px 2px 6px rgba(0,0,0,0.2);
        }
        .login-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .login-input {
            margin-bottom: 15px;
        }
        .btn-login {
            width: 100%;
        }
        .login-footer {
            text-align: center;
            margin-top: 15px;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Đăng Nhập Vào Hệ Thống</h2>

        <!-- Hiển thị alert nếu có -->
        <c:if test="${alert != null}">
            <div class="alert alert-danger">${alert}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <!-- Username -->
            <div class="input-group login-input">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fa fa-user"></i></span>
                </div>
                <input type="text" name="username" class="form-control" 
                       placeholder="Tài khoản" required>
            </div>

            <!-- Password -->
            <div class="input-group login-input">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fa fa-lock"></i></span>
                </div>
                <input type="password" name="password" class="form-control" 
                       placeholder="Mật khẩu" required>
            </div>

            <!-- Remember me + Forgot -->
            <div class="d-flex justify-content-between align-items-center mb-3">
                <div class="form-check">
                    <input type="checkbox" name="remember" class="form-check-input" id="rememberMe">
					<label class="form-check-label" for="rememberMe">Nhớ tôi</label>
                </div>
                <a href="${pageContext.request.contextPath}/views/forgetPassword.jsp">Quên mật khẩu?</a>
            </div>

            <!-- Submit -->
            <button type="submit" class="btn btn-primary btn-login">Đăng nhập</button>
        </form>

        <div class="login-footer">
            Nếu bạn chưa có tài khoản trên hệ thống, thì hãy 
            <a href="register.jsp">Đăng ký</a>
        </div>
    </div>
</body>
</html>
