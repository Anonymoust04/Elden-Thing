package game.items.eggs;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.conditions.Condition;
import game.items.Consumable;

/**
 * Abstract class representing an egg in the game.
 * This class extends the Item class and provides a base for all egg types.
 * Eggs are items that can be carried by actors and be consumed, or spawn new entities.
 * @author Joel Wong Sing Yue
 * Modified By: Tee Zhi Hong
 */
public class Egg extends Item implements Consumable {

    private Condition hatchCondition;
    private EggHandler eggCreature;

    /**
     * Constructor.
     *
     *
     * @param eggCreature Creature inside the Egg.
     * @param hatchCondition Condition of hatching an Egg.
     */
    public Egg(EggHandler eggCreature, Condition hatchCondition) {
        super("Egg", '0', true);
        this.eggCreature = eggCreature;
        this.hatchCondition = hatchCondition;
    }

    /**
     * Returns a string representation of the egg.
     *
     */
    @Override
    public String toString() {
        return super.toString() + " produced by " + eggCreature;
    }

    /**
     * Actions that can be performed on the egg.
     *
     * @param owner the actor that owns the item
     * @param map   the map where the actor is performing the action on
     * @return A list of actions that can be performed on the egg
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = super.allowableActions(owner, map);
        if (owner.getItemInventory().contains(this)) {
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }

    /**
     * Allows the egg to experience the passage of time on the ground, decreasing the incubation time by 1 each turn.
     * If the incubation time reaches 0, the hatch method is called to hatch the egg.
     * @param currentLocation The location of the egg.
     */
    @Override
    public void tick(Location currentLocation) {
        if (hatchCondition.isFulfilled(eggCreature.getActor(), currentLocation) && eggCreature.hatch(currentLocation)){
            currentLocation.removeItem(this);
        }
    }

    /**
     * Consumes the egg, handling the logic of what happens when the egg is consumed.
     * @param actor the actor that is consuming the egg
     * @param map the current GameMap
     * @return a string describing the result of the consumption
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        String effectResult = eggCreature.consumeEggEffect(actor, map,this);
        return actor + " succesfully consumes the egg " + effectResult;
    }
}