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
		
		CheckAvailComposite checkAvailComp = new CheckAvailComposite(shell, SWT.NONE);
				
		// Main buttons
		Button btnCheckOutBook = new Button(shell, SWT.NONE);
		btnCheckOutBook.setBounds(5, 5, 134, 46);
		btnCheckOutBook.setText("Check Out Book");
		btnCheckOutBook.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Check out book");
			}
		});
		
		Button btnAddNewCustomer = new Button(shell, SWT.NONE);
		btnAddNewCustomer.setBounds(5, 56, 134, 46);
		btnAddNewCustomer.setText("Add New Customer");
		
		Button btnAddNewBook = new Button(shell, SWT.NONE);
		btnAddNewBook.setBounds(5, 107, 134, 46);
		btnAddNewBook.setText("Add New Book");
		
		Button btnReturnToLogin = new Button(shell, SWT.NONE);
		btnReturnToLogin.setBounds(5, 158, 134, 46);
		btnReturnToLogin.setText("Return to Login Page");
		
		Button btnCheckAvail = new Button(shell, SWT.NONE);
		btnCheckAvail.setBounds(5, 209, 134, 47);
		btnCheckAvail.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
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
