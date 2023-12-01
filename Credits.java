import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;

public class Credits extends Menu {
    public Credits() {
        super();
        this.screenName = MenuManager.ScreenName.CREDITS;
        this.title = "Credits";
    }

    public void generate() {
        pane = new Pane();
        // I will do something soon.
        try {
            pane.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream("images/credits.png")),
                    null, null, null, null)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                MenuManager.changeScreen(MenuManager.ScreenName.MAIN);
                me.consume();
            }
        });
    }
}
