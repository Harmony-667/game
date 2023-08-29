package gg.rsmod.plugins.content.areas.yanille

on_npc_option(Npcs.BARTENDER_739, option = "talk-to") {
    player.queue { chat(this) }
}

suspend fun chat(it: QueueTask) {
    it.chatPlayer("Hello, how's it going?")
    when (world.random(5)) {
        0 -> it.chatNpc("Not too bad thanks.")
        1 -> {
            it.chatNpc("Not too bad, but I'm a little worried about", "the increase of Trolls these days.")
            it.chatPlayer("Don't worry, I'll kill them.")
        }
        2 -> {
            it.chatNpc("Who are you?")
            it.chatPlayer("I'm a bold adventurer.")
            it.chatNpc("Ah, a very noble profession.")
        }
        3 -> {
            it.chatNpc("How can I help you?")
            when (
                it.options(
                    "What ales are you serving?",
                    "What can you tell me about the Dragon bitter?",
                    "I'm in search of enemies to kill.",
                )
            ) {
                1 -> {
                    it.chatPlayer("What ales are you serving?")
                    it.chatNpc("Well, we've got beer, Wizard's Mind Bomb,", "and Dragon bitter.")
                    when (
                        it.options(
                            "One Beer, please.",
                            "I'll try the Mind Bomb.",
                            "Can I have a Dragon bitter?",
                            "I don't feel like any of those.",
                        )
                    ) {
                        1 -> {
                            it.chatPlayer("One Beer, please. Thanks.")
                            it.chatNpc("Here's a Asgarnian Ale on the house!")
                            it.player.inventory.add(Items.ASGARNIAN_ALE, 1, true, false, -1)
                        }

                        2 -> {
                            it.chatPlayer("I'll try the Mind Bomb.")
                            it.chatNpc("Here's a Mind Bomb on the house!")
                            it.player.inventory.add(Items.WIZARDS_MIND_BOMB, 1, true, false, -1)
                        }

                        3 -> {
                            it.chatNpc("Here's a Dragon bitter on the house!")
                            it.player.inventory.add(Items.DRAGON_BITTER, 1, true, false, -1)
                        }

                        4 -> {
                            it.chatPlayer("I don't feel like any of those.")
                        }
                    }
                }

                2 -> {
                    it.chatPlayer("What can you tell me about the Dragon bitter?")
                    it.chatNpc("Oh it's you again. Forgotten the secret already?")
                    it.chatPlayer("No... I just... misplaced it.")
                    it.chatNpc(
                        "Mmmmm... the secret is in the gold.",
                        "Drop a dragon tooth into beer and",
                        "watch it unfold.",
                    )
                    it.chatPlayer("Thanks, I wont forg...err...misplcae it, this time.")
                }


                3 -> {
                    it.chatPlayer("I'm in search of enemies to kill.")
                    it.chatNpc("I've heard there are many fearsome creatures", "in the Yanille Dungeons, but you need a red key for that.")
                }
            }
        }

        4 -> it.chatNpc("I'm a little worried. I've heard there are people going", "about killing citizens at random!")
        5 -> it.chatNpc("That is classified information.")
    }
}
