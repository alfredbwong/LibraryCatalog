package libcatalog.gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CheckAvailGui {

	protected Shell shell;
	private Text txtBooktitle;
	private Text txtBookauthor;
	private Button btnSearch;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CheckAvailGui window = new CheckAvailGui();
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
		shell.setText("CheckAvailGui");
		shell.setLayout(new GridLayout(2, false));
		new Label(shell, SWT.NONE);
		
		Label lblFindABook = new Label(shell, SWT.NONE);
		lblFindABook.setText("FIND A BOOK");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblBookTitle = new Label(shell, SWT.NONE);
		GridData gd_lblBookTitle = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblBookTitle.widthHint = 126;
		lblBookTitle.setLayoutData(gd_lblBookTitle);
		lblBookTitle.setText("Book Title");
		
		txtBooktitle = new Text(shell, SWT.BORDER);
		GridData gd_txtBooktitle = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtBooktitle.widthHint = 157;
		txtBooktitle.setLayoutData(gd_txtBooktitle);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblAuthor = new Label(shell, SWT.NONE);
		lblAuthor.setText("Author");
		
		txtBookauthor = new Text(shell, SWT.BORDER);
		txtBookauthor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		btnSearch = new Button(shell, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
			}
		});
		btnSearch.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnSearch.setText("Search");

	}

}
