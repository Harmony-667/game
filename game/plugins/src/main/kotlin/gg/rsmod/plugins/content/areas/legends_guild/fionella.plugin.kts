package gg.rsmod.plugins.content.areas.legends_guild
import gg.rsmod.plugins.content.mechanics.shops.CoinCurrency

/**
 * @author Eikenboom <https://github.com/Eikenb00m>
 */

create_shop("Legends Guild General Store", CoinCurrency(), containsSamples = false, purchasePolicy = PurchasePolicy.BUY_TRADEABLES) {
    items[0] = ShopItem(Items.SWORDFISH, 20)
    items[1] = ShopItem(Items.APPLE_PIE, 5)
    items[2] = ShopItem(Items.ATTACK_POTION_4, 3)
    items[3] = ShopItem(Items.IRON_ARROW, 1000)
    items[4] = ShopItem(Items.STEEL_ARROW, 500)
}

on_npc_option(npc = Npcs.FIONELLA, option = "trade") {
    player.openShop("Legends Guild General Store")
}
on_npc_option(npc = Npcs.FIONELLA, option = "talk-to") {
    player.openShop("Legends Guild General Store")
}