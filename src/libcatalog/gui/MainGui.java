package libcatalog.gui;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import libcatalog.entities.Book;

import org.eclipse.swt.widgets.Listener;

import java.io.File;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

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
		shell.setLayout(new GridLayout(2, false));
		
		Button btnCheckOutBook = new Button(shell, SWT.NONE);
		btnCheckOutBook.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		btnCheckOutBook.setText("Check Out Book");
		btnCheckOutBook.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Check out book");
			}
		});
		
		Button btnCheckAvail = new Button(shell, SWT.NONE);
		btnCheckAvail.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CheckAvailGui checkAvailGui = new CheckAvailGui();
				checkAvailGui.open();
			}
		});
		btnCheckAvail.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		btnCheckAvail.setText("Check book availability.");
		
		Button btnAddNewCustomer = new Button(shell, SWT.NONE);
		btnAddNewCustomer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		btnAddNewCustomer.setText("Add New Customer");
		new Label(shell, SWT.NONE);
		
		Button btnAddNewBook = new Button(shell, SWT.NONE);
		btnAddNewBook.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		btnAddNewBook.setText("Add New Book");
		new Label(shell, SWT.NONE);
		
		Button btnReturnToLogin = new Button(shell, SWT.NONE);
		btnReturnToLogin.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		btnReturnToLogin.setText("Return to Login Page");
		new Label(shell, SWT.NONE);
		
		btnReturnToLogin.addListener(SWT.Selection, new Listener(){
            public void handleEvent(Event event) {
            	shell.close();
            	LoginGui libGui = new LoginGui();
            	libGui.open();
            }
		});

	}

}
