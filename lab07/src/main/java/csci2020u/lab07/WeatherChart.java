package csci2020.lab07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
			
//			System.out.println(m);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		HBox hboxPane = new HBox(); //For all elements
		
		VBox legendVBox = new VBox(10); //For legend
		
//		for(String key: m.keySet()) {
//			//Iterates through all keys, for legend if you want (optional)
//			System.out.println(key);
//		}
		
		for(HashMap.Entry<String, Integer> entry: m.entrySet()) {
			//Iterates through all keys and all values for legend & chart
			System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
