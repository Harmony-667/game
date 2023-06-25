package gg.rsmod.plugins.content.catherby

val FORCE_CHAT_TIMER = TimerKey()
val DELAY = 10..100

on_global_npc_spawn {
    when (npc.id) {
        Npcs.FISHERMAN -> npc.timers[FORCE_CHAT_TIMER] = world.random(DELAY)
    }
}

on_timer(FORCE_CHAT_TIMER) {
    if (!npc.isAlive())
        return@on_timer
    npc.forceChat("We are building a Fishing Guild!")
    npc.timers[FORCE_CHAT_TIMER] = DELAY.random()
}
on_npc_option(npc = Npcs.FISHERMAN, option = "talk-to") {
    player.queue {
        mainDialogue(this, false)
    }
}
suspend fun mainDialogue(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer. How can I help you?")
    when(it.options("Who are you?", "Can you teleport me to the Fishing Guild?.", "Never mind")) {
        1 -> {
            whoareyou(it)
        }
        2 -> {
            teleport(it)
        }
        3 -> {
            nevermind(it)
        }
    }
}

suspend fun whoareyou(it: QueueTask) {
    it.chatPlayer("Who are you?")
    it.chatNpc("I'm a old fisherman, got a high skill of fishing!")
    it.chatNpc("I had fight for years with fish!")
    mainDialogue(it, true)
}

suspend fun teleport(it: QueueTask) {
    it.chatPlayer("Can you teleport me to the Fishing Guild?")
    it.chatNpc("We are still building the fishing guild!")
    it.chatNpc("Come back later!")
    mainDialogue(it, true)
}

/*suspend fun teleport(it: QueueTask) {
    it.chatNpc("Now, I'm sure I can spare a couple of runes for such", "a worthy cause as these notes. Do you want me to", "teleport you back?")
    when(it.options("Yes, please.", "No, thank you.")) {
        1 -> {
            it.chatPlayer("Yes, please.")
            travelTeleport(it.player, dialogue = "Ego Piscari ad Loca!", Tile(2611, 3392, 0))
        }
        2 -> {
            it.chatPlayer("No, thank you.")
        }
    }
}*/

suspend fun nevermind(it: QueueTask) {
    it.chatPlayer("Never mind...")
    it.chatNpc("Its still a good day for Fishing!.")
}