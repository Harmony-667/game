package gg.rsmod.plugins.content.areas.yanille

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */

on_npc_option(npc = Npcs.WIZARD_FRUMSCONE, option = "talk-to") {
    player.queue {
        mainDialogue(this, false)
    }
}

suspend fun mainDialogue(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer. What do you want?")
    when(it.options("Who are you?", "What is this place?.", "I'm fine for now, thanks.")) {
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
    it.chatNpc("I am the Wizard Frumscone,",)
    it.chatNpc("I have discoved this prison down our magic guild.", "But I don't know its purpose yet...")
    it.chatNpc("Maybe i will discover some time where it was used for.", "And maybe we can use it again.", "Come back later and i maybe know it!")
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
    it.chatNpc("Have a nice day, and get a beer!")
}