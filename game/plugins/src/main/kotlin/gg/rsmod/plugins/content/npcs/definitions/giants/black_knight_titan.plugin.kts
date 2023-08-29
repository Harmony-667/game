package gg.rsmod.plugins.content.npcs.definitions.giants

import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Herbs

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */

val ids = intArrayOf(Npcs.BLACK_KNIGHT_TITAN)

val table = DropTableFactory
val titan = table.build {
    guaranteed {
        obj(Items.JOGRE_BONES)
    }
    main {
        total(1024)
        obj(Items.COINS, quantityRange = 1500..5000, slots = 32)
        obj(Items.SNAPE_GRASS_NOTED, quantity = 5, slots = 8)
        obj(Items.RED_SPIDERS_EGGS_NOTED, quantity = 5, slots = 8)
        obj(Items.OBSIDIAN_CAPE, quantity = 1, slots = 4)
        obj(Items.DRAGON_LONGSWORD, quantity = 1, slots = 4)
        obj(Items.KEY_1545, quantity = 1, slots = 4)
        obj(Items.SINISTER_KEY, quantity = 1, slots = 4)
        obj(Items.DRAGON_BATTLEAXE, quantity = 1, slots = 4)
        obj(Items.DRAGON_2H_SWORD, quantity = 1, slots = 2)
        obj(Items.UNCUT_ONYX, quantity = 1, slots = 2)

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

table.register(titan, *ids)

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
            attackSpeed = 7
            respawnDelay = 300
            attackStyle = StyleType.CRUSH
        }
        stats {
            hitpoints = 1420
            attack = 91
            strength = 100
            defence = 91
        }
        bonuses {
            attackStab = 27
            attackCrush = 27
            strengthBonus = 22
            defenceStab = 18
            defenceSlash = 27
            defenceCrush = 18
            defenceMagic = 1000
            defenceRanged = 1000
        }
        anims {
            attack = 128
            death = 131
            block = 129
        }
        aggro {
            radius = 8
        }
    }
}