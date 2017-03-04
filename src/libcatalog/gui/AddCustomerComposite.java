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

public class AddCustomerComposite extends Composite{
	private Text textCustomerName;
	private Text textAddress;
	private Label lblAddNewCustomer;
	private Label lblCustomerName;
	private Label lblAddressLabel;
	private Button btnAddNewCustomer;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AddCustomerComposite(Composite parent, int style) {
		super(parent, style);
		this.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		this.setBounds(148, 5, 276, 251);
		lblAddNewCustomer = new Label(this, SWT.NONE);
		lblAddNewCustomer.setVisible(true);
		lblAddNewCustomer.setBounds(53, 55, 130, 15);
		lblAddNewCustomer.setText("Add a New Customer");

		lblCustomerName = new Label(this, SWT.NONE);
		lblCustomerName.setVisible(true);
		lblCustomerName.setBounds(10, 83, 95, 15);
		lblCustomerName.setText("Customer Name:");

		textCustomerName = new Text(this, SWT.BORDER);
		textCustomerName.setVisible(true);
		textCustomerName.setBounds(111, 80, 153, 21);

		lblAddressLabel = new Label(this, SWT.NONE);
		lblAddressLabel.setVisible(true);
		lblAddressLabel.setBounds(10, 120, 95, 15);
		lblAddressLabel.setText("Address:");

		textAddress = new Text(this, SWT.BORDER);
		textAddress.setVisible(true);
		textAddress.setBounds(111, 114, 153, 21);

		btnAddNewCustomer = new Button(this, SWT.NONE);
		btnAddNewCustomer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (textCustomerName.getText().isEmpty() || textCustomerName.getText().isEmpty() ||
						textAddress.getText().isEmpty() || textAddress.getText().isEmpty()){
					MessageBox alertMsgBox = new MessageBox(new Shell(), SWT.OK | SWT.ICON_ERROR);
					alertMsgBox.setMessage("Cannot have empty fields");
					alertMsgBox.open();
				}else{
					addBookToXML();
					MessageBox alertMsgBox = new MessageBox(new Shell(), SWT.OK | SWT.ICON_WORKING);
					alertMsgBox.setMessage("Successfully added new customer");
					alertMsgBox.open();
				}
			}
		});
		btnAddNewCustomer.setVisible(true);
		btnAddNewCustomer.setBounds(53, 157, 130, 25);
		btnAddNewCustomer.setText("Add New Customer!");			
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
			document = documentBuilder.parse(new File("src\\xmlresources\\customer.xml"));
			int newCustomerNumber = document.getElementsByTagName("customer").getLength();
			Element root = document.getDocumentElement();


			Element newCustomer = document.createElement("customer");
			newCustomer.setAttribute("id",Integer.toString(newCustomerNumber+1));

			Element customerName = document.createElement("name");
			customerName.appendChild(document.createTextNode(textCustomerName.getText()));
			newCustomer.appendChild(customerName);

			Element customerAddr = document.createElement("address");
			customerAddr.appendChild(document.createTextNode(textAddress.getText()));
			newCustomer.appendChild(customerAddr);

			root.appendChild(newCustomer);

			DOMSource source = new DOMSource(document);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			StreamResult result = new StreamResult("src\\xmlresources\\customer.xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(source, result);
		} catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		} 

	}
}
