package gg.rsmod.plugins.content.areas.dragontooth_island

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */
//Entering KBD Dungeon
world.spawn(DynamicObject(id = Objs.CAVE_ENTRANCE, type = 10, rot = 2, tile = Tile(3801, 3529, 0)))//spawn Cave entrance

on_obj_option(obj = Objs.CAVE_ENTRANCE, option = "enter") {

    if (player.skills.getCurrentLevel(Skills.PRAYER) > 49) {
        if (player.skills.getCurrentLevel(Skills.SLAYER) > 49) {
            player.moveTo(3017, 10248)
        }
        else {
            player.message("You need a prayer and slayer level of 50")
        }
    }
    else {
        player.message("You need a prayer and slayer level of 50")
    }
}

    private val DRAGONTOOTH_ISLAND_REGIONS = intArrayOf(15159) //Multi combat
DRAGONTOOTH_ISLAND_REGIONS.forEach { set_multi_combat_region(region = it) }