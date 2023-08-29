package gg.rsmod.plugins.content.skills.thieving.cages

import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.cfg.Items
import gg.rsmod.plugins.api.cfg.Npcs
import gg.rsmod.plugins.api.cfg.Objs
import gg.rsmod.plugins.content.drops.DropTableBuilder
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.DropTableType

enum class CageTarget(
    val fullAndEmptyObjectIds: Map<Int, Int>,
    val level: Int,
    val xp: Double,
    val drops: DropTableBuilder.() -> Unit,
    val respawnTicks: Int,
    val message: String,
    val guards: List<Int> = listOf(Npcs.WATCHMAN),
    val lowChance: Int = 0,
    val highChance: Int = 0,
) {
    Cage(
        fullAndEmptyObjectIds = mapOf(Objs.CAGE to Objs.CAGE_13313),
        level = 1,
        xp = 10.0,
        drops = DropTableFactory.build {
            guaranteed {
                obj(Items.COINS_995, quantity = 2)
            }
        },
        respawnTicks = 5,
        message = "You unlocked the Cage and found some gold."
    );

    fun hasInventorySpace(player: Player): Boolean {
        return DropTableFactory.hasInventorySpaceForAnyDrop(player, fullAndEmptyObjectIds.toList().first().first, DropTableType.CAGE)
            ?: false
    }

    fun getEmpty(id: Int) = fullAndEmptyObjectIds[id]
}
