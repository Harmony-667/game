package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Herbs.minorHerbTable

val ids = intArrayOf(Npcs.WATCHMAN)

val table = DropTableFactory
val watchman = table.build {
    guaranteed {
        obj(Items.BONES)
    }

    main {
        total(512)
        obj(Items.COINS_995, quantity = 5, slots = 18)
        obj(Items.COINS_995, quantity = 9, slots = 18)
        obj(Items.COINS_995, quantity = 2, slots = 18)
        obj(Items.COINS_995, quantity = 15, slots = 18)
        obj(Items.COINS_995, quantity = 11, slots = 18)
        obj(Items.STEEL_MED_HELM, quantity = 1, slots = 16)
        obj(Items.STEEL_BOOTS, quantity = 1, slots = 4)
        obj(Items.STEEL_CHAINBODY, quantity = 1, slots = 4)
        obj(Items.STEEL_PLATELEGS, quantity = 1, slots = 2)
        obj(Items.STEEL_PLATEBODY, quantity = 1, slots = 2)
        obj(Items.IRON_KEY, quantity = 1, slots = 2)
        nothing(slots = 18)
        table(minorHerbTable, slots = 8)
    }

}

table.register(watchman, *ids)

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
            hitpoints = 220
            attack = 31
            strength = 31
            defence = 31
            magic = 1
            ranged = 1
        }
        bonuses {
            attackStab = 0
            attackCrush = 0
            attackMagic = 0
            attackRanged = 0

            defenceStab = 24
            defenceSlash = 14
            defenceCrush = 19
            defenceMagic = -4
            defenceRanged = 16
        }
        anims {
            attack = 422
            death = 836
            block = 404
        }
    }
}