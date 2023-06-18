package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(Npcs.ZAMORAK_WIZARD)

val table = DropTableFactory
val wizard = table.build {
    guaranteed {
        obj(Items.BONES)
    }

    main {
        total(1024)

        obj(Items.STAFF, slots = 64)
        obj(Items.WINE_OF_ZAMORAK, quantity = 1, slots = 8)
        obj(Items.ZAMORAK_ROBE, quantity = 1, slots = 36)
        obj(Items.ZAMORAK_ROBE_1035, quantity = 1, slots = 36)

        obj(Items.CHAOS_RUNE, quantity = 2, slots = 64)
        obj(Items.NATURE_RUNE, quantity = 2, slots = 64)

        obj(Items.AIR_RUNE, quantity = 5, slots = 24)
        obj(Items.BODY_RUNE, quantity = 5, slots = 24)
        obj(Items.EARTH_RUNE, quantity = 5, slots = 24)
        obj(Items.FIRE_RUNE, quantity = 5, slots = 24)
        obj(Items.MIND_RUNE, quantity = 5, slots = 24)
        obj(Items.WATER_RUNE, quantity = 5, slots = 24)

        obj(Items.AIR_RUNE, quantity = 12, slots = 16)
        obj(Items.BODY_RUNE, quantity = 12, slots = 16)
        obj(Items.EARTH_RUNE, quantity = 12, slots = 16)
        obj(Items.FIRE_RUNE, quantity = 12, slots = 16)
        obj(Items.MIND_RUNE, quantity = 12, slots = 16)
        obj(Items.WATER_RUNE, quantity = 12, slots = 16)

        nothing(slots = 128)

    }
}

table.register(wizard, *ids)

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
            respawnDelay = 30
            spell = 503
        }
        stats {
            hitpoints = 760
            attack = 24
            strength = 24
            defence = 24
            magic = 80
            ranged = 1
        }
        bonuses {
            attackStab = 0
            attackCrush = 0
            defenceStab = 0
            defenceSlash = 0
            defenceCrush = 0
            defenceMagic = 3
            defenceRanged = 0
        }
        anims {
            attack = 811
            death = 836
            block = 404
        }
    }
}