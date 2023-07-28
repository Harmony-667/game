package gg.rsmod.plugins.content.areas.taverley

/**
 * Needs fix the doors, they open weird now!
 */

listOf(Objs.LARGE_DOOR_31814, Objs.LARGE_DOOR_31815).forEach {
    on_obj_option(obj = it, option = "open") {

            player.queue {
                chat(this)
            }

    }
    }

on_npc_option(Npcs.ELITE_BLACK_KNIGHT_8330, "talk-to") {
    player.queue { chat(this) }
}


suspend fun chat(it: QueueTask) {
    val player = it.player
    it.chatPlayer("Can I come through this doors?")
    it.chatNpc("You must give me a jail key to pass.", npc = Npcs.ELITE_BLACK_KNIGHT_8330)
    when (it.options("Yes, okay.", "No thank you.")) {
        1 -> {
            it.chatPlayer("Yes, okay.")
            it.chatNpc("I take now you key.", npc = Npcs.ELITE_BLACK_KNIGHT_8330)
            if (payToll(player)) {
                handleLegendsGate(player)
                player.filterableMessage("You hand the key over and the doors are open.")
            } else {
                it.chatPlayer("Oh dear, I don't actually seem to have the key.")
            }
        }
        2 -> {
            it.chatPlayer("No thank you.")
            it.chatNpc("Go away...", npc = Npcs.ELITE_BLACK_KNIGHT_8330)
        }
    }
}

fun payToll(player: Player): Boolean {
    val toll = Item(Items.JAIL_KEY, amount = 1)
    return (player.inventory.remove(item = toll, assureFullRemoval = true).hasSucceeded())
}

fun handleLegendsGate(player: Player) {
    val eastOriginalGate = DynamicObject(id = 31814, type = 0, rot = 1, tile = Tile(x = 2908, z = 9697))
    val westOriginalGate = DynamicObject(id = 31815, type = 0, rot = 1, tile = Tile(x = 2907, z = 9697))
    val eastGate = DynamicObject(id = 31816, type = 0, rot = 0, tile = Tile(x = 2908, z = 9698))
    val westGate = DynamicObject(id = 31817, type = 0, rot = 0, tile = Tile(x = 2906, z = 9698))

    player.lockingQueue(lockState = LockState.DELAY_ACTIONS) {
        world.remove(eastOriginalGate)
        world.remove(westOriginalGate)

        player.playSound(Sfx.DOOR_OPEN)
        world.spawn(eastGate)
        world.spawn(westGate)
        val x = if (player.tile.x == 2908) 2907 else 2908
        val z = if (player.tile.z == 9697) 9698 else 9697
        player.walkTo(tile = Tile(x = x, z = z), detectCollision = false)
        wait(3)
        world.remove(eastGate)
        world.remove(westGate)
        world.spawn(eastOriginalGate)
        world.spawn(westOriginalGate)
        player.playSound(Sfx.DOOR_CLOSE)
    }
}