package libcatalog.gui;

import java.io.File;
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
import org.w3c.dom.NodeList;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CheckInComposite extends Composite {
	private Text textBookInput;
	private Label lblCheckInBook;
	private Label lblBookId;
	private Button btnCheckInBook;
	private final String BOOK_XMLPATH = "src\\xmlresources\\books.xml";


	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @param bookList 
	 */
	public CheckInComposite(Composite parent, int style) {
		super(parent, style);
		this.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		this.setBounds(148, 5, 276, 251);
		lblCheckInBook = new Label(this, SWT.NONE);
		lblCheckInBook.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblCheckInBook.setBounds(69, 10, 102, 15);
		lblCheckInBook.setText("Check In a Book");

		lblBookId = new Label(this, SWT.NONE);
		lblBookId.setBounds(10, 47, 55, 15);
		lblBookId.setText("Book ID:");

		textBookInput = new Text(this, SWT.BORDER);
		textBookInput.setBounds(111, 44, 117, 21);

		btnCheckInBook = new Button(this, SWT.NONE);
		btnCheckInBook.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkInBook();				
			}
		});
		btnCheckInBook.setBounds(43, 92, 128, 25);
		btnCheckInBook.setText("Check In Book");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	protected void checkInBook() {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		/* parse existing file to DOM */ 
		Document document;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(new File(BOOK_XMLPATH));
			NodeList bookNodes = document.getElementsByTagName("book");
			Node bookToCheckIn = null;
			for (int j = 0; j < bookNodes.getLength(); j ++){
				String isbnNumber = bookNodes.item(j).getAttributes().item(0).getNodeValue();
				if (isbnNumber.equals(textBookInput.getText())){
					bookToCheckIn = bookNodes.item(j);
				}
			}
			if (bookToCheckIn == null){
				return;
			}

			NodeList list = bookToCheckIn.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
//				System.out.println(i + " : " + node.getNodeName());
				if ("availability".equals(node.getNodeName())) {
					node.setTextContent("true");
				}
				if ("borrowerID".equals(node.getNodeName())){
					node.setTextContent(null);
				}
			}
//			System.out.println("wasContentUpdated: " + wasContentUpdated);
//			// Write to XML file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(BOOK_XMLPATH));
			transformer.transform(source, result);

		}catch (Exception e) {
			System.out.println("Failed to check in book.");
			e.printStackTrace();
		}

	}
}
