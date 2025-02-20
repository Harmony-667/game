package gg.rsmod.plugins.content.starter

import gg.rsmod.game.model.attr.CREATION_DATE
import gg.rsmod.game.model.attr.NEW_ACCOUNT_ATTR
import gg.rsmod.game.model.interf.DisplayMode
import gg.rsmod.plugins.content.inter.bank.Bank
import gg.rsmod.util.Misc

load_metadata {
    propertyFileName = "starter_kit"

    author = "Tomm"
    name = "Starter Kit"
    description = "Give items to new accounts."

    properties(
        // Inventory first row
        0.getItem to Items.BRONZE_SWORD,
        1.getItem to Items.WOODEN_SHIELD,
        2.getItem to Items.SHORTBOW,
        3.getItem to Items.BRONZE_ARROW,
        3.getItemAmount to 25,

        // Inventory second row
        4.getItem to Items.AIR_RUNE,
        4.getItemAmount to 25,
        5.getItem to Items.MIND_RUNE,
        5.getItemAmount to 15,
        6.getItem to Items.COINS_995,
        5.getItemAmount to 25,
    )
}

on_login {
    val newAccount = player.attr[NEW_ACCOUNT_ATTR] ?: return@on_login
    if (newAccount) {
        val inventory = player.getInventoryStarterItems()
        val bank = player.getBankStarterItems()

        inventory.forEach { slotItem ->
            player.inventory.add(item = slotItem.item, beginSlot = slotItem.slot)
        }

        bank.forEach { slotItem ->
            player.bank.add(item = slotItem.item, beginSlot = slotItem.slot)
        }

        player.setCurrentPrayerPoints(10)
        player.setVarp(Bank.LAST_X_INPUT, 50)
        player.attr[CREATION_DATE] = System.currentTimeMillis()

        world.players.entries.filterNotNull().filter { it != player }.forEach {
            val color = when (it.interfaces.displayMode) {
                DisplayMode.FIXED -> "0000ff"
                DisplayMode.RESIZABLE_NORMAL -> "7fa9ff"
                else -> "#fa9ff"
            }
            it.message(
                "[<col=d45b5b>Global</col>]: <col=$color>${Misc.formatForDisplay(player.username)} has just logged into Harmony for the first time.</col>"
            )
        }
    }
}

fun Player.getInventoryStarterItems() = getStarterItems(inventory.capacity, { getItem }, { getItemAmount })

fun Player.getBankStarterItems() = getStarterItems(bank.capacity, { getBankItem }, { getBankItemAmount })

fun getStarterItems(containerCapacity: Int, itemProperty: (Int).() -> String, amountProperty: (Int).() -> String): List<SlotItem> {
    val items = mutableListOf<SlotItem>()
    for (i in 0 until containerCapacity) {
        val item = getProperty<Int>(itemProperty(i)) ?: continue
        val amt = getProperty<Int>(amountProperty(i)) ?: 1
        items.add(SlotItem(i, Item(item, amt)))
    }
    return items
}

val Int.getItem: String
    get() = "item[$this]"

val Int.getItemAmount: String
    get() = "amount[$this]"

val Int.getBankItem: String
    get() = "bank_item[$this]"

val Int.getBankItemAmount: String
    get() = "bank_amount[$this]"