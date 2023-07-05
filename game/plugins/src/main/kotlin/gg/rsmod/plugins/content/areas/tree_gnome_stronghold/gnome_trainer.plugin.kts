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
    when(it.options("Gnome Stronghold Agility Course", "Penguin Agility Course", "Barbian Outpost Agility Course", "Wilderness Agility Course (DANGER)", "Werewolf Agility Course")) {
        1 -> {
            Gnome(it)
        }
        2 -> {
            Penguin(it)
        }
        3 -> {
            Barbian(it)
        }
        4 -> {
            Wilderness(it)
        }
        5 -> {
            Werewolf(it)
        }
    }
}

suspend fun Gnome(it: QueueTask) {
    it.chatNpc("Would you change course to the Gnome Stronghild Agility Course?")
    when(it.options("Yes, please.", "No, thank you.")) {
        1 -> {
            it.chatPlayer("Yes, please.")
            travelTeleport(it.player, dialogue = "Ego te movere", Tile(2480, 3428, 0))
        }
        2 -> {
            it.chatPlayer("No, thank you.")
        }
    }
}

suspend fun Penguin(it: QueueTask) {
    it.chatNpc("Comming soon!")
}

suspend fun Barbian(it: QueueTask) {
    it.chatNpc("Comming Soon!")
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