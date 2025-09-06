package controller;

import model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

// Servlet được gọi đến với tên liên kết "book" theo web.xml
public class BookServlet extends HttpServlet {

    // Danh sách sách fix cứng
    private static List<Book> bookList = new ArrayList<>();

    // Biến đếm id tự tăng
    private static int idCounter = 1;

    @Override
    public void init() throws ServletException {
        // Chỉ khởi tạo dữ liệu 1 lần khi servlet chạy
        if (bookList.isEmpty()) {
            bookList.add(new Book(idCounter++, "Lập trình Java", "Nguyễn Văn A", 120000));
            bookList.add(new Book(idCounter++, "Spring Boot Cơ bản", "Trần Văn B", 150000));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        String idStr = req.getParameter("id");

        if (action == null) action = "list";

        switch (action) {
            case "create":
                req.getRequestDispatcher("book-form.jsp").forward(req, resp);
                break;
            case "edit":
                int idEdit = Integer.parseInt(idStr);
                Book editBook = findById(idEdit);
                req.setAttribute("book", editBook);
                req.getRequestDispatcher("book-form.jsp").forward(req, resp);
                break;
            case "delete":
                int idDelete = Integer.parseInt(idStr);
                bookList.removeIf(b -> b.getBookId() == idDelete);
                resp.sendRedirect("book");
                break;
            case "detail":
                int idDetail = Integer.parseInt(idStr);
                Book detailBook = findById(idDetail);
                req.setAttribute("book", detailBook);
                req.getRequestDispatcher("book-detail.jsp").forward(req, resp);
                break;
            default: // list
                req.setAttribute("bookList", bookList);
                req.getRequestDispatcher("book-list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String idStr = req.getParameter("bookId");
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String priceStr = req.getParameter("price");
        String imagePath = req.getParameter("imagePath");

        float price = (priceStr != null && !priceStr.isEmpty()) ? Float.parseFloat(priceStr) : 0;

        if (idStr == null || idStr.isEmpty()) {
            // thêm mới
            bookList.add(new Book(idCounter++, title, author, price, imagePath));
        } else {
            // cập nhật
            int id = Integer.parseInt(idStr);
            Book existing = findById(id);
            if (existing != null) {
                existing.setTitle(title);
                existing.setAuthor(author);
                existing.setPrice(price);
                existing.setImgPath(imagePath);
            }
        }

        resp.sendRedirect("book");
    }

    private Book findById(int id) {
        for (Book b : bookList) {
            if (b.getBookId() == id) return b;
        }
        return null;
    }
}
