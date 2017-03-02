package libcatalog.gui;

import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import libcatalog.entities.Book;

public class CheckAvailComposite extends Composite implements MainCompInterface {
	private Text txtBookTitle;
	private Label lblCheckBookAvailability;
	private Label lblBookTitle;
	private Label lblSearchResult;
	private Button btnFindBook;
	private Label lblReferToBookXML;
	private LinkedList<Book> listOfBooks;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CheckAvailComposite(Composite parent, int style, LinkedList<Book> listOfBooks) {
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

	public void setupWidgets() {
		//Check Book Availability Widgets
		lblCheckBookAvailability = new Label(this, SWT.NONE);
		lblCheckBookAvailability.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblCheckBookAvailability.setBounds(82, 10, 134, 15);
		lblCheckBookAvailability.setText("Check Book Availability");

		lblBookTitle = new Label(this, SWT.NONE);
		lblBookTitle.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblBookTitle.setBounds(10, 57, 74, 15);
		lblBookTitle.setText("Book Title :");

		txtBookTitle = new Text(this, SWT.BORDER);
		txtBookTitle.setBounds(90, 54, 176, 21);

		lblSearchResult = new Label(this, SWT.NONE);
		lblSearchResult.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblSearchResult.setAlignment(SWT.CENTER);
		lblSearchResult.setBounds(10, 132, 256, 37);

		btnFindBook = new Button(this, SWT.NONE);
		btnFindBook.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean hasFoundBook = false;
				for (Book b : listOfBooks){
					if (b.getTitle().equals(txtBookTitle.getText())){
						hasFoundBook = true;
						if (b.isAvailable()){
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
		
		lblReferToBookXML = new Label(this, SWT.NONE);
		lblReferToBookXML.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblReferToBookXML.setBounds(23, 31, 231, 15);
		lblReferToBookXML.setText("Refer to xmlresources/books.xml for books");
	}

}
