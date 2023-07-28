package gg.rsmod.plugins.content.areas.taverley

/**
 * @author Alycia <https://github.com/alycii>
 */


on_obj_option(obj = Objs.GATE_2623, option = "open") {
    if(!player.inventory.contains(Items.DUSTY_KEY)) {
        player.message("This gate is locked.")
    } else {
        handleDustyGate(player)
    }
}

on_item_on_obj(obj = Objs.GATE_2623, item = Items.DUSTY_KEY) {
    handleDustyGate(player)
}


/**
 * This function handles the interaction with the dusty gate for a given player.
 *
 * @param player the player object to handle the dusty gate interaction for.
 */
fun handleDustyGate(player: Player) {
    /**
     * Checks if the player has the DUSTY_KEY item in their inventory. If not, displays a message
     * and returns.
     */
    if (!player.inventory.contains(Items.DUSTY_KEY)) {
        player.message("This gate is locked.")
        return
    }

    /**
     * Creates closed and opened gate objects using the DynamicObject class and respective IDs,
     * types, rotations, and tile positions.
     */
    val closedDoor = DynamicObject(id = Objs.GATE_2623, type = 0, rot = 0, tile = Tile(x = 2924, z = 9803))
    val openedDoor = DynamicObject(id = Objs.GATE_2623, type = 0, rot = 1, tile = Tile(x = 2924, z = 9803))

    // Removes the closed gate object and spawns the opened gate object in the game world.
    world.remove(closedDoor)
    world.spawn(openedDoor)

    /**
     * Sets the player's locking queue to delay actions and performs the following actions:
     * 1. Determines the target tile for the player to walk to based on their current position.
     * 2. Displays a message to the player indicating they have unlocked the gate.
     * 3. Makes the player walk to the target tile.
     * 4. Waits for 3 game ticks (approximately 1.8 seconds).
     * 5. Removes the opened gate object and spawns the closed gate object in the game world.
     */
    player.lockingQueue(lockState = LockState.DELAY_ACTIONS) {
        val x = if (player.tile.x >= 2924) 2923 else 2924
        val z = 9803
        player.message("You unlock the gate.")
        player.walkTo(tile = Tile(x = x, z = z), detectCollision = false)
        wait(4)
        world.remove(openedDoor)
        world.spawn(closedDoor)
    }
}

/**
 * This function handles the interaction with the jail door for a given player.
 *
 * @param player the player object to handle the jail door interaction for.
 */


on_obj_option(obj = Objs.OBSTACLE_PIPE_9293, option = "Squeeze-through") {
    if (player.skills.getCurrentLevel(Skills.AGILITY) > 70) {
        player.queue {
            val z = 9799
            val x = if (player.tile.x == 2892) 2886 else 2892
            player.moveTo(tile = Tile(x = x, z = z))
        }
    } else {
        player.message("You need a agility of level 70 to use this!")
    }
}
on_obj_option(obj = Objs.STRANGE_FLOOR, option = "Jump-over") {
    if (player.skills.getCurrentLevel(Skills.AGILITY) > 80) {
        player.queue {
            val z = 9813
            val x = if (player.tile.x == 2880) 2878 else 2880
            player.moveTo(tile = Tile(x = x, z = z))
        }
    } else {
        player.message("You need a agility of level 80 to use this!")
    }
}
private val TAVERLEY_DUNGEON_REGIONS = intArrayOf(11417, 11416, 11673, 11672, 11671) //Multi combat
TAVERLEY_DUNGEON_REGIONS.forEach { set_multi_combat_region(region = it) }