package csci2020.lab08;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	BorderPane borderPane = new BorderPane();

	private TableView<StudentRecord> studentRecordTableView;
	private String currentFileName = "sample.csv";

	public void start(Stage primaryStage) {

		primaryStage.setTitle("Lab 08");

		TableColumn<StudentRecord, String> idColumn = new TableColumn<>("ID");
		idColumn.setPrefWidth(100);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("SID"));

		TableColumn<StudentRecord, Double> assignmentColumn = new TableColumn<>("Assignment Mark");
		assignmentColumn.setPrefWidth(100);
		assignmentColumn.setCellValueFactory(new PropertyValueFactory<>("assignmentMark"));

		TableColumn<StudentRecord, Double> midtermColumn = new TableColumn<>("Midterm Mark");
		midtermColumn.setPrefWidth(100);
		midtermColumn.setCellValueFactory(new PropertyValueFactory<>("midtermMark"));

		TableColumn<StudentRecord, Double> examColumn = new TableColumn<>("Exam Mark");
		examColumn.setPrefWidth(100);
		examColumn.setCellValueFactory(new PropertyValueFactory<>("examMark"));

		TableColumn<StudentRecord, Double> finalColumn = new TableColumn<>("Final Mark");
		finalColumn.setPrefWidth(100);
		finalColumn.setCellValueFactory(new PropertyValueFactory<>("finalMark"));

		TableColumn<StudentRecord, Character> gradeColumn = new TableColumn<>("Grade");
		gradeColumn.setPrefWidth(100);
		gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

		this.studentRecordTableView = new TableView<>();

		this.studentRecordTableView.getColumns().add(idColumn);
		this.studentRecordTableView.getColumns().add(assignmentColumn);
		this.studentRecordTableView.getColumns().add(midtermColumn);
		this.studentRecordTableView.getColumns().add(examColumn);
		this.studentRecordTableView.getColumns().add(finalColumn);
		this.studentRecordTableView.getColumns().add(gradeColumn);

		borderPane.setCenter(this.studentRecordTableView);
		
		Menu fileMenu = new Menu("File");
		
		MenuItem menuItm1 = new MenuItem("New");
		menuItm1.setOnAction(e -> {
			DataSource.clearAllMarks();
		});
		MenuItem menuItm2 = new MenuItem("Open");
		menuItm2.setOnAction(e -> {
			// load();
		});
		MenuItem menuItm3 = new MenuItem("Save");
		menuItm3.setOnAction(e -> {
			save();
		});
		MenuItem menuItm4 = new MenuItem("Save As");
		MenuItem menuItm5 = new MenuItem("Exit");
		
		fileMenu.getItems().addAll(menuItm1, menuItm2, menuItm3, menuItm4, menuItm5);
		
		MenuBar mainBar = new MenuBar(fileMenu);
		
		borderPane.setTop(mainBar);
		
		GridPane txtFields = new GridPane();
		txtFields.setPadding(new Insets(10, 10, 10, 10));
		txtFields.setHgap(10);
		txtFields.setVgap(8);
		
		Label txt1 = new Label("SID:");
		Label txt2 = new Label("Assignments:");
		Label txt3 = new Label("Midterm:");
		Label txt4 = new Label("Final Exam:");
		
		TextField txtfd1 = new TextField();
		txtfd1.setPromptText("SID");
		
		TextField txtfd2 = new TextField();
		txtfd2.setPromptText("Assignments");
		
		TextField txtfd3 = new TextField();
		txtfd3.setPromptText("Midterm");
		
		TextField txtfd4 = new TextField();
		txtfd4.setPromptText("Final Exam");
		
		Button btnAdd = new Button("Add");
		btnAdd.setOnMousePressed(e -> {
			DataSource.addRecord(new StudentRecord(txtfd1.getText(), Double.valueOf(txtfd2.getText()), Double.valueOf(txtfd3.getText()), Double.valueOf(txtfd4.getText())));
		});
		
		txtFields.add(txt1, 0, 0);
		txtFields.add(txtfd1, 1, 0);
		txtFields.add(txt2, 2, 0);
		txtFields.add(txtfd2, 3, 0);
		txtFields.add(txt3, 0, 1);
		txtFields.add(txtfd3, 1, 1);
		txtFields.add(txt4, 2, 1);
		txtFields.add(txtfd4, 3, 1);
		txtFields.add(btnAdd, 1, 4);
		
		borderPane.setBottom(txtFields);

		Scene scene = new Scene(borderPane);
		primaryStage.setScene(scene);
		primaryStage.show();

		this.studentRecordTableView.setItems(DataSource.setAllMarks());

	}
	
	public void save() {
		File f = new File(currentFileName);
		ObservableList<StudentRecord> l = DataSource.getAllMarks();
		try {
			
			PrintWriter p = new PrintWriter(f);
			
			for(int i = 0;i < l.size();i++) {
				p.println(l.get(i).getSID() + ',' + l.get(i).getAssignmentMark() + ',' + l.get(i).getMidtermMark() + ',' + l.get(i).getExamMark());
			}
			
			p.flush();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void load() {
		
		DataSource.clearAllMarks();
		
		try {
			Scanner in = new Scanner(new File(currentFileName));
			
			while(in.hasNextLine()) {
				String[] str = in.nextLine().split(",");
				DataSource.addRecord(new StudentRecord(str[0], Double.valueOf(str[1]), Double.valueOf(str[2]), Double.valueOf(str[3])));
			}
			
			in.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}