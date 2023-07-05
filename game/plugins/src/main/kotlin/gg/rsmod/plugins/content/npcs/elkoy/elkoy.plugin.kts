package gg.rsmod.plugins.content.npcs.elkoy


on_npc_option(npc = Npcs.ELKOY, option = "talk-to") {
    player.queue {
        mainDialogue(this, false)
    }
}

suspend fun mainDialogue(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer. Where you want to go?")
    when(it.options("Tree Gnome Stronghold", "Tree Gnome Village (Comming Soon)", "Gnome Ship Yard (Comming soon)", "Gnome Battle Field (Comming soon)")) {
        1 -> {
            Stronghold(it)
        }
        2 -> {
            Village(it)
        }
        3 -> {
            Shipyard(it)
        }
        4 -> {
            Battlefield(it)
        }
    }
}

suspend fun Stronghold(it: QueueTask) {
    it.chatNpc("You would like to travel to the Tree Gnome Stronghold?")
    when(it.options("Yes, please.", "No, thank you.")) {
        1 -> {
            it.chatPlayer("Yes, please.")
            travelTeleport(it.player, dialogue = "Ego te movere", Tile(2458, 3412, 0))
        }
        2 -> {
            it.chatPlayer("No, thank you.")
        }
    }
}

suspend fun Village(it: QueueTask) {
    it.chatNpc("We are building our village right now!")
}

suspend fun Shipyard(it: QueueTask) {
    it.chatNpc("The Ship Yard is flooded, you cant go there!")
}
suspend fun Battlefield(it: QueueTask) {
    it.chatNpc("There is no war, we live all in peace for now!")
}