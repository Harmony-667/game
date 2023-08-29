package gg.rsmod.plugins.content.areas.legends_guild

val stairs = listOf(
    Objs.LADDER_41458,
    Objs.LADDER_1746,)

stairs.forEach { stairs ->
    if (if_obj_has_option(obj = stairs, option = "climb-up")) {
        on_obj_option(obj = stairs, option = "climb-up") {
            player.handleLadder(height = 2)
        }
    }
    if (if_obj_has_option(obj = stairs, option = "climb-down")) {
        on_obj_option(obj = stairs, option = "climb-down") {
            player.handleLadder(height = 1)
        }
    }
}
on_obj_option(obj = Objs.STAIRCASE_41436, option = "Climb-Down") {
    player.moveTo(2732, 3376, 0)
}
on_obj_option(obj = Objs.STAIRCASE_41435, option = "Climb-Up") {
    player.moveTo(2732, 3380, 1)
}

on_obj_option(obj = Objs.STAIRCASE_41425, option = "Climb-Down") {
    player.moveTo(2720, 9775, 0)
}
on_obj_option(obj = Objs.STAIRCASE_32048, option = "Climb-Up") {
    player.moveTo(2723, 3375, 0)
}