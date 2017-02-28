package libcatalog.gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;

public class AddBookGui {

	protected Shell shell;
	private Label lblBookTitle;
	private Label lblIsbn;
	private Label lblNumberOfPages;
	private Label lblAvailability;
	private Text txtBookTitle;
	private Text txtIsbn;
	private Text txtNumberOfPages;
	private Text txtAvailablity;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AddBookGui window = new AddBookGui();
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
		shell.setText("AddBookGui");
		shell.setLayout(new GridLayout(2, false));
		
		lblBookTitle = new Label(shell, SWT.NONE);
		lblBookTitle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBookTitle.setText("Book Title");
		
		txtBookTitle = new Text(shell, SWT.BORDER);
		txtBookTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblIsbn = new Label(shell, SWT.NONE);
		lblIsbn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblIsbn.setText("ISBN");
		
		txtIsbn = new Text(shell, SWT.BORDER);
		txtIsbn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblNumberOfPages = new Label(shell, SWT.NONE);
		lblNumberOfPages.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNumberOfPages.setText("Number of Pages");
		
		txtNumberOfPages = new Text(shell, SWT.BORDER);
		txtNumberOfPages.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblAvailability = new Label(shell, SWT.NONE);
		lblAvailability.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAvailability.setText("Availability");
		
		txtAvailablity = new Text(shell, SWT.BORDER);
		txtAvailablity.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

	}
}
