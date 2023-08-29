package gg.rsmod.plugins.content.cmd

import gg.rsmod.game.model.entity.Player
import gg.rsmod.game.model.priv.Privilege
import gg.rsmod.plugins.content.magic.TeleportType
import gg.rsmod.plugins.content.magic.teleport

on_command("teleto", Privilege.ADMIN_POWER) {
    val args = player.getCommandArgs()
    tryWithUsage(
        player,
        args,
        "Invalid format! Example of proper command <col=42C66C>::teleto alycia</col>"
    ) { values ->
        val p = world.getPlayerForName(values[0].replace("_", " ")) ?: return@tryWithUsage
        player.teleport(p.tile, TeleportType.RING_OF_KINSHIP)
    }
}

on_command("teletome", Privilege.ADMIN_POWER) {
    val args = player.getCommandArgs()
    tryWithUsage(
        player,
        args,
        "Invalid format! Example of proper command <col=42C66C>::teleto alycia</col>"
    ) { values ->
        val p = world.getPlayerForName(values[0].replace("_", " ")) ?: return@tryWithUsage
        p.teleport(player.tile, TeleportType.RING_OF_KINSHIP)
    }
}

on_command("tele", Privilege.ADMIN_POWER) {
    val args = player.getCommandArgs()
    var x: Int
    var z: Int
    var height: Int
    tryWithUsage(
        player,
        args,
        "Invalid format! Example of proper command <col=42C66C>::tele 3200 3200</col>"
    ) { values ->
        if (values.size == 1 && values[0].contains(",")) {
            val split = values[0].split(",".toRegex())
            x = split[1].toInt() shl 6 or split[3].toInt()
            z = split[2].toInt() shl 6 or split[4].toInt()
            height = split[0].toInt()
        } else {
            x = values[0].toInt()
            z = values[1].toInt()
            height = if (values.size > 2) values[2].toInt() else 0
        }
        player.moveTo(x, z, height)
    }
}

on_command("teler", Privilege.ADMIN_POWER) {
    val args = player.getCommandArgs()
    tryWithUsage(player, args, "Invalid format! Example of proper command <col=42C66C>::teler 12850</col>") { values ->
        val region = values[0].toInt()
        val tile = Tile.fromRegion(region)
        player.moveTo(tile)
    }
}


on_command("home", Privilege.MOD_POWER) {
    val home = world.gameContext.home
    player.moveTo(home)
}


on_command("staffzone", Privilege.MOD_POWER) {
    player.teleport(Tile(2935, 4695, 0), TeleportType.CABBAGE)
    player.message("You teleported to the Staff Zone")
}

on_command("yanille", Privilege.MOD_POWER) {
    player.teleport(Tile(2606, 3104, 0), TeleportType.SCROLL)
    player.message("You teleported to Yanille")
}
on_command("seers", Privilege.MOD_POWER) {
    player.teleport(Tile(2724, 3485, 0), TeleportType.SCROLL)
    player.message("You teleported to Seers Village")
}
on_command("cath", Privilege.MOD_POWER) {
    player.teleport(Tile(2804, 3433, 0), TeleportType.SCROLL)
    player.message("You teleported to Catherby")
}
on_command("tav", Privilege.MOD_POWER) {
    player.teleport(Tile(2895, 3456, 0), TeleportType.SCROLL)
    player.message("You teleported to Taverley")
}
on_command("legends", Privilege.MOD_POWER) {
    player.teleport(Tile(2729, 3376, 0), TeleportType.SCROLL)
    player.message("You teleported to the Legends Guild")
}
on_command("colony", Privilege.MOD_POWER) {
    player.teleport(Tile(2336, 3690, 0), TeleportType.SCROLL)
    player.message("You teleported to the Fishing Colony")
}
on_command("edge", Privilege.MOD_POWER) {
    player.teleport(Tile(3087, 3491, 0), TeleportType.SCROLL)
    player.message("You teleported to Edgeville")
}
on_command("westardy", Privilege.MOD_POWER) {
    player.teleport(Tile(2526, 3307, 0), TeleportType.SCROLL)
    player.message("You teleported to West Ardougne")
}
on_command("ardy", Privilege.MOD_POWER) {
    player.teleport(Tile(2661, 3305, 0), TeleportType.SCROLL)
    player.message("You teleported to Ardounge")
}
on_command("dragontooth", Privilege.MOD_POWER) {
    player.teleport(Tile(3795, 3558, 0), TeleportType.SCROLL)
    player.message("You teleported to Dragon Tooth island")
}
on_command("treestronghold", Privilege.MOD_POWER) {
    player.teleport(Tile(2458, 3412, 0), TeleportType.SCROLL)
    player.message("You teleported to the Gnome Tree Stronghold")
}
on_command("gnomevillage", Privilege.MOD_POWER) {
    player.teleport(Tile(2534, 3167, 0), TeleportType.SCROLL)
    player.message("You teleported to Tree Gnome Village")
}
on_command("shipyard", Privilege.MOD_POWER) {
    player.teleport(Tile(2954, 3030, 0), TeleportType.SCROLL)
    player.message("You teleported to Ship Yard")
}
on_command("battlefield", Privilege.MOD_POWER) {
    player.teleport(Tile(2530, 3231, 0), TeleportType.SCROLL)
    player.message("You teleported to the Gnome Battle Field")
}

fun tryWithUsage(player: Player, args: Array<String>, failMessage: String, tryUnit: Function1<Array<String>, Unit>) {
    try {
        tryUnit.invoke(args)
    } catch (e: Exception) {
        player.message(failMessage, type = ChatMessageType.CONSOLE)
        e.printStackTrace()
    }
}