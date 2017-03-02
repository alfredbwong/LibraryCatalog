package libcatalog.gui;

import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

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
	private LinkedList<Book> listOfBooks;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @param bookList 
	 */
	public CheckOutComposite(Composite parent, int style, LinkedList<Book> listOfBooks) {
		super(parent, style);
		this.listOfBooks = listOfBooks;
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
		textCustomerInput.setVisible(false);
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
				
			}
		});
		btnCheckOutBook.setBounds(47, 103, 128, 25);
		btnCheckOutBook.setText("Check Out Book");

	}
}
