public class CharacterManager {
	private Player player;
	private Boss activeBoss;
	private static final CharacterManager instance = new CharacterManager();

	protected CharacterManager() {
		player = new Player();
		activeBoss = new Boss1("Tanner", " from Sales");
	}

	public static CharacterManager getInstance() {
		return instance;
	}

	public Player getPlayer() {
		return player;
	}

	public Boss getActiveBoss() {
		return activeBoss;
	}

	public void setBossLevel(int level) {
		switch (level) {
			case 2:
				activeBoss = new Boss2("Margaret", " from HR");
				break;
			case 3:
				activeBoss = new Boss3("A. A. Ron", " the CEO");
				break;
		}
	}

}
