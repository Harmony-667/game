package gg.rsmod.plugins.content.npcs.definitions.animals

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(Npcs.GIANT_CRYPT_SPIDER)

val table = DropTableFactory
val spider = table.build {
    main {
        total(48)
        obj(Items.ANTIPOISON_4, slots = 6)
        obj(Items.SNAPE_GRASS_NOTED, quantityRange = 1..2, slots = 18)
        obj(Items.WHITE_BERRIES_NOTED, quantityRange = 1..2, slots = 6)
        nothing(48)
    }

    table("Secondary") {
        total(256)
        obj(Items.EYE_OF_NEWT, slots = 48)
        obj(Items.LIMPWURT_ROOT, slots = 12)
        obj(Items.UNICORN_HORN_DUST, slots = 12)
        obj(Items.RED_SPIDERS_EGGS, slots = 4)
        obj(Items.WINE_OF_ZAMORAK, slots = 1)
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
            poisonDamage = 12
        }
        stats {
            hitpoints = 800
            attack = 65
            strength = 67
            defence = 65
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
            attackBonus = +25
            strengthBonus= +50
        }
        anims {
            attack = 5327
            block = 5328
            death = 5329
        }
        aggro {
            radius = 5
        }
    }
}