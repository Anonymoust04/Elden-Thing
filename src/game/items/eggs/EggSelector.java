package game.items.eggs;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.npc.GoldenBeetle;
import game.actors.npc.OmenSheep;
import game.conditions.Condition;
import game.conditions.NearStatusCondition;
import game.conditions.RepeatableTurnCondition;
import game.enums.Status;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * A class that selects a random egg to be produced.
 * The eggs are selected based on the conditions specified in the eggContents map.
 * The eggs are produced when the produceEgg() method is called.
 *
 * @author Tee Zhi Hong
 */
public class EggSelector {

    /**
     * A random number generator.
     */
    private Random random = new Random();

    /**
     * A map that contains the egg handlers and their corresponding conditions.
     * The egg handlers are the entities that can be produced from the eggs.
     * The conditions are the conditions that must be met for the egg to be produced.
     */
    private Map<EggHandler, Condition>  eggContents = new HashMap<>();

    /**
     * Constructor for EggSelector.
     * Initializes the eggContents map with the egg handlers and their corresponding conditions.
     *
     * @param eggLocation The location of the egg.
     */
    public EggSelector(Location eggLocation){
        /*
          Incubation time is set to 4, as the Sheep laying the egg is considered one turn, the sheep moving away from
          the egg is considered another turn, and the egg hatching is considered the third turn. Making it impossible
          for the Player to collect the egg.
         */
        eggContents.put(new OmenSheep(), new RepeatableTurnCondition(4));
        eggContents.put(new GoldenBeetle(), new NearStatusCondition(Status.CURSED, eggLocation));
    }

    /**
     * Produces a random egg based on the eggContents map.
     * The egg is produced by selecting a random egg handler and its corresponding condition.
     *
     * @return A new Egg object containing the selected egg handler and its corresponding condition.
     */
    public Egg produceEgg(){
        int randomIndex = random.nextInt(eggContents.size());
        List<EggHandler> keys = eggContents.keySet().stream().toList();
        List<Condition> values = eggContents.values().stream().toList();
        return new Egg(keys.get(randomIndex), values.get(randomIndex));
    }


}
