package gg.rsmod.plugins.content.npcs.definitions.other

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory


val ids = intArrayOf(Npcs.ICE_GIANT, Npcs.ICE_GIANT_3072, Npcs.ICE_GIANT_4685, Npcs.ICE_GIANT_4686, Npcs.ICE_GIANT_4687)

val table = DropTableFactory
val iceGiant = table.build {
    guaranteed {
        obj(Items.BIG_BONES)
    }

    main {
        total(512)
        obj(Items.COINS_995, quantityRange = 100..1000, slots = 16)
        obj(Items.ADAMANT_PICKAXE, quantity = 1, slots = 1)
        obj(Items.ADAMANT_BOOTS, quantity = 1, slots = 4)
        obj(Items.ADAMANT_GAUNTLETS, quantity = 1, slots = 4)
        obj(Items.ADAMANT_PICKAXE, quantity = 1, slots = 1)
        obj(Items.ADAMANT_HATCHET, quantity = 1, slots = 1)
        obj(Items.ADAMANT_ARROW, quantityRange = 1..12, slots = 2)
        obj(Items.NATURE_RUNE, quantityRange = 1..5, slots = 2)
        obj(Items.DEATH_RUNE, quantityRange = 1..5, slots = 2)
        obj(Items.BLOOD_RUNE, quantityRange = 1..5, slots = 2)
        obj(Items.MITHRIL_ORE_NOTED, quantityRange = 1..10, slots = 4)
    }


}

table.register(iceGiant, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.GIANT_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 5
            respawnDelay = 30
            attackStyle = StyleType.SLASH
        }
        stats {
            hitpoints = 700
            attack = 40
            strength = 40
            defence = 40
        }
        bonuses {
            attackBonus = 29
            strengthBonus = 31
            defenceSlash = 3
            defenceCrush = 2
        }
        anims {

            attack = 4672
            block = 4671
            death = 4673
        }
        aggro {
            radius = 5
        }
        slayer {
            assignment = SlayerAssignment.ICE_GIANT
            level = 1
            experience = 70.0
        }
    }
}