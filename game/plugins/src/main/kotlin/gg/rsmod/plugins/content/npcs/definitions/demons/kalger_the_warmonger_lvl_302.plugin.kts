package gg.rsmod.plugins.content.npcs.definitions.demons

import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory


val ids = intArrayOf(Npcs.KALGER_THE_WARMONGER_12800)
val table = DropTableFactory
val warmonger = table.build {

    guaranteed {
        obj(Items.INFERNAL_ASHES)
    }

    main {
        total(total = 128)

        nothing(1)

    }
}

table.register(warmonger, *ids)

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
            attackSpeed = 3
            respawnDelay = 300
            attackStyle = StyleType.SLASH
        }
        stats {
            hitpoints = 13000
            attack = 114
            strength = 150
            defence = 114
            ranged = 114
            magic = 114
        }
        bonuses {
            attackBonus = 52
            strengthBonus = 72
            defenceMagic = 100
            defenceRanged = -80
            defenceCrush = 10
            defenceSlash = 30
            defenceStab = -20
        }
        anims {
            attack = 14450
            block = 14371
            death = 14378
        }
        aggro {
            radius = 8
            searchDelay = 1
            aggroTimer = 99999

        }
    }
}