package csci2020.lab10;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Client extends Application {
	
	DataOutputStream out;
	
	Socket socket = null;
	
	TextField usernameTxtfd;
	TextField msgTxtfd;

	public void start(Stage primaryStage) {
		
		VBox pane = new VBox(10);
		pane.setPadding(new Insets(25, 25, 25, 25));
		
		HBox usernameHBox = new HBox(20);
		HBox msgHBox = new HBox(20);
		
		Text usernameTxt = new Text("Username:");
		usernameTxtfd = new TextField();
		
		Text msgTxt = new Text("Message:");
		msgTxtfd = new TextField();
		
		usernameHBox.getChildren().addAll(usernameTxt, usernameTxtfd);
		msgHBox.getChildren().addAll(msgTxt, msgTxtfd);
		
		Button sendBt = new Button("Send");
		sendBt.setOnAction(e -> {
			try {
				
				socket = new Socket("localhost", 800);
				
				out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF(usernameTxtfd.getText() + ": " + msgTxtfd.getText());
				out.flush();
				
				socket.close();
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		
		Button exitBt = new Button("Exit");
		exitBt.setOnAction(e -> {
			try {
				if(socket != null && !socket.isClosed())
					socket.close();
				primaryStage.close();
				System.exit(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		pane.getChildren().addAll(usernameHBox, msgHBox, sendBt, exitBt);
		
		Scene s = new Scene(pane);
		
		primaryStage.setTitle("Lab 10, Client");
		primaryStage.setScene(s);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
