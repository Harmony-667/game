package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Herbs.minorHerbTable

val ids = intArrayOf(Npcs.ICE_QUEEN)

val table = DropTableFactory
val icequeen = table.build {
    guaranteed {
        obj(Items.BONES)
    }

    main {
        total(512)
        obj(Items.COINS_995, quantityRange = 10..500, slots = 4)
        obj(Items.ADAMANT_PICKAXE, quantity = 1, slots = 1)
        obj(Items.ICE_GLOVES, quantity = 1, slots = 1)
        obj(Items.NEW_CRYSTAL_BOW, quantity = 1, slots = 1)
        obj(Items.NEW_CRYSTAL_SHIELD, quantity = 1, slots = 1)
        obj(Items.NATURE_RUNE, quantity = 1, slots = 2)
        obj(Items.DEATH_RUNE, quantity = 1, slots = 2)
        obj(Items.BLOOD_RUNE, quantity = 1, slots = 2)
        nothing(slots = 256)
    }

}

table.register(icequeen, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.ICE_WARRIOR_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 3
            respawnDelay = 300
        }
        stats {
            hitpoints = 1040
            attack = 95
            strength = 94
            defence = 95
            magic = 1
            ranged = 1
        }
        bonuses {
            defenceStab = 30
            defenceSlash = 40
            defenceCrush = 20
            defenceMagic = 10
            defenceRanged = 30
        }
        anims {
            attack = 422
            death = 836
            block = 404
        }
    }
}