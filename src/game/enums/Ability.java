package game.enums;

/**
 * Use this enum to represent abilities.
 * Example #1: if the player is capable jumping over walls, you can attach Ability.WALL_JUMP to the player class.
 *
 * <p> Modified by: Tee Zhi Hong, Nigel Wong Wei Lun </p>
 */
public enum Ability {

    /**
     * An Ability enumeration constant that the Actor obtaining talisman item(s) is able to cure entities that are curable by the cure relic.
     */
    TALISMAN_CURE,

    /**
     * An Ability enumeration constant for all Grounds that are suitable to plant.
     */
    PLANT_SUITABLE,

    /**
     * An Ability enumeration constant for any entity that can be listened.
     */
    LISTENABLE,

    /**
     * An Ability enumeration constant for any entity that can be followed.
     */
    FOLLOWABLE,

    /**
     * An Ability enumeration constant for any entity that can be purchase an item.
     */
    PURCHASE_CAPABLE,

    /**
     * An Ability enumeration constant for any entity that can be teleported.
     */
    TELEPORT,
    /**
     * An Ability enumeration constant for any entity that is planted
     */
    PLANT,

    /**
     * An Ability enumeration constant for any entity that can be shoveled with an ancient shovel.
     */
    ANCIENT_SHOVEABLE,

    /**
     * An Ability enumeration constant that represents an affectionate actor.
     */
    AFFECTIONATE,

    /**
     * An Ability enumeration constant for any actor that can provide an affection (For now, it is only the farmer).
     */
    GIVE_AFFECTION,

    /**
     * An Ability enumeration constant for any affectionate actor that can receive an item.
     */
    RECEIVE_CAPABLE,
}