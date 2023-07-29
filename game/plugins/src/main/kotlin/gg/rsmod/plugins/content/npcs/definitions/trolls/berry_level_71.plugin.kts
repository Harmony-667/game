package gg.rsmod.plugins.content.npcs.definitions.trolls

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Herbs

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */

val ids = intArrayOf(Npcs.BERRY)

val table = DropTableFactory
val berry = table.build {
    guaranteed {
        obj(Items.BIG_BONES)
    }
    main {
        total(1024)
        obj(Items.RUNE_WARHAMMER, quantity = 1, slots = 4)
        obj(Items.RUNE_BATTLEAXE, quantity = 1, slots = 4)
        obj(Items.GRANITE_MAUL, quantity = 1, slots = 1)
        obj(Items.KEY_1543, quantity = 1, slots = 1)
        obj(Items.COAL_NOTED, quantityRange = 5..20, slots = 4)
        obj(Items.LOBSTER, quantityRange = 1..5, slots = 4)
        nothing (slots = 512)

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

table.register(berry, *ids)

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
            respawnDelay = 150
            attackStyle = StyleType.CRUSH
        }
        stats {
            hitpoints = 900
            attack = 40
            strength = 90
            defence = 25
        }
        bonuses {
            attackStab = 20
            attackBonus = 20
            attackCrush = 50
            attackSlash = 20
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
    }
}