package gg.rsmod.plugins.content.areas.legends_guild


listOf(Objs.GATE_2391, Objs.GATE_2392).forEach {
    on_obj_option(obj = it, option = "open") {

            player.queue {
                chat(this)
            }

    }
    }

on_npc_option(Npcs.LEGENDS_GUARD, "talk-to") {
    player.queue { chat(this) }
}
on_npc_option(Npcs.LEGENDS_GUARD_399, "talk-to") {
    player.queue { chat(this) }
}

suspend fun chat(it: QueueTask) {
    val player = it.player
    it.chatPlayer("Can I come through this gate?")
    it.chatNpc("You must pay a toll of 500 gold coins to pass.", npc = Npcs.LEGENDS_GUARD)
    when (it.options("Yes, okay.", "Who does my money go to?", "No thank you.")) {
        1 -> {
            it.chatPlayer("Yes, okay.")
            if (payToll(player)) {
                handleLegendsGate(player)
                player.filterableMessage("You pay the guards and pass through the gate.")
            } else {
                it.chatPlayer("Oh dear, I don't actually seem to have enough money.")
            }
        }
        2 -> {
            it.chatPlayer("Who does my money go to?")
            it.chatNpc("The money goes to the treasury of the Legends Guild.", npc = Npcs.LEGENDS_GUARD)
        }
        3 -> {
            it.chatPlayer("No thank you.")
            it.chatNpc("Okay, suit yourself.", npc = Npcs.LEGENDS_GUARD)
        }
    }
}

fun payToll(player: Player): Boolean {
    val toll = Item(Items.COINS_995, amount = 500)
    return (player.inventory.remove(item = toll, assureFullRemoval = true).hasSucceeded())
}

fun handleLegendsGate(player: Player) {
    val eastOriginalGate = DynamicObject(id = 2391, type = 0, rot = 1, tile = Tile(x = 2728, z = 3349))
    val westOriginalGate = DynamicObject(id = 2392, type = 0, rot = 1, tile = Tile(x = 2729, z = 3349))
    val eastGate = DynamicObject(id = 2391, type = 0, rot = 0, tile = Tile(x = 2728, z = 3350))
    val westGate = DynamicObject(id = 2392, type = 0, rot = 2, tile = Tile(x = 2729, z = 3350))

    player.lockingQueue(lockState = LockState.DELAY_ACTIONS) {
        world.remove(eastOriginalGate)
        world.remove(westOriginalGate)
        
        player.playSound(Sfx.DOOR_OPEN)
        world.spawn(eastGate)
        world.spawn(westGate)
        val x = if (player.tile.x == 2728) 2729 else 2728
        val z = if (player.tile.z == 3349) 3350 else 3349
        player.walkTo(tile = Tile(x = x, z = z), detectCollision = false)
        wait(3)
        world.remove(eastGate)
        world.remove(westGate)
        world.spawn(eastOriginalGate)
        world.spawn(westOriginalGate)
        player.playSound(Sfx.DOOR_CLOSE)
    }
}