package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Gems
import gg.rsmod.plugins.content.drops.global.Herbs

val ids = intArrayOf(Npcs.DRUID, Npcs.DRUID_6973, Npcs.DRUID_6974, Npcs.DRUID_6975, Npcs.DRUID_6976, Npcs.DRUID_6977, Npcs.DRUID_6978, Npcs.DRUID_6979, Npcs.DRUID_6980,
    Npcs.DRUIDESS, Npcs.DRUIDESS_6982, Npcs.DRUIDESS_6983, Npcs.DRUIDESS_6984, Npcs.DRUIDESS_6985, Npcs.DRUIDESS_6986, Npcs.DRUIDESS_6987)
val table = DropTableFactory
val Druid = table.build {

    guaranteed {
        obj(Items.BONES)
    }
    main {
        total(total = 128)

        table(Herbs.minorHerbTable, slots = 46)

        nothing(slots = 33)
        obj(Items.VIAL_OF_WATER, quantity = 1, slots = 10)

    }
}

table.register(Druid, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.HUMAN_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 25
        }
        stats {
            hitpoints = 300
            attack = 28
            strength = 28
            defence = 32
            magic = 25
        }
        anims {
            attack = 422
            block = 404
            death = 836
        }
        aggro {
            radius = 4
        }
    }
}