import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
  // Variables that we need to use
      private TextField _user, _name, _email, _phone;
      private PasswordField _pw1;
      private DatePicker _dp1;
      private Button _btn1;


      // Fake Console
      private TextArea _ta;

      public static void main(String[] args) {
          Application.launch(args);
      }

      @Override
      public void start(Stage primaryStage) throws Exception {
          primaryStage.setTitle("Lab 04");

          // Grid Pane
          GridPane gp = new GridPane();
          gp.setPadding(new Insets(10,10,10,10));

          // Set our variables
          Label textLabel = new Label("Username:c");
          _text1 = new TextField();
          _text1.setPromptText("Prompt text for text1");

          gp.add(textLabel, 0,0);
          gp.add(_text1, 1, 0);

          Label pwLabel = new Label("Password: ");
          _pw1 = new PasswordField();
          gp.add(pwLabel, 0, 1);
          gp.add(_pw1, 1, 1);

          Label dateLabel = new Label("Date: ");
          _dp1 = new DatePicker();
          gp.add(dateLabel, 0, 2);
          gp.add(_dp1, 1, 2);

          Label btnLabel = new Label("Button!");
          _btn1 = new Button("Click me!");

          // Set pressing "enter" to submit
          _btn1.setDefaultButton(true);

          gp.add(btnLabel, 0, 3);
          gp.add(_btn1, 1, 3);


          _ta = new TextArea();
          _ta.setDisable(true);
          _ta.appendText("Appended text will show here:\n");

          gp.add(_ta, 0, 5, 3, 5);

          // Button handler
          _btn1.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  // Append to our fake console

                  _ta.appendText(_text1.getText() + "\n" +
                                 _pw1.getText() + "\n" +
                                 _dp1.getValue() + "\n");

                  // Clear previous inputs
                  _text1.clear();
                  _pw1.clear();
              }
          });
          Scene scene = new Scene(gp, 500, 300);
          primaryStage.setScene(scene);
          primaryStage.show();
      }
  }
