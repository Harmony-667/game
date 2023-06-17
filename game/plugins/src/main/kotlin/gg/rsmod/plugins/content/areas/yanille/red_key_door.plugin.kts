package gg.rsmod.plugins.content.areas.yanille

/**
 * @author Alycia <https://github.com/alycii>
 */

on_obj_option(obj = Objs.DOOR_1533, option = "open") {
    if(!player.inventory.contains(Items.KEY_1543)) {
        player.message("The door is locked.")
        return@on_obj_option
    }

    handleDoor(player)
}

on_obj_option(obj = Objs.STAIRCASE_32271, option = "Climb-Down") {
    player.handleLadder(underground = true)
}

on_obj_option(obj = Objs.LADDER_29358, option = "Climb-Down") {
    player.handleLadder()
}


fun handleDoor(player: Player) {
    val closedDoor = DynamicObject(id = 1533, type = 0, rot = 3, tile = Tile(x = 2603, z = 3082))
    val door = DynamicObject(id = 1533, type = 0, rot = if (player.tile.z == 3080) 1 else 3, tile = Tile(x = 3115, z = 3082))
    player.lock = LockState.DELAY_ACTIONS
    world.remove(closedDoor)
    player.playSound(Sfx.DOOR_OPEN)
    world.spawn(door)

    player.queue {
        val x = 2603
        val z = if (player.tile.z == 3081) 3082 else 3081
        player.walkTo(tile = Tile(x = x, z = z), detectCollision = false)
        wait(3)
        world.remove(door)
        player.lock = LockState.NONE
        world.spawn(closedDoor)
        player.playSound(Sfx.DOOR_CLOSE)
    }
}