abstract class Boss extends Character {

	public Boss(String bossName, String bossTitle) {
		super(bossName, bossTitle);
	}

	public abstract String takeTurn(Character enemy);

}
