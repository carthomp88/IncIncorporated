import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.Image;

public class MainMenu extends Menu {
    public MainMenu() {
        super();
        this.screenName = MenuManager.ScreenName.MAIN;
    }

    // public MainMenu(Stage stage, Scene scene, String title) {
    // super(stage, scene, title);
    // }

    @Override
    public void generate() {
        // Start Game Button
        Button button1 = new Button("START GAME");
        button1.setStyle("-fx-background-color: #727272;");
        button1.setTextFill(Color.web("#FFFFFF"));
        button1.setFont(new Font("Elephant", 30));
        button1.setTranslateX(490);
        button1.setTranslateY(400);
        button1.setMinWidth(300);
        button1.setMinHeight(80);
        button1.setOnAction((ActionEvent event) -> {
            MenuManager.changeScreen(MenuManager.ScreenName.LEVEL);

            event.consume();
        }); // end Button1 click event

        // How to Play Button
        Button button2 = new Button("HOW TO PLAY");
        button2.setStyle("-fx-background-color: #727272;");
        button2.setTextFill(Color.web("#FFFFFF"));
        button2.setFont(new Font("Elephant", 30));
        button2.setTranslateX(490);
        button2.setTranslateY(400);
        button2.setMinWidth(300);
        button2.setMinHeight(80);
        button2.setOnAction((ActionEvent event) -> {
            MenuManager.changeScreen(MenuManager.ScreenName.INSTRUCTION);

            event.consume();
        }); // end Button2 click event

        Button button3 = new Button("QUIT");
        button3.setStyle("-fx-background-color: #727272;");
        button3.setTextFill(Color.web("#FFFFFF"));
        button3.setFont(new Font("Elephant", 30));
        button3.setTranslateX(490);
        button3.setTranslateY(400);
        button3.setMinWidth(300);
        button3.setMinHeight(80);
        button3.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });

        pane = new GridPane();
        try {
            pane.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream("images/mainmenu.png")),
                    null, null, null, null)));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        GridPane buttonPane = new GridPane();
        // pane.setStyle("-fx-background-color: #323232;");

        buttonPane.addRow(0, button1);
        buttonPane.addRow(1, button2);
        buttonPane.addRow(2, button3);
        buttonPane.setVgap(20);
        ((GridPane) pane).addColumn(0, buttonPane);

    }
}
