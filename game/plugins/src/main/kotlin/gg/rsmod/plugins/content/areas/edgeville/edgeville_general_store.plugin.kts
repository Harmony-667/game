package gg.rsmod.plugins.content.areas.edgeville

import gg.rsmod.plugins.content.mechanics.shops.CoinCurrency

val shopkeepers = arrayOf(Npcs.SHOPKEEPER_522, Npcs.SHOP_ASSISTANT_523)

create_shop("Edgeville General Store", CoinCurrency()) {
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
        player.openShop("Edgeville General Store")
    }

    on_npc_option(it, "talk-to") {
        player.queue {
            chatNpc("Can I help you at all?")
            when(options("Yes please. What are you selling?", "No thanks.", title = "Select an Option")) {
                1 -> {
                    player.openShop("Edgeville General Store")
                }
                2 -> {
                    chatPlayer("No thanks.")
                }
            }
        }
    }
}
