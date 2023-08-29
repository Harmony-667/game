package gg.rsmod.plugins.content.npcs.definitions.other

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(Npcs.SHADOW_WARRIOR)

val table = DropTableFactory
val shadow_Warrior = table.build {
    guaranteed {
        obj(Items.BONES, quantity = 1)
    }
    main {
        total(512)
        obj(Items.MYSTIC_HAT, quantity = 1, slots = 2)
        obj(Items.MYSTIC_ROBE_TOP, quantity = 1, slots = 2)
        obj(Items.MYSTIC_ROBE_BOTTOM, quantity = 1, slots = 2)
        obj(Items.MYSTIC_GLOVES, quantity = 1, slots = 2)
        obj(Items.MYSTIC_BOOTS, quantity = 1, slots = 2)
        nothing(slots = 2)
    }

}

table.register(shadow_Warrior, *ids)

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
            attackSpeed = 4
            respawnDelay = 30
            attackStyle = StyleType.SLASH
        }
        stats {
            hitpoints = 670
            attack = 36
            strength = 33
            defence = 36
        }
        bonuses {
            attackBonus = 20
            attackSlash = 20
            strengthBonus = 26
            defenceStab = 43
            defenceSlash = 31
            defenceCrush = 19
            defenceMagic = 15
            defenceRanged = 38
        }
        anims {
            attack = 391
            block = 399
            death = 843
        }
        aggro {
            radius = 5
        }
    }
}