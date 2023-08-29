package gg.rsmod.plugins.content.npcs.definitions.demons

import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory


val ids = intArrayOf(Npcs.BLACK_DEMON, Npcs.BLACK_DEMON_4702, Npcs.BLACK_DEMON_4703, Npcs.BLACK_DEMON_4705)
val table = DropTableFactory
val blackDemon = table.build {

    guaranteed {
        obj(Items.INFERNAL_ASHES)
    }

    main {
        total(total = 128)

        nothing(1)

    }
}

table.register(blackDemon, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.BLACK_DEMON_DEATH)
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
            hitpoints = 1570
            attack = 145
            strength = 148
            defence = 152
        }
        bonuses {
            defenceMagic = -10
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