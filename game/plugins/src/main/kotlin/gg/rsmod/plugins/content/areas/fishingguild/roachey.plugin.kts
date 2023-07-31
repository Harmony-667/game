package gg.rsmod.plugins.content.areas.fishingguild

import gg.rsmod.plugins.content.mechanics.shops.CoinCurrency



create_shop("Fishing Guild Shop", CoinCurrency(), containsSamples = false, purchasePolicy = PurchasePolicy.BUY_STOCK) {
    var index = 0
    items[index++] = ShopItem(Items.SMALL_FISHING_NET, 5, false, 5, 3)
    items[index++] = ShopItem(Items.BIG_FISHING_NET, 5, false, 20, 14)
    items[index++] = ShopItem(Items.FISHING_ROD, 5, false, 5, 3)
    items[index++] = ShopItem(Items.FLY_FISHING_ROD, 5, false, 5, 3)
    items[index++] = ShopItem(Items.HARPOON, 5, false, 20, 14)
    items[index++] = ShopItem(Items.LOBSTER_POT, 5, false, 20, 15)
    items[index++] = ShopItem(Items.FISHING_BAIT, 2000, false, 11,0)
    items[index++] = ShopItem(Items.FEATHER, 1500, false, 25, 0)
    items[index++] = ShopItem(Items.RAW_MACKEREL, 0, false, 15, 10)
    items[index++] = ShopItem(Items.RAW_COD, 0, false, 20, 10)
    items[index++] = ShopItem(Items.RAW_TUNA, 0, false, 25, 10)
    items[index++] = ShopItem(Items.RAW_LOBSTER, 0, false, 50, 15)
    items[index++] = ShopItem(Items.RAW_BASS, 0, false, 25, 10)
    items[index++] = ShopItem(Items.RAW_SWORDFISH, 0, false, 50, 15)
    items[index++] = ShopItem(Items.RAW_MONKFISH, 0, false, 100, 25)
    items[index++] = ShopItem(Items.RAW_SHARK, 0, false, 250, 50)
    items[index++] = ShopItem(Items.RAW_SEA_TURTLE, 0, false, 300, 50)
    items[index] = ShopItem(Items.RAW_MANTA_RAY, 0, false, 500, 100)



}

on_npc_option(npc = Npcs.ROACHEY, option = "trade") {
    player.openShop("Fishing Guild Shop")
}
on_npc_option(npc = Npcs.ROACHEY, option = "talk-to") {
    player.queue {
        chatNpc("Hello. How can I help you?")
        when (this.options("What are you selling?","I'm okay, thank you.")) {
            1 -> player.openShop("Fishing Guild Shop")
            2 -> Unit
        }
    }
}