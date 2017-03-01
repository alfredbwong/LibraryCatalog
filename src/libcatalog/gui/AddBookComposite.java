package libcatalog.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class AddBookComposite extends Composite implements MainCompInterface {
	private Text txtBookTitle;
	private Text txtBookIsbn;
	private Label lblBookTitle;
	private Label lblIsbn;
	private Label lblAvailability;
	private Label lblAddANew;
	private Button btnAddBook;
	private Button button;
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
		
		lblIsbn = new Label(this, SWT.NONE);
		lblIsbn.setBounds(26, 93, 55, 15);
		lblIsbn.setText("ISBN:");
		
		lblAvailability = new Label(this, SWT.NONE);
		lblAvailability.setBounds(26, 158, 74, 15);
		lblAvailability.setText("Availability:");
		
		txtBookTitle = new Text(this, SWT.BORDER);
		txtBookTitle.setText("Book Title");
		txtBookTitle.setBounds(111, 51, 128, 21);
		
		txtBookIsbn = new Text(this, SWT.BORDER);
		txtBookIsbn.setText("Book ISBN");
		txtBookIsbn.setBounds(111, 90, 128, 21);
		
		lblAddANew = new Label(this, SWT.NONE);
		lblAddANew.setBounds(87, 30, 95, 15);
		lblAddANew.setText("Add a New Book");
		
		btnAddBook = new Button(this, SWT.NONE);
		btnAddBook.setBounds(87, 192, 75, 25);
		btnAddBook.setText("Add Book!");
		
		button = new Button(this, SWT.CHECK);
		button.setBounds(119, 157, 93, 16);
		
		lblPageCount = new Label(this, SWT.NONE);
		lblPageCount.setBounds(26, 126, 79, 15);
		lblPageCount.setText("Page Count:");
		
		textPageCount = new Text(this, SWT.BORDER);
		textPageCount.setBounds(111, 123, 128, 21);
	}

	@Override
	public void reveal() {
		lblBookTitle.setVisible(true);
		lblIsbn.setVisible(true);
		lblAvailability.setVisible(true);
		txtBookTitle.setVisible(true);
		txtBookIsbn.setVisible(true);
		lblAddANew.setVisible(true);
		btnAddBook.setVisible(true);
		button.setVisible(true);
		lblPageCount.setVisible(true);
		textPageCount.setVisible(true);
	}

}
