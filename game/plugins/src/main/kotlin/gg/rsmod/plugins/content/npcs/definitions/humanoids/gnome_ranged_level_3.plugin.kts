package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Herbs.minorHerbTable
import gg.rsmod.plugins.content.drops.global.Seeds.allotmentSeedTable

val ids = intArrayOf(Npcs.GNOME_2249, Npcs.GNOME_2251)

val table = DropTableFactory
val gnomeranger = table.build {
    guaranteed {
        obj(Items.BONES)
    }
}

table.register(gnomeranger, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.GNOME_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 50
            attackStyle = StyleType.RANGED
        }
        stats {
            hitpoints = 300
            attack = 1
            strength = 1
            defence = 1
            magic = 1
            ranged = 5
        }
        bonuses {
            attackStab = -42
            attackCrush = -42
            defenceStab = -42
            defenceSlash = -42
            defenceCrush = -42
            defenceMagic = -42
            defenceRanged = 0
            rangedStrengthBonus = 2
        }
        anims {
            attack = 190
            death = 196
            block = 193
        }
    }
}