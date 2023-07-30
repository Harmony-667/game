package gg.rsmod.plugins.content.areas.seers
on_obj_option(obj = Objs.LADDER_25938, option = "climb-Up") {
    when(player.tile.x) {
        2715 -> { // Spinningwheel
            player.handleLadder(x = 2715, z = 3471, 1)
        }
        2714 -> { // Spinningwheel
            player.handleLadder(x = 2715, z = 3471, 1)
        }
        else ->  player.handleLadder(x = 2728, z = 3492, 1)
    }
}
/*on_obj_option(obj = Objs.LADDER_25939, option = "Climb-Down") {
    player.moveTo(2728, 3492, 0)
}*/
on_obj_option(obj = Objs.LADDER_25939, option = "climb-Down") {
    when(player.tile.x) {
        2715 -> { // Spinningwheel
            player.handleLadder(x = 2715, z = 3471, 0)
        }
        2714 -> { // Spinningwheel
            player.handleLadder(x = 2715, z = 3471, 0)
        }
        else ->  player.handleLadder(x = 2728, z = 3492, 0)
    }
}

on_obj_option(obj = Objs.TRAPDOOR_26119, option = "Climb-Down") {
    player.handleLadder(2714, 3472, 1)
}
on_obj_option(obj = Objs.LADDER_26118, option = "Climb-Up") {
    player.handleLadder(2714, 3472, 3)
}
on_obj_option(obj = Objs.STAIRCASE_25801 , option = "Climb-Down") {
    player.handleLadder(2697, 3496, 0)
}
on_obj_option(obj = Objs.STAIRCASE_25935, option = "Climb-Up") {
    player.handleLadder(2698, 3495, 1)
}