package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "12";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		String rememberedUser = null;

		if (cookies != null) {
			for (Cookie c : cookies) {
				// chi lay cookie voi ten da gi vao truoc do
				if (c.getName().equals("rememberedUser")) {
					rememberedUser = c.getValue();
					break;
				}
			}
		}

		// ghi gia tri cookie ra request scope de su dung tren trang login.jsp dien vao
		// o username
		req.setAttribute("rememberedUser", rememberedUser);
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");

		if (USERNAME.equals(username) && PASSWORD.equals(password)) {
			// dang nhap thanh cong
			HttpSession session = req.getSession();
			session.setAttribute("username", username);

			if ("on".equals(remember)) {// co check ghi nho dang nhap
				Cookie cookie = new Cookie("rememberedUser", username);
				cookie.setMaxAge(60 * 60 * 24 * 7);// 7 ngay
				// cookie duoc them vao response cua servlet gui ve client
				// se duoc trinh duyet nhan va ghi vao bo nho cookie cua trinh duyet
				resp.addCookie(cookie);
			} else {
				Cookie cookie = new Cookie("rememberedUser", "");
				cookie.setMaxAge(0);// xoa cookie
				resp.addCookie(cookie);
			}
		} else {
			// thuoc tinh error duoc ghi vao request de bao loi tren trang login.jsp
			req.setAttribute("error", "Sai ten dang nhap hoac mat khau");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}
}
