package gg.rsmod.plugins.content.areas.wilderness

val FORCE_CHAT_TIMER = TimerKey()
val DELAY = 10..50

on_npc_option(npc = Npcs.MALIGNIUS_MORTIFER, option = "talk-to") {
    player.queue {
        mainDialogue(this, false)
    }
}

suspend fun mainDialogue(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer. Do you want to change course?")
    when(it.options("Gnome Stronghold Agility Course", "Barbarian Agility Course")) {
        1 -> {
            Gnome(it)
        }
        2 -> {
            Barbarian(it)
        }
    }
}

suspend fun Gnome(it: QueueTask) {
    it.chatNpc("Would you change course to the Gnome Stronghold Agility Course?")
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

suspend fun Barbarian(it: QueueTask) {
    it.chatNpc("Would you change course to the barbarian Agility Course?")
    when(it.options("Yes, please.", "No, thank you.")) {
        1 -> {
            it.chatPlayer("Yes, please.")
            travelTeleport(it.player, dialogue = "Ego te movere", Tile(2548, 3568, 0))
        }
        2 -> {
            it.chatPlayer("No, thank you.")
        }
    }
}

on_timer(FORCE_CHAT_TIMER) {
    if (!npc.isAlive())
        return@on_timer
    npc.forceChat("Don't get hurt and be aware!")
    npc.timers[FORCE_CHAT_TIMER] = DELAY.random()
}