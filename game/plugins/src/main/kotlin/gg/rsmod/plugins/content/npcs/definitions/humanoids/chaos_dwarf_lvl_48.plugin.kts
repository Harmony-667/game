package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(Npcs.CHAOS_DWARF)

val table = DropTableFactory
val chaosDwarf = table.build {
    guaranteed {
        obj(Items.BONES)
    }
    main {
        total(total = 128)

        nothing(slots = 5)

    }


}

table.register(chaosDwarf, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.DWARF_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 50
        }
        stats {
            hitpoints = 610
            attack = 38
            strength = 42
            defence = 28
            magic = 1
            ranged = 1
        }
        bonuses {
            attackStab = 13
            attackCrush = 9
            defenceStab = 40
            defenceSlash = 34
            defenceCrush = 25
            defenceMagic = 10
            defenceRanged = 35
        }
        anims {
            attack = 99
            death = 102
            block = 100
        }
        aggro {
            radius = 4
        }

    }
}