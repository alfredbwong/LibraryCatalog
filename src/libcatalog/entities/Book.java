package libcatalog.entities;

public class Book {
	protected String title;
	protected int pageCount;
	protected int refId;
	protected boolean isAvailable;
	
	//Constructor for Book
	public Book (String title, int pageCount, int refId, boolean isAvailable){
		this.title = title;
		this.pageCount = pageCount;
		this.refId = refId;
		this.isAvailable = isAvailable;
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
	public int getRefId() {
		return refId;
	}
	
	//Sets the book ID
	public void setRefId(int refId) {
		this.refId = refId;
	}
	
	//Returns the availability of the book
	public boolean checkAvailability(){
		return isAvailable;
	}
	
	public void checkOut(){
		this.isAvailable = false;
	}
	public void checkIn(){
		this.isAvailable = true;
	}
}
