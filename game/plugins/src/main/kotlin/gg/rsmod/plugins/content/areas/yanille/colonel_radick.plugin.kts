package gg.rsmod.plugins.content.areas.yanille


on_npc_option(npc = Npcs.COLONEL_RADICK, option = "talk-to") {
    player.queue {
        mainDialogue(this, false)
    }
}

suspend fun mainDialogue(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer. How can I help you?")
    when(it.options("Who are you?", "What about the trolls?.", "I'm fine for now, thanks.")) {
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
    it.chatNpc("I am Colonel Radick, the Colonel of", "this gate neaby me! I keep it guarded!.")
    it.chatNpc("But now... ", "That damm Trolls....")
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