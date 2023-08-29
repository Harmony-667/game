package gg.rsmod.plugins.content.areas.catherby

import gg.rsmod.plugins.content.skills.Skillcapes

val FORCE_CHAT_TIMER = TimerKey()
val DELAY = 10..100

on_global_npc_spawn {
    when (npc.id) {
        Npcs.FISHERMAN -> npc.timers[FORCE_CHAT_TIMER] = world.random(DELAY)
    }
}

on_timer(FORCE_CHAT_TIMER) {
    if (!npc.isAlive())
        return@on_timer
    npc.forceChat("I am looking for experienced anglers!")
    npc.timers[FORCE_CHAT_TIMER] = DELAY.random()
}
on_npc_option(npc = Npcs.FISHERMAN, option = "talk-to") {
    player.queue {
        if (player.skills.getCurrentLevel(Skills.FISHING) >= 60) {
            mainChatWith60 (this)
        }else{ mainChat (this)
        }
    }
}

suspend fun mainChat(it: QueueTask) {
    it.chatNpc(
        "Greetings, adventurer. How can I help you?")
    when (it.options(
        "Who are you?",
        "Can you teleport me to the fishing guild?",
        "Never Mind"
    )) {
        FIRST_OPTION -> {
            it.chatPlayer("Who are you?")
            it.chatNpc(
                "I'm a old fisherman, got a high skill of fishing!",
                "I had fight for years with fish!")
        }
        SECOND_OPTION -> {
            it.chatPlayer("Can you teleport me to the fishing guild?")
            it.chatNpc(
                "Yes i can, but only realy experienced fishers",
                "can catch fish there, come back when",
                "you got a fishing level of 60.")
        }
        THIRD_OPTION -> {
            it.chatPlayer("Never Mind")
        }
    }
}

suspend fun mainChatWith60(it: QueueTask) {
    it.chatNpc(
        "Greetings, adventurer. How can I help you?")
    when (it.options(
        "Who are you?",
        "Can you teleport me to the fishing guild?",
        "Never Mind"
    )) {
                FIRST_OPTION -> {
                    it.chatPlayer("Who are you?")
                    it.chatNpc(
                        "I'm a old fisherman, got a high skill of fishing!",
                        "I had fight for years with fish!")
                }
                SECOND_OPTION -> {
                    it.chatPlayer("Can you teleport me to the fishing guild?")
                    it.chatNpc(
                        "Sure i can teleport you to the fishing guild.",
                        "are you sure about it?")
                    when (it.options(
                        "Yes.",
                        "No.")) {
                        FIRST_OPTION -> {
                            it.chatPlayer("Yes, please.")
                            travelTeleport(it.player, dialogue = "De Piscibus Et Mari!", Tile(2596, 3403, 0))
                            }
                        SECOND_OPTION -> {
                            it.chatPlayer("No thanks...")
                            }
                        }
                    }
        THIRD_OPTION -> {
            it.chatPlayer("Never Mind")
        }
    }
}
