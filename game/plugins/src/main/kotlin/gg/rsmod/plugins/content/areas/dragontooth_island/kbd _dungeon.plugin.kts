package gg.rsmod.plugins.content.areas.dragontooth_island

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */
//Entering KBD Dungeon
world.spawn(DynamicObject(id = Objs.CAVE_ENTRANCE, type = 10, rot = 2, tile = Tile(3801, 3529, 0)))//spawn Cave entrance

on_obj_option(obj = Objs.CAVE_ENTRANCE, option = "enter") {
    player.moveTo(3017, 10248)
}
on_obj_option(obj = Objs.LADDER_32015, option = "climb-up") {
    player.moveTo(3803, 3533)
}
