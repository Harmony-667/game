package gg.rsmod.plugins.content.areas.tutorialdungeon


on_npc_option(npc = Npcs.SIR_VANT, option = "talk-to") {
    player.queue {
        mainDialogue(this, false)
    }
}

suspend fun mainDialogue(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer.", "Welcome to Project Harmony!", "Would you like to go to the mainlands?")
    when(it.options("Yes, teleport me", "No!")) {
        1 -> {
            Yanille(it)
        }
        2 -> {
            Seers(it)
        }
    }
}

suspend fun Yanille(it: QueueTask) {
    it.chatNpc("Are you sure?")
    when(it.options("Yes!", "No, thank you.")) {
        1 -> {
            it.chatPlayer("Yes!")
            travelTeleport(it.player, dialogue = "Ego te movere", Tile(2606, 3102, 0))
        }
        2 -> {
            it.chatPlayer("No, thank you.")
        }
    }
}

suspend fun Seers(it: QueueTask) {
    it.chatNpc("Ok you can hang out here for a while!", "Watch out for the dragon!")
}