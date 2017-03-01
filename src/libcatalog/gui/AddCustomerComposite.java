package libcatalog.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class AddCustomerComposite extends Composite implements MainCompInterface{
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
		this.setVisible(true);
		
		setupWidgets();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	@Override
	public void setupWidgets() {
		lblAddNewCustomer = new Label(this, SWT.NONE);
		lblAddNewCustomer.setVisible(false);
		lblAddNewCustomer.setBounds(53, 55, 130, 15);
		lblAddNewCustomer.setText("Add a New Customer");
		
		lblCustomerName = new Label(this, SWT.NONE);
		lblCustomerName.setVisible(false);
		lblCustomerName.setBounds(10, 83, 95, 15);
		lblCustomerName.setText("Customer Name:");
		
		textCustomerName = new Text(this, SWT.BORDER);
		textCustomerName.setVisible(false);
		textCustomerName.setBounds(111, 80, 153, 21);
		
		lblAddressLabel = new Label(this, SWT.NONE);
		lblAddressLabel.setVisible(false);
		lblAddressLabel.setBounds(10, 120, 95, 15);
		lblAddressLabel.setText("Address:");
		
		textAddress = new Text(this, SWT.BORDER);
		textAddress.setVisible(false);
		textAddress.setBounds(111, 114, 153, 21);
		
		btnAddNewCustomer = new Button(this, SWT.NONE);
		btnAddNewCustomer.setVisible(false);
		btnAddNewCustomer.setBounds(53, 157, 130, 25);
		btnAddNewCustomer.setText("Add New Customer!");		
	}

	@Override
	public void reveal() {
		lblAddNewCustomer.setVisible(true);
		lblCustomerName.setVisible(true);
		textCustomerName.setVisible(true);
		lblAddressLabel.setVisible(true);
		textAddress.setVisible(true);
		btnAddNewCustomer.setVisible(true);

	}

}
