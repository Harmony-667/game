package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Herbs.minorHerbTable

val ids = intArrayOf(Npcs.MAN, Npcs.MAN_2, Npcs.MAN_3, Npcs.MAN_24, Npcs.WOMAN_BRUNETTE_LONGSKIRT_4, Npcs.WOMAN_BRUNETTE_PONYTAIL_5, Npcs.WOMAN_WOMAN_BLOND_PONYTAIL_6, Npcs.MAN_16, Npcs.MAN_3223, Npcs.WOMAN_BLOND_LONGSKIRT_3226, Npcs.WOMAN_25)

val table = DropTableFactory
val citizen = table.build {
    guaranteed {
        obj(Items.BONES)
    }

    main {
        total(1024)
        obj(Items.BRONZE_MED_HELM, slots = 16)
        obj(Items.BRONZE_DAGGER, slots = 8)
        obj(Items.BRONZE_ARROW, quantity = world.random(2..56), slots = 138)
        obj(Items.EARTH_RUNE, quantity = 4, slots = 16)
        obj(Items.FIRE_RUNE, quantity = 6, slots = 16)
        obj(Items.MIND_RUNE, quantity = 9, slots = 16)
        obj(Items.CHAOS_RUNE, quantity = 2, slots = 8)
        table(minorHerbTable, slots = 152)
        obj(Items.COINS_995, quantity = 3, slots = 277)
        obj(Items.COINS_995, quantity = 10, slots = 138)
        obj(Items.COINS_995, quantity = 5, slots = 73)
        obj(Items.COINS_995, quantity = 15, slots = 32)
        obj(Items.COINS_995, quantity = 25, slots = 8)
        nothing(slots = 16)
        obj(Items.CABBAGE, quantity = 1, slots = 8)
    }

}

table.register(citizen, *ids)

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
            respawnDelay = 25
        }
        stats {
            hitpoints = 70
            attack = 1
            strength = 1
            defence = 1
            magic = 1
            ranged = 1
        }
        bonuses {
            attackStab = 0
            attackCrush = 0
            defenceStab = -21
            defenceSlash = -21
            defenceCrush = -21
            defenceMagic = -21
            defenceRanged = -21
        }
        anims {
            attack = 422
            death = 836
            block = 404
        }
    }
}