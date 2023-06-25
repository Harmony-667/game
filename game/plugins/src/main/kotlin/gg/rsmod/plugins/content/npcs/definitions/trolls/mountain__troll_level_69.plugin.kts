package gg.rsmod.plugins.content.npcs.definitions.trolls

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Herbs

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */

val ids = intArrayOf(Npcs.MOUNTAIN_TROLL, Npcs.MOUNTAIN_TROLL_1107, Npcs.MOUNTAIN_TROLL_1108, Npcs.MOUNTAIN_TROLL_1109, Npcs.MOUNTAIN_TROLL_1110, Npcs.MOUNTAIN_TROLL_1111, Npcs.MOUNTAIN_TROLL_1112)

val table = DropTableFactory
val mountaintroll = table.build {
    guaranteed {
        obj(Items.BIG_BONES)
    }
    main {
        total(512)
        obj(Items.ADAMANT_WARHAMMER, quantity = 1, slots = 4)
        obj(Items.MITHRIL_WARHAMMER, quantity = 1, slots = 4)
        obj(Items.BLACK_WARHAMMER, quantity = 1, slots = 4)
        obj(Items.IRON_KEY, quantity = 1, slots = 2)
        obj(Items.COAL_NOTED, quantity = 3, slots = 4)
        obj(Items.LOBSTER, quantity = 2, slots = 4)
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

table.register(mountaintroll, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.TROLL_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 6
            respawnDelay = 30
            attackStyle = StyleType.CRUSH
        }
        stats {
            hitpoints = 190
            attack = 40
            strength = 75
            defence = 40
        }
        bonuses {
            attackStab = 29
            attackCrush = 31
            strengthBonus = 20
            defenceCrush = 10
            defenceMagic = 200
            defenceRanged = 200
        }
        anims {
            attack = 1158
            death = 1934
            block = 4995
        }
        /*slayer {
            assignment = SlayerAssignment.FIRE_GIANT
            level = 1
            experience = 60.0
        }*/
    }
}