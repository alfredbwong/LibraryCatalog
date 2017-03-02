package libcatalog.gui;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddBookComposite extends Composite implements MainCompInterface {
	private Text txtBookTitle;
	private Label lblBookTitle;
	private Label lblAvailability;
	private Label lblAddANew;
	private Button btnAddBook;
	private Button buttonAvail;
	private Label lblPageCount;
	private Text textPageCount;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AddBookComposite(Composite parent, int style) {
		super(parent, style);
		this.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		this.setBounds(148, 5, 276, 251);
		setupWidgets();
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	@Override
	public void setupWidgets() {
		lblBookTitle = new Label(this, SWT.NONE);
		lblBookTitle.setBounds(26, 57, 55, 15);
		lblBookTitle.setText("Book Title:");
		
		lblAvailability = new Label(this, SWT.NONE);
		lblAvailability.setBounds(26, 130, 74, 15);
		lblAvailability.setText("Availability:");
		
		txtBookTitle = new Text(this, SWT.BORDER);
		txtBookTitle.setText("Book Title");
		txtBookTitle.setBounds(111, 51, 128, 21);
		
		lblAddANew = new Label(this, SWT.NONE);
		lblAddANew.setBounds(87, 30, 95, 15);
		lblAddANew.setText("Add a New Book");
		
		btnAddBook = new Button(this, SWT.NONE);
		btnAddBook.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (txtBookTitle.getText().isEmpty() || textPageCount.getText().isEmpty()){
					MessageBox alertMsgBox = new MessageBox(new Shell(), SWT.OK | SWT.ICON_ERROR);
					alertMsgBox.setMessage("Cannot have empty fields");
					alertMsgBox.open();
				}else{
					addBookToXML();
					MessageBox alertMsgBox = new MessageBox(new Shell(), SWT.OK | SWT.ICON_WORKING);
					alertMsgBox.setMessage("Successfully added new book");
					alertMsgBox.open();
				}
			}
		});
		btnAddBook.setBounds(87, 163, 75, 25);
		btnAddBook.setText("Add Book!");
		
		buttonAvail = new Button(this, SWT.CHECK);
		buttonAvail.setBounds(111, 129, 93, 16);
		
		lblPageCount = new Label(this, SWT.NONE);
		lblPageCount.setBounds(26, 92, 79, 15);
		lblPageCount.setText("Page Count:");
		
		textPageCount = new Text(this, SWT.BORDER);
		textPageCount.setBounds(111, 89, 128, 21);
	}

	@Override
	public void reveal() {
		lblBookTitle.setVisible(true);
		lblAvailability.setVisible(true);
		txtBookTitle.setVisible(true);
		lblAddANew.setVisible(true);
		btnAddBook.setVisible(true);
		buttonAvail.setVisible(true);
		lblPageCount.setVisible(true);
		textPageCount.setVisible(true);
	}
	private void addBookToXML(){
		// instance of a DocumentBuilderFactory
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		/* parse existing file to DOM */
		Document document;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(new File("src\\xmlresources\\books.xml"));
			int newIsbnNumber = document.getElementsByTagName("book").getLength();
			Element root = document.getDocumentElement();
			
			
			Element newBook = document.createElement("book");
			newBook.setAttribute("isbn",Integer.toString(newIsbnNumber+1));
			
			Element bookTitle = document.createElement("title");
			bookTitle.appendChild(document.createTextNode(txtBookTitle.getText()));
			newBook.appendChild(bookTitle);
			
			Element bookPages = document.createElement("pages");
			bookPages.appendChild(document.createTextNode(textPageCount.getText()));
			newBook.appendChild(bookPages);
			
			Element bookAvailability = document.createElement("availability");
			bookAvailability.appendChild(document.createTextNode(String.valueOf(buttonAvail.getSelection())));
			newBook.appendChild(bookAvailability);
			
			Element bookBorrower = document.createElement("borrowerID");
			newBook.appendChild(bookBorrower);
			
			root.appendChild(newBook);
			
			DOMSource source = new DOMSource(document);
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        StreamResult result = new StreamResult("src\\xmlresources\\books.xml");
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	        transformer.transform(source, result);
		} catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		} 
		
	}

}
