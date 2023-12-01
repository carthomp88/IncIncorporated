public class Boss2 extends Boss {

    public Boss2(String _name, String _title) {
        super(_name, _title);
    }

    public String takeTurn(Character Opponent) {
        if (currentDefense < DEF_INCREMENT * 2)
            return this.defend();
        if (Opponent.getDefendVal() == 0)
            return this.attack(Opponent);
        if (currentTaxAmt > MAX_HEALTH / 4)
            return this.payTax(Opponent);
        return evadeTax();
    }

}