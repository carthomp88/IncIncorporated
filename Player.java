public class Player extends Character
{
    public Player()
    {
        super("Jerry");
    }

    public void playerTurn()
    {
        //Allow the player to select action option.
        //Not sure if this should be implemented in the GUI code or in here.
        this.endTurnEvents();
    }



}