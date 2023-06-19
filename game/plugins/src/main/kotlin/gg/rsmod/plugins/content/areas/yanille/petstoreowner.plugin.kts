package gg.rsmod.plugins.content.areas.yanille

import gg.rsmod.plugins.content.mechanics.shops.CoinCurrency

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */

create_shop("Yanille Pet Shop", CoinCurrency(), containsSamples = false, purchasePolicy = PurchasePolicy.BUY_STOCK) {
    sampleItems[0] = ShopItem(Items.SPIRIT_SHARDS, 5, resupplyCycles = 5000)
    sampleItems[1] = ShopItem(Items.POUCH, 1, resupplyCycles = 5000)
    sampleItems[2] = ShopItem(Items.GOLD_CHARM, 1, resupplyCycles = 5000)
    items[0] = ShopItem(Items.SPIRIT_SHARDS, 5000)
    items[1] = ShopItem(Items.POUCH, 1000)
    items[2] = ShopItem(Items.SPIRIT_SHARD_PACK, 1000)
}

on_npc_option(npc = Npcs.PET_SHOP_OWNER, option = "Trade") {
    player.openShop("Yanille Pet Shop")
}

on_npc_option(npc = Npcs.ALECK, "talk-to") {
    player.queue {
        chatPlayer("Hello.")
        chatNpc("Hello,"," welcome to the Yanille Pet Store.", "We have everything that you need for Summoning.")
        chatNpc("Would you like to look into my store?"," Or was there something specific you were after?")
        when(options("Ok, let's see what you've got!","Where are the Moss Giants that lived here ages ago?", "I'm not interested, thanks.", title = "Select an Option")) {
            1 -> {
                chatPlayer("Ok, let's see what you've got!")
                player.openShop("Yanille Pet Shop")
            }
            2 -> {
                chatPlayer("here are the Moss Giants that lived here ages ago?")
                chatNpc("Well, they are moved since we start building here.")
                chatNpc("They are now living at the woods, right behind", "the south city wall of yanille!")
                chatNpc("Maybe you can see them form behind the shop!")
            }
            3 -> {
                chatPlayer("I'm not interested, thanks.")
                chatNpc("Well, 'if you do ever find yourself in need of ", "the finest Hunter equipment available, ", "then you know where to come.")
            }
        }
    }
}
