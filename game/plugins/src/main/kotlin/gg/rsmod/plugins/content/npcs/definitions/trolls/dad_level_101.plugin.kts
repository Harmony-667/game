package gg.rsmod.plugins.content.npcs.definitions.trolls

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Herbs

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */

val ids = intArrayOf(Npcs.DAD)

val table = DropTableFactory
val dad = table.build {
    guaranteed {
        obj(Items.BIG_BONES)
    }
    main {
        total(1024)
        obj(Items.COINS_995, quantityRange = 200..2500, slots = 32)
        obj(Items.LIMPWURT_ROOT_NOTED, quantityRange = 1..22, slots = 8)
        obj(Items.COAL_NOTED, quantityRange = 1..32, slots = 8)
        obj(Items.RUNE_PLATELEGS, quantity = 1, slots = 4)
        obj(Items.RUNE_PLATESKIRT, quantity = 1, slots = 4)
        obj(Items.RUNE_PLATEBODY, quantity = 1, slots = 4)
        obj(Items.RUNE_FULL_HELM, quantity = 1, slots = 4)
        obj(Items.RUNE_BATTLEAXE, quantity = 1, slots = 4)
        obj(Items.RUNE_2H_SWORD, quantity = 1, slots = 4)
        obj(Items.KEY_1543, quantity = 1, slots = 2)
        obj(Items.GRANITE_MAUL, quantity = 1, slots = 2)
        obj(Items.GRANITE_MACE, quantity = 1, slots = 2)
        obj(Items.GRANITE_SHIELD, quantity = 1, slots = 2)
        obj(Items.GRANITE_LEGS, quantity = 1, slots = 1)
        obj(Items.GRANITE_BODY, quantity = 1, slots = 1)
        obj(Items.GRANITE_HELM, quantity = 1, slots = 1)
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

table.register(dad, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.TROLL_CHAMPION_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 8
            respawnDelay = 300
            attackStyle = StyleType.CRUSH
        }
        stats {
            hitpoints = 1200
            attack = 60
            strength = 120
            defence = 50
        }
        bonuses {
            attackStab = 40
            attackBonus = 40
            attackCrush = 40
            attackSlash = 40
            strengthBonus = 70
            defenceStab = 25
            defenceSlash = 25
            defenceCrush = 40
            defenceMagic = 200
            defenceRanged = 200
        }
        anims {
            attack = 1932
            death = 1934
            block = 4995
        }
    }
}