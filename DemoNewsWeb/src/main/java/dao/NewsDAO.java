package dao;

import db.ConnectDB;
import model.News;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO {

	private Connection getConnection() throws SQLException {
	    return ConnectDB.getConnection();
	}


    public List<News> getAllNews() {
        String sql = "SELECT id, title, content FROM news ORDER BY id DESC";
        List<News> list = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new News(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public News findById(int id) {
        String sql = "SELECT id, title, content FROM news WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new News(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("content")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

   
    public boolean addNews(News news) {
        String sql = "INSERT INTO news (title, content) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getContent());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(News news) {
        String sql = "UPDATE news SET title = ?, content = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getContent());
            ps.setInt(3, news.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM news WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}