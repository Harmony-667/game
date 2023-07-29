package gg.rsmod.plugins.content.skills.slayer

import gg.rsmod.plugins.content.mechanics.shops.CoinCurrency

/**
 * @author Alycia <https://github.com/alycii>
 */

create_shop(
    "Slayer Equipment",
    currency = CoinCurrency(),
    purchasePolicy = PurchasePolicy.BUY_NONE,
    containsSamples = false
) {
    var index = 0
    items[index++] = ShopItem(Items.ENCHANTED_GEM, amount = 10000)
    items[index] = ShopItem(Items.EARMUFFS, amount = 10)

}

val masters = arrayOf(Npcs.TURAEL, Npcs.SPRIA, Npcs.VANNAKA, Npcs.MAZCHNA, Npcs.ACHTRYN, Npcs.CHAELDAR, Npcs.SUMONA, Npcs.DURADEL_8466, Npcs.LAPALOK, Npcs.KURADAL_9085)
masters.forEach {
    on_npc_option(npc = it, option = "trade") {
        player.openShop("Slayer Equipment")
    }
}