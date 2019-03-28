package csci2020.lab10;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Server extends Application {
	
	TextArea boardTxtArea;
	
	ServerSocket serverSocket = null;

	public void start(Stage primaryStage) {
		
		Thread serverThread = new Thread(() ->  {
			
			try {
				serverSocket = new ServerSocket(800);
				
				while(true) {
					Socket socket = serverSocket.accept();
					new Thread(new HandleClient(socket)).start();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		serverThread.start();
		
		
		VBox pane = new VBox(10);
		pane.setPadding(new Insets(25, 25, 25, 25));
		
		boardTxtArea = new TextArea();
		
		Button exitBt = new Button("Exit");
		exitBt.setOnAction(e -> {
			try {
				serverThread.interrupt();
				if(serverSocket != null && !serverSocket.isClosed())
					serverSocket.close();
				primaryStage.close();
				System.exit(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		pane.getChildren().addAll(boardTxtArea, exitBt);
		
		Scene s = new Scene(pane, 400, 300);
		
		primaryStage.setTitle("Lab 10, Server");
		primaryStage.setScene(s);
		primaryStage.show();
		
	}
	
	class HandleClient implements Runnable {
		
		private Socket socket;
		
		public HandleClient(Socket s) {
			this.socket = s;
		}
		
		public void run() {
			
			try {
				
				DataInputStream in = new DataInputStream(socket.getInputStream());
				
				while(true) {
					if(in.available() > 0) {						
						String msg = in.readUTF();
						Platform.runLater(() -> {
							boardTxtArea.appendText(msg + "\n\n");
						});
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
