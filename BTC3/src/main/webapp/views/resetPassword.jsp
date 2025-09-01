<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt lại mật khẩu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background: white;
            padding: 20px 30px;
            border-radius: 8px;
            box-shadow: 0px 2px 6px rgba(0,0,0,0.2);
            width: 350px;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            width: 100%;
            padding: 10px;
            background: #2196F3;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background: #1976D2;
        }
        .message {
            color: red;
            text-align: center;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Đặt lại mật khẩu</h2>

    <!-- Thông báo lỗi/thành công -->
    <div class="message">
        ${message}
    </div>

    <form action="${pageContext.request.contextPath}/reset-password" method="post">
        <!-- Nếu em dùng token, có thể thêm hidden field -->
        <input type="hidden" name="token" value="${token}" />
        
        <label for="password">Mật khẩu mới:</label>
        <input type="password" id="password" name="password" required />

        <label for="confirmPassword">Nhập lại mật khẩu:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required />

        <button type="submit">Xác nhận</button>
    </form>
</div>
</body>
</html>
