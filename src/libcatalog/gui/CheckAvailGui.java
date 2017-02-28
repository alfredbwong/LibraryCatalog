package libcatalog.gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import java.io.File;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import libcatalog.entities.Book;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CheckAvailGui {

	protected Shell shell;
	private Text txtBookTitle;
	private Text txtBookauthor;
	private Button btnSearch;
	private Label lblResult;
	private Button btnReturnToMain;
	private Label lblSearchresult;

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
		shell.setSize(528, 282);
		shell.setText("CheckAvailGui");
		shell.setLayout(new GridLayout(2, false));
		new Label(shell, SWT.NONE);
		
		Label lblFindABook = new Label(shell, SWT.NONE);
		lblFindABook.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
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
		
		txtBookTitle = new Text(shell, SWT.BORDER);
		GridData gd_txtBookTitle = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtBookTitle.widthHint = 157;
		txtBookTitle.setLayoutData(gd_txtBookTitle);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblAuthor = new Label(shell, SWT.NONE);
		lblAuthor.setText("Author");
		
		txtBookauthor = new Text(shell, SWT.BORDER);
		txtBookauthor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);
		
		btnSearch = new Button(shell, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean hasFoundBook = false;
				LinkedList<Book> bookCollection = readInDateFromBooksXML();
				for (Book b : bookCollection){
					if (b.getTitle().equals(txtBookTitle.getText())){
						hasFoundBook = true;
						lblSearchresult.setText("Found a book with title: " + txtBookTitle.getText());
					}
				}
				if (!hasFoundBook){
					lblSearchresult.setText("Did not find a book with title: " + txtBookTitle.getText());
				}
			}
		});
		btnSearch.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnSearch.setText("Search");
		
		lblResult = new Label(shell, SWT.NONE);
		lblResult.setText("Result:");
		
		lblSearchresult = new Label(shell, SWT.NONE);
		GridData gd_lblSearchresult = new GridData(SWT.CENTER, SWT.CENTER, false, true, 1, 1);
		gd_lblSearchresult.widthHint = 233;
		lblSearchresult.setLayoutData(gd_lblSearchresult);
		new Label(shell, SWT.NONE);
		
		btnReturnToMain = new Button(shell, SWT.NONE);
		btnReturnToMain.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
				MainGui mainGui = new MainGui();
				mainGui.open();
			}
		});
		btnReturnToMain.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnReturnToMain.setText("Return to Main Page");
		

	}
	private static LinkedList<Book> readInDateFromBooksXML() {
		LinkedList<Book> tmpBookList = new LinkedList<Book>();

		try{
			File inputFile = new File("src\\xmlresources\\books.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("book");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				int isbn = Integer.parseInt(eElement.getAttribute("isbn"));
				int pages = Integer.parseInt(eElement.getElementsByTagName("pages").item(0).getTextContent());
				String title = eElement.getElementsByTagName("title").item(0).getTextContent();
				String availStr = eElement.getElementsByTagName("availability").item(0).getTextContent();
				boolean availability = availStr.equals("true") ? true : false;
				Book b = new Book (title, pages, isbn, availability);
				tmpBookList.add(b);
			}
		}catch (Exception e){
			System.out.println("Failed to Parse XML");
			System.out.println(e);
		}
		return tmpBookList;
	}
}
