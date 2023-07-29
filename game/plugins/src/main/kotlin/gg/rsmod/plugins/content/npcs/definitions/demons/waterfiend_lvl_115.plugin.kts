package gg.rsmod.plugins.content.npcs.definitions.demons

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Gems

val ids = intArrayOf(Npcs.WATERFIEND_9054, Npcs.WATERFIEND_9056, Npcs.WATERFIEND_9057, Npcs.WATERFIEND_9058, Npcs.WATERFIEND_9059)

val table = DropTableFactory
val waterfiend = table.build {
    guaranteed {
        obj(Items.WATER_RUNE, 1..5)
    }

    main {
        total(128)
        nothing(1)
    }
}

table.register(waterfiend, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.WATERFIEND_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 15
            attackStyle = StyleType.MAGIC_MELEE
        }
        stats {
            hitpoints = 1280
            attack = 0
            strength = 0
            defence = 128
            magic = 105
            ranged = 105
        }
        bonuses {
            defenceStab = 100
            defenceSlash = 100
            defenceCrush = 10
            defenceMagic = 100
            defenceRanged = 100
        }
        anims {
            attack = 299
            block = 301
            death = 300
        }
        slayer {
            assignment = SlayerAssignment.WATERFIEND
            experience = 128.0
            level = 1
        }
    }
}