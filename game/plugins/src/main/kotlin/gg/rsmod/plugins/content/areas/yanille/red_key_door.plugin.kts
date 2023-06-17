package gg.rsmod.plugins.content.areas.yanille

/**
 * @author Alycia <https://github.com/alycii>
 */

on_obj_option(obj = Objs.DOOR_2559, option = "open") {
    if(!player.inventory.contains(Items.KEY_1543)) {
        player.message("The door is locked.")
        return@on_obj_option
    }

    handleDoor(player)
}

on_obj_option(obj = Objs.STAIRCASE_32271, option = "Climb-Down") {
    player.moveTo(2602, 9479)
}

on_obj_option(obj = Objs.STAIRCASE_32270, option = "Climb-Up") {
    player.moveTo(2606, 3079)
}


fun handleDoor(player: Player) {
    val closedDoor = DynamicObject(id = 2559, type = 0, rot = 3, tile = Tile(x = 2601, z = 9482))
    val door = DynamicObject(id = 2559, type = 0, rot = if (player.tile.z == 9481) 2 else 2, tile = Tile(x = 2601, z = 9482))
    player.lock = LockState.DELAY_ACTIONS
    world.remove(closedDoor)
    player.playSound(Sfx.DOOR_OPEN)
    world.spawn(door)

    player.queue {
        val x = 2601
        val z = if (player.tile.z == 9481) 9482 else 9481
        player.walkTo(tile = Tile(x = x, z = z), detectCollision = false)
        wait(3)
        world.remove(door)
        player.lock = LockState.NONE
        world.spawn(closedDoor)
        player.playSound(Sfx.DOOR_CLOSE)
    }
}