import javafx.event.ActionEvent;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.ProgressBar;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class BasicLevel extends Menu {

    private boolean clickable = true;

    private final Color GREEN = Color.web("#1ed611");
    private final Color RED = Color.web("#ed0c0c");
    private final Color LIGHTGRAY = Color.web("#cfcfd1");
    private final Color LIGHTDARKGRAY = Color.web("#525252");
    private final Color WHITE = Color.web("#FFFFFF");

    // Images
    private Image playerImage = null, playerHurtImage = null, playerActImage = null;
    private Image playerAttackImage = null, playerDefendImage = null;
    private Image playerEvadeTaxImage = null, playerPayTaxImage = null;
    private Image enemyImage = null, enemyHurtImage = null, enemyActImage = null;
    private Image enemyAttackImage = null, enemyDefendImage = null;
    private Image enemyPayTaxImage = null, enemyEvadeTaxImage = null;

    // Button Bar and Buttons
    private Rectangle buttonBar;
    private Button attackButton;
    private Button defendButton;
    private Button dodgeTaxButton;
    private Button payTaxButton;

    // Character ImageViews
    private Circle jerry;
    private Circle enemy;
    private ImageView playerImageView;
    private ImageView enemyImageView;

    // Character Status Bars
    private GridPane jerryStatus;
    private Text jerryAtkText;
    private Text jerryDefText;
    private GridPane enemyStatus;
    private Text enemyAtkText;
    private Text enemyDefText;
    private Text enemyTaxText;
    private Text jerryTaxText;
    private Text jerryTaxNums;
    private Text enemyTaxNums;

    // Character Names and HP Bars
    private Text enemyName;
    private Text jerryName;
    private GridPane jerryHP;
    private ProgressBar jerryHPBar;
    private Text jerryHPText;
    private GridPane enemyHP;
    private ProgressBar enemyHPBar;
    private Text enemyHPText;

    // Action Bar
    private Rectangle actionTextBar;
    private Text playerAction;
    private Text enemyAction;
    private String[] actionHolder;

    private CharacterManager charMgr = CharacterManager.getInstance();
    private RoundManager RndMgr = RoundManager.getInstance();

    // Default Constructor
    public BasicLevel() {
        super();
        this.screenName = MenuManager.ScreenName.LEVEL;
        this.title = "Level ";
    }

    public void generate() {
        Integer ln = levelnum;
        this.title += ln.toString();

        buttonBar = new Rectangle();
        jerry = new Circle();
        enemy = new Circle();
        jerryStatus = new GridPane();
        jerryHP = new GridPane();
        jerryHPBar = new ProgressBar(1);
        jerryHPText = new Text(manageHP(jerryHPBar, charMgr.getPlayer()));
        jerryAtkText = new Text();
        jerryDefText = new Text();
        enemyStatus = new GridPane();
        enemyHP = new GridPane();
        enemyHPBar = new ProgressBar(1);
        enemyHPText = new Text(manageHP(enemyHPBar, charMgr.getActiveBoss()));
        enemyAtkText = new Text();
        enemyDefText = new Text();
        actionTextBar = new Rectangle();
        playerAction = new Text();
        enemyAction = new Text();
        enemyName = new Text();
        jerryName = new Text();
        enemyTaxText = new Text();
        jerryTaxText = new Text();
        jerryTaxNums = new Text();
        enemyTaxNums = new Text();
        attackButton = new Button("ATTACK");
        defendButton = new Button("DEFEND");
        dodgeTaxButton = new Button("EVADE TAX");
        payTaxButton = new Button("PAY TAX");

        manageAtk(jerryAtkText, enemyAtkText);
        manageDef(jerryDefText, enemyDefText);

        // Getting images for the player and the enemy
        try {
            playerImage = new Image(new FileInputStream("images/jerry.png"));
            playerHurtImage = new Image(new FileInputStream("images/jerryHurt.png"));
            playerActImage = new Image(new FileInputStream("images/jerryAct.png"));

            if (levelnum == 1) {
                // Tanner
                enemyImage = new Image(new FileInputStream("images/tanner.png"));
                enemyHurtImage = new Image(new FileInputStream("images/tannerHurt.png"));
                enemyActImage = new Image(new FileInputStream("images/tannerAct.png"));
            } else if (levelnum == 2) {
                // Margaret
                enemyImage = new Image(new FileInputStream("images/margaret.png"));
                enemyHurtImage = new Image(new FileInputStream("images/margaretHurt.png"));
                enemyActImage = new Image(new FileInputStream("images/margaretAct.png"));
            } else if (levelnum == 3) {
                // A. A. Ron
                enemyImage = new Image(new FileInputStream("images/aaron.png"));
                enemyHurtImage = new Image(new FileInputStream("images/aaronHurt.png"));
                enemyActImage = new Image(new FileInputStream("images/aaronAct.png"));
            }

            playerAttackImage = new Image(new FileInputStream("images/playerAttack.png"));
            enemyAttackImage = new Image(new FileInputStream("images/enemyAttack.png"));
            playerDefendImage = new Image(new FileInputStream("images/playerDefend.png"));
            enemyDefendImage = new Image(new FileInputStream("images/enemyDefend.png"));
            playerEvadeTaxImage = new Image(new FileInputStream("images/playerEvadeTax.png"));
            enemyEvadeTaxImage = new Image(new FileInputStream("images/enemyEvadeTax.png"));
            playerPayTaxImage = new Image(new FileInputStream("images/playerPayTax.png"));
            enemyPayTaxImage = new Image(new FileInputStream("images/enemyPayTax.png"));

        } catch (FileNotFoundException e) {
            System.out.println("Could not load an image");
        }

        playerImageView = new ImageView(playerImage);
        enemyImageView = new ImageView(enemyImage);

        // Setting the position of the player and enemy images
        playerImageView.setX(150);
        playerImageView.setY(200);
        playerImageView.setFitHeight(300);
        playerImageView.setFitWidth(256);

        enemyImageView.setX(880);
        enemyImageView.setY(200);
        enemyImageView.setFitHeight(300);
        enemyImageView.setFitWidth(256);

        // The bottom bar that houses all the action buttons
        buttonBar.setFill(LIGHTDARKGRAY);
        buttonBar.setX(15);
        buttonBar.setY(605);
        buttonBar.setWidth(1250);
        buttonBar.setHeight(100);

        // The buttons
        attackButton.setTranslateX(25);
        attackButton.setTranslateY(615);
        attackButton.setStyle("-fx-background-color: #727272;");
        attackButton.setTextFill(WHITE);
        attackButton.setFont(new Font("Comic Sans MS", 25));
        defendButton.setTranslateX(335);
        defendButton.setTranslateY(615);
        defendButton.setStyle("-fx-background-color: #727272;");
        defendButton.setTextFill(WHITE);
        defendButton.setFont(new Font("Comic Sans MS", 25));
        dodgeTaxButton.setTranslateX(645);
        dodgeTaxButton.setTranslateY(615);
        dodgeTaxButton.setStyle("-fx-background-color: #727272;");
        dodgeTaxButton.setTextFill(WHITE);
        dodgeTaxButton.setFont(new Font("Comic Sans MS", 25));
        payTaxButton.setTranslateX(955);
        payTaxButton.setTranslateY(615);
        payTaxButton.setStyle("-fx-background-color: #727272;");
        payTaxButton.setTextFill(WHITE);
        payTaxButton.setFont(new Font("Comic Sans MS", 25));
        attackButton.setMinWidth(300);
        defendButton.setMinWidth(300);
        dodgeTaxButton.setMinWidth(300);
        payTaxButton.setMinWidth(300);
        attackButton.setMinHeight(80);
        defendButton.setMinHeight(80);
        dodgeTaxButton.setMinHeight(80);
        payTaxButton.setMinHeight(80);

        // attack button action event
        attackButton.setOnAction((ActionEvent event) -> {
            if (clickable == false)
                return;
            else {
                clickable = false;
                enemyAction.setText("");
                changeButtonColorOnClick(attackButton);
                actionHolder = RndMgr.round("attack").split("BREAK!");
                updateUICharacterInfo();
                playerAction.setText(actionHolder[0]);
                Animator.attackAnimation(playerImageView, playerActImage, playerImage);
                Animator.damageAnimation(enemyImageView, enemyHurtImage, enemyImage);

                PauseTransition transition = new PauseTransition(Duration.seconds(1));
                // do all this after .5s
                transition.setOnFinished(e -> {
                    doEnemyAnimation(actionHolder[1]);
                    enemyAction.setText(actionHolder[1]);
                    PauseTransition t = new PauseTransition(Duration.seconds(0.5));
                    t.setOnFinished(e2 -> {
                        updateUICharacterInfo();
                        clickable = true;
                        event.consume();
                    });
                    t.playFromStart();
                });
                transition.playFromStart();
            }
        });

        // defend button action event
        defendButton.setOnAction((ActionEvent event) -> {
            if (clickable == false)
                return;
            else {
                clickable = false;
                enemyAction.setText("");
                changeButtonColorOnClick(defendButton);
                actionHolder = RndMgr.round("defend").split("BREAK!");
                updateUICharacterInfo();
                playerAction.setText(actionHolder[0]);
                Animator.defendAnimation(playerImageView, playerActImage, playerImage);
                PauseTransition transition = new PauseTransition(Duration.seconds(1));
                transition.setOnFinished(e -> {
                    doEnemyAnimation(actionHolder[1]);
                    enemyAction.setText(actionHolder[1]);
                    PauseTransition t = new PauseTransition(Duration.seconds(0.5));
                    t.setOnFinished(e2 -> {
                        updateUICharacterInfo();
                        clickable = true;
                        event.consume();
                    });
                    t.playFromStart();
                });
                transition.playFromStart();
            }
        });

        // evade button action event
        dodgeTaxButton.setOnAction((ActionEvent event) -> {
            if (clickable == false)
                return;
            else {
                clickable = false;
                enemyAction.setText("");
                changeButtonColorOnClick(dodgeTaxButton);
                actionHolder = RndMgr.round("evade").split("BREAK!");
                updateUICharacterInfo();
                playerAction.setText(actionHolder[0]);
                Animator.evadeTaxAnimation(playerImageView, playerActImage, playerImage);
                PauseTransition transition = new PauseTransition(Duration.seconds(1));
                transition.setOnFinished(e -> {
                    doEnemyAnimation(actionHolder[1]);
                    enemyAction.setText(actionHolder[1]);
                    PauseTransition t = new PauseTransition(Duration.seconds(0.5));
                    t.setOnFinished(e2 -> {
                        updateUICharacterInfo();
                        clickable = true;
                        event.consume();
                    });
                    t.playFromStart();
                });
                transition.playFromStart();
            }
        });

        // pay button action event
        payTaxButton.setOnAction((ActionEvent event) -> {
            if (clickable == false)
                return;
            else {
                clickable = false;
                enemyAction.setText("");
                changeButtonColorOnClick(payTaxButton);
                actionHolder = RndMgr.round("pay").split("BREAK!");
                updateUICharacterInfo();
                playerAction.setText(actionHolder[0]);
                Animator.payTaxAnimation(playerImageView, playerActImage, playerImage);
                Animator.damageAnimation(enemyImageView, enemyHurtImage, enemyImage);
                PauseTransition transition = new PauseTransition(Duration.seconds(1));
                transition.setOnFinished(e -> {
                    doEnemyAnimation(actionHolder[1]);
                    enemyAction.setText(actionHolder[1]);
                    PauseTransition t = new PauseTransition(Duration.seconds(0.5));
                    t.setOnFinished(e2 -> {
                        updateUICharacterInfo();
                        clickable = true;
                        event.consume();
                    });
                    t.playFromStart();
                });
                transition.playFromStart();
            }
        });

        // The text bar that will state the last 2 actions performed
        actionTextBar.setFill(LIGHTGRAY);
        actionTextBar.setX(230);
        actionTextBar.setY(515);
        actionTextBar.setWidth(820);
        actionTextBar.setHeight(75);
        playerAction.setX(245);
        playerAction.setY(545);
        playerAction.setFont(new Font("Comic Sans MS", 18));
        enemyAction.setX(245);
        enemyAction.setY(575);
        enemyAction.setFont(new Font("Comic Sans MS", 18));

        // The placeholder shapes for the player and enemy
        jerry.setFill(GREEN);
        enemy.setFill(RED);
        jerry.setCenterX(250);
        jerry.setCenterY(300);
        jerry.setRadius(125);
        enemy.setCenterX(980);
        enemy.setCenterY(300);
        enemy.setRadius(125);

        // The player's name and HP bar
        jerryName.setText("Jerry");
        jerryName.setFill(WHITE);
        jerryName.setFont(new Font("Comic Sans MS", 30));
        jerryHP.add(jerryName, 0, 0, 2, 1);
        jerryName.setTextAlignment(TextAlignment.CENTER);
        jerryHPBar.setMinWidth(200);
        jerryHPBar.setMinHeight(40);
        jerryHP.add(jerryHPBar, 0, 1);
        jerryHP.add(jerryHPText, 1, 1);
        jerryHP.setHgap(15);
        jerryHP.setVgap(15);
        jerryHP.getColumnConstraints().add(new ColumnConstraints(200));
        jerryHPText.setFill(WHITE);
        jerryHPText.setFont(new Font("Comic Sans MS", 25));
        jerryHP.setPadding(new Insets(15));

        // The player's status bar (ATK, DEF, TAX, PREVTAX)
        jerryAtkText.setFill(WHITE);
        jerryAtkText.setFont(new Font("Comic Sans MS", 25));
        jerryDefText.setFill(WHITE);
        jerryDefText.setFont(new Font("Comic Sans MS", 25));
        jerryTaxText.setText("TAX:");
        jerryTaxText.setFill(WHITE);
        jerryTaxText.setFont(new Font("Comic Sans MS", 25));
        jerryTaxNums.setText(manageTaxNumbers(charMgr.getPlayer()));
        jerryTaxNums.setFill(WHITE);
        jerryTaxNums.setFont(new Font("Comic Sans MS", 25));
        jerryStatus.add(jerryAtkText, 0, 0);
        jerryStatus.add(jerryDefText, 0, 1);
        jerryStatus.add(jerryTaxText, 0, 2);
        jerryStatus.add(jerryTaxNums, 1, 2);
        jerryStatus.setPadding(new Insets(15));
        jerryStatus.setTranslateX(400);
        jerryStatus.setTranslateY(250);
        jerryStatus.setVgap(25);

        // The enemy's name and HP bar
        enemyName.setText(charMgr.getActiveBoss().getName() + charMgr.getActiveBoss().title);
        enemyName.setFill(WHITE);
        enemyName.setFont(new Font("Comic Sans MS", 30));
        enemyHP.add(enemyName, 0, 0, 2, 1);
        enemyName.setTextAlignment(TextAlignment.CENTER);
        enemyHPBar.setMinWidth(200);
        enemyHPBar.setMinHeight(40);
        enemyHP.add(enemyHPBar, 1, 1);
        enemyHP.add(enemyHPText, 0, 1);
        enemyHP.setHgap(15);
        enemyHP.setVgap(15);
        // enemyHP.getColumnConstraints().add(new ColumnConstraints(200));
        enemyHPText.setFill(WHITE);
        enemyHPText.setFont(new Font("Comic Sans MS", 25));
        enemyHP.setPadding(new Insets(15));
        enemyHP.setTranslateX(925);

        // The enemy's status bar (ATK, DEF, TAX, PREVTAX)
        enemyAtkText.setFill(WHITE);
        enemyAtkText.setFont(new Font("Comic Sans MS", 25));
        enemyDefText.setFill(WHITE);
        enemyDefText.setFont(new Font("Comic Sans MS", 25));
        enemyTaxText.setText("TAX: ");
        enemyTaxText.setFill(WHITE);
        enemyTaxText.setFont(new Font("Comic Sans MS", 25));
        enemyTaxNums.setText(manageTaxNumbers(charMgr.getActiveBoss()));
        enemyTaxNums.setFill(WHITE);
        enemyTaxNums.setFont(new Font("Comic Sans MS", 25));
        enemyStatus.add(enemyAtkText, 0, 0);
        enemyStatus.add(enemyDefText, 0, 1);
        enemyStatus.add(enemyTaxText, 0, 2);
        enemyStatus.add(enemyTaxNums, 1, 2);
        enemyStatus.setPadding(new Insets(15));
        enemyStatus.setTranslateX(700);
        enemyStatus.setTranslateY(250);
        enemyStatus.setVgap(25);

        pane = new Pane(buttonBar, actionTextBar, playerAction, enemyAction, jerry, enemy, jerryHP,
                jerryStatus, enemyHP, enemyStatus, attackButton, defendButton, dodgeTaxButton, payTaxButton,
                playerImageView,
                enemyImageView);
        pane.setStyle("-fx-background-color: #323232;");
    }

    public void updateUICharacterInfo() {
        manageDef(jerryDefText, enemyDefText);
        manageAtk(jerryAtkText, enemyAtkText);
        enemyHPText.setText(manageHP(enemyHPBar, charMgr.getActiveBoss()));
        jerryHPText.setText(manageHP(jerryHPBar, charMgr.getPlayer()));
        enemyTaxNums.setText(manageTaxNumbers(charMgr.getActiveBoss()));
        jerryTaxNums.setText(manageTaxNumbers(charMgr.getPlayer()));
        jerryTaxNums.setFill(Color.WHITE);
        jerryTaxText.setFill(Color.WHITE);
        enemyTaxNums.setFill(Color.WHITE);
        enemyTaxText.setFill(Color.WHITE);
        if (charMgr.getPlayer().taxMaxed) {
            jerryTaxText.setFill(Color.YELLOW);
            jerryTaxNums.setFill(Color.YELLOW);
        }
        if (charMgr.getActiveBoss().taxMaxed) {
            enemyTaxText.setFill(Color.YELLOW);
            enemyTaxNums.setFill(Color.YELLOW);
        }
    }
    // Using transitions to change the color of a button for 100 milliseconds when
    // clicked

    public void changeButtonColorOnClick(Button button) {
        button.setStyle("-fx-background-color: #929292;");
        PauseTransition transition = new PauseTransition(Duration.seconds(0.1));
        transition.setOnFinished(event -> button.setStyle("-fx-background-color: #727272;"));
        transition.playFromStart();
    }

    public void doEnemyAnimation(String type) {
        if (type.contains((charMgr.getActiveBoss().getName() + " attacked"))) {
            Animator.attackAnimation(enemyImageView, enemyActImage, enemyImage);
            Animator.damageAnimation(playerImageView, playerHurtImage, playerImage);
        } else if (type.contains((charMgr.getActiveBoss().getName() + " gained 5 defense"))) {
            Animator.defendAnimation(enemyImageView, enemyActImage, enemyImage);
        } else if (type.contains((charMgr.getActiveBoss().getName() + " unleashed"))) {
            Animator.payTaxAnimation(enemyImageView, enemyActImage, enemyImage);
            Animator.damageAnimation(playerImageView, playerHurtImage, playerImage);
        } else if (type.contains((charMgr.getActiveBoss().getName() + " gained"))) {
            Animator.evadeTaxAnimation(enemyImageView, enemyActImage, enemyImage);
        }
    }

    public String manageTaxNumbers(Character c) {
        Integer tax = c.currentTaxAmt;
        Integer prevTax = c.previousTaxAmt;
        return (tax.toString() + " (" + prevTax.toString() + ")");
    }

    public String manageHP(ProgressBar pb, Character c) {
        pb.setProgress(c.currentHealth / 100.0);
        return ("" + c.currentHealth + " / 100");
    }

    public void manageDef(Text t1, Text t2) {
        Integer playerdef = charMgr.getPlayer().getDefendVal();
        Integer enemydef = charMgr.getActiveBoss().getDefendVal();
        t1.setText("DEF: " + playerdef.toString());
        t2.setText("DEF: " + enemydef.toString());
        t1.setFill(Color.WHITE);
        t2.setFill(Color.WHITE);
        if (charMgr.getPlayer().defMaxed)
            t1.setFill(Color.YELLOW);
        if (charMgr.getActiveBoss().defMaxed)
            t2.setFill(Color.YELLOW);
        if (charMgr.getPlayer().defDownNextTurnFlag)
            t1.setFill(Color.RED);
        if (charMgr.getActiveBoss().defDownNextTurnFlag)
            t2.setFill(Color.RED);
    }

    public void manageAtk(Text t1, Text t2) {
        Integer playeratk = charMgr.getPlayer().getAttackVal();
        Integer enemyatk = charMgr.getActiveBoss().getAttackVal();
        t1.setText("ATK: " + playeratk.toString());
        t2.setText("ATK: " + enemyatk.toString());
        t1.setFill(Color.WHITE);
        t2.setFill(Color.WHITE);
    }

}
