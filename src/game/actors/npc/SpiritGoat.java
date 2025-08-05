package game.actors.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.*;
import game.statuseffects.RotEffect;
import game.behaviours.WanderBehaviour;
import game.conditions.NearStatusCondition;
import game.conditions.SpawnCondition;
import game.enums.Ability;
import game.enums.Attribute;
import game.enums.Status;
import game.items.TalismanCurable;

import java.util.Map;
import java.util.TreeMap;

/**
 * Class representing SpiritGoat.
 *
 * @author Tee Zhi Hong
 * Modified by: Nigel Wong Wei Lun
 */
public class SpiritGoat extends Actor implements TalismanCurable, Reproduceable, NPCControllerSelectable {
    private static final int WANDER_PRIORITY = 999;

    /**
     * The NPC controller for SpiritGoat.
     */
    private NPCController npcController = new StandardNPCController();

    /**
     * A map of behaviours for SpiritGoat.
     * The key is an integer representing the priority of the behaviour.
     * The value is the Behaviour object.
     */
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * SpiritGoat class constructor.
     */
    public SpiritGoat() {
        super("Spirit Goat", 'y', 50);
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
     * At each turn, an action is selected based on the behaviour of the SpiritGoat.
     *
     * @param actions    collection of possible Actions for SpiritGoat.
     * @param lastAction The Action this SpiritGoat last turn. Can do interesting things in conjunction with Action.getNextAction().
     * @param map        the map containing the SpiritGoat.
     * @param display    the I/O object to which messages may be written.
     * @return the valid action that can be performed in that iteration or DoNothingAction() if no valid action is found.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!isConscious() || getAttribute(Attribute.ROT_COUNTDOWN_TIMER) <= 0) {
            display.println(unconscious(map));
            return new DoNothingAction();
        }

        reproduce(this, map.locationOf(this));

        return npcController.processPlayTurn(behaviours, this, map);
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current SpiritGoat.
     *
     * @param otherActor the Actor that might be performing attack current SpiritGoat
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A list of actions that can act on this SpiritGoat as an ActionList
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
     * Use talisman to cure SpiritGoat.
     *
     * @param actor Actor who executes the TalismanCureAction
     * @param location A location that the SpiritGoat to be cured by talisman is present.
     * @return whether the SpiritGoat can be cured by talisman successfully as a boolean.
     */
    @Override
    public boolean talismanCure(Actor actor, Location location) {
        this.modifyAttribute(Attribute.ROT_COUNTDOWN_TIMER, ActorAttributeOperations.UPDATE, getAttributeMaximum(Attribute.ROT_COUNTDOWN_TIMER));
        return true;
    }

    /**
     * A method for returning the string representation of a SpiritGoat.
     * It displays the SpiritGoat's name and its current hit points, along with its maximum health hit points and also the rot countdown timer.
     *
     * @return SpiritGoat details in String
     */
    @Override
    public String toString() {
        return name + " (Health: " +
                this.getAttribute(BaseActorAttributes.HEALTH) + "/" +
                this.getAttributeMaximum(BaseActorAttributes.HEALTH) + ", " +
                "Rot Countdown Timer: " + this.getAttribute(Attribute.ROT_COUNTDOWN_TIMER) + "/" +
                this.getAttributeMaximum(Attribute.ROT_COUNTDOWN_TIMER) +
                ")";
    }

    /**
     * The reproduce method that allows the Goat to reproduce. The method passes in the required status condition and
     * the location of the goat to check if any BLESSED entities are surrounding it to fulfil the spawn condition.
     * And then checks if there is any valid location to spawn the entity beside the goat.
     * @param actor the actor that is reproducing
     * @param currentLocation the location where the new entity will spawn
     *
     */
    public void reproduce(Actor actor, Location currentLocation) {
        NearStatusCondition nearStatusCondition = new NearStatusCondition(Status.BLESSED, currentLocation);
        if (nearStatusCondition.isFulfilled(actor, currentLocation)){
            SpawnCondition spawnCondition = new SpawnCondition();
            SpiritGoat child = new SpiritGoat();
            child.selectNPCController(npcController);
            spawnCondition.isFulfilled(child, currentLocation);
        }
    }
}
