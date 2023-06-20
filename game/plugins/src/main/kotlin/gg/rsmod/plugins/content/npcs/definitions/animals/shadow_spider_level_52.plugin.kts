package gg.rsmod.plugins.content.npcs.definitions.critters

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(Npcs.SHADOW_SPIDER)

val table = DropTableFactory
val spider = table.build {
    main {
        total(48)
        obj(Items.RUNE_ESSENCE_NOTED, quantityRange = 4..6, slots = 18)
        obj(Items.PURE_ESSENCE_NOTED, quantityRange = 4..6, slots = 6)
        nothing(24)
    }

    table("Secondary") {
        total(256)
        obj(Items.BLACK_DAGGER, slots = 48)
        obj(Items.BLACK_SCIMITAR, slots = 12)
        obj(Items.BLACK_BATTLEAXE, slots = 12)
        obj(Items.BLACK_2H_SWORD, slots = 4)
        obj(Items.BLACK_KITESHIELD, slots = 1)
        nothing(256)
    }

 /*   table("Charms") {
        total(1000)
        obj(Items.GOLD_CHARM, quantity = 1, slots = 90)
        obj(Items.GREEN_CHARM, quantity = 1, slots = 11)
        obj(Items.CRIMSON_CHARM, quantity = 1, slots = 30)
        obj(Items.BLUE_CHARM, quantity = 1, slots = 9)
        nothing(slots = 860)
    }*/
}

table.register(spider, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.BIG_SPIDER_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 30
        }
        stats {
            hitpoints = 55
            attack = 44
            strength = 42
            defence = 44
            magic = 1
            ranged = 1
        }
        bonuses {
            attackStab = 0
            attackCrush = 0
            defenceStab = 0
            defenceSlash = 0
            defenceCrush = 0
            defenceMagic = 0
            defenceRanged = 0
        }
        anims {
            attack = 5327
            block = 5328
            death = 5329
        }
        aggro {
            radius = 5
        }
 /*       slayer {
            assignment = SlayerAssignment.SPIDER
            level = 1
            experience = 5.0
        }*/
    }
}