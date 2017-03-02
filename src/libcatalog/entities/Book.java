package libcatalog.entities;

public class Book {
	protected String title;
	protected int pageCount;
	protected int isbn;
	protected boolean isAvailable;
	private static int numOfBooks = 0;
	
	//Constructor for Book
	public Book (String title, int pageCount, int isbn, boolean isAvailable){
		this.title = title;
		this.pageCount = pageCount;
		this.isbn = isbn;
		this.isAvailable = isAvailable;
		numOfBooks++;
	}
	public static int getNumBooks(){
		return numOfBooks;
	}
	//Returns title of book
	public String getTitle() {
		return title;
	}
	
	//Sets title of book
	public void setTitle(String title) {
		this.title = title;
	}
	
	//Returns number pages
	public int getPageCount() {
		return pageCount;
	}
	
	//Sets number of pages
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	//Returns book ID
	public int getIsbn() {
		return isbn;
	}
	
	//Sets the book ID
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	
	//Returns the availability of the book
	public boolean isAvailable(){
		return isAvailable;
	}
	
	public void checkOut(){
		this.isAvailable = false;
	}
	public void checkIn(){
		this.isAvailable = true;
	}
}
