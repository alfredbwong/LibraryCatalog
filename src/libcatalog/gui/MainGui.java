package libcatalog.gui;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainGui {

	protected Shell shell;
	protected CheckAvailComposite checkAvailComp;
	protected AddBookComposite addBookComp;
	protected AddCustomerComposite addCustComp;
	protected CheckOutComposite checkOutComp;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainGui window = new MainGui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Main Gui");
		shell.setLayout(null);
		
				
		// Main buttons
		Button btnCheckOutBook = new Button(shell, SWT.NONE);
		btnCheckOutBook.setBounds(5, 5, 134, 46);
		btnCheckOutBook.setText("Check Out Book");
		btnCheckOutBook.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (addBookComp != null){
					addBookComp.dispose();
				}
				if (checkAvailComp != null){
					checkAvailComp.dispose();
				}
				if (addCustComp != null){
					addCustComp.dispose();
				}
				checkOutComp = new CheckOutComposite(shell, SWT.NONE);
				checkOutComp.reveal();
			}
		});
		
		Button btnAddNewCustomer = new Button(shell, SWT.NONE);
		btnAddNewCustomer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (addBookComp != null){
					addBookComp.dispose();
				}
				if (checkAvailComp != null){
					checkAvailComp.dispose();
				}
				if (checkOutComp != null){
					checkOutComp.dispose();
				}
				addCustComp = new AddCustomerComposite(shell, SWT.NONE);
				addCustComp.reveal();
			}
		});
		btnAddNewCustomer.setBounds(5, 56, 134, 46);
		btnAddNewCustomer.setText("Add New Customer");
		
		Button btnAddNewBook = new Button(shell, SWT.NONE);
		btnAddNewBook.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (checkAvailComp != null){
					checkAvailComp.dispose();
				}
				if (addCustComp != null){
					addCustComp.dispose();
				}
				if (checkOutComp != null){
					checkOutComp.dispose();
				}
				addBookComp = new AddBookComposite(shell, SWT.NONE);
				addBookComp.reveal();
			}
		});
		btnAddNewBook.setBounds(5, 107, 134, 46);
		btnAddNewBook.setText("Add New Book");
		
		Button btnReturnToLogin = new Button(shell, SWT.NONE);
		btnReturnToLogin.setBounds(5, 212, 134, 46);
		btnReturnToLogin.setText("Return to Login Page");
		
		Button btnCheckAvail = new Button(shell, SWT.NONE);
		btnCheckAvail.setBounds(5, 159, 134, 47);
		btnCheckAvail.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (addBookComp != null){
					addBookComp.dispose();
				}
				if (addCustComp != null){
					addCustComp.dispose();
				}
				if (checkOutComp != null){
					checkOutComp.dispose();
				}
				checkAvailComp = new CheckAvailComposite(shell, SWT.NONE);
				checkAvailComp.reveal();
			}
		});
		btnCheckAvail.setText("Check book availability");
		
		
		
		
		btnReturnToLogin.addListener(SWT.Selection, new Listener(){
            public void handleEvent(Event event) {
            	shell.close();
            	LoginGui libGui = new LoginGui();
            	libGui.open();
            }
		});

	}
}
