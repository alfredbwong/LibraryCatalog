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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddBookComposite extends Composite {
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
				setLayout(null);
		
				lblAddANew = new Label(this, SWT.NONE);
				lblAddANew.setBounds(84, 10, 88, 15);
				lblAddANew.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				lblAddANew.setText("Add a New Book");
												lblBookTitle = new Label(this, SWT.NONE);
												lblBookTitle.setBounds(23, 48, 64, 15);
												lblBookTitle.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
												lblBookTitle.setText("Book Title:");
												
														txtBookTitle = new Text(this, SWT.BORDER);
														txtBookTitle.setBounds(117, 45, 136, 21);
								
										lblPageCount = new Label(this, SWT.NONE);
										lblPageCount.setBounds(23, 74, 70, 15);
										lblPageCount.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
										lblPageCount.setText("Page Count:");
								
										textPageCount = new Text(this, SWT.BORDER);
										textPageCount.setBounds(117, 71, 136, 21);
				
						lblAvailability = new Label(this, SWT.NONE);
						lblAvailability.setBounds(23, 97, 70, 15);
						lblAvailability.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						lblAvailability.setText("Availability:");
				
						buttonAvail = new Button(this, SWT.CHECK);
						buttonAvail.setBounds(180, 98, 13, 16);
						buttonAvail.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
						buttonAvail.setSelection(true);
		
				btnAddBook = new Button(this, SWT.NONE);
				btnAddBook.setBounds(52, 131, 170, 25);
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
				btnAddBook.setText("Add Book!");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
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
			
			//Find next biggest isbn number
			int newIsbnNumber = 0;
			NodeList bookNodes = document.getElementsByTagName("book");
			for (int j = 0; j < bookNodes.getLength(); j ++){
				int iterBookIsbnNum =  Integer.parseInt(bookNodes.item(j).getAttributes().item(0).getNodeValue());
				newIsbnNumber = iterBookIsbnNum > newIsbnNumber ? iterBookIsbnNum : newIsbnNumber;
			}
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
