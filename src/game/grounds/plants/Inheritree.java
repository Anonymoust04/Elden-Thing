package game.grounds.plants;

import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.actors.attributes.*;
import edu.monash.fit2099.engine.positions.*;
import game.enums.*;
import game.grounds.*;

/**
 * A class that represent an Inheritree plant item.
 *
 * @author Tee Zhi Hong
 * <p> Modified By: Nigel Wong Wei Lun </p>
 */
public class Inheritree extends Ground implements Plant {

    /**
     * Inheritree Class Constructor.
     */
    public Inheritree() {
        super('t', "Inheritree");
        this.addCapability(Status.BLESSED);
        this.addCapability(Ability.PLANT);
    }

    /**
     * Obtains the effects of plating an Inheritree.
     *
     * @param owner Actor who owns a seed item.
     * @param map current game map.
     * @return whether the Inheritree seed is planted successfully as a boolean.
     */
    @Override
    public boolean plant(Actor owner, GameMap map) {

        int requiredStamina = 25;

        if (owner.hasAttribute(BaseActorAttributes.STAMINA)) {
            if (owner.getAttribute(BaseActorAttributes.STAMINA) >= requiredStamina) {
                owner.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, requiredStamina);
            } else {
                return false;
            }
        }

        Location ownerLocation = map.locationOf(owner);
        ownerLocation.setGround(this);
        for (Exit exit : ownerLocation.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround().hasCapability(Status.CURSED)){
                destination.setGround(new Soil());
            }
        }

        return true;
    }

    /**
     * Affects the surrounding actors when Inheritree is blooming in each game turn.
     *
     * @param location current Location.
     */
    public void bloomEffectActor(Location location){
        Actor actor = location.getActor();
        actor.heal(5);
        if (actor.hasAttribute(BaseActorAttributes.STAMINA)) {
            actor.modifyAttribute(
                    BaseActorAttributes.STAMINA,
                    ActorAttributeOperations.INCREASE,
                    5
            );
        }
        if(actor.hasCapability(Status.ROT)){

        }
    }

    /**
     * Affects the surroundings of Inheritree as the plant blooms in each game turn.
     * <p></p>
     * <p> At each turn after the “Inheritree” blooms, it will heal any
     * actor within its surroundings by 5 points and
     * restore their stamina by 5 points. </p>
     *
     * @param location the current Location.
     */
    @Override
    public void tick(Location location) {

        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()){
                bloomEffectActor(destination);
            }

        }
    }

//    @Override
//    public String shovel(Actor actor, GameMap map) {
//        int requiredStamina = 25;
//
//        if (!actor.hasAttribute(BaseActorAttributes.STAMINA) || actor.getAttribute(BaseActorAttributes.STAMINA) < requiredStamina) {
//            return actor + " does not have enough stamina to shovel the Bloodrose.";
//        }
//
//        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, requiredStamina);
//
//        Location location  = map.locationOf(actor);
//        for (Exit exit : location.getExits()) {
//            Location destination = exit.getDestination();
//            if (destination.getGround().hasCapability(Ability.PLANT)) {
//                destination.setGround(new Soil());
//                return "The Inheritree has been shovelled from the soil.";
//            }
//        }
//        return "You cannot shovel the Inheritree as it is not planted in the soil.";
//    }
//
//    @Override
//    public ActionList allowableActions(Actor actor, Location location, String direction) {
//        ActionList actions = new ActionList();
//        if (actor.hasCapability(Ability.GARDENING_SHOVEL)) {
//            actions.add(new game.actions.ShovelAction(this));
//        }
//        return actions;
//    }

}
