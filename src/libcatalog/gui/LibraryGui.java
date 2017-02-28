package libcatalog.gui;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

public class LibraryGui {

	protected Shell shell;
	private Text usernameText;
	private Text passwordText;
	private Label lblLibraryLogo;
	
	private String usernameInput;
	private String passwordInput;
	
	private boolean isValidLogin = false;


	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LibraryGui window = new LibraryGui();
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
			if (isValidLogin){
			
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(546, 447);
		shell.setText("SWT Application");
		
		Label lblUsername = new Label(shell, SWT.NONE);
		lblUsername.setBounds(86, 288, 65, 15);
		lblUsername.setText("Username :");
		
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setBounds(86, 320, 55, 15);
		lblPassword.setText("Password:");
		
		usernameText = new Text(shell, SWT.BORDER);
		usernameText.setBounds(187, 285, 200, 21);
		
		passwordText = new Text(shell, SWT.BORDER);
		passwordText.setBounds(187, 317, 200, 21);
		
		lblLibraryLogo = new Label(shell, SWT.NONE);
		lblLibraryLogo.setImage(SWTResourceManager.getImage(LibraryGui.class, "/imgs/librarylogo.jpeg"));
		lblLibraryLogo.setBounds(126, 42, 277, 194);
		
		Button btnLogin = new Button(shell, SWT.NONE);
		btnLogin.setBounds(201, 358, 75, 25);
		btnLogin.setText("Login");
		
        btnLogin.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
				usernameInput = usernameText.getText();
				passwordInput = passwordText.getText();
				
				if (usernameInput == null || usernameInput.isEmpty() || passwordInput == null || passwordInput.isEmpty()){
					String errorMsg = null;
					MessageBox msgBox = new MessageBox(shell, SWT.OK | SWT.ICON_ERROR);
					msgBox.setText("Alert");
					if (usernameInput == null || usernameInput.isEmpty()){
						errorMsg = "Please enter username.";
					}else if (passwordInput == null || passwordInput.isEmpty()){
						errorMsg = "Plese enter password.";
					}
					if (errorMsg != null){
						msgBox.setMessage(errorMsg);
						msgBox.open();
					}
				}else{
					isValidLogin = true;
					MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_WORKING);
					messageBox.setText("Info");
					messageBox.setMessage("Valid");
					messageBox.open();
					shell.close();
					MainGui mainGui = new MainGui();
					mainGui.open();
				}
            }
        });
	}
}
