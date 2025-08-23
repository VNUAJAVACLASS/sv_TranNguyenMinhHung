package services;

import java.util.List;

import dao.NewsDAO;
import model.News;

public class NewsService {
	private NewsDAO dao;
	
	public NewsService() {
		dao = new NewsDAO();
	}
	
	public List<News> getAllNews() {
		return dao.getAllNews();
	}
	public boolean addNews(News news) {
		return dao.addNews(news);
	}
	public News findById(int id) {
		return dao.findById(id);
	}
	public boolean updateNews(News news) {
		return dao.update(news);
	}
	public boolean deleteNews(int id) {
	    return dao.delete(id);
	}
}