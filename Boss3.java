public class Boss3 extends Boss {
    private boolean needAttack = false; // next attack will win the game
    private boolean needPayTax = false; // next pay tax will win the game
    private boolean needDefend = false; // Defense is 0 OR not defending could result in losing the game next turn.

    private boolean shouldPayTax = false;
    private boolean shouldAttack = false; // Attacking will do full damage to an enemy
    private boolean shouldEvade = false; // Building tax will not increase prevtax amt to a value at which the character
    private boolean shouldDefend = false; // Defense is not enough to nullify opponent tax amount or previous tax amt

    private boolean canDefend = false; // Defense is not maxed out
    private boolean canAttack = false; // Attack does some damage
    private boolean canPayTax = false; // PayTax does some damage

    public Boss3(String _name, String _title) {
        super(_name, _title);
    }

    public void turnScoper(Character Opponent) {
        // NEEDS = Game-ending moves

        if (Opponent.getCurrentHealth() + Opponent.getDefendVal() - currentTaxAmt <= 0
                && currentHealth + currentDefense - previousTaxAmt > 0)
            needPayTax = true;
        if (Opponent.getCurrentHealth() + Opponent.getDefendVal() - currentAttack <= 0)
            needAttack = true;
        if (currentDefense == 0 || currentHealth + currentDefense - currentAttack <= 0
                || currentHealth + currentDefense - Opponent.getCurrentTaxAmt() <= 0)
            needDefend = true;

        // SHOULDS = Good ideas

        if (Opponent.getDefendVal() == 0)
            shouldAttack = true;
        if (currentDefense < DEF_INCREMENT * 3
                && (Opponent.getCurrentTaxAmt() - currentDefense > 0 || previousTaxAmt > currentDefense))
            shouldDefend = true;
        if ((currentHealth + currentDefense) - (currentTaxAmt + Opponent.getCurrentTaxAmt()) > 0 && currentTaxAmt < MAX_TAX_AMMOUNT)
            shouldEvade = true;
        if (currentHealth + currentDefense - previousTaxAmt < 10 && currentHealth + currentDefense - previousTaxAmt > 0)
            shouldPayTax = true;

        // CANS = Well... it does SOMETHING

        if (currentAttack > Opponent.getDefendVal())
            canAttack = true;
        if (currentDefense < MAX_DEFENSE)
            canDefend = true;
        if (currentHealth + currentDefense - previousTaxAmt > 0 && currentTaxAmt > DEF_INCREMENT * 3)
            canPayTax = true;

    }

    public String takeTurn(Character Opponent) {
        setFalse();
        turnScoper(Opponent);
        if (needAttack)
            return this.attack(Opponent);
        if (needPayTax)
            return this.payTax(Opponent); // TODO: aksfjlsdkfja;s
        if (needDefend)
            return this.defend();

        if (shouldAttack)
            return this.attack(Opponent);
        if (shouldPayTax)
            return this.payTax(Opponent);
        if (shouldDefend)
            return this.defend();
        if (shouldEvade)
            return this.evadeTax();

        if (canAttack)
            return this.attack(Opponent);
        if (canDefend)
            return this.defend();
        if (canPayTax)
            return this.payTax(Opponent);

        return this.defend();

    }

    private void setFalse() {
        needAttack = false; // next attack will win the game
        needPayTax = false; // next pay tax will win the game
        needDefend = false; // Defense is 0 OR not defending could result in losing the game next turn.

        shouldPayTax = false;
        shouldAttack = false; // Attacking will do full damage to an enemy
        shouldEvade = false; // Building tax will not increase prevtax amt to a value at which the
                             // character can be killed by prevtax amt
        shouldDefend = false; // Defense is not enough to nullify opponent tax amount or previous tax
                              // amt

        canDefend = false; // Defense is not maxed out
        canAttack = false; // Attack does some damage
        canPayTax = false; // PayTax does some damage
    }

}