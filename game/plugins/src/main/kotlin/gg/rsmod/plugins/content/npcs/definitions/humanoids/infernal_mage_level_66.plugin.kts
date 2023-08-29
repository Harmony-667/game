package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(Npcs.INFERNAL_MAGE, Npcs.INFERNAL_MAGE_1644, Npcs.INFERNAL_MAGE_1645, Npcs.INFERNAL_MAGE_1646, Npcs.INFERNAL_MAGE_1647)

val table = DropTableFactory

val infernalMage = table.build {
    guaranteed {
        obj(Items.BONES)
    }
    main {
        total(512)
        obj(Items.MYSTIC_HAT_4099, quantity = 1, slots = 2)
        obj(Items.MYSTIC_ROBE_TOP_4101, quantity = 1, slots = 2)
        obj(Items.MYSTIC_ROBE_BOTTOM_4103, quantity = 1, slots = 2)
        obj(Items.MYSTIC_GLOVES_4105, quantity = 1, slots = 2)
        obj(Items.MYSTIC_BOOTS_4107, quantity = 1, slots = 2)
        obj(Items.EARTH_RUNE, quantity = 1, slots = 16)
        obj(Items.FIRE_RUNE, quantity = 1, slots = 16)
        obj(Items.AIR_RUNE, quantity = 1, slots = 16)
        obj(Items.WATER_RUNE, quantity = 1, slots = 16)
        obj(Items.MIND_RUNE, quantity = 1, slots = 4)
        obj(Items.BODY_RUNE, quantity = 1, slots = 4)
        obj(Items.DEATH_RUNE, quantity = 1, slots = 4)
        obj(Items.CHAOS_RUNE, quantity = 1, slots = 4)
        nothing(slots = 18)

    }
}

table.register(infernalMage, *ids)

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
            attackSpeed = 6
            respawnDelay = 15
            spell = 995
        }
        stats {
            hitpoints = 600
            attack = 1
            strength = 1
            defence = 60
            magic = 75
            ranged = 1
        }
        bonuses {
            attackStab = 0
            attackCrush = 0
            defenceStab = 0
            defenceSlash = 0
            defenceCrush = 0
            defenceMagic = 40
            defenceRanged = 0
        }
        anims {
            attack = 422
            death = 836
            block = 404
        }
    }
}