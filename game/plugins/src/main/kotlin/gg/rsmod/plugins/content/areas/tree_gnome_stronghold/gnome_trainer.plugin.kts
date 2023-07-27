package gg.rsmod.plugins.content.npcs.mysterious_mage

val FORCE_CHAT_TIMER = TimerKey()
val DELAY = 10..50

on_npc_option(npc = Npcs.GNOME_TRAINER, option = "talk-to") {
    player.queue {
        mainDialogue(this, false)
    }
}

suspend fun mainDialogue(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer. Do you want to change course?")
    when(it.options("Penguin Agility Course (Comming Soon)", "Barbian Outpost Agility Course", "Wilderness Agility Course (DANGER) (Comming soon)", "Werewolf Agility Course (Comming soon)")) {

        1 -> {
            Penguin(it)
        }
        2 -> {
            Barbian(it)
        }
        3 -> {
            Wilderness(it)
        }
        4 -> {
            Werewolf(it)
        }
    }
}


suspend fun Penguin(it: QueueTask) {
    it.chatNpc("Comming soon!")
}

suspend fun Barbian(it: QueueTask) {
    it.chatNpc("Would you change course to the Gnome Stronghild Agility Course?")
    when(it.options("Yes, please.", "No, thank you.")) {
        1 -> {
            it.chatPlayer("Yes, please.")
            travelTeleport(it.player, dialogue = "Ego te movere", Tile(2552, 3558, 0))
        }
        2 -> {
            it.chatPlayer("No, thank you.")
        }
    }
}
suspend fun Wilderness(it: QueueTask) {
    it.chatNpc("Comming Soon!")
}
suspend fun Werewolf(it: QueueTask) {
    it.chatNpc("Comming Soon!")
}

on_timer(FORCE_CHAT_TIMER) {
    if (!npc.isAlive())
        return@on_timer
    npc.forceChat("Come on, Faster!")
    npc.timers[FORCE_CHAT_TIMER] = DELAY.random()
}