package gg.rsmod.plugins.content.skills.thieving.chests

import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.cfg.Items
import gg.rsmod.plugins.api.cfg.Objs
import gg.rsmod.plugins.content.drops.DropTableBuilder
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.DropTableType

enum class ChestTarget(
    val fullAndEmptyObjectIds: Map<Int, Int>,
    val level: Int,
    val xp: Double,
    val drops: DropTableBuilder.() -> Unit,
    val respawnTicks: Int,
    val message: String,
    val lowChance: Int = 0,
    val highChance: Int = 0,
) {
    YanilleChest(
        fullAndEmptyObjectIds = mapOf(Objs.CLOSED_CHEST_375 to Objs.OPEN_CHEST_378),
        level = 70,
        xp = 100.0,
        drops = DropTableFactory.build {
            main {
                total(1240)
                obj(Items.COINS_995, quantityRange = 100..1000, slots = 256)
                obj(Items.COINS_995, quantityRange = 1000..5000, slots = 20)
                obj(Items.WIZARD_BOOTS, quantity = 1, slots = 2)
                obj(Items.HIGHWAYMAN_MASK, quantity = 1, slots = 2)
                obj(Items.RANGER_BOOTS, quantity = 1, slots = 2)
                nothing(slots = 128)
            }
        },
        respawnTicks = 300,
        message = "You unlocked the Chest."
    );

    fun hasInventorySpace(player: Player): Boolean {
        return DropTableFactory.hasInventorySpaceForAnyDrop(player, fullAndEmptyObjectIds.toList().first().first, DropTableType.CHEST)
            ?: false
    }

    fun getEmpty(id: Int) = fullAndEmptyObjectIds[id]
}
