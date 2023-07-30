package gg.rsmod.plugins.content.areas.seers

import gg.rsmod.plugins.content.mechanics.shops.CoinCurrency
import gg.rsmod.plugins.content.skills.Skillcapes



on_npc_option(npc = Npcs.MASTER_CRAFTER, option = "talk-to") {
    player.queue {
        if (player.skills.getCurrentLevel(Skills.CRAFTING) >= 99) {
            mainChatWith99 (this, player)
        }else{
            mainChat (this, player)
        }

    }
}

suspend fun mainChat(it: QueueTask, player: Player) {
    it.chatNpc(
        "Hello,", "Would you like to ask about a Skillcape of Crafting?")
    when (it.options(
        "Yes please",
        "No thank you."
    )) {
        FIRST_OPTION -> {
           it.chatNpc (
               "This? This is a Skillcape of Crafting.", " If you should ever achieve level 99 Crafting come and talk ", "to me and we'll see if we can sort you out with one."
           )
        }
        SECOND_OPTION -> {
            //nothing, so close
        }
    }
}

suspend fun mainChatWith99(it: QueueTask, player: Player) {
    it.chatPlayer("Are you the person I need to talk to about", "buying a Skillcape of Crafting?")
    it.chatNpc("I certainly am, and I can see that you", "are definitely talented enough to own one!")
    it.chatNpc("Unfortunately, being such a prestigious item,", "they are appropriately expensive. ", "I'm afraid I must ask you for 99000 gold.")
    when (it.options(
        "99000 gold? Are you mad?",
        "I think I have the money right here, actually.",
        "No thank you"
    )) {
        FIRST_OPTION -> {
            it.chatPlayer("99000 gold? Are you mad?")
            it.chatNpc(
                "Not at all; there are many other adventurers who",
                "would love the opportunity to purchase such a",
                "prestigious item! You can find me here if you change",
                "your mind.")
        }
        SECOND_OPTION -> {
            it.chatPlayer("I think I have the money right here, actually.")
            if (it.player.inventory.freeSlotCount < 2) {
                it.chatNpc(
                    "You don't have enough free space in your inventory ",
                    "for me to sell you a Skillcape of Crafting."
                )
                it.chatNpc("Come back to me when you've cleared up some space.")
                return
            }
            if (it.player.inventory.getItemCount(Items.COINS_995) >= 99000) {
                Skills.purchaseSkillcape(it.player, data = Skillcapes.CRAFTING)
                it.chatNpc("Excellent! Wear that cape with pride my friend.")
            } else{
                it.chatPlayer("But, unfortunately, I was mistaken.")
                it.chatNpc("Well, come back and see me when you do.")
            }
        }
        THIRD_OPTION -> {
            //nothing, so close
        }
    }
}