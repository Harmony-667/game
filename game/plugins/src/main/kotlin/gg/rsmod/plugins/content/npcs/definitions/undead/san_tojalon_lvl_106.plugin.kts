package gg.rsmod.plugins.content.npcs.definitions.undeads

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Herbs
/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */

val ids = intArrayOf(Npcs.SAN_TOJALON)
val table = DropTableFactory
val santojalon = table.build {

    guaranteed {
        obj(Items.BIG_BONES)
    }
    main {
        total(1024)
        obj(Items.COINS, quantityRange = 1000..2500, slots = 32)
        obj(Items.SNAPE_GRASS_NOTED, quantityRange = 1..5, slots = 8)
        obj(Items.RED_SPIDERS_EGGS_NOTED, quantityRange = 1..5, slots = 8)
        obj(Items.CAPE_OF_LEGENDS, quantity = 1, slots = 4)
        obj(Items.DRAGON_LONGSWORD, quantity = 1, slots = 4)
        obj(Items.KEY_1544, quantity = 1, slots = 4)
        obj(Items.DRAGON_SQ_SHIELD, quantity = 1, slots = 4)
        obj(Items.DRAGON_CHAINBODY, quantity = 1, slots = 4)
        obj(Items.DRAGON_PLATEBODY, quantity = 1, slots = 2)
        obj(Items.DRAGON_PLATELEGS, quantity = 1, slots = 2)
        obj(Items.DRAGON_PLATESKIRT, quantity = 1, slots = 2)
        obj(Items.DRAGON_SCIMITAR, quantity = 1, slots = 2)
        table(Herbs.minorHerbTable, slots = 4)
        nothing(slots = 512)

    }
    /*table("Charms") {
        total(1000)
        obj(Items.GOLD_CHARM, quantity = 1, slots = 30)
        obj(Items.GREEN_CHARM, quantity = 1, slots = 17)
        obj(Items.CRIMSON_CHARM, quantity = 1, slots = 14)
        obj(Items.BLUE_CHARM, quantity = 1, slots = 2)
        nothing(slots = 937)
    }*/
}

table.register(santojalon, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.MUMMY_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 300
            attackStyle = StyleType.MAGIC_MELEE
        }
        stats {
            hitpoints = 1200
            attack = 86
            strength = 84
            defence = 86
        }
        bonuses {
            attackStab = 16
            attackCrush = 16
            attackBonus = 26
            strengthBonus = 50
            defenceStab = 18
            defenceSlash = 22
            defenceCrush = 20
            defenceMagic = -1
            defenceRanged = 20
        }
        anims {
            attack = 5499
            block = 5508
            death = 5491
        }
        aggro {
            radius = 5
        }
    }
}