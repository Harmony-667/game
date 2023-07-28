package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Gems
import gg.rsmod.plugins.content.drops.global.Herbs

val ids = intArrayOf(Npcs.CHAOS_DRUID_WARRIOR)
val table = DropTableFactory
val chaosDruid = table.build {

    guaranteed {
        obj(Items.BONES)
    }
    main {
        total(total = 128)

        table(Herbs.minorHerbTable, slots = 46)

        nothing(slots = 33)
        obj(Items.VIAL_OF_WATER, quantity = 1, slots = 10)

    }
}

table.register(chaosDruid, *ids)

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
            respawnDelay = 50
            attackStyle = StyleType.CRUSH
        }
        stats {
            hitpoints = 400
            attack = 32
            strength = 34
            defence = 25
        }
        bonuses {
            attackCrush = 9
            strengthBonus = 5
            defenceStab = 13
            defenceSlash = 17
            defenceCrush = 14
            defenceMagic = -4
            defenceRanged = 14
        }
        anims {
            attack = 422
            block = 404
            death = 836
        }
        aggro {
            radius = 4
        }
    }
}