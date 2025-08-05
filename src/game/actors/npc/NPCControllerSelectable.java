package game.actors.npc;

/**
 * An interface to represent NPCs that its NPC Controller can be chosen different during the initialisation.
 *
 * @author Tee Zhi Hong
 */
public interface NPCControllerSelectable {

    /**
     * Sets the NPCController during the initialisation.
     * @param npcController NPCController selected during the initialisation.
     */
    void selectNPCController(NPCController npcController);
}
