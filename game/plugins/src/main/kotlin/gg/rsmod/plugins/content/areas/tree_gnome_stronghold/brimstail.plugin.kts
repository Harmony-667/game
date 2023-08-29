package gg.rsmod.plugins.content.areas.tree_gnome_stronghold

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */

//Iron Key Dungeon
on_obj_option(obj = Objs.CAVE_ENTRANCE_17209, option = "Enter") {
    player.queue {
        player.moveTo(2523, 3070)
    }
}
on_obj_option(obj = Objs.TUNNEL_17222, option = "Exit") {
    player.queue {
        player.moveTo(2402, 3419)
    }
}
on_obj_option(obj = Objs.TUNNEL_17223, option = "Exit") {
    player.queue {
        player.moveTo(2402, 3419)
    }
}