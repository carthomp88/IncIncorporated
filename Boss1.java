public class Boss1 extends Boss {

    public Boss1(String _name, String _title) {
        super(_name, _title);
    }

    // Boss1 defends if he has no defense.
    // If he has at least 1 defense, he randomly selects another option (including
    // defense)
    public String takeTurn(Character Opponent) {
        if (this.currentDefense < DEF_INCREMENT) {
            return this.defend();
        }

        int attackRandomInt = (int) (Math.random() * 101); // credit to w3schools for the help on this
        if (attackRandomInt <= 25) {
            return this.attack(Opponent);
        } else if (attackRandomInt > 25 && attackRandomInt <= 50) {
            return this.defend();
        } else if (attackRandomInt > 50 && attackRandomInt <= 75) {
            return this.evadeTax();
        } else {
            return this.payTax(Opponent);
        }

        // Not sure if this should be implemented in the GUI code or in here.
    }

}
