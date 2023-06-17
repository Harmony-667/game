package gg.rsmod.plugins.content.npcs.definitions.critters

import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(Npcs.GIANT_RAT_950, Npcs.GIANT_RAT_4922, Npcs.GIANT_RAT_4923, Npcs.GIANT_RAT_4924, Npcs.GIANT_RAT_4942, Npcs.GIANT_RAT_4943, Npcs.GIANT_RAT_12349, Npcs.GIANT_RAT_12350, Npcs.GIANT_RAT_12351)

val table = DropTableFactory
val giantrats = table.build {
    guaranteed {
        obj(Items.BONES)
        obj(Items.RAW_RAT_MEAT)
    }
   /* main {
        total(128)
        obj(Items.RAW_RAT_MEAT, quantity = 1, slots = 1)
        nothing(slots = 1000)
    }*/
}

table.register(giantrats, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.RAT_DEATH)
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
            hitpoints = 5
            attack = 2
            strength = 3
            defence = 2
            magic = 1
            ranged = 1
        }
        bonuses {
            attackStab = 0
            attackCrush = 0
            defenceStab = 0
            defenceSlash = 0
            defenceCrush = 0
            defenceMagic = 0
            defenceRanged = 0
        }
        anims {
            attack = 14859
            death = 14860
            block = 14861
        }
    }
}