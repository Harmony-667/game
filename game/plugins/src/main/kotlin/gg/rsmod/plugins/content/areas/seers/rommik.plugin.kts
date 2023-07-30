package gg.rsmod.plugins.content.areas.seers

import gg.rsmod.plugins.content.mechanics.shops.CoinCurrency

create_shop("Rommik's Crafty Supplies", CoinCurrency(), containsSamples = false, purchasePolicy = PurchasePolicy.BUY_STOCK) {
    var index = 0
    items[index++] = ShopItem(Items.NEEDLE, 5, false, 5, 0)
    items[index++] = ShopItem(Items.THREAD, 500,false, 5, 0)
    items[index++] = ShopItem(Items.CHISEL, 2,false, 5, 0)
    items[index++] = ShopItem(Items.RING_MOULD, 4,false, 100, 0)
    items[index++] = ShopItem(Items.NECKLACE_MOULD, 2,false, 100, 0)
    items[index++] = ShopItem(Items.BRACELET_MOULD, 5,false, 100, 0)
    items[index++] = ShopItem(Items.AMULET_MOULD, 2,false, 100, 0)
    items[index++] = ShopItem(Items.TIARA_MOULD, 1,false, 100, 0)
    items[index++] = ShopItem(Items.UNHOLY_MOULD, 1,false, 100, 0)
    items[index++] = ShopItem(Items.HOLY_MOULD, 2,false, 100, 0)
    items[index++] = ShopItem(Items.SICKLE_MOULD, 1,false, 100, 0)
    items[index++] = ShopItem(Items.BOLT_MOULD, 1,false, 100, 0)
    items[index++] = ShopItem(Items.FINE_CLOTH, 10,false, 1000, 100)
    items[index++] = ShopItem(Items.SINEW, 10,false, 100, 100)
    items[index++] = ShopItem(Items.FLAX, 25,false, 1000, 100)
    items[index++] = ShopItem(Items.WOOL, 10,false, 1000, 0)
    items[index] = ShopItem(Items.COWHIDE, 5,false, 1000, 0)

}

on_npc_option(npc = Npcs.ROMMIK, option = "trade") {
    player.openShop("Rommik's Crafty Supplies")
}

on_npc_option(npc = Npcs.ROMMIK, option = "talk-to") {
    player.queue {
        chatNpc("Hello. How can I help you?")
        when (this.options("What are you selling?","I'm okay, thank you.")) {
            1 -> player.openShop("Rommik's Crafty Supplies")
            2 -> Unit
        }
    }
}
