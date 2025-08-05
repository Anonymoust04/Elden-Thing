package game.items;

import edu.monash.fit2099.engine.actions.*;
import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.items.*;
import edu.monash.fit2099.engine.positions.*;
import game.actions.*;
import game.enums.*;
import game.grounds.plants.*;

/**
 * Class representing a seed item.
 *
 * @author Tee Zhi Hong
 */
public class Seed extends Item {

    private Plant plant;

    /**
     * Seed class constructor.
     *
     * @param name name of the plant seed.
     * @param displayChar display character of the plant seed.
     * @param portable true if and only if the plant seed can be picked up.
     */
    public Seed(String name, char displayChar, boolean portable, Plant plant){
        super(name, displayChar, portable);
        this.plant = plant;
    }

    /**
     * Checks whether the plant seed item is suitable to be planted at that location.
     *
     * @param location a Location that the plant seed would be planted.
     * @return tells whether the plant seed item is suitable to be planted at that location in boolean.
     */
    public boolean canPlant(Location location){
        Ground locationGround = location.getGround();
        return locationGround.hasCapability(Ability.PLANT_SUITABLE);
    }


    /**
     * List of allowable actions that the plant seed item can perform to its owner
     * or to the current map while being carried by an actor.
     *
     * @param owner the actor that owns the plant seed item.
     * @param map the map where the actor is performing the action on.
     * @return an unmodifiable list of Actions.
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        Location ownerLocation = map.locationOf(owner);

        if (canPlant(ownerLocation)){
            actions.add(new PlantAction(this, plant));
        }

        return actions;
    }


}
