package gg.rsmod.plugins.content.npcs.definitions.demons

import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Gems
import gg.rsmod.plugins.content.drops.global.Herbs
import gg.rsmod.plugins.content.drops.global.Rare

val ids = intArrayOf(Npcs.NEZIKCHENED)
val table = DropTableFactory
val Nezikchend = table.build {

    guaranteed {
        obj(Items.INFERNAL_ASHES)
    }

    main {
        total(1024)
        obj(Items.COINS, quantityRange = 2500..5000, slots = 32)
        obj(Items.SNAPE_GRASS_NOTED, quantityRange = 1..10, slots = 8)
        obj(Items.RED_SPIDERS_EGGS_NOTED, quantityRange = 2..10, slots = 8)
        obj(Items.WINE_OF_ZAMORAK_NOTED, quantityRange = 1..5, slots = 4)
        obj(Items.INFINITY_BOOTS, quantity = 1, slots = 4)
        obj(Items.INFINITY_GLOVES, quantity = 1, slots = 4)
        obj(Items.INFINITY_HAT, quantity = 1, slots = 2)
        obj(Items.INFINITY_BOOTS, quantity = 1, slots = 2)
        obj(Items.INFINITY_GLOVES, quantity = 1, slots = 2)
        obj(Items.INFINITY_HAT, quantity = 1, slots = 2)
        obj(Items.INFINITY_TOP, quantity = 1, slots = 2)
        obj(Items.INFINITY_BOTTOMS, quantity = 1, slots = 2)
        obj(Items.MASTER_WAND, quantity = 1, slots = 2)
        table(Herbs.minorHerbTable, slots = 4)
        table(Gems.gemTable, slots = 4)
        obj(Items.AIR_RUNE, quantityRange = 10..50, slots = 8)
        obj(Items.CHAOS_RUNE, quantityRange = 1..10, slots = 8)
        obj(Items.BLOOD_RUNE, quantityRange = 1..3, slots = 8)
        obj(Items.FIRE_RUNE, quantityRange = 10..50, slots = 8)
        obj(Items.LAW_RUNE, quantityRange = 1..3, slots = 8)

    }
}

table.register(Nezikchend, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.BLACK_DEMON_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 5
            respawnDelay = 300
            attackStyle = StyleType.SLASH
        }
        stats {
            hitpoints = 3000
            attack = 165
            strength = 168
            defence = 167
            magic = 160
            ranged = 160
        }
        anims {
            attack = 64
            block = 4676
            death = 4624
        }
        aggro {
            radius = 4
        }
    }
}