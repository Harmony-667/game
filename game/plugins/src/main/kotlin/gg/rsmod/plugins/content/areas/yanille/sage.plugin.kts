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
    it.chatNpc("The land of Harmony is build on the remains of", "a big ancient world.")
    it.chatNpc("That old world will always be respected!")
    it.chatNpc("The new residents of Harmony have", "changed some things.")
    it.chatNpc("Like the rats live now outside Yanille","Dad is forced back to his dungeon where he belongs", "The Wizards has come back to ther Magic Guild.", "The Moss Giants has left the city!")
    it.chatNpc("The dwarfs has find the Yanille Mines","The Yanille dungeon has rediscoverd!", "And many more!", "We are not done yet to discover this new lands!")
    it.chatNpc("When u got Questions, ask a player or Harmony moderator!", "Or join our discord and ask other players!")
    mainDialogue(it, true)
}

suspend fun optionThree(it: QueueTask) {
    it.chatNpc("Good adventuring, traveller.")
}