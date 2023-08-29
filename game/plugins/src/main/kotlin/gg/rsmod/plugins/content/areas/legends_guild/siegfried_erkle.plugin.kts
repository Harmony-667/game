package gg.rsmod.plugins.content.areas.legends_guild
import gg.rsmod.plugins.content.mechanics.shops.CoinCurrency

/**
 * @author Eikenboom <https://github.com/Eikenb00m>
 */

create_shop("Legends Guild Shop", CoinCurrency(), containsSamples = false, purchasePolicy = PurchasePolicy.BUY_NONE) {
    items[0] = ShopItem(Items.BLACK_FULL_HELM_G, 1)
    items[1] = ShopItem(Items.BLACK_PLATEBODY_G, 1)
    items[2] = ShopItem(Items.BLACK_PLATELEGS_G, 1)
    items[3] = ShopItem(Items.BLACK_PLATESKIRT_G, 1)
    items[4] = ShopItem(Items.BLACK_KITESHIELD_G, 1)
    items[5] = ShopItem(Items.BLACK_FULL_HELM_T, 1)
    items[6] = ShopItem(Items.BLACK_PLATEBODY_T, 1)
    items[7] = ShopItem(Items.BLACK_PLATELEGS_T, 1)
    items[8] = ShopItem(Items.BLACK_PLATESKIRT_T, 1)
    items[9] = ShopItem(Items.BLACK_KITESHIELD_T, 1)
    items[10] = ShopItem(Items.ANTIDRAGON_SHIELD, 1)
    items[11] = ShopItem(Items.CAPE_OF_LEGENDS, 1)
}

on_npc_option(npc = Npcs.SIEGFRIED_ERKLE, option = "trade") {
    player.openShop("Legends Guild Shop")
}
on_npc_option(npc = Npcs.SIEGFRIED_ERKLE, option = "talk-to") {
    player.openShop("Legends Guild Shop")
}