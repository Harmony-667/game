package gg.rsmod.plugins.content.areas.taverley

import gg.rsmod.plugins.content.mechanics.shops.CoinCurrency

create_shop("Gaius' Metal Shop", CoinCurrency(), containsSamples = false, purchasePolicy = PurchasePolicy.BUY_STOCK) {
    var index = 0
    items[index++] = ShopItem(Items.IRON_LONGSWORD, 5, false, 200, 100)
    items[index++] = ShopItem(Items.IRON_SCIMITAR, 5,false, 220, 100)
    items[index++] = ShopItem(Items.IRON_BATTLEAXE, 5,false, 240, 100)
    items[index++] = ShopItem(Items.IRON_2H_SWORD, 5,false, 250, 100)
    items[index++] = ShopItem(Items.IRON_KITESHIELD, 5,false, 250, 100)
    items[index++] = ShopItem(Items.IRON_FULL_HELM, 5,false, 250, 100)
    items[index++] = ShopItem(Items.IRON_PLATESKIRT, 5,false, 500, 100)
    items[index++] = ShopItem(Items.IRON_PLATELEGS, 5,false, 500, 100)
    items[index++] = ShopItem(Items.IRON_PLATEBODY, 5,false, 500, 100)
    items[index++] = ShopItem(Items.STEEL_LONGSWORD, 3, false, 1000, 500)
    items[index++] = ShopItem(Items.STEEL_SCIMITAR, 3, false, 1200, 500)
    items[index++] = ShopItem(Items.STEEL_BATTLEAXE, 3, false, 1250, 500)
    items[index++] = ShopItem(Items.STEEL_2H_SWORD, 3,false, 1500, 500)
    items[index++] = ShopItem(Items.STEEL_KITESHIELD, 3,false, 1500, 500)
    items[index++] = ShopItem(Items.STEEL_FULL_HELM, 3,false, 1500, 500)
    items[index++] = ShopItem(Items.STEEL_PLATESKIRT, 3,false, 1750, 500)
    items[index++] = ShopItem(Items.STEEL_PLATELEGS, 3,false, 1750, 500)
    items[index++] = ShopItem(Items.STEEL_PLATEBODY, 3,false, 1750, 500)
    items[index++] = ShopItem(Items.BLACK_LONGSWORD, 1,false, 2000, 1000)
    items[index++] = ShopItem(Items.BLACK_SCIMITAR, 1,false, 2100, 1000)
    items[index++] = ShopItem(Items.BLACK_BATTLEAXE, 1,false, 2200, 1000)
    items[index++] = ShopItem(Items.BLACK_2H_SWORD, 1,false, 2500, 1000)
    items[index++] = ShopItem(Items.BLACK_KITESHIELD, 1,false, 2500, 1000)
    items[index++] = ShopItem(Items.BLACK_FULL_HELM, 1,false, 2500, 1000)
    items[index++] = ShopItem(Items.BLACK_PLATESKIRT, 1,false, 3000, 1000)
    items[index++] = ShopItem(Items.BLACK_PLATELEGS, 1,false, 3000, 1000)
    items[index] = ShopItem(Items.BLACK_PLATEBODY, 1,false, 3000, 1000)

}

on_npc_option(npc = Npcs.GAIUS, option = "trade") {
    player.openShop("Gaius' Metal Shop")
}

on_npc_option(npc = Npcs.GAIUS, option = "talk-to") {
    player.queue {
        chatNpc("Hello. How can I help you?")
        when (this.options("What are you selling?","I'm okay, thank you.")) {
            1 -> player.openShop("Gaius' Metal Shop")
            2 -> Unit
        }
    }
}
