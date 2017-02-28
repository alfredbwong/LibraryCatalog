package libcatalog.entities;
import java.util.LinkedList;

public class Customer {
	
	private int customerID;
	private String customerName;
	private LinkedList<Book> usersBooks;

	public Customer (int customerID, String customerName, LinkedList<Book> usersBooks){
		this.customerID = customerID;
		this.customerName = customerName;
		this.usersBooks = usersBooks;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public int getCustomerID() {
		return customerID;
	}

	public LinkedList<Book> getUsersBooks() {
		return usersBooks;
	}
	public void addBookToUser(Book b){
		usersBooks.add(b);
	}
	public void removeBookFromUser(Book b){
		if (usersBooks.contains(b)){
			usersBooks.remove(b);
		} else {
			System.out.println("No book was of that nature was found for the user.");
		}
	}
}
