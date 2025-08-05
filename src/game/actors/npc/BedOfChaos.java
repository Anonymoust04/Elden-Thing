package game.actors.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.actions.ConsumeAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.GrowBehaviour;
import game.enums.Status;
import game.weapons.BossFist;

import java.util.Map;
import java.util.TreeMap;
/**
 * Class representing the Bed of Chaos boss NPC.
 * <p>
 * This class extends Actor and implements Growable.
 * </p>
 *
 * @author Liew Yi Wei
 * Modified by: Tee Zhi Hong
 */
public class BedOfChaos extends Actor  implements Growable, NPCControllerSelectable {
    private static final int ATTACK_PRIORITY = 1;
    private static final int GROW_PRIORITY = 2;

    private NPCController npcController = new StandardNPCController();

    private Map<Integer, Behaviour> behaviours = new TreeMap<>();

    private BossPart rootBranch;
    private static final int ROOT_BRANCH_DAMAGE = 0;

    /**
     * Constructor for BedOfChaos.
     */
    public BedOfChaos(){
        super("Bed of Chaos", 'T', 1000);
        this.setIntrinsicWeapon(new BossFist(this));
        behaviours.put(ATTACK_PRIORITY, new AttackBehaviour());
        behaviours.put(GROW_PRIORITY, new GrowBehaviour(this));
        rootBranch = new Branch(ROOT_BRANCH_DAMAGE);
    }

    /**
     * Sets the NPCController during the initialisation.
     * @param npcController NPCController selected during the initialisation.
     */
    @Override
    public void selectNPCController(NPCController npcController) {
        this.npcController = npcController;
    }

    /**
     * This method is to grow the branch of the Bed of Chaos.
     *
     * @param display, which is used to print messages to the console
     *
     */
    @Override
    public void grow(Display display){
        display.println(this + " is growing....");
        rootBranch.grow(this, display);
    }

    /**
     * This method is to get the total damage of the boss.
     *
     * @return the total damage of the boss dealing.
     */
    public int getTotalDamage(){
        return rootBranch.getDamage();
    }

    /**
     * This method is to reset the growth of the Bed of Chaos.
     * It resets the growth of the root branch and all its child branches.
     */
    public void resetGrow(){
        rootBranch.resetGrowth();
    }

    /**
     * Select and return an action to perform by the Bed of Chaos at each play turn of the game.
     *
     * @return the action to be performed by BedOfChaos.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!isConscious()) {
            display.println(unconscious(map));
            return new DoNothingAction();
        }
        resetGrow();

        return npcController.processPlayTurn(behaviours, this, map);
    }

    /**
     * Returns a list of actions that otherActor can perform to the Bed of Chaos.
     *
     * @param otherActor The actor performing the action.
     * @param direction The direction of the other action
     * @param map Current GameMap
     *
     * @return  A list of actions that otherActor can perform to the Bed of Chaos.
     */

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        ActionList actions = super.allowableActions(otherActor, direction, map);

        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }

        return actions;
    }

    /**
     * The toString method for BedOfChaos.
     * @return a string representation of the BedOfChaos, including its name and health status.
     */
    @Override
    public String toString() {
        return name + " (Health: " +
                this.getAttribute(BaseActorAttributes.HEALTH) + "/" +
                this.getAttributeMaximum(BaseActorAttributes.HEALTH) + ")";
    }
}
