package gg.rsmod.plugins.content.npcs.definitions.avatars

import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(Npcs.ARZINIAN_AVATAR_OF_MAGIC_1858)

val table = DropTableFactory
val avatar = table.build {
    guaranteed {
        obj(Items.BONES)
    }

    main {
        total(1024)

        obj(Items.SPLITBARK_HELM, quantity = 1, slots = 2)
        obj(Items.SPLITBARK_BODY, quantity = 1, slots = 2)
        obj(Items.SPLITBARK_LEGS, quantity = 1, slots = 2)
        obj(Items.SPLITBARK_GAUNTLETS, quantity = 1, slots = 4)
        obj(Items.SPLITBARK_BOOTS, quantity = 1, slots = 4)
        obj(Items.APPRENTICE_WAND, quantity = 1, slots = 1)
        obj(Items.KEY_1543, quantity = 1, slots = 1)
        obj(Items.RUNE_ESSENCE_NOTED, quantity = 50, slots = 8)
        obj(Items.PURE_ESSENCE_NOTED, quantity = 50, slots = 4)
        obj(Items.AIR_RUNE, quantity = 12, slots = 36)
        obj(Items.EARTH_RUNE, quantity = 6, slots = 18)
        obj(Items.DEATH_RUNE, quantity = 3, slots = 18)
        obj(Items.BLOOD_RUNE, quantity = 2, slots = 36)

        nothing(slots = 128)

    }
}

table.register(avatar, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.TROLL_CHAMPION_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 300
            spell = 101

        }

        stats {
            hitpoints = 1000
            attack = 10
            strength = 50
            defence = 75
            magic = 75
            ranged = 1
        }
        bonuses {
            attackStab = 0
            attackCrush = 0
            defenceStab = 40
            defenceSlash = 40
            defenceCrush = 40
            defenceMagic = 15
            defenceRanged = 10
        }
        anims {
            attack = 1843
            death = 1841
            block = 1842
        }
    }
}