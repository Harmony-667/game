package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Herbs.minorHerbTable

val ids = intArrayOf(Npcs.SOLDIER)

val table = DropTableFactory
val soldier = table.build {
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
        obj(Items.STEEL_LONGSWORD, quantity = 1, slots = 16)
        obj(Items.STEEL_BATTLEAXE, quantity = 1, slots = 16)
        obj(Items.STEEL_2H_SWORD, quantity = 1, slots = 16)
        obj(Items.STEEL_SWORD, quantity = 1, slots = 16)
        obj(Items.STEEL_SCIMITAR, quantity = 1, slots = 16)
        obj(Items.STEEL_WARHAMMER, quantity = 1, slots = 16)
        obj(Items.STEEL_HATCHET, quantity = 1, slots = 16)
        obj(Items.STEEL_HALBERD, quantity = 1, slots = 16)
        obj(Items.STEEL_MED_HELM, quantity = 1, slots = 16)
        obj(Items.STEEL_ARROW, quantity = 5, slots = 8)
        obj(Items.STEEL_ARROW, quantity = 12, slots = 8)
        obj(Items.STEEL_KITESHIELD, quantity = 1, slots = 4)
        obj(Items.STEEL_FULL_HELM, quantity = 1, slots = 4)
        obj(Items.STEEL_BOOTS, quantity = 1, slots = 4)
        obj(Items.STEEL_CHAINBODY, quantity = 1, slots = 4)
        obj(Items.STEEL_PLATELEGS, quantity = 1, slots = 2)
        obj(Items.STEEL_PLATEBODY, quantity = 1, slots = 2)
        nothing(slots = 18)
        table(minorHerbTable, slots = 8)
    }

}

table.register(soldier, *ids)

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
            hitpoints = 650
            attack = 25
            strength = 25
            defence = 25
            magic = 1
            ranged = 1
        }
        bonuses {
            attackStab = 9
            attackCrush = 13
            attackMagic = 0
            attackRanged = 0

            defenceStab = 39
            defenceSlash = 31
            defenceCrush = 34
            defenceMagic = 0
            defenceRanged = 0
        }
        anims {
            attack = 401
            death = 836
            block = 424
        }
    }
}