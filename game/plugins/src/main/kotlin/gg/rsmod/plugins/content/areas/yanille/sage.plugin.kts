package gg.rsmod.plugins.content.areas.yanille


on_npc_option(npc = Npcs.LUMBRIDGE_SAGE, option = "talk-to") {
    player.queue {
        mainDialogue(this, false)
    }
}

suspend fun mainDialogue(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer. How can I help you?")
    when(it.options("Who are you?", "Tell me about the land of Harmony.", "I'm fine for now, thanks.")) {
        1 -> {
            optionOne(it)
        }
        2 -> {
            optionTwo(it)
        }
        3 -> {
            optionThree(it)
        }
    }
}

suspend fun optionOne(it: QueueTask) {
    it.chatNpc("I am Phileas, the Harmony Sage. In times", "past, people came from all around to ask me for advice.")
    it.chatNpc("My renown seems to have diminished ", "somewhat in recent years, though.")
    it.chatNpc("Can I help you with anything?")
    mainDialogue(it, true)
}

suspend fun optionTwo(it: QueueTask) {
    it.chatNpc("To-Do line 1.")
    it.chatNpc("To-Do line 2.")
    it.chatNpc("To-Do line 3")
    it.chatNpc("To-Do Line 4","Line 4.1")
    mainDialogue(it, true)
}

suspend fun optionThree(it: QueueTask) {
    it.chatNpc("Good adventuring, traveller.")
}