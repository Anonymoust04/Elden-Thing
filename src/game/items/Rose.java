package game.items;

import edu.monash.fit2099.engine.actors.attributes.*;

/**
 * Class representing a "giveable" item called Rose.
 * This item will increase the affection level of any affectionate actors (e.g. Guts) by 10.
 *
 * @author Tee Zhi Hong
 */
public class Rose extends GiveableItem {

    /**
     * Rose Class Constructor
     */
    public Rose(){
        super("Rose", 'r', ActorAttributeOperations.INCREASE, 10);
    }

}
