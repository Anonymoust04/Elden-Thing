package game.items.eggs;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface representing the behavior of an egg that implements it.
 * @author Joel Wong Sing Yue
 * Modified By: Tee Zhi Hong
 */
public interface EggHandler {

     /**
      * Checks if the egg can hatch at the given location.
      * @param location
      * @return true if the egg can hatch, false otherwise
      */
     boolean hatch(Location location);

     /**
      * Returns the producer of the egg.
      * @return the producer of the egg
      */
     Actor getActor();

     /**
      * Consumes the egg and returns a string describing the effect.
      * @param actor the actor consuming the egg
      * @param map the map the actor is currently on
      * @param egg the egg being consumed
      * @return a string describing the effect of consuming the egg
      */
     String consumeEggEffect(Actor actor, GameMap map, Egg egg);
}
