//NAME:mohit hira
//student number:3086957
package application;

//imports
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMail extends Application {

//declaring text field
	TextField txtfrom, txtsubject, txtto, textemail;
//label
	Label lblfrom, lblsubject, lblto, lblstatus, lblemail;
	Label lblnames;
//text area of text
	TextArea txtmain;
//add buttons
	Button btnsend, btnok, btnquit, btnCancel;
	Button btnemail;
	HashMap<String, String> test = new HashMap<String, String>();
	String Name = "";
	ImageView img;
//list view
	ListView<String> lvnames;

	public FXMail() {
		// instantiate with 'new' keyword
		// button
		btnemail = new Button("...");
		btnsend = new Button("Send");
		btnquit = new Button("Quit");
//text field
		txtfrom = new TextField();
		txtsubject = new TextField();
		txtto = new TextField();
		// labels
		lblfrom = new Label("From");
		lblsubject = new Label("Subject");
		lblto = new Label("To");
		lblstatus = new Label("Unready");
		txtmain = new TextArea();
//image
		img = new ImageView(new Image("asset/flower.jpg"));
	}

	public void start(Stage primaryStage) throws Exception {
//set title of fx mail
		primaryStage.setTitle("FxMail");
		//set width
		primaryStage.setWidth(600);
		//set height
		primaryStage.setHeight(500);
		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(10));
		VBox Vbmain = new VBox();
		// add component to the layout
		grid.add(lblfrom, 0, 0);
		grid.add(txtfrom, 1, 0);
		grid.add(lblto, 0, 1);
		grid.add(txtto, 1, 1);
		grid.add(lblsubject, 0, 2);
		grid.add(txtsubject, 1, 2);
		
		grid.add(btnemail, 2, 2);
		grid.setStyle("-fx-background-color:lightgreen");

		Vbmain.getChildren().add(grid);
		Vbmain.getChildren().add(txtmain);
		HBox hbox = new HBox();
		hbox.getChildren().addAll(btnsend, btnquit);
		hbox.setAlignment(Pos.BASELINE_RIGHT);
		hbox.setSpacing(10);
		Vbmain.getChildren().add(hbox);
		Vbmain.setStyle("-fx-background-color:pink");
		Vbmain.getChildren().add(lblstatus);
		HBox bottom = new HBox();
		img.setFitWidth(120);
		img.setPreserveRatio(true);
		bottom.setSpacing(20);
		bottom.getChildren().addAll(lblstatus,img);
		Vbmain.getChildren().add(bottom);
		txtfrom.setMinWidth(200);
		btnsend.setStyle("-fx-background-color:orange");
		btnquit.setStyle("-fx-background-color:orange");
		// add spacing and padding
		Vbmain.setSpacing(10);
		Vbmain.setPadding(new Insets(10));
		// create scene
		Scene s = new Scene(Vbmain);
		primaryStage.setScene(s);
		// show the stage
		primaryStage.show();
	}

//event hadling
	public void init() {
//event handling of quit button
		btnquit.setOnAction(ae -> Platform.exit());
		//event handling of email button 
		btnemail.setOnAction(ae -> showdialog());

		// events send button
		//btn send if i click the send button without fill any text warning will be appear 
		//please fill all the field
		
		btnsend.setOnAction(e -> {

			if (txtfrom.getText().isEmpty() || txtsubject.getText().isEmpty() || txtto.getText().isEmpty()
					|| txtmain.getText().isEmpty()) {
				Alert alert1 = new Alert(AlertType.WARNING);
				alert1.setTitle("WARNING");
				alert1.setContentText("Please fill all the fields");
				alert1.showAndWait();
			} else {
				String to = txtto.getText();
				String from = txtfrom.getText();
				String subject = txtsubject.getText();
				String message = txtmain.getText();

				Pattern p = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
				Matcher m = p.matcher(to);
				boolean b = m.matches();
				if (b == true) {

					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmation");
					alert.setContentText("Are you sure you want to send this message?");

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK) {
						lblstatus.setText("message sent");

					} else {
						lblstatus.setText("cancelled");
					}

				} else {
					Alert alert1 = new Alert(AlertType.WARNING);
					alert1.setTitle("WARNING");
					alert1.setContentText("Please enter a valid email address");
					alert1.showAndWait();
				}

			}
		});
	}
//method to open email addrfess book
	public void showdialog() {
		Stage emailaddress = new Stage();
//set title
		emailaddress.setTitle("list of email");
		//set width and hight
		emailaddress.setWidth(600);
		emailaddress.setHeight(400);
		//button ok
		btnok = new Button("ok");
		btnCancel = new Button("cancel");
		lblnames = new Label("names");
		lblemail = new Label("email");
		//list view
		lvnames = new ListView<String>();

		textemail = new TextField();
		//set the text field
		textemail.setMinWidth(100);
		btnok.minWidth(70);
		btnCancel.setMinWidth(70);
		VBox vblist = new VBox();
		VBox vbdetail = new VBox();
		HBox hbox = new HBox();
		GridPane gpemail = new GridPane();
		gpemail.setHgap(10);
		gpemail.setVgap(10);
		gpemail.setPadding(new Insets(10));

		vblist.getChildren().addAll(lblnames, lvnames);
		vbdetail.getChildren().addAll(lblemail, textemail);
		hbox.getChildren().addAll(btnok, btnCancel);
		hbox.setAlignment(Pos.BASELINE_RIGHT);
		gpemail.add(vblist, 0, 0);
		gpemail.add(vbdetail, 1, 0);
		gpemail.add(hbox, 1, 1);
		Scene scene = new Scene(gpemail);
		emailaddress.setScene(scene);
		lvnames.getItems().sort(null);
		readtestNames("./asset/test.csv");

		lvnames.setOnMousePressed(ae -> {
			String testname = lvnames.getSelectionModel().getSelectedItem();
			Name = testname;
			textemail.setText(test.get(testname));
		});
		
		btnok.setOnAction(ae -> {
			
			String email = textemail.getText();
			if (checkValidFormat(email)) {
				if (checkValidFormat(email) == true) {
					//set email to the text field
					txtto.setText(email);
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERROR");
					alert.setContentText("invalid email address");
					alert.showAndWait();
				}

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setContentText("invalid email address");
				alert.showAndWait();
			}
			//close the dialog
			emailaddress.close();

		});
		//show the dialog
		emailaddress.show();

	
	btnCancel.setOnAction(e -> {
		// when i click the cancel button the email address dialog will be close
		emailaddress.close();
	});
	}
	//method to read test names from a file
	public void readtestNames(String testFile) {
		lvnames.getItems().clear();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("./asset/test.csv"));
			String line = "";
			// read the file line by line
			while ((line = br.readLine()) != null) {
				// get the contact name
				String name = line.substring(0, line.indexOf(","));
				String email = line.substring(line.indexOf(",") + 1);
				test.put(name, email);
				// add the contact name to the list
				lvnames.getItems().add(name);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String[] showemail(String testFile, String selectName) {
		String[] select = new String[2];
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("./asset/test.csv"));
			String line = "";
			// read the file line by line
			while ((line = br.readLine()) != null) {
				// get the contact name
				String name = line.substring(0, line.indexOf(","));
				String email = line.substring(line.indexOf(",") + 1);
				// add the contact name to the list
				if (name.equals(selectName)) {
					select[0] = name;
					select[1] = email;

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return select;
	}

		public boolean checkValidFormat(String email) {
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(email);
		return m.find();
	}

	public void writefile() {
		FileWriter fw = null;
		try {
			File f = new File("./asset/test.csv");
			fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			// add contact name and email to the file
			for (Map.Entry<String, String> entry : test.entrySet()) {
				bw.write(entry.getKey() + "," + entry.getValue());
				bw.newLine();
			}
			// close the writer
			bw.close();
			// show the list of contacts
			readtestNames("./asset/test.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		
		// launching the app
		launch(args);
	}
}
