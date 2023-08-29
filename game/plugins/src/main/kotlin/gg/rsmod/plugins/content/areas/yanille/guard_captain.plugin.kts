package gg.rsmod.plugins.content.areas.yanille


on_npc_option(npc = Npcs.GUARD_CAPTAIN, option = "talk-to") {
    player.queue {
        mainDialogue(this, false)
    }
}

suspend fun mainDialogue(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer. What do you want?")
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
    it.chatNpc("I am the Guard Captain,", "but i failed, and now...")
    it.chatNpc("The colonel fired me! ", "because is was drunk during my shift", "and now the Trolls has blocked the gate..")
    it.chatNpc("What can i do?")
    mainDialogue(it, true)
}

suspend fun optionTwo(it: QueueTask) {
    it.chatNpc("I dont want to talk about it!")
    it.chatNpc("That stupid leader DAD....")
    it.chatNpc("He ruined my caqreer!")
    it.chatNpc("If u want know more...","Ask Colonel Radick!")
    mainDialogue(it, true)
}

suspend fun optionThree(it: QueueTask) {
    it.chatNpc("Have a nice day, and get a beer!")
}