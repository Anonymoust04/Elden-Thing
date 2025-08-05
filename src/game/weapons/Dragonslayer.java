package game.weapons;

import edu.monash.fit2099.engine.actors.attributes.*;
import edu.monash.fit2099.engine.items.*;
import game.items.*;
import game.sideeffects.*;

import java.util.*;

/**
 * Class representing a weapon item called Dragonslayer Greatsword.
 * This weapon item deals 70 damage points with a 75% chance
 * to hit the target.
 *
 * @author Tee Zhi Hong
 */
public class Dragonslayer extends WeaponItem implements Purchasable {

    /**
     * Dragonslayer class constructor.
     */
    public Dragonslayer(){
        super("Dragonslayer Greatsword", 'D', 70, "strikes",75);
    }


    /**
     * Give a Dragonslayer Greatsword item from the merchant after a successful purchase.
     * @return a Dragonslayer Greatsword Item from the merchant after a successful purchase.
     */
    @Override
    public Item giveItem() {
        return this;
    }

    /**
     * Gives the side effects when successfully purchasing a Dragonslayer Greatsword.
     * @return A list of SideEffects after successfully purchasing a Dragonslayer Greatsword.
     */
    @Override
    public List<SideEffect> getPurchaseSideEffects() {
        ArrayList<SideEffect> purchaseSideEffects = new ArrayList<>();
        purchaseSideEffects.add(new MaxActorAttributeEffect(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 15));
        return purchaseSideEffects;
    }
}
