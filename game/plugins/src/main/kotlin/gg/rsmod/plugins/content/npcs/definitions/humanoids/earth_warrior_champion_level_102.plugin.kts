package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(Npcs.EARTH_WARRIOR_CHAMPION)

val table = DropTableFactory
val earthWarriorChampion = table.build {
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
    table("Rare") {
        total(1024)
        obj(Items.RUNE_ARROW, quantity = 5, slots = 2)
        obj(Items.RUNE_MACE, quantity = 1, slots = 2)
        obj(Items.RUNE_FULL_HELM, quantity = 1, slots = 2)
        obj(Items.RUNE_PLATELEGS, quantity = 1, slots = 2)
        obj(Items.RUNE_PLATESKIRT, quantity = 1, slots = 2)
        obj(Items.RUNE_CHAINBODY, quantity = 1, slots = 2)
        obj(Items.RUNE_PLATEBODY, quantity = 1, slots = 2)
        obj(Items.RUNE_BOOTS, quantity = 1, slots = 2)
        obj(Items.RUNE_KITESHIELD, quantity = 1, slots = 2)
        obj(Items.RUNE_SQ_SHIELD, quantity = 1, slots = 2)
        obj(Items.RUNE_HATCHET, quantity = 1, slots = 2)
        obj(Items.RUNE_PICKAXE, quantity = 1, slots = 2)
        obj(Items.RUNE_ESSENCE_NOTED, quantity = 5, slots = 2)
        obj(Items.EYE_OF_NEWT_NOTED, quantity = 5, slots = 2)
        obj(Items.UNICORN_HORN_NOTED, quantity = 5, slots = 2)
        obj(Items.LIMPWURT_ROOT_NOTED, quantity = 5, slots = 2)
        obj(Items.WHITE_BERRIES_NOTED, quantity = 5, slots = 2)
        nothing(36)
    }
}



table.register(earthWarriorChampion, *ids)

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
            respawnDelay = 300
            attackStyle = StyleType.CRUSH
        }
        stats {
            hitpoints = 1080
            attack = 84
            strength = 84
            defence = 84
        }
        bonuses {
            defenceStab = 60
            defenceSlash = 80
            defenceCrush = 40
            defenceMagic = 20
            defenceRanged = 300
        }
        anims {
            attack = 2951
            block = 2952
            death = 2946
        }
    }
}