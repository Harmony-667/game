package gg.rsmod.plugins.content.areas.dragontooth_island

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */
on_obj_option(obj = Objs.LADDER_32015, option = "climb-up") {
    when(player.tile.regionId) {
        12181 -> { // Wizards Tower
            player.handleLadder(x = 3104, z = 3161)
        }
        11673 -> { // Taverly Dungeon
            player.handleLadder(x = 2884, z = 3398)
        }
        11417 -> { // Waterobilisk Island
            //player.handleLadder(x = 2842, z = 3423)
            player.handleLadder(x = 2884, z = 3398)
        }
        else -> player.handleLadder(x = 3803, z = 3533) //KBD dungeon
    }
}

world.spawn(DynamicObject(id = Objs.PILE_OF_SKULLS, type = 10, rot = 0, tile = Tile(3069, 10256, 0)))
on_obj_option(obj = Objs.LEVER_1816, option = "pull") {
    player.moveTo(2273, 4681)
}

private val KBD_DUNGEON_REGIONS = intArrayOf(12192) //Multi combat
KBD_DUNGEON_REGIONS.forEach { set_multi_combat_region(region = it) }