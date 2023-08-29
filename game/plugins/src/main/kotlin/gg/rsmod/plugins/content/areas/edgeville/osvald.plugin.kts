package gg.rsmod.plugins.content.areas.edgeville

import gg.rsmod.plugins.content.mechanics.shops.CoinCurrency

create_shop("Wild Food Store", CoinCurrency(), containsSamples = false, purchasePolicy = PurchasePolicy.BUY_NONE) {
    var index = 0
    items[index++] = ShopItem(Items.ROLL, 5, false, 1000)
    items[index++] = ShopItem(Items.SPICY_CRUNCHIES, 5,false, 2500)
    items[index++] = ShopItem(Items.RED_BANANA, 2,false, 5000)
    items[index++] = ShopItem(Items.FRIED_ONIONS, 5,false, 2500)
    items[index++] = ShopItem(Items.FRIED_MUSHROOMS, 5,false, 2500)
    items[index++] = ShopItem(Items.PLAIN_PIZZA, 5,false, 3500)
    items[index++] = ShopItem(Items.REDBERRY_PIE, 5,false, 4000)
    items[index++] = ShopItem(Items.TRIANGLE_SANDWICH, 5,false, 3500)
    items[index] = ShopItem(Items.COOKED_CHOMPY, 1,false, 5000)

}

on_npc_option(npc = Npcs.OSVALD, option = "trade") {
    player.openShop("Wild Food Store")
}

on_npc_option(npc = Npcs.OSVALD, option = "talk-to") {
    player.queue {
        chatNpc("Hello. How can I help you?")
        when (this.options("What are you selling?","I'm okay, thank you.")) {
            1 -> player.openShop("Wild Food Store")
            2 -> Unit
        }
    }
}
