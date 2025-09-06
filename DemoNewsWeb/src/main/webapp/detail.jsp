<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết sách</title>
</head>
<body>
    <!-- Truy cập đối tượng Book được servlet ghi vào request scope theo tên "book" -->
    <h2>${book.title}</h2>
    <p><strong>Tác giả:</strong> ${book.author}</p>
    <p><strong>Giá:</strong> ${book.price} VNĐ</p>
    <p>
        <img src="${book.imagePath}" alt="${book.title}" width="150" height="200"/>
    </p>
    <br>
    <!-- Quay lại danh sách sách -->
    <a href="${pageContext.request.contextPath}/book">Quay lại danh sách</a>
</body>
</html>
