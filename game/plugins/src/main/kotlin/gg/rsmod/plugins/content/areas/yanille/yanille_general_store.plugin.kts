package gg.rsmod.plugins.content.areas.yanille

import gg.rsmod.plugins.content.mechanics.shops.CoinCurrency

val shopkeepers = arrayOf(Npcs.SHOPKEEPER, Npcs.SHOP_ASSISTANT)

create_shop("Yanille General Store", CoinCurrency()) {
    sampleItems[0] = ShopItem(Items.TINDERBOX_590, 1, resupplyCycles = 5000)
    sampleItems[1] = ShopItem(Items.HAMMER, 1, resupplyCycles = 5000)
    sampleItems[2] = ShopItem(Items.BRONZE_PICKAXE, 1, resupplyCycles = 5000)
    sampleItems[3] = ShopItem(Items.CHISEL, 1, resupplyCycles = 1000)
    sampleItems[4] = ShopItem(Items.BRONZE_HATCHET, 1, resupplyCycles = 1000)
    items[0] = ShopItem(Items.BRONZE_HATCHET, 5)
    items[1] = ShopItem(Items.SHEARS, 5)
    items[2] = ShopItem(Items.BUCKET, 5)
    items[3] = ShopItem(Items.TINDERBOX_590, 5)
    items[4] = ShopItem(Items.CHISEL, 5)
    items[5] = ShopItem(Items.HAMMER, 5)
    items[6] = ShopItem(Items.BRONZE_PICKAXE, 5)

}

shopkeepers.forEach {
    on_npc_option(it, "trade") {
        player.openShop("Yanille General Store")
    }

    on_npc_option(it, "talk-to") {
        player.queue {
            chatNpc("Can I help you at all?")
            when(options("Yes please. What are you selling?", "No thanks.", title = "Select an Option")) {
                1 -> {
                    player.openShop("Yanille General Store")
                }
                2 -> {
                    chatPlayer("No thanks.")
                }
            }
        }
    }
}
