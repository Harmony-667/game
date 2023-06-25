package gg.rsmod.plugins.content.npcs.definitions.giants

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Herbs

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */

val ids = intArrayOf(Npcs.FIRE_GIANT, Npcs.FIRE_GIANT_1582, Npcs.FIRE_GIANT_1583, Npcs.FIRE_GIANT_1584, Npcs.FIRE_GIANT_1585, Npcs.FIRE_GIANT_1586)

val table = DropTableFactory
val fireGiant = table.build {
    guaranteed {
        obj(Items.BIG_BONES)
    }
    main {
        total(512)
        obj(Items.DRAGON_MED_HELM, quantity = 1, slots = 1)
        obj(Items.COOKING_GAUNTLETS, quantity = 1, slots = 2)
        obj(Items.DESERT_BOOTS, quantity = 1, slots = 2)
        obj(Items.COINS_995, quantity = 25, slots = 4)
        table(Herbs.minorHerbTable, slots = 2)
        nothing (slots = 64)

    }
/*    table("Charms") {
        total(1000)
        obj(Items.GOLD_CHARM, quantity = 1, slots = 360)
        obj(Items.GREEN_CHARM, quantity = 1, slots = 30)
        obj(Items.CRIMSON_CHARM, quantity = 1, slots = 30)
        obj(Items.BLUE_CHARM, quantity = 1, slots = 7)
        nothing(slots = 573)
    }*/
}

table.register(fireGiant, *ids)

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
            attackStyle = StyleType.CRUSH
        }
        stats {
            hitpoints = 1110
            attack = 65
            strength = 65
            defence = 65
        }
        bonuses {
            attackStab = 29
            attackCrush = 31
        }
        anims {
            attack = 4652
            death = 4653
            block = 4651
        }
        aggro {
            radius = 4
        }
        /*slayer {
            assignment = SlayerAssignment.FIRE_GIANT
            level = 1
            experience = 60.0
        }*/
    }
}