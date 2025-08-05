package game.items;

import edu.monash.fit2099.engine.actors.attributes.*;

/**
 * Class representing a "giveable" item called Diamond.
 * This item will increase the affection level of any affectionate actors (e.g. Guts) by 30.
 *
 * @author Tee Zhi Hong
 * Modified By: Nigel Wong Wei Lun
 */
public class Diamond extends GiveableItem {

    /**
     * Diamond Class Constructor
     */
    public Diamond(){
        super("Diamond", 'v', ActorAttributeOperations.INCREASE, 30);
    }

}
