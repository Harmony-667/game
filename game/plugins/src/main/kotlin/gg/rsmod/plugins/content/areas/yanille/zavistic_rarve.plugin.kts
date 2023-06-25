package gg.rsmod.plugins.content.areas.yanille

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */

on_npc_option(npc = Npcs.ZAVISTIC_RARVE, option = "talk-to") {
    player.queue {
        mainDialogue(this, false)
    }
}

suspend fun mainDialogue(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer. What do you want?")
    when(it.options("Who are you?", "Where goes all this magic portals?.", "I'm fine for now, thanks.")) {
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
    it.chatNpc("I am the Zavistic Rarve,",)
    it.chatNpc("The creator of those magic portals.", "But i have broken the spells.")
    it.chatNpc("Im sure i discover the right formula!.", "And then....")
    it.chatNpc("We can discover the lands of Harmony", "with MAGIC!", "isn't that fantastic!")
    mainDialogue(it, true)
}

suspend fun optionTwo(it: QueueTask) {
    it.chatNpc("Well the portal on the East goes to the Legends Guild!")
    it.chatNpc("Well the portal on the South goes to Nothing!")
    it.chatNpc("Well the portal on the West goes to Nothing!")
    it.chatNpc("I dont know the right formula for the spells.","I will figure that out!", "Come back later and maybe i know one of the spells!")
    mainDialogue(it, true)
}

suspend fun optionThree(it: QueueTask) {
    it.chatNpc("Have a nice day!")
}