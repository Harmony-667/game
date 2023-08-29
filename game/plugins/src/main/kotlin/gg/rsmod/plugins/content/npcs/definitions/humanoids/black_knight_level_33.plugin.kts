package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.plugins.content.drops.DropTableFactory


val ids = intArrayOf(Npcs.BLACK_KNIGHT, Npcs.BLACK_KNIGHT_6189)

val table = DropTableFactory
val blackknight = table.build {
    guaranteed {
        obj(Items.BONES)
    }

    main {
        total(128)

        nothing(2)
    }

}

table.register(blackknight, *ids)

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
            attackSpeed = 5
            respawnDelay = 25
        }
        stats {
            hitpoints = 420
            attack = 25
            strength = 25
            defence = 25
        }
        bonuses {
            attackStab = 18
            attackCrush = 16
            defenceStab = 73
            defenceSlash = 76
            defenceCrush = 70
            defenceMagic = -11
            defenceRanged = 72
        }
        anims {
            attack = 390
            death = 836
            block = 1156
        }
    }
}
