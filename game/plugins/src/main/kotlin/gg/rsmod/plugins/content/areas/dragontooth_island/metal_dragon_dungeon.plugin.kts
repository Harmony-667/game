package gg.rsmod.plugins.content.areas.dragontooth_island

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */
on_obj_option(obj = Objs.LEVER_1817, option = "pull") {
    player.moveTo(3067, 10254)
}

private val METAL_DUNGEON_REGIONS = intArrayOf(9033) //Multi combat
METAL_DUNGEON_REGIONS.forEach { set_multi_combat_region(region = it) }