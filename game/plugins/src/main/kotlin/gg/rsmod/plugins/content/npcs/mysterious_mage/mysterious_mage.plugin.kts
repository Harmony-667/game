package gg.rsmod.plugins.content.npcs.mysterious_mage


on_npc_option(npc = Npcs.MYSTERIOUS_MAGE, option = "talk-to") {
    player.queue {
        mainDialogue(this, false)
    }
}

suspend fun mainDialogue(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer. Where you want to go?")
    when(it.options("Yanille", "Seers Village (Comming Soon)", "Catherby", "Taverley (Comming soon)")) {
        1 -> {
            Yanille(it)
        }
        2 -> {
            Seers(it)
        }
        3 -> {
            Catherby(it)
        }
        3 -> {
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
    it.chatNpc("Seers Village will soon added to the game!")
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
    it.chatNpc("Taverley will soon added to the game!")
}