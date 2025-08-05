package game.grounds.plants;

import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.actors.attributes.*;
import edu.monash.fit2099.engine.positions.*;
import game.enums.*;

/**
 * A class that represent a Bloodrose plant.
 *
 * @author Tee Zhi Hong
 * <p> Modified By: Nigel Wong Wei Lun </p>
 */
public class Bloodrose extends Ground implements Plant{

    /**
     * Bloodrose class constructor.
     */
    public Bloodrose() {
        super('w', "Bloodrose");
        this.addCapability(Ability.PLANT);
    }

    /**
     * Obtains the effects of plating a Bloodrose.
     *
     * @param owner Actor who owns a Bloodrose seed item.
     * @param map current game map.
     * @return whether the Bloodrose seed is planted successfully as a boolean.
     */
    @Override
    public boolean plant(Actor owner, GameMap map) {

        int requiredStamina = 75;

        if (owner.hasAttribute(BaseActorAttributes.STAMINA)) {
            if (owner.getAttribute(BaseActorAttributes.STAMINA) >= requiredStamina) {
                owner.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, requiredStamina);
            } else {
                return false;
            }
        }

        Location ownerLocation = map.locationOf(owner);
        ownerLocation.setGround(this);
        owner.hurt(5);
        return true;
    }

    /**
     * Affects the surroundings of Bloodrose as the plant blooms in each game turn.
     * <p></p>
     * <p> At each game turn (or tick) after Bloodrose blooms, it will hurt surrounding actors by 10 points. </p>
     *
     * @param location  the current Location
     */
    @Override
    public void tick(Location location) {

        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()){
                Actor otherActor = destination.getActor();
                otherActor.hurt(10);
            }

        }
    }
}
