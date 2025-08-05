package game.enums;

/**
 * Use this enum class to represent a status.
 * Example #1: if the player is sleeping, you can attack a Status.SLEEP to the player class.
 *
 * @author Riordan D. Alfredo
 * <p> Modified by: Tee Zhi Hong </p>
 */
public enum Status {
    /**
     * A Status enumeration constant that represents the actors that are hostile to an enemy.
     */
    HOSTILE_TO_ENEMY,
    /**
     * A Status enumeration constant that represents the cursed grounds.
     */
    CURSED,
    /**
     * A Status enumeration constant that represents creatures that can rot.
     */
    ROT,
    /**
     * A status enumeration constant that represents the entities that are blessed by grace.
     */
    BLESSED,

    /**
     * A status enumeration constant that represents the affectionate actors that are happy.
     */
    HAPPY,

    /**
     * A status enumeration constant that represents the affectionate actors that are angry.
     */
    ANGRY,

    /**
     * A status enumeration constant that represents the affectionate actors that received affection.
     */
    RECEIVED_AFFECTION
}
