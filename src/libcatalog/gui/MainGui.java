package libcatalog.gui;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import libcatalog.entities.Book;

import org.eclipse.swt.widgets.Listener;

import java.io.File;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainGui {

	protected Shell shell;
	protected CheckAvailComposite checkAvailComp;
	protected AddBookComposite addBookComp;
	protected AddCustomerComposite addCustComp;
	protected CheckOutComposite checkOutComp;
	protected CheckInComposite checkInComp;
	protected LinkedList <Book> bookList;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainGui window = new MainGui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			bookList = readBooksXML();

			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Main Gui");
		shell.setLayout(null);
		
				
		// Main buttons
		Button btnCheckOutBook = new Button(shell, SWT.NONE);
		btnCheckOutBook.setBounds(5, 5, 134, 37);
		btnCheckOutBook.setText("Check Out Book");
		btnCheckOutBook.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (addBookComp != null){
					addBookComp.dispose();
				}
				if (checkAvailComp != null){
					checkAvailComp.dispose();
				}
				if (addCustComp != null){
					addCustComp.dispose();
				}
				if (checkInComp != null){
					checkInComp.dispose();
				}
				checkOutComp = new CheckOutComposite(shell, SWT.NONE, bookList);
			}
		});
		
		Button btnCheckIn = new Button(shell, SWT.NONE);
		btnCheckIn.setBounds(5, 47, 134, 37);
		btnCheckIn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (addBookComp != null){
					addBookComp.dispose();
				}
				if (addCustComp != null){
					addCustComp.dispose();
				}
				if (checkOutComp != null){
					checkOutComp.dispose();
				}
				if (checkAvailComp != null){
					checkAvailComp.dispose();
				}
				checkInComp = new CheckInComposite(shell, SWT.NONE);
			}
		});
		btnCheckIn.setText("Check In Book");
		
		Button btnAddNewCustomer = new Button(shell, SWT.NONE);
		btnAddNewCustomer.setBounds(5, 130, 134, 37);
		btnAddNewCustomer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (addBookComp != null){
					addBookComp.dispose();
				}
				if (checkAvailComp != null){
					checkAvailComp.dispose();
				}
				if (checkOutComp != null){
					checkOutComp.dispose();
				}
				if (checkInComp != null){
					checkInComp.dispose();
				}
				addCustComp = new AddCustomerComposite(shell, SWT.NONE);
			}
		});
		btnAddNewCustomer.setText("Add New Customer");
		
		Button btnCheckAvail = new Button(shell, SWT.NONE);
		btnCheckAvail.setBounds(5, 90, 134, 37);
		btnCheckAvail.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (addBookComp != null){
					addBookComp.dispose();
				}
				if (addCustComp != null){
					addCustComp.dispose();
				}
				if (checkOutComp != null){
					checkOutComp.dispose();
				}
				if (checkInComp != null){
					checkInComp.dispose();
				}
				checkAvailComp = new CheckAvailComposite(shell, SWT.NONE, bookList);
			}
		});
		btnCheckAvail.setText("Check book availability");
		
		Button btnAddNewBook = new Button(shell, SWT.NONE);
		btnAddNewBook.setBounds(5, 173, 134, 37);
		btnAddNewBook.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (checkAvailComp != null){
					checkAvailComp.dispose();
				}
				if (addCustComp != null){
					addCustComp.dispose();
				}
				if (checkOutComp != null){
					checkOutComp.dispose();
				}
				if (checkInComp != null){
					checkInComp.dispose();
				}
				addBookComp = new AddBookComposite(shell, SWT.NONE);
			}
		});
		btnAddNewBook.setText("Add New Book");
		
		Button btnReturnToLogin = new Button(shell, SWT.NONE);
		btnReturnToLogin.setBounds(5, 215, 134, 41);
		btnReturnToLogin.setText("Return to Login Page");
		
		btnReturnToLogin.addListener(SWT.Selection, new Listener(){
            public void handleEvent(Event event) {
            	shell.close();
            	LoginGui libGui = new LoginGui();
            	libGui.open();
            }
		});

	}
	private LinkedList <Book> readBooksXML() {
		LinkedList<Book> tmpBookList = new LinkedList<Book>();

		try{
			File inputFile = new File("src\\xmlresources\\books.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("book");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				int isbn = Integer.parseInt(eElement.getAttribute("isbn"));
				int pages = Integer.parseInt(eElement.getElementsByTagName("pages").item(0).getTextContent());
				String title = eElement.getElementsByTagName("title").item(0).getTextContent();
				String availStr = eElement.getElementsByTagName("availability").item(0).getTextContent();
				boolean availability = availStr.equals("true") ? true : false;
				Book b = new Book (title, pages, isbn, availability);
				tmpBookList.add(b);
			}
		}catch (Exception e){
			System.out.println("Failed to Parse XML");
			System.out.println(e);
		}
		return tmpBookList;
	}

}
