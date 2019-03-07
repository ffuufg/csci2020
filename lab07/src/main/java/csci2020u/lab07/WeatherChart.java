package csci2020.lab07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;

public class WeatherChart extends Application {

	public void start(Stage primaryStage) {
		
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		File f = new File("weatherwarnings-2015.csv");
		
		try {
			Scanner in = new Scanner(f);
			String str = "";
			in.useDelimiter(",");
			while(in.hasNextLine()) {
				for(int i = 0;i < 7;i++) {
					if(i < 5) in.next();
					else if(i == 5) str = in.next();
					else in.nextLine();
				}
				if(m.get(str) == null) m.put(str, 1);
				else m.put(str, m.get(str)+1);
			}
			
			System.out.println(m);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
