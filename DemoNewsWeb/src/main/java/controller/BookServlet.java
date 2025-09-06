package controller;

import model.Book;
import services.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

// Servlet được gọi đến với tên liên kết "book" theo web.xml
public class BookServlet extends HttpServlet {

    private BookService bookService = new BookService();

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
                if (idStr != null) {
                    int idEdit = Integer.parseInt(idStr);
                    Book editBook = bookService.findById(idEdit);
                    req.setAttribute("book", editBook);
                }
                req.getRequestDispatcher("book-form.jsp").forward(req, resp);
                break;
            case "delete":
                if (idStr != null) {
                    int idDelete = Integer.parseInt(idStr);
                    bookService.deleteBook(idDelete);
                }
                resp.sendRedirect("book");
                break;
            case "detail":
                if (idStr != null) {
                    int idDetail = Integer.parseInt(idStr);
                    Book detailBook = bookService.findById(idDetail);
                    req.setAttribute("book", detailBook);
                }
                req.getRequestDispatcher("book-detail.jsp").forward(req, resp);
                break;
            default: // list
                List<Book> books = bookService.getAllBooks();
                req.setAttribute("bookList", books);
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
            bookService.addBook(new Book(0, title, author, price, imagePath));
        } else {
            // cập nhật
            int id = Integer.parseInt(idStr);
            Book existing = new Book(id, title, author, price, imagePath);
            bookService.updateBook(existing);
        }

        resp.sendRedirect("book");
    }
}
