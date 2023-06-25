package gg.rsmod.plugins.content.areas.slayertower

on_obj_option(obj = Objs.ROCK_SLIDE, option = "Investigate") {
                  player.message("Its a rock slide, maybe you can mine it away!")
        }

on_obj_option(obj = Objs.ROCK_SLIDE, option = "Mine") {
    player.message("Comming Soon")
}
