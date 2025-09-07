<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<h2>Đăng nhập</h2> 

	<!-- Hiển thị lỗi đăng nhập nếu có -->
	<!-- error là thuộc tính trong request scope được LoginServlet ghi vào nếu có -->
	<!-- Khi bấm nút Đăng nhập > chuyển tới LoginServlet > nếu có lỗi ghi vào request scope và chuyển lại trang login.jsp -->
	<c:if test="${not empty error}">
		<p style="color: red;">${error}</p>
	</c:if>

	<form action="login" method="POST">
		Tên đăng nhập: <br>
		<input type="text" name="username" value="${rememberedUser}" required><br>
		<!-- rememberedUser là username lưu trong Cookie từ lần đăng nhập trước nếu có -->
		<!-- giá trị cookie này được LoginServlet lấy, ghi vào request scope -->

		<br> Mật khẩu: <br>
		<input type="password" name="password" required><br><br>

		<label><input type="checkbox" name="remember">
		Ghi nhớ đăng nhập</label><br><br>

		<input type="submit" value="Đăng nhập">
	</form>
</body>
</html>
