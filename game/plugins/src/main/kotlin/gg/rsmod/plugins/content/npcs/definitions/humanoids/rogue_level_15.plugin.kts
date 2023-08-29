package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(Npcs.ROGUE)

val table = DropTableFactory
val rogue = table.build {
    guaranteed {
        obj(Items.BONES)
    }
    main {
        total(128)
        obj(Items.COINS_995, quantity = 8, slots = 23)
        obj(Items.COINS_995, quantity = 15, slots = 12)
        obj(Items.COINS_995, quantity = 30, slots = 2)
        obj(Items.COINS_995, quantity = 20, slots = 1)
        nothing(slots = 30)
    }
}

table.register(rogue, *ids)

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
            respawnDelay = 50
        }
        stats {
            hitpoints = 170
            attack = 13
            strength = 13
            defence = 13
        }
        bonuses {
            attackStab = 5
            defenceStab = 6
            defenceSlash = 9
            defenceCrush = 11
        }
        anims {
            attack = 386
            block = 378
            death = 836
        }
    }
}
