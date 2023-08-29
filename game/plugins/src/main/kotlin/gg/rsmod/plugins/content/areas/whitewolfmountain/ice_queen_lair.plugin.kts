package gg.rsmod.plugins.content.areas.whitewolfmountain

on_obj_option(obj = Objs.LADDER_20987, option = "climb-down") { //handles the ladder south of taverley leading to the dungeon
    player.queue {
        player.animate(827) //animation id for climbing down ladder
        wait(world.getAnimationDelay(827))
        player.moveTo(Tile(2858, 9919, 0)) //sets the x, z, height for player destination
        player.message("You climb down into the ice mountain...")
    }
}
on_obj_option(obj = Objs.LADDER_33184, option = "climb-up") { //handles the ladder south of taverley leading to the dungeon
    player.queue {
        player.animate(827) //animation id for climbing down ladder
        wait(world.getAnimationDelay(827))
        player.moveTo(Tile(2844, 3516, 0)) //sets the x, z, height for player destination
    }
}
private val ICE_QUEEN_LAIR_REGIONS = intArrayOf(11419, 11418) //Multi combat
ICE_QUEEN_LAIR_REGIONS.forEach { set_multi_combat_region(region = it) }