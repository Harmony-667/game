package gg.rsmod.plugins.content.npcs.mysterious_mage


on_npc_option(npc = Npcs.MYSTERIOUS_MAGE, option = "talk-to") {
    player.queue {
        mainDialogue(this, false)
    }
}

suspend fun mainDialogue(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer. Where you want to go?")
    when(it.options("Yanille", "Seers Village", "Catherby", "Taverley")) {
        1 -> {
            Yanille(it)
        }
        2 -> {
            Seers(it)
        }
        3 -> {
            Catherby(it)
        }
        4 -> {
            Taverley(it)
        }
    }
}

suspend fun Yanille(it: QueueTask) {
    it.chatNpc("You would like to travel to Yanille?")
    when(it.options("Yes, please.", "No, thank you.")) {
        1 -> {
            it.chatPlayer("Yes, please.")
            travelTeleport(it.player, dialogue = "Ego te movere", Tile(2606, 3102, 0))
        }
        2 -> {
            it.chatPlayer("No, thank you.")
        }
    }
}

suspend fun Seers(it: QueueTask) {
    it.chatNpc("You would like to travel to Seers Village?")
    when(it.options("Yes, please.", "No, thank you.")) {
        1 -> {
            it.chatPlayer("Yes, please.")
            travelTeleport(it.player, dialogue = "Ego te movere", Tile(2732, 3485, 0))
        }
        2 -> {
            it.chatPlayer("No, thank you.")
        }
    }
}

suspend fun Catherby(it: QueueTask) {
    it.chatNpc("You would like to travel to Catherby?")
    when(it.options("Yes, please.", "No, thank you.")) {
        1 -> {
            it.chatPlayer("Yes, please.")
            travelTeleport(it.player, dialogue = "Ego te movere", Tile(2804, 3433, 0))
        }
        2 -> {
            it.chatPlayer("No, thank you.")
        }
    }
}
suspend fun Taverley(it: QueueTask) {
    it.chatNpc("You would like to travel to Taverley?")
    when(it.options("Yes, please.", "No, thank you.")) {
        1 -> {
            it.chatPlayer("Yes, please.")
            travelTeleport(it.player, dialogue = "Ego te movere", Tile(2897, 3457, 0))
        }
        2 -> {
            it.chatPlayer("No, thank you.")
        }
    }
}