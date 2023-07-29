package gg.rsmod.plugins.content.areas.yanille

//ladder to jail
on_obj_option(obj = Objs.LADDER_1754, option = "Climb-Down") {
    player.moveTo(2594, 9484)
}

on_obj_option(obj = Objs.LADDER_1757, option = "Climb-Up") {
    player.moveTo(2594, 3086)
}

//magicguild stairs between groundfloor and first floor
/*on_obj_option(obj = Objs.STAIRCASE_1722, option = "Climb-Up") {
    player.moveTo(2591, 3092, 1)
}*/

on_obj_option(obj = Objs.STAIRCASE_1723, option = "Climb-Down") {
    player.moveTo(2591, 3088, 0)
}
//magicguild stairs between first and second floor
on_obj_option(obj = Objs.STAIRCASE_1722, option = "Climb-Up") {
    player.moveTo(2591, 3087, 2)
}

/*
on_obj_option(obj = Objs.STAIRCASE_1723, option = "Climb-Down") {
    player.moveTo(2591, 3083, 1)
}*/
//magicguild East Portal
on_obj_option(obj = Objs.MAGIC_PORTAL, option = "Enter") {
    player.moveTo(2728, 3347, 0)
    player.message("You have been teleported...")
}
//magicguild South Portal
on_obj_option(obj = Objs.MAGIC_PORTAL_2157, option = "Enter") {
    player.moveTo(3087, 3491, 0)
    player.message("You have been teleported...")
}
//magicguild South Portal
on_obj_option(obj = Objs.MAGIC_PORTAL_2158, option = "Enter") {
    //player.moveTo(2728, 3347, 0)
    player.message("Comming Soon")
}