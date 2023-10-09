package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Herbs.minorHerbTable
import gg.rsmod.plugins.content.drops.global.Seeds.allotmentSeedTable

val ids = intArrayOf(Npcs.GNOME_GUARD, Npcs.GNOME_GUARD_164)

val table = DropTableFactory
val gnomeguard = table.build {
    guaranteed {
        obj(Items.BONES)
    }
}

table.register(gnomeguard, *ids)

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
            respawnDelay = 30
            attackStyle = StyleType.SLASH
        }
        stats {
            hitpoints = 310
            attack = 17
            strength = 17
            defence = 117
            magic = 1
            ranged = 1
        }
        bonuses {
            attackStab = 8
            attackCrush = 8
            attackSlash = 17
            strengthBonus = 13
        }
        anims {
            attack = 192
            death = 196
            block = if (it == Npcs.GNOME_GUARD) 193 else 194
        }
    }
}