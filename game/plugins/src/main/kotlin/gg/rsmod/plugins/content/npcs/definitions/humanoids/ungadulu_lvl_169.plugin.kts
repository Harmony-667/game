package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Gems
import gg.rsmod.plugins.content.drops.global.Herbs

val ids = intArrayOf(Npcs.UNGADULU_930)
val table = DropTableFactory
val ungadulu = table.build {

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

table.register(ungadulu, *ids)

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
            respawnDelay = 300
        }
        stats {
            hitpoints = 200
            attack = 147
            strength = 147
            defence = 147
            magic = 147
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