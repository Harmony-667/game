package gg.rsmod.plugins.content.npcs.definitions.demons

import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory


val ids = intArrayOf(Npcs.GREATER_DEMON)
val table = DropTableFactory
val GreaterDemon = table.build {

    guaranteed {
        obj(Items.INFERNAL_ASHES)
    }

    main {
        total(total = 128)

        nothing(1)

    }
}

table.register(GreaterDemon, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.DEMON_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 30
            attackStyle = StyleType.SLASH
        }
        stats {
            hitpoints = 870
            attack = 76
            strength = 78
            defence = 81
        }
        anims {
            attack = 64
            block = 65
            death = 67
        }
        aggro {
            radius = 4
        }
    }
}