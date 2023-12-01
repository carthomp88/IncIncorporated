import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.text.*;
import javafx.scene.paint.Color;

public class GameOverScreen extends Menu {
    public GameOverScreen() {
        super();
        this.screenName = MenuManager.ScreenName.GAMEOVER;
    }

    // public GameOverScreen(Stage stage, Scene scene, String title) {
    // super(stage, scene, title);
    // }

    public void generate() {
        // Text gameOver = new Text("Game Over!");

        Button restartButton = new Button("Restart Level");
        restartButton.setMinWidth(300);
        restartButton.setMinHeight(80);
        restartButton.setOnAction((ActionEvent event) -> {
            MenuManager.changeScreen(MenuManager.ScreenName.LEVEL); // MUST DECREMENT TO COMPENSATE
            event.consume();
        }); // end restartButton event

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setMinWidth(300);
        mainMenuButton.setMinHeight(80);
        mainMenuButton.setOnAction((ActionEvent event) -> {
            MenuManager.changeScreen(MenuManager.ScreenName.MAIN);
            event.consume();
        });

        GridPane buttonPane = new GridPane();
        buttonPane.addRow(0, restartButton);
        buttonPane.addRow(1, mainMenuButton);
        buttonPane.setVgap(20);

        pane = new GridPane();
        // ((GridPane) pane).addRow(0, gameOver);
        ((GridPane) pane).addRow(0, buttonPane);
        ((GridPane) pane).setPadding(new Insets(300.0));
        ((GridPane) pane).setVgap(15);
        ((GridPane) pane).setAlignment(Pos.CENTER_RIGHT);

        // Background Image
        try {
            pane.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream("images/gameover.png")),
                    null, null, null, null)));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mainMenuButton.setStyle("-fx-background-color: #727272;");
        mainMenuButton.setTextFill(Color.web("#FFFFFF"));
        mainMenuButton.setFont(new Font("Elephant", 32));
        restartButton.setStyle("-fx-background-color: #727272;");
        restartButton.setTextFill(Color.web("#FFFFFF"));
        restartButton.setFont(new Font("Elephant", 32));
        // gameOver.setFill(Color.web("#FFFFFF"));
    }
}
