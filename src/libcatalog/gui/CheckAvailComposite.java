package libcatalog.gui;

import java.io.File;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import libcatalog.entities.Book;

public class CheckAvailComposite extends Composite {
	private Text txtBookTitle;
	private Label lblCheckBookAvailability;
	private Label lblBookTitle;
	private Label lblSearchResult;
	private Button btnFindBook;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CheckAvailComposite(Composite parent, int style) {
		super(parent, style);
		this.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		this.setVisible(true);
		setupComposite();

	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	private void setupComposite() {
		this.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		this.setBounds(148, 5, 276, 251);
		this.setVisible(true);
		setupWidgets();
	}

	private void setupWidgets() {
		//Check Book Availability Widgets
		lblCheckBookAvailability = new Label(this, SWT.NONE);
		lblCheckBookAvailability.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblCheckBookAvailability.setBounds(82, 10, 134, 15);
		lblCheckBookAvailability.setText("Check Book Availability");
		lblCheckBookAvailability.setVisible(false);

		lblBookTitle = new Label(this, SWT.NONE);
		lblBookTitle.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblBookTitle.setBounds(10, 57, 74, 15);
		lblBookTitle.setText("Book Title :");
		lblBookTitle.setVisible(false);

		txtBookTitle = new Text(this, SWT.BORDER);
		txtBookTitle.setBounds(90, 54, 176, 21);
		txtBookTitle.setVisible(false);

		lblSearchResult = new Label(this, SWT.NONE);
		lblSearchResult.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblSearchResult.setAlignment(SWT.CENTER);
		lblSearchResult.setBounds(10, 132, 256, 37);
		lblSearchResult.setVisible(false);

		btnFindBook = new Button(this, SWT.NONE);
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

	public void reveal() {
		lblCheckBookAvailability.setVisible(true);
		lblBookTitle.setVisible(true);
		txtBookTitle.setVisible(true);
		lblSearchResult.setVisible(true);
		btnFindBook.setVisible(true);
	}
}
