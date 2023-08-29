package gg.rsmod.plugins.content.areas.fishingcolony

import gg.rsmod.plugins.content.mechanics.shops.CoinCurrency



create_shop("Arnold's Store", CoinCurrency(), containsSamples = false, purchasePolicy = PurchasePolicy.BUY_NONE) {
    var index = 0
    items[index++] = ShopItem(Items.BIG_FISHING_NET, 5, false, 20, 14)
    items[index++] = ShopItem(Items.RAW_MONKFISH, 5, false, 250, 3)
    items[index] = ShopItem(Items.MONKFISH, 10, false, 500, 0)



}

on_npc_option(npc = Npcs.ARNOLD_LYDSPOR, option = "trade") {
    player.openShop("Arnold's Store")
}