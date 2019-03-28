package csci2020.lab09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
	class StockNode {
		public LocalDate timestamp;
		public double close;
		
		public StockNode(LocalDate timestamp, double close) {
			this.timestamp = timestamp;
			this.close = close;
		}
	}

	public ArrayList<StockNode> downloadStockPrices(String ticker) {
		
		ArrayList<StockNode> closeVals = new ArrayList<StockNode>();
		
		String urlStr = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=" + ticker + "&outputsize=full&apikey=6H7OOKCDRMGU3ITZ&datatype=csv";
		URL url;

		String str = "";
		String[] vals;
		
		try {
			url = new URL(urlStr);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			str = in.readLine();
			while((str = in.readLine()) != null) {
				vals = str.split(",");
				LocalDate timestamp = LocalDate.parse(vals[0]);
				double close = Double.valueOf(vals[5]);
				
				if(timestamp.isBefore(LocalDate.of(LocalDate.now().getYear()-1, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()))) {
//					System.out.println("Broken at " + timestamp);
					break;
				}
				
				closeVals.add(new StockNode(timestamp, close));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return closeVals;
	}
	
	public void drawLinePlot(GraphicsContext gc, ArrayList<StockNode> stock1, ArrayList<StockNode> stock2) {
		
		LocalDate minTime = stock1.get(stock1.size()-1).timestamp;
		
		if(stock2.get(stock2.size()-1).timestamp.compareTo(minTime) < 0)
			minTime = stock2.get(stock2.size()-1).timestamp;
		
		LocalDate maxTime = stock1.get(0).timestamp;
		
		if(stock2.get(0).timestamp.compareTo(maxTime) > 0)
			maxTime = stock2.get(0).timestamp;
		
		long minTimeLong = getLong(minTime);
		long maxTimeLong = getLong(maxTime);
		
		double maxClose = stock1.get(0).close;
		double minClose = maxClose;
		
		for(int i = 1;i < stock1.size();i++) {
			if(stock1.get(i).close > maxClose)
				maxClose = stock1.get(i).close;
			
			if(stock1.get(i).close < minClose)
				minClose = stock1.get(i).close;
		}

		for(int i = 0;i < stock2.size();i++) {
			if(stock2.get(i).close > maxClose)
				maxClose = stock2.get(i).close;
			
			if(stock2.get(i).close < minClose)
				minClose = stock2.get(i).close;
		}
			
//		System.out.println("" + minTime + " " + maxTime);
//		System.out.println("" + stock1.get(stock1.size()-1).timestamp + " " + stock1.get(0).timestamp);
//		System.out.println("" + stock2.get(stock2.size()-1).timestamp + " " + stock2.get(0).timestamp);
		
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		gc.strokeLine(50, 50, 50, gc.getCanvas().getHeight()-50);
		gc.strokeLine(50, gc.getCanvas().getHeight()-50, gc.getCanvas().getWidth()-50, gc.getCanvas().getHeight()-50);
		
		plotLine(gc, stock1, minTimeLong, maxTimeLong, minClose, maxClose, Color.BLUE);
		plotLine(gc, stock2, minTimeLong, maxTimeLong, minClose, maxClose, Color.RED);
		
	}
	
	public void plotLine(GraphicsContext gc, ArrayList<StockNode> stock, long minTime, long maxTime, double minClose, double maxClose, Color c) {
		
		gc.setStroke(c);
		
		long prevTime = getLong(stock.get(0).timestamp);
		long curTime;
		
		double prevClose = stock.get(0).close;
		double curClose;
		
		double x1, x2, y1, y2;
		
		for(int i = 1;i < stock.size();i++) {
			
			curTime = getLong(stock.get(i).timestamp);
			curClose = stock.get(i).close;
			
			x1 = ((double)(prevTime)-minTime) / ((double)(maxTime)-minTime) * (gc.getCanvas().getWidth()-100) + 50;
			x2 = ((double)(curTime)-minTime) / ((double)maxTime-minTime) * (gc.getCanvas().getWidth()-100) + 50;

			y1 = gc.getCanvas().getHeight() - ((prevClose-minClose) / (maxClose-minClose) * (gc.getCanvas().getHeight()-100) + 50);
			y2 = gc.getCanvas().getHeight() - ((curClose-minClose) / (maxClose-minClose) * (gc.getCanvas().getHeight()-100) + 50);
			
			prevTime = curTime;
			prevClose = curClose;
			
			gc.strokeLine(x1, y1, x2, y2);
			
//			System.out.println(((double)(prevTime)-minTime) / ((double)(maxTime)-minTime));
			
		}
		
	}
	
	public long getLong(LocalDate l) {
		long val = 0;
		LocalDate temp;
		for(int i = 1970;i < l.getYear();i++) {
			val += 365;
			temp = LocalDate.ofYearDay(i, 1);
			if(temp.isLeapYear()) val++;
		}
		val += l.getDayOfYear();
		return val;
	}
	
	public void start(Stage primaryStage) {
		
		Pane p = new Pane();
		
		Canvas cv = new Canvas(800, 600);
		GraphicsContext gc = cv.getGraphicsContext2D();
		
		drawLinePlot(gc, downloadStockPrices("ATVI"), downloadStockPrices("MSFT"));
		
		p.getChildren().add(cv);
		
		Scene s = new Scene(p);
		
		primaryStage.setTitle("Lab 9");
		primaryStage.setScene(s);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
