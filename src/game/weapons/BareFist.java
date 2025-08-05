package game.weapons;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * Class representing an intrinsic weapon called a bare fist.
 * This intrinsic weapon deals 25 damage points with a 50% chance
 * to hit the target.
 *
 * @author Adrian Kristanto
 * <p> Modified By: Tee Zhi Hong </p>
 */
public class BareFist extends IntrinsicWeapon {
    /**
     * BareFist class constructor.
     */
    public BareFist() {
        super(25, "punches", 50);
    }
}
