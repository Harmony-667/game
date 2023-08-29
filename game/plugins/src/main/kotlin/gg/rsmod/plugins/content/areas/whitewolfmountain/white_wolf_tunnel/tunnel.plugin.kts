package gg.rsmod.plugins.content.areas.whitewolfmountain.white_wolf_tunnel

import gg.rsmod.game.model.priv.Privilege

//Catherby
on_obj_option(obj = Objs.STAIRS_55, option = "Climb-Down") {
    if (player.privilege.id >= 1) {
        player.moveTo(2821, 9882)
    } else {
        player.message("You must be premium to enter the tunnel.")
    }
}
//Taverley
on_obj_option(obj = Objs.STAIRS_57, option = "Climb-Down") {
    if (player.privilege.id >= 1) {
        player.moveTo(2876, 9879)
    } else {
        player.message("You must be premium to enter the tunnel.")
    }
}

//Catherby
on_obj_option(obj = Objs.STAIRS, option = "Climb-Up") {
    player.moveTo(2820, 3486)
}
//Taverley
on_obj_option(obj = Objs.STAIRS_56, option = "Climb-Up") {
    player.moveTo(2876, 3482)
}
//Train Cart
on_obj_option(obj = Objs.TRAIN_CART_7030, option = "Ride") {
    player.message("The tracks are broken. You cant ride it!")
}