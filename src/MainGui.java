import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FillLayout;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.custom.StackLayout;
import swing2swt.layout.BoxLayout;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.layout.RowLayout;

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
		shell.setLayout(new GridLayout(1, false));
		
		Button btnCheckOutBook = new Button(shell, SWT.NONE);
		btnCheckOutBook.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		btnCheckOutBook.setText("Check Out Book");
		
		Button btnAddNewCustomer = new Button(shell, SWT.NONE);
		btnAddNewCustomer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		btnAddNewCustomer.setText("Add New Customer");
		
		Button btnAddNewBook = new Button(shell, SWT.NONE);
		btnAddNewBook.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		btnAddNewBook.setText("Add New Book");
		
		Button btnReturnToLogin = new Button(shell, SWT.NONE);
		btnReturnToLogin.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		btnReturnToLogin.setText("Return to Login Page");
		
		btnReturnToLogin.addListener(SWT.Selection, new Listener(){
            public void handleEvent(Event event) {
            	shell.close();
            	LibraryGui libGui = new LibraryGui();
            	libGui.open();
            }
		});

	}

}
