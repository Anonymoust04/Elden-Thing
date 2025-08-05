package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.*;
import game.grounds.plants.Plant;
import game.items.*;

/**
 * Class representing an action to plant a plant-able item.
 *
 * @author Tee Zhi Hong
 */
public class PlantAction extends Action  {

    private Seed seed;
    private Plant plant;

    /**
     * PlantAction class constructor.
     *
     * @param seed the Seed to be planted.
     */
    public PlantAction(Seed seed, Plant plant) {
        this.seed = seed;
        this.plant = plant;
    }

    /**
     * When the plant action is executed, seed will be removed from the inventory of an Actor and plants it onto the ground that can be planted.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing which seed will be planted.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        boolean isPlanted = plant.plant(actor, map);
        if (isPlanted){
            actor.removeItemFromInventory(seed);
            return "Actor is able to plant the " + seed + " successfully";
        } else{
            return "Plant Action Error: Unable to plant the " + seed + " seed";
        }
    }

    /**
     * Describe what action will be performed if PlantAction is chosen in the menu.
     *
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " plants the " + seed + " seed";
    }

}
