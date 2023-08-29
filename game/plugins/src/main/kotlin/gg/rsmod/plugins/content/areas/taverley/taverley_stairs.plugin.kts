package gg.rsmod.plugins.content.areas.taverley

val ladders = listOf(
    Objs.LADDER_28652, //ladders for sanfew house
    Objs.LADDER_35125,
    Objs.LADDER_28653,
    Objs.LADDER_28572,

)

ladders.forEach { ladder ->
    if (if_obj_has_option(ladder, option = "climb-up")) {
        on_obj_option(ladder, option = "climb-up") {
            player.handleLadder(height = 1)
        }
    }
    if (if_obj_has_option(ladder, option = "climb-down")) {
        on_obj_option(ladder, option = "climb-down") {
            player.handleLadder(height = 0)
        }
    }
}

on_obj_option(obj = Objs.LADDER_55404, option = "climb-down") { //handles the ladder south of taverley leading to the dungeon
    player.queue {
        player.animate(827) //animation id for climbing down ladder
        wait(world.getAnimationDelay(827))
        player.moveTo(Tile(2884, 9798, 0)) //sets the x, z, height for player destination
    }
}