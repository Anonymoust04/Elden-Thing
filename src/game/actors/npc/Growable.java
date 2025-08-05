package game.actors.npc;

import edu.monash.fit2099.engine.displays.Display;
/**
 * Interface representing a growable entity in the game.
 * Classes implementing this interface should define how they grow.
 *
 * @author Liew Yi Wei
 */
public interface Growable {

    /**
     * Method to grow the entity.
     *
     * @param display the display to show messages during the growing process
     */
    void grow(Display display);
}
