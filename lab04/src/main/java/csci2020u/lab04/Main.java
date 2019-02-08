package csci2020.lab04;

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
          Label textLabel = new Label("Username:");
          _user = new TextField();
          _user.setPromptText("Prompt text for username");

          gp.add(textLabel, 0,0);
          gp.add(_user, 1, 0);

          Label pwLabel = new Label("Password: ");
          _pw1 = new PasswordField();
          gp.add(pwLabel, 0, 1);
          gp.add(_pw1, 1, 1);

          Label nameLabel = new Label("Full name:");
          _name = new TextField();
          _name.setPromptText("Prompt text for full name");

          gp.add(nameLabel, 0, 2);
          gp.add(_name, 1, 2);

          Label emailLabel = new Label("Email:");
          _email = new TextField();
          _email.setPromptText("Prompt text for email");

          gp.add(emailLabel, 0, 3);
          gp.add(_email, 1, 3);

          Label phoneLabel = new Label("Phone:");
          _phone = new TextField();
          _phone.setPromptText("Prompt text for phone");

          gp.add(phoneLabel, 0, 4);
          gp.add(_phone, 1, 4);

          Label dateLabel = new Label("Date: ");
          _dp1 = new DatePicker();
          gp.add(dateLabel, 0, 5);
          gp.add(_dp1, 1, 5);

          Label btnLabel = new Label("Button!");
          _btn1 = new Button("Click me!");

          // Set pressing "enter" to submit
          _btn1.setDefaultButton(true);

          gp.add(btnLabel, 0, 6);
          gp.add(_btn1, 1, 6);


          _ta = new TextArea();
          _ta.setDisable(true);
          _ta.appendText("Appended text will show here:\n");

          gp.add(_ta, 0, 8, 8, 8);

          // Button handler
          _btn1.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                  // Append to our fake console

                  _ta.appendText(_user.getText() + "\n" +
                                 _pw1.getText() + "\n" +
                                 _name.getText() + "\n" +
                                 _email.getText() + "\n" +
                                 _phone.getText().substring(0,3) + "-" + 
                                 _phone.getText().substring(3,6) + "-" +
                                 _phone.getText().substring(6) + "\n" +
                                 _dp1.getValue() + "\n");


                  // Clear previous inputs
                  _user.clear();
                  _pw1.clear();
                  _name.clear();
                  _email.clear();
                  _phone.clear();
              }
          });
          Scene scene = new Scene(gp, 500, 300);
          primaryStage.setScene(scene);
          primaryStage.show();
      }
  }
