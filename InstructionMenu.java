import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class InstructionMenu extends Menu {
    private int frameNum;

    public InstructionMenu() {
        super();
        this.screenName = MenuManager.ScreenName.INSTRUCTION;
        this.title = "How To Play";
        frameNum = 1;
    }

    // public InstructionMenu(Stage stage, Scene scene, String title) {
    // super(stage, scene, title);
    // }

    @Override
    public void generate() {
        pane = new Pane();
        try {
            pane.setBackground(
                    new Background(new BackgroundImage(new Image(new FileInputStream("images/howtoplay/1.png")),
                            null, null, null, null)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Button nextButton = new Button("NEXT");
        nextButton.setTranslateX(1160);
        nextButton.setTranslateY(660);
        nextButton.setMinHeight(40);
        nextButton.setMinWidth(100);
        nextButton.setStyle("-fx-background-color: #727272;");
        nextButton.setTextFill(Color.WHITE);
        nextButton.setOnAction((ActionEvent event) -> {
            frameNum++;
            updateBackground();
            event.consume();
        });

        Button prevButton = new Button("BACK");
        prevButton.setTranslateX(20);
        prevButton.setTranslateY(660);
        prevButton.setMinHeight(40);
        prevButton.setMinWidth(100);
        prevButton.setStyle("-fx-background-color: #727272;");
        prevButton.setTextFill(Color.WHITE);
        prevButton.setOnAction((ActionEvent event) -> {
            frameNum--;
            updateBackground();
            event.consume();
        });

        Button menuButton = new Button("MAIN MENU");
        menuButton.setTranslateX(590);
        menuButton.setTranslateY(660);
        menuButton.setMinHeight(40);
        menuButton.setMinWidth(100);
        menuButton.setStyle("-fx-background-color: #727272;");
        menuButton.setTextFill(Color.WHITE);
        menuButton.setOnAction((ActionEvent event) -> {
            MenuManager.changeScreen(MenuManager.ScreenName.MAIN);
            event.consume();
        });

        pane.getChildren().addAll(nextButton, prevButton, menuButton);
    }

    public void updateBackground() {
        if (frameNum > 13) {
            MenuManager.changeScreen(MenuManager.ScreenName.MAIN);
            return;
        }
        if (frameNum < 1) {
            frameNum = 1;
            return;
        }
        try {
            pane.setBackground(
                    new Background(
                            new BackgroundImage(new Image(new FileInputStream("images/howtoplay/" + frameNum + ".png")),
                                    null, null, null, null)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
