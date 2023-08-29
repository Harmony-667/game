package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Gems
import gg.rsmod.plugins.content.drops.global.Herbs

val ids = intArrayOf(Npcs.CHAOS_DRUID_2547, Npcs.CHAOS_DRUID, Npcs.CHAOS_DRUID_7105)
val table = DropTableFactory
val chaosDruid = table.build {

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

table.register(chaosDruid, *ids)

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
            hitpoints = 200
            attack = 8
            strength = 8
            defence = 12
            magic = 10
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