package gg.rsmod.plugins.content.areas.dragontooth_island


on_npc_option(npc = Npcs.NED_4475, option = "talk-to") {
    player.queue {
        mainDialogue(this, false)
    }
}

suspend fun mainDialogue(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer.", "Do you want to sail to", "Dragontooth Island, the island of dragons?")
    when(it.options("Yes", "No")) {
        1 -> {
            DragonTooth(it)
        }
        2 -> {
            No(it)
        }
    }
}

suspend fun DragonTooth(it: QueueTask) {
    it.chatNpc("Are you sure?", "It is very dangerous there!")
    when(it.options("Yes!", "No, thank you.")) {
        1 -> {
            it.chatPlayer("Yes!")
            sailTeleport(it.player, dialogue = "Come on board!", Tile(3791, 3559, 0))
        }
        2 -> {
            it.chatPlayer("No, thank you.")
        }
    }
}

suspend fun No(it: QueueTask) {
    it.chatPlayer("No.")
}

on_npc_option(npc = Npcs.NED, option = "talk-to") {
    player.queue {
        mainDialogue(this, false)
    }
}

suspend fun mainDialogue(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer.", "Do you want to sail back?")
    when(it.options("Yes", "No")) {
        1 -> {
            Mainland(it)
        }
        2 -> {
            No1(it)
        }
    }
}

suspend fun Mainland(it: QueueTask) {
    it.chatNpc("Are you sure?")
    when(it.options("Yes!", "No, thank you.")) {
        1 -> {
            it.chatPlayer("Yes!")
            sailTeleport(it.player, dialogue = "Come on board!", Tile(2600, 3425, 0))
        }
        2 -> {
            it.chatPlayer("No, thank you.")
        }
    }
}

suspend fun No1(it: QueueTask) {
    it.chatPlayer("No.")
}