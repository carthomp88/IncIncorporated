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

public class GameWinScreen extends Menu {
    public GameWinScreen() {
        super();
        this.screenName = MenuManager.ScreenName.LEVELWIN;
        this.title = "YOU WIN!";
    }

    public void generate() {
        // Text youWin = new Text("YOU WIN!");
        Button nextButton = new Button("Credits");
        nextButton.setMinWidth(300);
        nextButton.setMinHeight(80);
        nextButton.setOnAction((ActionEvent event) -> {
            MenuManager.changeScreen(MenuManager.ScreenName.CREDITS);

            event.consume();
        }); // end nextButton event

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setMinWidth(300);
        mainMenuButton.setMinHeight(80);
        mainMenuButton.setOnAction((ActionEvent event) -> {
            MenuManager.changeScreen(MenuManager.ScreenName.MAIN);

            event.consume();
        });

        GridPane buttonPane = new GridPane();
        buttonPane.addRow(0, nextButton);
        buttonPane.addRow(1, mainMenuButton);
        buttonPane.setVgap(15);

        pane = new GridPane();
        // ((GridPane) pane).addRow(1, youWin);
        ((GridPane) pane).addRow(0, buttonPane);
        ((GridPane) pane).setPadding(new Insets(300.0));
        ((GridPane) pane).setVgap(15);
        ((GridPane) pane).setAlignment(Pos.CENTER_RIGHT);

        // Background Image
        try {
            pane.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream("images/gamewin.png")),
                    null, null, null, null)));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mainMenuButton.setStyle("-fx-background-color: #727272;");
        mainMenuButton.setTextFill(Color.web("#FFFFFF"));
        mainMenuButton.setFont(new Font("Elephant", 32));
        nextButton.setStyle("-fx-background-color: #727272;");
        nextButton.setTextFill(Color.web("#FFFFFF"));
        nextButton.setFont(new Font("Elephant", 32));
        // youWin.setFill(Color.web("#FFFFFF"));
    }
}
