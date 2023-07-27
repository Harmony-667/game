package gg.rsmod.plugins.content.areas.dragontooth_island

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */
on_obj_option(obj = Objs.LADDER_32015, option = "climb-up") {
    player.moveTo(3803, 3533)
}
world.spawn(DynamicObject(id = Objs.PILE_OF_SKULLS, type = 10, rot = 0, tile = Tile(3069, 10256, 0)))
on_obj_option(obj = Objs.LEVER_1816, option = "pull") {
    player.moveTo(2273, 4681)
}

private val KBD_DUNGEON_REGIONS = intArrayOf(12192) //Multi combat
KBD_DUNGEON_REGIONS.forEach { set_multi_combat_region(region = it) }