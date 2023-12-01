import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuManager extends Application {
    public static enum ScreenName {
        MAIN,
        INSTRUCTION,
        LEVEL,
        LEVELWIN,
        GAMEOVER,
        CREDITS,
        GAMEWIN
    }

    private static Stage stage;
    private static CharacterManager charMgr = CharacterManager.getInstance();
    private static RoundManager RndMgr = RoundManager.getInstance();

    @Override
    public void start(Stage stage) {
        stage.getIcons().add(new Image("./images/jerryAct.png"));
        MenuManager.stage = stage;
        stage.setResizable(false);
        MainMenu mai = new MainMenu();
        mai.setStage(stage);
        mai.generate();
        mai.setLevelNum(0);

        Scene ma = new Scene(mai.getPane(), 1280, 720);

        stage.setTitle("I.N.C. Inc.");
        stage.setScene(ma);
        stage.show();
    }

    // Static method that can be called from within other menu class' buttons to
    // change the current scene.
    public static void changeScreen(MenuManager.ScreenName dest) {
        MediaPlayer mp = new MediaPlayer(new Media(Paths.get("sfx/chaching.mp3").toUri().toString()));
        mp.setStopTime(new Duration(2000));
        stage.setResizable(false);
        String basicTitle = "I.N.C. Inc. - ";

        switch (dest) {
            case MAIN:
                MainMenu mai = new MainMenu();
                mai.setStage(stage);
                mai.generate();
                Scene ma = new Scene(mai.getPane(), 1280, 720);
                stage.setTitle(basicTitle);
                stage.setScene(ma);
                stage.show();
                break; // END MAIN

            case INSTRUCTION:
                InstructionMenu im = new InstructionMenu();
                im.generate();
                im.setStage(stage);
                Scene imScene = new Scene(im.getPane(), 1280, 720);
                stage.setTitle(basicTitle + im.getTitle());
                stage.setScene(imScene);
                stage.show();
                break; // END INSTRUCTION

            case LEVEL:
                BasicLevel gameField = new BasicLevel();
                gameField.setLevelNum(RndMgr.getActiveLevel());
                gameField.generate();
                gameField.setStage(stage);
                gameField.setTitle("Level " + RndMgr.getActiveLevel());
                Scene gameScene = new Scene(gameField.getPane(), 1280, 720);
                stage.setTitle(basicTitle + gameField.getTitle());
                stage.setScene(gameScene);
                stage.show();
                RndMgr.startMusic();
                break;
            case GAMEWIN:
                GameWinScreen gg = new GameWinScreen();
                gg.generate();
                gg.setStage(stage);
                Scene gws = new Scene(gg.getPane(), 1280, 720);
                stage.setTitle(basicTitle + gg.getTitle());
                stage.setScene(gws);
                stage.show();
                mp.play();
                break;
            case LEVELWIN:
                LevelWinScreen lw = new LevelWinScreen();
                lw.generate();
                lw.setStage(stage);
                Scene lws = new Scene(lw.getPane(), 1280, 720);
                stage.setTitle(basicTitle + lw.getTitle());
                stage.setScene(lws);
                stage.show();
                mp.play();
                break; // END LEVELWIN
            case GAMEOVER:
                GameOverScreen go = new GameOverScreen();
                go.generate();
                go.setStage(stage);
                Scene gos = new Scene(go.getPane(), 1280, 720);
                stage.setTitle(basicTitle + go.getTitle());
                stage.setScene(gos);
                stage.show();
                break; // END GAMEOVER
            case CREDITS:
                Credits c = new Credits();
                c.generate();
                c.setStage(stage);
                Scene cs = new Scene(c.getPane(), 1280, 720);
                stage.setScene(cs);
                stage.show();
                break; // END CREDITS

        }

        // stage.setTitle("I.N.C. Inc. - " + title);
        // stage.setScene(scene);
        // stage.show();
    }

    public static void startAdapter() {
        launch();
    }

}
