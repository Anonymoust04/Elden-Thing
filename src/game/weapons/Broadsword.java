package game.weapons;

import edu.monash.fit2099.engine.actors.attributes.*;
import edu.monash.fit2099.engine.items.*;
import game.items.*;
import game.sideeffects.*;

import java.util.*;

/**
 * Class representing a weapon item called Broadsword.
 * This weapon item deals 30 damage points with a 50% chance
 * to hit the target.
 *
 * @author Tee Zhi Hong
 */
public class Broadsword extends WeaponItem implements Purchasable {

    /**
     * Broadsword class constructor.
     */
    public Broadsword(){
        super("Broadsword", 'b', 30, "slashes",50);
    }

    /**
     * Give a Broadsword item from the merchant after a successful purchase.
     * @return a Broadsword Item from the merchant after a successful purchase.
     */
    @Override
    public Item giveItem() {
        return this;
    }

    /**
     * Gives the side effects when successfully purchasing a Broadsword.
     * @return A list of SideEffects after successfully purchasing a Broadsword.
     */
    @Override
    public List<SideEffect> getPurchaseSideEffects() {
        ArrayList<SideEffect> purchaseSideEffects = new ArrayList<>();
        purchaseSideEffects.add(new ActorAttributeEffect(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 10));
        return purchaseSideEffects;
    }
}
