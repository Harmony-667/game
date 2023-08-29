package gg.rsmod.plugins.content.npcs.mysterious_mage

val FORCE_CHAT_TIMER = TimerKey()
val DELAY = 10..50

on_npc_option(npc = Npcs.GUNNJORN, option = "talk-to") {
    player.queue {
        mainDialogue(this, false)
    }
}

suspend fun mainDialogue(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer. Do you want to change course?")
    when(it.options("Gnome Stronghold Agility Course", "Wilderness Agility Course (DANGER)")) {
        1 -> {
            Gnome(it)
        }
        2 -> {
            Wilderness(it)
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

suspend fun Wilderness(it: QueueTask) {
        it.chatNpc("Would you change course to the Wilderness Agility Course?", "Its wild out there, u can lose your items!")
        when(it.options("Yes, please.", "No, thank you.")) {
            1 -> {
                it.chatPlayer("Yes, please.")
                travelTeleport(it.player, dialogue = "Ego te movere", Tile(2998, 3911, 0))
            }
            2 -> {
                it.chatPlayer("No, thank you.")
            }
        }
    }

on_timer(FORCE_CHAT_TIMER) {
    if (!npc.isAlive())
        return@on_timer
    npc.forceChat("Grr, you are to Weak! Go Faster!")
    npc.timers[FORCE_CHAT_TIMER] = DELAY.random()
}