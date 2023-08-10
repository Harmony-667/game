package gg.rsmod.plugins.content.areas.wilderness


listOf(Objs.GATE_2308, Objs.GATE_2307).forEach {
    on_obj_option(obj = it, option = "open") {

            player.queue {
                handleWildernessAgilityCourseGate(player)
            }

    }
    }

fun handleWildernessAgilityCourseGate(player: Player) {
    val eastOriginalGate = DynamicObject(id = 2308, type = 0, rot = 3, tile = Tile(x = 2997, z = 3931))
    val westOriginalGate = DynamicObject(id = 2307, type = 0, rot = 3, tile = Tile(x = 2998, z = 3931))
    val eastGate = DynamicObject(id = 2308, type = 0, rot = 0, tile = Tile(x = 2997, z = 3932))
    val westGate = DynamicObject(id = 2307, type = 0, rot = 0, tile = Tile(x = 2997, z = 3932))

    player.lockingQueue(lockState = LockState.DELAY_ACTIONS) {
        world.remove(eastOriginalGate)
        world.remove(westOriginalGate)

        player.playSound(Sfx.DOOR_OPEN)
        world.spawn(eastGate)
        world.spawn(westGate)
        val x = if (player.tile.x == 2997) 2998 else 2997
        val z = if (player.tile.z == 3931) 3930 else 3931
        player.walkTo(tile = Tile(x = x, z = z), detectCollision = false)
        wait(3)
        world.remove(eastGate)
        world.remove(westGate)
        world.spawn(eastOriginalGate)
        world.spawn(westOriginalGate)
        player.playSound(Sfx.DOOR_CLOSE)
    }
}