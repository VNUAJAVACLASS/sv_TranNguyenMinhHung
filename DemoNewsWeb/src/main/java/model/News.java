package model;

public class News {
	private int id;
	private String title, content;

	//constructor
	public News(int id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	
	//getter va setter
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
