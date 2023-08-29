package gg.rsmod.plugins.content.areas.taverley

import gg.rsmod.plugins.content.mechanics.shops.CoinCurrency


create_shop("Jatix's Herblore Shop", CoinCurrency(), containsSamples = false, purchasePolicy = PurchasePolicy.BUY_STOCK) {
    var index = 0
    items[index++] = ShopItem(Items.VIAL, 10)
    items[index++] = ShopItem(Items.VIAL_PACK, 5)
    items[index++] = ShopItem(Items.EYE_OF_NEWT, 10)
    items[index] = ShopItem(Items.EYE_OF_NEWT_PACK, 5)
}

on_npc_option(npc = Npcs.JATIX, option = "trade") {
    player.openShop("Jatix's Herblore Shop")
}

on_npc_option(npc = Npcs.JATIX, option = "talk-to") {
    player.queue {
        chatNpc("Hello. How can I help you?")
        when (this.options("What are you selling?","I'm okay, thank you.")) {
            1 -> player.openShop("Jatix's Herblore Shop")
            2 -> Unit
        }
    }
}