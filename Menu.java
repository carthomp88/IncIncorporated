import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;

abstract class Menu {
    protected Pane pane = null;
    protected Stage stage = null;
    protected Scene scene = null;
    protected String title = "";
    protected MenuManager.ScreenName screenName;
    protected int levelnum;

    public Menu() {
    }

    // public Menu(Stage stage, Scene scene, String title) {
    // this.stage = stage;
    // this.scene = scene;
    // this.title = title;
    // } THIS SHOULD NO LONGER BE NECESSARY AFTER REFACTORING

    /**
     * generate() - All necessary JavaFX objects should be created within generate,
     * added to the Pane, and returned by getPane!
     */
    public abstract void generate();

    public Pane getPane() {
        return pane;
    }

    public String getTitle() {
        return title;
    }

    // levelnum defaults to -1 for menus that don't need to track it
    public void setLevelNum(int levelnum) {
        this.levelnum = levelnum;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
