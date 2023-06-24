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
    it.chatNpc("There used to be nasty trolls here who terrorized the city.", "Especially their leader, the big one, they called him Dad.")
    it.chatNpc("Our heroes kept him in check all this time,", "until the moment came and I ", "and the Guard Captain defeated him.")
    it.chatNpc("His sons then came to fetch him and ", "brought him back to his caves...")
    it.chatNpc("Watch out adventurer, the caves are guarded,", "if you are looking for him, follow the trolls.","They have blocked the cave entrance but I and my soldiers have", "the key. You need an Iron key to get through.")

    mainDialogue(it, true)
}

suspend fun optionThree(it: QueueTask) {
    it.chatNpc("Good adventuring, traveller.")
}