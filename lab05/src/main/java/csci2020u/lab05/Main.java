package csci2020.lab05;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	BorderPane borderPane = new BorderPane();

	private TableView<StudentRecord> studentRecordTableView;

	public void start(Stage primaryStage) {

		primaryStage.setTitle("Lab 05");

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

		Scene scene = new Scene(borderPane, 800, 300);
		primaryStage.setScene(scene);
		primaryStage.show();

		this.studentRecordTableView.setItems(DataSource.getAllMarks());

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}