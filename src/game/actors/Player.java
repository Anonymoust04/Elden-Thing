package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.displays.FancyMessage;
import game.enums.Ability;
import game.weapons.BareFist;
import game.enums.Status;

/**
 * Class representing the Player.
 *
 * @author Adrian Kristanto
 * <p> Modified by: Tee Zhi Hong, Nigel Wong Wei Lun </p>
 */
public class Player extends Actor {


    /**
     * Player class constructor.
     *
     * @param name        Name to call the player in the UI.
     * @param displayChar Character to represent the player in the UI.
     * @param hitPoints   Player's starting number of hit points.
     */
    public Player(String name, char displayChar, int hitPoints, int stamina) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(stamina));
        this.setIntrinsicWeapon(new BareFist());
        this.addCapability(Ability.LISTENABLE);
        this.addCapability(Ability.FOLLOWABLE);
        this.addCapability(Ability.PURCHASE_CAPABLE);
        this.addCapability(Ability.TELEPORT);
        this.addCapability(Ability.GIVE_AFFECTION);
    }

    /**
     * Select and return an action to perform by the player on the current turn.
     *
     * @param actions    collection of possible Actions for this Player.
     * @param lastAction The Action this Player took last turn. Can do interesting things in conjunction with Action.getNextAction().
     * @param map        the map containing the Player.
     * @param display    the I/O object to which messages may be written.
     * @return the Action to be performed.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
       if (!isConscious()){
           display.println(unconscious(map));
           display.print(FancyMessage.YOU_DIED);
           return new DoNothingAction();
       }

        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * A method for returning the string representation of a Player.
     * It displays the Player's name and its current hit points, along with its maximum health hit points.
     *
     * @return Player details in String.
     */
    @Override
    public String toString() {
        return name + " (Health: " +
                this.getAttribute(BaseActorAttributes.HEALTH) + "/" +
                this.getAttributeMaximum(BaseActorAttributes.HEALTH) + "," +
                " Stamina: " + this.getAttribute(BaseActorAttributes.STAMINA) + "/" +
                this.getAttributeMaximum(BaseActorAttributes.STAMINA) +
                ", Runes: " + this.getBalance() +
                ")";
    }
}
