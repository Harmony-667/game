package gg.rsmod.plugins.content.npcs.definitions.trolls

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Herbs

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */

val ids = intArrayOf(Npcs.THROWER_TROLL_1130)

val table = DropTableFactory
val throwertroll = table.build {
    guaranteed {
        obj(Items.BIG_BONES)
    }
    main {
        total(512)
        obj(Items.YEW_SHORTBOW, quantity = 1, slots = 4)
        obj(Items.MAPLE_SHORTBOW, quantity = 1, slots = 4)
        obj(Items.YEW_LONGBOW, quantity = 1, slots = 4)
        obj(Items.MAPLE_LONGBOW, quantity = 1, slots = 4)
        obj(Items.COAL_NOTED, quantityRange = 5..15, slots = 4)
        obj(Items.LOBSTER, quantityRange = 1..5, slots = 4)
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

table.register(throwertroll, *ids)

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
            attackSpeed = 6
            respawnDelay = 30
            attackStyle = StyleType.CRUSH
        }
        stats {
            hitpoints = 195
            attack = 30
            strength = 80
            defence = 30
            ranged = 60
        }
        bonuses {
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