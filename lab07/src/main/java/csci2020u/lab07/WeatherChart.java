package csci2020.lab07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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

		GridPane legendGridPane = new GridPane(); //For legend

//		for(String key: m.keySet()) {
//			//Iterates through all keys, for legend if you want (optional)
//			System.out.println(key);
//		}

		ArrayList<Color> colors = new ArrayList<Color>();
		for(int i = 0;i < 25;i++) {
			for(int j = 0;j < 25;j++) {
				for(int k = 0;k < 25;k++) {
					colors.add(new Color(i*0.04, j*0.04, k*0.04, 1.0));
				}
			}
		}
		Collections.shuffle(colors);

		int iterCount = 0;

		for(HashMap.Entry<String, Integer> entry: m.entrySet()) {
			//Iterates through all keys and all values for legend & chart
			System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());

			Rectangle r = new Rectangle(50, 20);
			r.setFill(colors.get(iterCount % 15625)); //15625 colours

			Text t = new Text(entry.getKey());

			legendGridPane.add(r, 0, iterCount);
			legendGridPane.add(t, 1, iterCount);

			iterCount++;

		}

		HBox fin = new HBox();

		Pane p = new Pane();
		double lastAngle = 0, lastLength = 0;
		int count = 0;
		for(int i: m.values()) {
			count += i;
		}

		iterCount = 0;
		for(HashMap.Entry<String, Integer> entry: m.entrySet()) {
			Arc young = new Arc();
			young.setStroke(colors.get(iterCount % 15625));
			young.setFill(colors.get(iterCount % 15625));
			young.centerXProperty().bind(p.widthProperty().divide(2));
			young.centerYProperty().bind(p.heightProperty().divide(2));
			young.setRadiusX(50.f);
			young.setRadiusY(50.f);
			young.setStartAngle(lastAngle + lastLength);
			young.setLength(entry.getValue()/count * 360);
			young.setType(ArcType.ROUND);

			p.getChildren().add(young);

			lastAngle = young.getStartAngle();
			lastLength = young.getLength();
			iterCount++;
		}
		fin.getChildren().addAll(legendGridPane, p);

		Scene s = new Scene(fin);
		primaryStage.setScene(s);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
