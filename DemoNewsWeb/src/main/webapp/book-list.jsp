<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách sách</title>
</head>
<body>
    <h2>Danh sách sách</h2>
    <a href="book?action=create">Thêm sách mới</a>
    <br><br>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Tiêu đề</th>
            <th>Tác giả</th>
            <th>Giá</th>
            <th>Ảnh bìa</th>
            <th>Hành động</th>
        </tr>
        <!-- bookList là attribute trong request được servlet set -->
        <c:forEach var="book" items="${bookList}">
            <tr>
                <td>${book.bookId}</td>
                <td>
                    <a href="book?action=detail&id=${book.bookId}">${book.title}</a>
                </td>
                <td>${book.author}</td>
                <td>${book.price}</td>
                <td>
                    <img src="${book.imgPath}" alt="${book.title}" width="60" height="80"/>
                </td>
                <td>
                    <a href="book?action=edit&id=${book.bookId}">Sửa</a> |
                    <a href="book?action=delete&id=${book.bookId}" 
                       onclick="return confirm('Xoá sách này?');">Xoá</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
