import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Animator {
    public static void damageAnimation(ImageView character, Image hurtImage, Image characterImage) {
        // player
        if (character.getX() < 500) {
            character.setX(character.getX() - 50);
            character.setImage(hurtImage);
            PauseTransition transition = new PauseTransition(Duration.seconds(0.5));
            transition.setOnFinished(event -> {
                // could put delay here so once the transition ends it waits a bit, then resets
                character.setX(character.getX() + 50);
                character.setImage(characterImage);
            });
            transition.playFromStart();
        } else {
            // enemy
            character.setX(character.getX() + 50);
            character.setImage(hurtImage);
            PauseTransition transition = new PauseTransition(Duration.seconds(0.5));
            transition.setOnFinished(event -> {
                // could put delay here so once the transition ends it waits a bit, then resets
                character.setX(character.getX() - 50);
                character.setImage(characterImage);
            });
            transition.playFromStart();
        }
    }

    public static void attackAnimation(ImageView character, Image attackImage, Image characterImage) {
        character.setImage(attackImage);
        PauseTransition transition = new PauseTransition(Duration.seconds(0.5));
        transition.setOnFinished(event -> character.setImage(characterImage));
        transition.playFromStart();
    }

    public static void defendAnimation(ImageView character, Image defendImage, Image characterImage) {
        character.setImage(defendImage);
        PauseTransition transition = new PauseTransition(Duration.seconds(0.5));
        transition.setOnFinished(event -> character.setImage(characterImage));
        transition.playFromStart();
    }

    public static void evadeTaxAnimation(ImageView character, Image evadeTaxImage, Image characterImage) {
        character.setImage(evadeTaxImage);
        PauseTransition transition = new PauseTransition(Duration.seconds(0.5));
        transition.setOnFinished(event -> character.setImage(characterImage));
        transition.playFromStart();
    }

    public static void payTaxAnimation(ImageView character, Image payTaxImage, Image characterImage) {
        character.setImage(payTaxImage);
        PauseTransition transition = new PauseTransition(Duration.seconds(0.5));
        transition.setOnFinished(event -> character.setImage(characterImage));
        transition.playFromStart();
    }
}
