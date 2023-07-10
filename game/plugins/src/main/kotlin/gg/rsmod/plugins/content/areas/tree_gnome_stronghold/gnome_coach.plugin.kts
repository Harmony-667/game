package gg.rsmod.plugins.content.areas.tree_gnome_stronghold

import gg.rsmod.plugins.content.mechanics.shops.AgilityTickets
import gg.rsmod.plugins.content.mechanics.shops.CoinCurrency
import gg.rsmod.plugins.content.skills.Skillcapes

create_shop(
    "Agility Tickets Store",
    currency = AgilityTickets(),
    purchasePolicy = PurchasePolicy.BUY_NONE,
    containsSamples = false
)
{
    items[0] = ShopItem(Items.AGILE_TOP, 2, false, 100, 0, 1, 300)
    items[1] = ShopItem(Items.AGILE_LEGS, 2, false, 100, 0, 1, 300)
    items[2] = ShopItem(Items.BOOTS_OF_LIGHTNESS, 3, false, 75, 0, 1, 300)
    items[3] = ShopItem(Items.BRAWLING_GLOVES_AGILITY, 1, false, 150, 0, 600)
    items[4] = ShopItem(Items.AGILITY_POTION_4, 10, false,10, 0, 1, 300)
    items[5] = ShopItem(Items.AGILITY_TOME, 10, false,50, 0, 1, 300)
}


on_npc_option(npc = Npcs.GNOME_COACH, option = "talk-to") {
    player.queue {
        if (player.skills.getCurrentLevel(Skills.AGILITY) >= 1) { //Add later Expert Store
            mainChat (this, player)
        }else{
            mainChat (this, player)
        }

    }
}

suspend fun mainChat(it: QueueTask, player: Player) {
    it.chatNpc(
        "Welcome to Agility Arena tickets store",
        "Would you like to see the store?")
    when (it.options(
        "Yes please",
        "No thank you."
    )) {
        FIRST_OPTION -> {
            player.openShop("Agility Tickets Store")
        }
        SECOND_OPTION -> {
            //nothing, so close
        }
    }
}

