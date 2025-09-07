package model;

public class Book {
	private int bookId;
	private String title;
	private String author;
	private float price;
	private String imgPath;
	
	//constructor
	public Book() {}

	public Book(int bookId, String title, String author, float price) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	public Book(int bookId, String title, String author, float price, String imgPath) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.price = price;
		this.imgPath = imgPath;
	}

	//getter and setter
	
	public String getTitle() {
		return title;
	}
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
