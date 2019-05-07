import javafx.application.*;
import javafx.event.*;
import javafx.geometry.HPos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.scene.text.Font;

import javax.xml.ws.handler.Handler;


public class GUI extends Application {

    private Stage window;
    private Scene scene1, scene2;

    public static void main (String args[]){
        launch (args);
    }

    @Override
    public void start(Stage primaryStage)throws Exception{
        window = primaryStage;

        Label l1 = new Label(" E-mail: ");
        Label l2 = new Label(" Password: ");

        TextField email = new TextField();
        TextField password = new TextField();

        Button button = new Button("Login");
        button.setOnAction(event -> System.out.println("AINDA NAO ACABEI CRALHES"));

        VBox v1 = new VBox(20);
        v1.getChildren().addAll(l1,l2);


        Button button2 = new Button("Sign Up");
        button2.setOnAction(event -> System.out.println("O QUE E QUE DISSE???"));

        GridPane v2 = new GridPane();
        v2.add(l1, 0,0);
        v2.add(l2, 0,1);
        v2.add(email, 1 ,0);
        v2.add(password, 1,1);
        v2.add(button, 1, 2);
        v2.add(button2, 1, 3);
        v2.setHalignment(button, HPos.CENTER);
        v2.setHalignment(button2, HPos.CENTER);

        BorderPane pane = new BorderPane();
        pane.setCenter(v2);

        scene1 = new Scene(pane, 300,150);
        window.setScene(scene1);
        window.setTitle("Título");
        window.show();
    }

}
