package csci2020.lab06;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    private static double[] avgHousingPricesByYear = {247381.0, 264171.4, 287715.3, 294736.1, 308431.4, 322635.9, 340253.0, 363153.7};
    private static double[] avgCommercialPricesByYear = {1121585.3, 1219479.5, 1246354.2, 1295364.8, 1335932.6, 1472362.0, 1583521.9, 1613246.3};

    private static String[] ageGroups = {"18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
    private static int[] purchasesByAgeGroup = {648, 1021, 2453, 3173, 1868, 2247};
    private static Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Drawing Graphs");
        Group root = new Group();
        Canvas canvas = new Canvas(500, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawBarGraph(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void drawBarGraph(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.RED);
        gc.setLineWidth(11);

        int j = 25;

        for (int i = 0; i < avgHousingPricesByYear.length; i++) {
            gc.strokeLine(j + i*28, 550 - (avgHousingPricesByYear[i]/4500), j + i*28, 550);
        }
        
        j += 11;
        
        gc.setStroke(Color.BLUE);
        
        for(int i = 0; i < avgCommercialPricesByYear.length; i++) {
        	gc.strokeLine(j + i*28, 550 - (avgCommercialPricesByYear[i]/4500), j + i*28, 550);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}