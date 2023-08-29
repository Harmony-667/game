package gg.rsmod.plugins.content.areas.dragontooth_island


on_npc_option(npc = Npcs.JEB, option = "talk-to") {
    player.queue {
        mainDialogue(this, false)
    }
}

suspend fun mainDialogue(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer.", "Do you want to the metal dragon dungeon?")
    when(it.options("Yes", "No")) {
        1 -> {
            Metal(it)
        }
        2 -> {
            No(it)
        }
    }
}

suspend fun Metal(it: QueueTask) {
    it.chatNpc("Are you sure?", "It is very dangerous there!")
    when(it.options("Yes!", "No, thank you.")) {
        1 -> {
            it.chatPlayer("Yes!")
            travelTeleport(it.player, dialogue = "Ego te movere", Tile(2285, 4684, 0))
        }
        2 -> {
            it.chatPlayer("No, thank you.")
        }
    }
}

suspend fun No(it: QueueTask) {
    it.chatPlayer("No.")
}

on_npc_option(npc = Npcs.JEB_4896, option = "talk-to") {
    player.queue {
        mainDialogue1(this, false)
    }
}

suspend fun mainDialogue1(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer.", "Teleport back to DragonThooth island?")
    when(it.options("Yes", "No")) {
        1 -> {
            DragonThooth(it)
        }
        2 -> {
            No1(it)
        }
    }
}

suspend fun DragonThooth(it: QueueTask) {
    it.chatNpc("Are you sure?")
    when(it.options("Yes!", "No, thank you.")) {
        1 -> {
            it.chatPlayer("Yes!")
            travelTeleport(it.player, dialogue = "Ego te movere", Tile(3809, 3544, 0))
        }
        2 -> {
            it.chatPlayer("No, thank you.")
        }
    }
}

suspend fun No1(it: QueueTask) {
    it.chatPlayer("No.")
}