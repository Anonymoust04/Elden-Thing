package game.actors.npc;

import edu.monash.fit2099.engine.displays.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a Branch part of the Bed of Chaos boss.
 * It can grow into more branches or leaves, leaves can heal and add damage for the boss and branches add damage.
 *
 * @author Liew Yi Wei
 * Modified by: Tee Zhi Hong
 */
public class Branch implements BossPart{

    private List<BossPart> child = new ArrayList<>();
    private int damage;
    private Random random = new Random();
    private boolean grown = false;
    private static final int BRANCH_DAMAGE = 3;

    /**
     * Constructor for Branch, initializes the damage value.
     */
    public Branch(int damage){
        this.damage = damage;
    }

    /**
     * Returns the total damage of this branch and all its child parts.
     *
     * @return total damage
     */
    @Override
    public int getDamage() {
        int totalDamage = 0;
        totalDamage += damage;
        for(BossPart part: child){
            totalDamage += part.getDamage();
        }
        return totalDamage;
    }
    /**
     * Resets the growth state of this branch and all its child parts.
     * This method is used to reset the growth state when the boss is reset.
     */
    public void resetGrowth() {
        this.grown = false;
        for (BossPart part : child) {
            part.resetGrowth();
        }
    }

    /**
     * This method will grow the branch into either a  Branch or a Leaf. And recursively calls the grow method to all its child parts.
     *
     * @param boss the Bed of Chaos boss
     * @param display the display to print messages
     */
    @Override
    public void grow(BedOfChaos boss, Display display) {

        if(!grown) {
            display.println("Branch is growing....");
            if (random.nextBoolean()) {
                display.println("It grows a Branch....\n");
                child.add(new Branch(BRANCH_DAMAGE));
            } else {
                display.println("It grows a Leaf....\n");
                child.add(new Leaf());
            }
            grown = true;
        }

        for (BossPart part : child) {
            part.grow(boss, display);
        }
    }
    /**
     * This method applies the effect of the branch, which is to heal the boss and add damage.
     * This method also call the getEffect method for all its child parts as well.
     *
     * @param boss the Bed of Chaos boss
     * @param display the display to print messages
     */
    @Override
    public void getEffect(BedOfChaos boss, Display display) {

        for (BossPart part: child){
            part.getEffect(boss, display);
        }
    }
    /**
     * This method returns the list of child parts of this branch.
     *
     * @return list of child parts
     */
    @Override
    public List<BossPart> getGrowPart() {
        return child;
    }

}
