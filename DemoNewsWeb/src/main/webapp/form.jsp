<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    // Form dùng chung cho tạo mới và sửa sách
    // Nếu trong request có "book" thì là chế độ Edit
    request.setAttribute("isEdit", request.getAttribute("book") != null);
%>
<html>
<head>
<meta charset="UTF-8">
<title>${isEdit ? 'Sửa sách' : 'Thêm sách mới'}</title>
</head>
<body>
    <h2>${isEdit ? 'Sửa sách' : 'Thêm sách mới'}</h2>
    
    <form action="book" method="post">
        <!-- Nếu là Edit thì truyền bookId vào hidden input -->
        <c:if test="${isEdit}">
            <input type="hidden" name="bookId" value="${book.bookId}">
        </c:if>

        Tiêu đề: <br>
        <input type="text" name="title" value="${book.title}" required><br><br>

        Tác giả: <br>
        <input type="text" name="author" value="${book.author}" required><br><br>

        Giá: <br>
        <input type="number" step="0.01" name="price" value="${book.price}" required><br><br>

        Ảnh bìa (URL hoặc đường dẫn): <br>
        <input type="text" name="imagePath" value="${book.imagePath}" required><br><br>

        <input type="submit" value="${isEdit ? 'Cập nhật' : 'Thêm mới'}">
    </form>

    <br>
    <a href="book">Quay lại danh sách</a>
</body>
</html>
