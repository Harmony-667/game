package gg.rsmod.plugins.content.areas.yanille

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */

//Iron Key Dungeon
on_obj_option(obj = Objs.CAVE_ENTRANCE_2806, option = "Enter") {
    if(!player.inventory.contains(Items.IRON_KEY)) {
        player.message("A Strange force hold you form entering!.")
        return@on_obj_option
    }
    player.queue {
        player.moveTo(2646, 9378)
        player.message("The entrance you enterd, is a long dark weird tunnel. Be carefull!")
    }
}
on_obj_option(obj = Objs.TUNNEL_32068, option = "Pass-through") {
    player.queue {
        player.moveTo(2523, 3070)
    }
}
on_obj_option(obj = Objs.TUNNEL_32069, option = "Pass-through") {
    player.queue {
        player.moveTo(2523, 3070)
    }
}