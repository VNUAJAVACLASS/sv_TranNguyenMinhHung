package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;

@WebServlet("/clientHome")
public class ClientHomeServlet extends HttpServlet {
	private static List<Book> bookList = new ArrayList<>();
	private static int idCounter = 1;

	@Override
	public void init() {
		bookList.add(new Book(idCounter++, "Sach 1", "Tac gia 1", 10000, "fajsdf"));
		bookList.add(new Book(idCounter++, "Sach 2", "Tac gia 2", 10000, "fajsdf"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		String action = req.getParameter("action");
		String idStr = req.getParameter("id");

		if (action == null)
			action = "list";

		switch (action) {
			case "detail" -> { // truong hop click xem chi tiet
				int idDetail = Integer.parseInt(idStr);
				Book detailBook = findById(idDetail);
				req.setAttribute("book", detailBook);
				req.getRequestDispatcher("index.jsp").forward(req, resp);
				break;
			}
			default -> {//truong hop mac dinh tai trang chu o phia may khach
				req.setAttribute("bookList", bookList);
				req.getRequestDispatcher("index.jsp").forward(req, resp);
				break;
			}
		}
	}
	
	private Book findById(int id) {
		for(Book b: bookList) {
			if(b.getBookId() == id) return b;
		}
		return null;
	}
}
