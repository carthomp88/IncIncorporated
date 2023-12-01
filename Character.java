public abstract class Character {
    // Subclasses share these:
    // Constants:
    protected final int MAX_HEALTH = 100;
    protected final int MAX_DEFENSE = 15; // DEF_INCREMENT * 3
    protected final int MAX_TAX_AMMOUNT = 55;
    protected final int BASE_TAX = 2;
    protected final int BASE_DEFENSE = 0;
    protected final int BASE_TURN_COUNTER = 1;
    protected final int ATTACKVAL = 7;
    protected final int DEF_INCREMENT = 5;
    protected final int DEF_DECREMENT = 5;
    protected final int DEF_WEAR_OFF = 3;
    // Data:
    protected String name;
    protected String title = "";
    protected int currentHealth;
    protected int previousTaxAmt;
    protected int currentTaxAmt;
    protected int currentAttack;
    protected int currentDefense;
    protected int turnCounter = 1;
    protected int defenseTurnNumber;
    protected boolean defDownNextTurnFlag;
    protected boolean defMaxed;
    protected boolean taxMaxed;

    // Constructor initializes characters to default settings
    // Parameter: String _name is the name of the character
    public Character(String _name, String _title) {
        name = _name;
        title = _title;
        currentHealth = MAX_HEALTH;
        previousTaxAmt = BASE_TAX - 1;
        currentTaxAmt = BASE_TAX;
        currentDefense = BASE_DEFENSE;
        currentAttack = ATTACKVAL;
    }

    public Character(String _name) {
        name = _name;
        currentHealth = MAX_HEALTH;
        previousTaxAmt = BASE_TAX - 1;
        currentTaxAmt = BASE_TAX;
        currentDefense = BASE_DEFENSE;
        currentAttack = ATTACKVAL;
    }

    public void reset() {
        currentHealth = MAX_HEALTH;
        previousTaxAmt = BASE_TAX - 1;
        currentTaxAmt = BASE_TAX;
        currentDefense = BASE_DEFENSE;
        currentAttack = ATTACKVAL;
    }

    // getter for character's defense value
    public int getDefendVal() {
        return currentDefense;
    }

    public int getAttackVal() {
        return currentAttack;
    }

    // getter for character's health value
    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getCurrentTaxAmt() {
        return currentTaxAmt;
    }

    // getter for character's name
    public String getName() {
        return name;
    }

    // Damage Received SPECS
    // Param: amount of damage incoming
    // If the character's current defense is higher than the incoming damage...
    // do nothing
    // OTHERWISE,
    // subtract the character's health by the incoming damage less their current
    // defense.
    // Return the amount of hp subtracted from the character.
    public int damageReceived(int damage) {
        if (currentDefense > damage)
            return 0;
        currentHealth -= (damage - currentDefense);
        return damage - currentDefense;
    }

    // Attack SPECS
    // Param: Character object to be attacked
    // If the Opponent's defense value is less than the universal attack value...
    // decrease the opponent's health by the universal attack value minus the
    // opponent's defense value
    // Otherwise...
    // Send message to GUI that attack did nothing.
    public String attack(Character Opponent) {
        return name + " attacked " + Opponent.getName() + " for " + Opponent.damageReceived(currentAttack) + "HP";
    }

    // defend SPECS
    // if defense has been played less than 3 times...
    // Increases the amount of times defense has been played
    // equates the turn counter for defense to the current turn counter
    // raises defense by 3
    public String defend() {
        defenseTurnNumber = turnCounter;
        if (currentDefense < MAX_DEFENSE) {
            currentDefense += DEF_INCREMENT;

            if (currentDefense > MAX_DEFENSE) {
                currentDefense = MAX_DEFENSE;
            }
            if (currentDefense == MAX_DEFENSE)
                defMaxed = true;
            else
                defMaxed = false;
            return name + " gained " + DEF_INCREMENT + " defense for a total of " + currentDefense + "!";
        } else {
            return "Defense is already maxed out!";
            // ALLOW THE PLAYER TO SELECT A DIFFERENT MOVE
        }
    }

    // payTax SPEC
    // returns String stating outcome of action
    // Attacks opponent with the current tax amount
    // Loses HP equal to the previous tax value
    // Resets both tax values to base amounts.
    public String payTax(Character Opponent) {
        // Display on the main screen that the player and enemy were affected by the tax
        String output = name + " unleashed " + Opponent.damageReceived(currentTaxAmt) + " tax hits on "
                + Opponent.getName() + "! "
                + name + " took a hit of " + this.damageReceived(previousTaxAmt) + "! " + "Tax has been reset to "
                + BASE_TAX + ".";
        currentTaxAmt = BASE_TAX;
        previousTaxAmt = BASE_TAX - 1;
        taxMaxed = false;
        return output;
    }

    // evadeTax SPECs
    // returns String stating outcome of action
    // Adds the previous tax amount to the current tax amount
    // and sets the previous tax amount to the new previous amount
    public String evadeTax() {
        if (currentTaxAmt >= MAX_TAX_AMMOUNT)
            return "Max Tax amount!";
        currentAttack++;
        int tempPrevTaxAmt = previousTaxAmt;
        previousTaxAmt = currentTaxAmt;
        int tempCurrentTax = currentTaxAmt;
        currentTaxAmt += tempPrevTaxAmt;
        if (currentTaxAmt >= MAX_TAX_AMMOUNT)
            taxMaxed = true;
        else
            taxMaxed = false;
        return name + " gained " + (currentTaxAmt - tempCurrentTax) + " tax points! Fine is now set to "
                + previousTaxAmt + ". Attack is now " + currentAttack + "!";
    }

    // defenseEndConditionHelper SPEC
    // To be used within the endTurnEvents method
    // If the character has defense above 0...
    // defense is decreased by the defaut defense decrementor.
    // less than 0 safeguard
    public void defenseEndConditionHelper() {
        if (currentDefense >= DEF_INCREMENT && turnCounter > defenseTurnNumber + DEF_WEAR_OFF) {
            currentDefense -= DEF_DECREMENT;
            defMaxed = false;
            defenseTurnNumber = turnCounter;
            if (currentDefense < 0) {
                currentDefense = 0;
            }
            defDownNextTurnFlag = false;
        } else if (currentDefense != 0 && turnCounter == defenseTurnNumber + DEF_WEAR_OFF) {
            defDownNextTurnFlag = true;
        } else
            defDownNextTurnFlag = false;
    }

    public void endTurnEvents() {
        turnCounter++;
        defenseEndConditionHelper();
    }
}
