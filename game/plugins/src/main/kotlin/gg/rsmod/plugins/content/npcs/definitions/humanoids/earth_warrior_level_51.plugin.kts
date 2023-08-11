package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(Npcs.EARTH_WARRIOR)

val table = DropTableFactory
val earthWarrior = table.build {
    guaranteed {
        obj(Items.BONES, quantity = 1)
    }
    main {
        total(512)
        obj(Items.EARTH_RUNE, quantity = 5, slots = 4)
        obj(Items.BRONZE_MED_HELM, quantity = 1, slots = 4)
        obj(Items.BRONZE_MACE, quantity = 1, slots = 4)
        obj(Items.BRONZE_BOOTS, quantity = 1, slots = 4)
        obj(Items.BRONZE_PLATELEGS, quantity = 1, slots = 4)
        obj(Items.BRONZE_PLATESKIRT, quantity = 1, slots = 4)
        obj(Items.BRONZE_PLATEBODY, quantity = 1, slots = 4)
        obj(Items.BRONZE_CHAINBODY, quantity = 1, slots = 4)
        obj(Items.BRONZE_FULL_HELM, quantity = 1, slots = 4)
        obj(Items.BRONZE_SQ_SHIELD, quantity = 1, slots = 4)
        obj(Items.BRONZE_KITESHIELD, quantity = 1, slots = 4)
        obj(Items.NATURE_RUNE, quantity = 5, slots = 2)
        obj(Items.STEEL_ARROW, quantity = 20, slots = 2)
        obj(Items.MITHRIL_ARROW, quantity = 20, slots = 2)
        obj(Items.ADAMANT_ARROW, quantity = 20, slots = 2)
        obj(Items.STAFF_OF_EARTH, quantity = 1, slots = 2)
        nothing(18)
    }
}



table.register(earthWarrior, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.HUMAN_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 30
            attackStyle = StyleType.CRUSH
        }
        stats {
            hitpoints = 540
            attack = 42
            strength = 42
            defence = 42
        }
        bonuses {
            defenceStab = 30
            defenceSlash = 40
            defenceCrush = 20
            defenceMagic = 10
            defenceRanged = 30
        }
        anims {
            attack = 391
            block = 399
            death = 843
        }
    }
}