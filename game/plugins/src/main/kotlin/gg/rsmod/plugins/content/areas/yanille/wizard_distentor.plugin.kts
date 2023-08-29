package gg.rsmod.plugins.content.areas.yanille


on_npc_option(npc = Npcs.WIZARD_DISTENTOR, option = "talk-to") {
    player.queue {
        mainDialogue(this, false)
    }
}
on_npc_option(npc = Npcs.WIZARD_DISTENTOR, option = "teleport") {
    essenceTeleport(player, targetTile = Tile(2920, 4821))
}
suspend fun mainDialogue(it: QueueTask, skipStart: Boolean) {
    if (!skipStart)
        it.chatNpc("Greetings, adventurer. How can I help you?")
    when(it.options("Who are you?", "Can you teleport me to the rune essence mine?.", "I'm fine for now, thanks.")) {
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
    it.chatNpc("Blah")
    it.chatNpc("Blah")
    it.chatNpc("Blah")
    mainDialogue(it, true)
}

suspend fun optionTwo(it: QueueTask) {
    it.chatNpc("Now, I'm sure I can spare a couple of runes for such", "a worthy cause as these notes. Do you want me to", "teleport you back?")
    when(it.options("Yes, please.", "No, thank you.")) {
        1 -> {
            it.chatPlayer("Yes, please.")
            essenceTeleport(it.player, dialogue = "Sparanti morduo calmentor!", Tile(2911, 4832, 0))
        }
        2 -> {
            it.chatPlayer("No, thank you.")
        }
    }
}

suspend fun optionThree(it: QueueTask) {
    it.chatNpc("Good adventuring, traveller.")
}
suspend fun teleportToEssencemine(it: QueueTask) {
    it.chatNpc("Ah the teleport to the rune essence mine.", "are you sure?")
    when(it.options("Yes, please.", "No, thank you.")) {
        1 -> {
            it.chatPlayer("Yes, please.")
            essenceTeleport(it.player, dialogue = "Senventior disthine molenko!", Tile(2911, 4832, 0))
        }
        2 -> {
            it.chatPlayer("No, thank you.")
        }
    }
}