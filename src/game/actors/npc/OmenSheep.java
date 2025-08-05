package game.actors.npc;

import edu.monash.fit2099.engine.actions.*;
import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.actors.attributes.*;
import edu.monash.fit2099.engine.displays.*;
import edu.monash.fit2099.engine.positions.*;
import game.actions.*;
import game.conditions.RepeatableTurnCondition;
import game.statuseffects.RotEffect;
import game.behaviours.*;
import game.conditions.SpawnCondition;
import game.enums.*;
import game.grounds.plants.Inheritree;
import game.items.*;
import game.items.eggs.Egg;
import game.items.eggs.EggHandler;
import game.items.eggs.EggSelector;

import java.util.Map;
import java.util.TreeMap;

/**
 * Class representing OmenSheep.
 *
 * @author Tee Zhi Hong
 * Modified by: Nigel Wong Wei Lun, Joel Wong Sing Yue
 */
public class OmenSheep extends Actor implements TalismanCurable, Reproduceable, EggHandler, NPCControllerSelectable {

    private static final int LAY_EGG_PRIORITY = 1;
    private static final int WANDER_PRIORITY = 999;

    private NPCController npcController = new StandardNPCController();
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * OmenSheep class constructor.
     */
    public OmenSheep(){
        super("Omen Sheep", 'm', 75);
        behaviours.put(LAY_EGG_PRIORITY, new LayEggBehaviour(this, new RepeatableTurnCondition(7)));
        behaviours.put(WANDER_PRIORITY, new WanderBehaviour());
        this.addCapability(Status.ROT);
        this.addAttribute(Attribute.ROT_COUNTDOWN_TIMER, new BaseActorAttribute(10));
        this.addStatusEffect(new RotEffect());
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
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for OmenSheep.
     * @param lastAction The Action this OmenSheep took last turn. Can do interesting things in conjunction with Action.getNextAction().
     * @param map        the map containing the OmenSheep.
     * @param display    the I/O object to which messages may be written.
     * @return the valid action that can be performed in that iteration or null if no valid action is found.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!isConscious() || getAttribute(Attribute.ROT_COUNTDOWN_TIMER) <= 0) {
            display.println(unconscious(map));
            return new DoNothingAction();
        }

        return npcController.processPlayTurn(behaviours, this, map);
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current OmenSheep.
     *
     * @param otherActor the Actor that might be performing attack.
     * @param direction  String representing the direction of the other Actor.
     * @param map        Current GameMap.
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }

        if(otherActor.hasCapability((Ability.TALISMAN_CURE))){
            actions.add(new TalismanCureAction(this, map.locationOf(this), direction));
        }
        return actions;
    }

    /**
     * Use talisman to cure OmenSheep.
     *
     * @param actor Actor who executes the TalismanCureAction.
     * @param location A location that the OmenSheep to be cured by talisman is present.
     * @return whether the OmenSheep can be cured by talisman successfully as a boolean.
     */
    @Override
    public boolean talismanCure(Actor actor, Location location) {
        for (Exit exit: location.getExits()){
            Location exitDestination = exit.getDestination();
            Ground exitDestinationGround = exitDestination.getGround();
            if (exitDestinationGround.canActorEnter(actor)){
                exitDestination.setGround(new Inheritree());
            }
        }
        return true;
    }

    /**
     * A method for returning the string representation of an OmenSheep.
     * It displays the OmenSheep's name and its current hit points, along with its maximum health hit points and also the rot countdown timer.
     *
     * @return OmenSheep details in String.
     */
    @Override
    public String toString() {
        return name + " (Health: " +
                this.getAttribute(BaseActorAttributes.HEALTH) + "/" +
                this.getAttributeMaximum(BaseActorAttributes.HEALTH) + ", " +
                "Rot Countdown Timer: " + this.getAttribute(Attribute.ROT_COUNTDOWN_TIMER) + "/" +
                this.getAttributeMaximum(Attribute.ROT_COUNTDOWN_TIMER) + ")";
    }

    /**
     * Reproduce method for the Omen Sheep. The sheep lays an egg at its current location,
     * and resets its timer to lay the next egg.
     * @param actor the actor that is reproducing
     * @param location the location of the actor
     */
    @Override
    public void reproduce(Actor actor, Location location) {
        EggSelector eggSelector = new EggSelector(location);
        location.addItem(eggSelector.produceEgg());
    }

    /**
     * Hatch method for the Omen Sheep. The egg hatches into an Omen Sheep at the current location,
     * or any allowable locations surrounding it depending on the spawn condition fulfilled.
     *
     * @param currentLocation the current location of the egg
     * @return True if the egg hatches successfully, False otherwise
     */
    @Override
    public boolean hatch(Location currentLocation) {
        SpawnCondition spawnCondition = new SpawnCondition();
        OmenSheep child = new OmenSheep();
        child.selectNPCController(npcController);
        return spawnCondition.isFulfilled(child, currentLocation);
    }

    /**
     * Returns the OmenSheep object itself.
     *
     * @return this OmenSheep object
     */
    @Override
    public Actor getActor() {
        return this;
    }

    /**
     * Consume method for the Omen Sheep's Egg. Restoring 10 health points to the actor that consumes it.
     *
     * @param actor the actor that is consuming
     * @param map   the current GameMap
     * @param egg
     * @return an empty string
     */
    @Override
    public String consumeEggEffect(Actor actor, GameMap map, Egg egg) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 10);
        actor.removeItemFromInventory(egg);
        return "Increased the base health by 10 health points.";
    }

}
