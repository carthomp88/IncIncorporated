import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class RoundManager {
	private static int roundCount;
	private static int activeLevel = 1;
	private static final RoundManager instance = new RoundManager();
	private static CharacterManager charMgr = CharacterManager.getInstance();
	// The Music Player
	private MediaPlayer mp;

	protected RoundManager() {
		roundCount = 0;
	}

	public static RoundManager getInstance() {
		return instance;
	}

	public void startMusic() {
		if (getActiveLevel() == 1)
			mp = new MediaPlayer(new Media(Paths.get("music/TannerBGM.wav").toUri().toString()));
		else if (getActiveLevel() == 2)
			mp = new MediaPlayer(new Media(Paths.get("music/hrBGM.wav").toUri().toString()));
		else
			mp = new MediaPlayer(new Media(Paths.get("music/ceoBGM.wav").toUri().toString()));
		mp.play();
	}

	public int getRoundCount() {
		return roundCount;
	}

	public int getActiveLevel() {
		return activeLevel;
	}

	public String round(String type) {
		String holder = "";
		if (type.equals("attack")) {
			holder = charMgr.getPlayer().attack(charMgr.getActiveBoss());
		} else if (type.equals("defend")) {
			holder = charMgr.getPlayer().defend();
		} else if (type.equals("pay")) {
			holder = charMgr.getPlayer().payTax(charMgr.getActiveBoss());
		} else if (type.equals("evade")) {
			holder = charMgr.getPlayer().evadeTax();
		} else {
			return "error";
		}
		holder = holder + "\nBREAK!" + charMgr.getActiveBoss().takeTurn(charMgr.getPlayer());
		if (charMgr.getPlayer().getCurrentHealth() <= 0) {
			mp.stop();
			MenuManager.changeScreen(MenuManager.ScreenName.GAMEOVER);
			charMgr.getActiveBoss().reset();
			charMgr.getPlayer().reset();
			return holder;
		}
		if (charMgr.getActiveBoss().getCurrentHealth() <= 0) {
			if (activeLevel == 3) {
				activeLevel = 0;
				mp.stop();
				MenuManager.changeScreen(MenuManager.ScreenName.GAMEWIN);
				charMgr.getActiveBoss().reset();
				charMgr.getPlayer().reset();
			} else {
				mp.stop();
				MenuManager.changeScreen(MenuManager.ScreenName.LEVELWIN);
				charMgr.getActiveBoss().reset();
				charMgr.getPlayer().reset();
			}
			activeLevel++;
			charMgr.setBossLevel(activeLevel);
			return holder;
		}
		charMgr.getPlayer().endTurnEvents();
		charMgr.getActiveBoss().endTurnEvents();
		roundCount++;
		return holder;
	}

}
