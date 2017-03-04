package libcatalog.gui;

import java.io.File;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import libcatalog.entities.Book;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CheckOutComposite extends Composite implements MainCompInterface {
	private Text textCustomerInput;
	private Text textBookInput;
	private Label lblCheckOutBook;
	private Label lblCustomerId;
	private Label lblBookId;
	private Button btnCheckOutBook;
	final int REF_NUM_OF_BORROWER = 8;
	final String BOOK_XMLPATH = "src\\xmlresources\\books.xml";


	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @param bookList 
	 */
	public CheckOutComposite(Composite parent, int style, LinkedList<Book> listOfBooks) {
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
		lblCheckOutBook = new Label(this, SWT.NONE);
		lblCheckOutBook.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblCheckOutBook.setBounds(69, 10, 102, 15);
		lblCheckOutBook.setText("Check Out a Book");

		lblCustomerId = new Label(this, SWT.NONE);
		lblCustomerId.setBounds(10, 34, 82, 15);
		lblCustomerId.setText("Customer ID:");

		textCustomerInput = new Text(this, SWT.BORDER);
		textCustomerInput.setBounds(107, 31, 117, 21);

		lblBookId = new Label(this, SWT.NONE);
		lblBookId.setBounds(10, 69, 55, 15);
		lblBookId.setText("Book ID:");

		textBookInput = new Text(this, SWT.BORDER);
		textBookInput.setBounds(107, 63, 117, 21);

		btnCheckOutBook = new Button(this, SWT.NONE);
		btnCheckOutBook.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				writeCustomerToBook();				
			}
		});
		btnCheckOutBook.setBounds(47, 103, 128, 25);
		btnCheckOutBook.setText("Check Out Book");

	}

	protected void writeCustomerToBook() {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		/* parse existing file to DOM */ 
		Document document;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(new File(BOOK_XMLPATH));
			Node bookToCheckOut = document.getElementsByTagName("book").item(Integer.parseInt(textBookInput.getText())-1);
			Node borrowerAttr = bookToCheckOut.getChildNodes().item(REF_NUM_OF_BORROWER);
			borrowerAttr.setNodeValue(textCustomerInput.getText());
			// Write to XML file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(BOOK_XMLPATH));
			transformer.transform(source, result);

		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}
}
