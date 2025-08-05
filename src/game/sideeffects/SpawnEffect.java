package game.sideeffects;

import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.displays.*;
import edu.monash.fit2099.engine.positions.*;

import java.util.*;

/**
 * A class that modify the enumeration attributes of an Actor as a side effect an Item purchase.
 *
 * @author Tee Zhi Hong
 */
public class SpawnEffect implements SideEffect {

    private Actor affectedActor;

    private Actor spawnCreature;

    private Random random = new Random();

    private Display displayEffects = new Display();


    /**
     * SpawnEffect class constructor.
     *
     * @param affectedActor An Actor that is affected by the spawn effect.
     * @param spawnCreature An Actor creature to be spawned in the target Actor surroundings for as a side effect.
     */
    public SpawnEffect(Actor affectedActor, Actor spawnCreature){
        this.affectedActor = affectedActor;
        this.spawnCreature = spawnCreature;
    }

    /**
     * Executes the side effect by spawning the creature in the target Actor surroundings.
     *
     * @param mainActor The main actor
     * @param map       Current game map.
     */
    @Override
    public void execute(Actor mainActor, GameMap map) {

        List<Exit> possibleExitsAvailable = new ArrayList<>();
        Location currentActorLocation = map.locationOf(affectedActor);

        for (Exit exit: currentActorLocation.getExits()){
            Location exitDestination = exit.getDestination();
            if (!exitDestination.containsAnActor()){
                possibleExitsAvailable.add(exit);
            }
        }

        if (!possibleExitsAvailable.isEmpty()){
            int randomListIndex = random.nextInt(possibleExitsAvailable.size());
            Exit randomlySelectedExit = possibleExitsAvailable.get(randomListIndex);
            Location randomlySelectedLocation = randomlySelectedExit.getDestination();
            randomlySelectedLocation.addActor(spawnCreature);
            displayEffects.println("Successfully spawns a creature that surrounds " + affectedActor + " called " + spawnCreature + " at " + randomlySelectedExit.getName());
        } else{
            displayEffects.println("Unable to spawn a creature called " + spawnCreature);
        }
    }
}
