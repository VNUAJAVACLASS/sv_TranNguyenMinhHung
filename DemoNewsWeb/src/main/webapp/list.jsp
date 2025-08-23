<!-- Tương ứng thiết lập UTF-8 cho response của servlet -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Khai báo sử dụng thư viện thẻ tiện ích -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<!-- Chỉ dẫn encoding cho trình duyệt -->
<title>Danh sách tin tức</title>
</head>
<body>
	<h2>Danh sách tin tức</h2>
	<!-- Link gọi đến servlet "news" với tham số action đi kèm -->
	<a href="news?action=create">Tạo tin mới</a>
	<br>
	<br>
	<table border="1" cellpadding="5">
		<tr>
			<th>ID</th>
			<th>Tiêu đề</th>
			<th>Hành động</th>
		</tr>
		<!-- Duyệt qua danh sách tin tức -->
		<!-- newsList là tên thuộc tính đang có trong request scope được ghi vào bởi servlet -->
		<!-- var="news" là biến của vòng lặp, news đại diện cho đối tượng News trong danh sách
được duyệt -->
		<c:forEach var="news" items="${newsList}">
			<tr>
				<td>${news.id}</td>
				<!-- id là tên thuộc tính của đối tượng News trong file
News.java -->
				<td>
					<!-- Khi click sẽ gửi yêu cầu về servlet trả lại trang detail.jsp -->
					<a href="news?action=detail&id=${news.id}">${news.title}</a>
				</td>
				<td>
					<!-- Khi click sẽ gửi yêu cầu về servlet trả lại trang form.jsp -->
					<a href="news?action=edit&id=${news.id}">Sửa</a> | <a
					href="news?action=delete&id=${news.id}"
					onclick="return confirm('Xoá tin
này?');">Xoá</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>