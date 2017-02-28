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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

public class MainGui {

	protected Shell shell;
	private Text txtBookTitle;

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
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setBounds(148, 5, 276, 251);
		composite.setVisible(true);
		
		//Check Book Availability Widgets
		Label lblCheckBookAvailability = new Label(composite, SWT.NONE);
		lblCheckBookAvailability.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblCheckBookAvailability.setBounds(82, 10, 134, 15);
		lblCheckBookAvailability.setText("Check Book Availability");
		lblCheckBookAvailability.setVisible(false);
		
		Label lblBookTitle = new Label(composite, SWT.NONE);
		lblBookTitle.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblBookTitle.setBounds(10, 57, 74, 15);
		lblBookTitle.setText("Book Title :");
		lblBookTitle.setVisible(false);
		
		txtBookTitle = new Text(composite, SWT.BORDER);
		txtBookTitle.setBounds(90, 54, 176, 21);
		txtBookTitle.setVisible(false);
		
		Label lblSearchResult = new Label(composite, SWT.NONE);
		lblSearchResult.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblSearchResult.setAlignment(SWT.CENTER);
		lblSearchResult.setBounds(10, 132, 256, 37);
		lblSearchResult.setVisible(false);
		
		Button btnFindBook = new Button(composite, SWT.NONE);
		btnFindBook.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean hasFoundBook = false;
				LinkedList<Book> bookCollection = readInDateFromBooksXML();
				for (Book b : bookCollection){
					if (b.getTitle().equals(txtBookTitle.getText())){
						hasFoundBook = true;
						if (b.checkAvailability()){
							lblSearchResult.setText("Found a book with title: " + txtBookTitle.getText() + " and is available.");
						} else {
							lblSearchResult.setText("Found a book with title: " + txtBookTitle.getText() + " and is unavailable.");
						}
					}
				}
				if (!hasFoundBook){
					lblSearchResult.setText("Did not find a book with title: " + txtBookTitle.getText());
				}
			}
		});
		btnFindBook.setBounds(101, 93, 75, 25);
		btnFindBook.setText("Find Book!");
		btnFindBook.setVisible(false);
		
		// Main buttons
		Button btnCheckOutBook = new Button(shell, SWT.NONE);
		btnCheckOutBook.setBounds(5, 5, 134, 46);
		btnCheckOutBook.setText("Check Out Book");
		btnCheckOutBook.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Check out book");
			}
		});
		
		Button btnAddNewCustomer = new Button(shell, SWT.NONE);
		btnAddNewCustomer.setBounds(5, 56, 134, 46);
		btnAddNewCustomer.setText("Add New Customer");
		
		Button btnAddNewBook = new Button(shell, SWT.NONE);
		btnAddNewBook.setBounds(5, 107, 134, 46);
		btnAddNewBook.setText("Add New Book");
		
		Button btnReturnToLogin = new Button(shell, SWT.NONE);
		btnReturnToLogin.setBounds(5, 158, 134, 46);
		btnReturnToLogin.setText("Return to Login Page");
		
		Button btnCheckAvail = new Button(shell, SWT.NONE);
		btnCheckAvail.setBounds(5, 209, 134, 47);
		btnCheckAvail.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblCheckBookAvailability.setVisible(true);
				lblBookTitle.setVisible(true);
				txtBookTitle.setVisible(true);
				lblSearchResult.setVisible(true);
				btnFindBook.setVisible(true);
			}
		});
		btnCheckAvail.setText("Check book availability");
		
		
		
		
		btnReturnToLogin.addListener(SWT.Selection, new Listener(){
            public void handleEvent(Event event) {
            	shell.close();
            	LoginGui libGui = new LoginGui();
            	libGui.open();
            }
		});

	}
	private static LinkedList<Book> readInDateFromBooksXML() {
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
