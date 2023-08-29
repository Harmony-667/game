package gg.rsmod.plugins.content.areas.seers

val PRIEST = arrayOf(Npcs.HIGH_PRIEST, Npcs.MONK)

PRIEST.forEach { priest ->
    on_npc_option(npc = priest, option = "talk-to") {
        player.queue { chat(this) }
    }
}

suspend fun chat(it: QueueTask) {
    it.chatPlayer("Hello, how's it going?")
    when (world.random(17)) {
        0 -> it.chatNpc("Not too bad thanks.")
        1 -> {
            it.chatNpc("I'm fine, how are you?")
            it.chatPlayer("Very well thank you.")
        }
        2 -> it.chatNpc("I think we need a new king. The one we've got",
                                "isn't very good.")
        3 -> it.chatNpc("Get out of my way, I'm in a hurry!")
        4 -> it.chatNpc("Do I know you? I'm in a hurry!")
        5 -> it.chatNpc("None of your business.")
        6 -> {
            it.chatNpc("Not too bad, but I'm a little worried about the",
                                "my magical powers, they are gone.")
            it.chatPlayer("Don't worry, they come back.")
        }
        7 -> it.chatNpc("I'm busy right now.")
        8 -> {
            it.chatNpc("Who are you?")
            it.chatPlayer("I'm a bold adventurer.")
            it.chatNpc("Ah, a very noble profession.")
        }
        9 -> it.chatNpc("No I don't have any spare change.")
        10 -> {
            it.chatNpc("You can pray here if u want?")
        }
        11 -> it.chatNpc("I'm very well thank you.")
        12 -> {
            it.chatNpc("Do you have Sins??")
        }
        13 -> it.chatNpc("I'm a little worried - I've heard there's",
                                "lots of people going about, killing citizens",
                                "at random.")
        14 -> it.chatNpc("That is classified information.")
        15 -> it.chatNpc("Saradomin is our god!")
        16 -> it.chatNpc("No, I don't want to buy anything!")
        17 -> it.chatNpc("Amen.")
    }
}