package gg.rsmod.plugins.content.npcs.definitions.other

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(Npcs.ICE_WARRIOR, Npcs.ICE_WARRIOR_145, Npcs.ICE_WARRIOR_3073)

val table = DropTableFactory
val iceWarrior = table.build {
    guaranteed {
        obj(Items.BONES, quantity = 1)
    }
    main {
        total(512)
        obj(Items.MITHRIL_PICKAXE, quantity = 1, slots = 2)
        obj(Items.ADAMANT_PICKAXE, quantity = 1, slots = 1)
        obj(Items.MITHRIL_PLATELEGS, quantity = 1, slots = 1)
        obj(Items.MITHRIL_PLATESKIRT, quantity = 1, slots = 1)
        obj(Items.MITHRIL_PLATEBODY, quantity = 1, slots = 1)
        obj(Items.MITHRIL_SCIMITAR, quantity = 1, slots = 1)
        obj(Items.MITHRIL_FULL_HELM, quantity = 1, slots = 1)
        obj(Items.MITHRIL_KITESHIELD, quantity = 1, slots = 2)
        obj(Items.MITHRIL_HATCHET, quantity = 1, slots = 4)
        obj(Items.MITHRIL_SWORD, quantity = 1, slots = 4)
        obj(Items.MITHRIL_SQ_SHIELD, quantity = 1, slots = 4)
        obj(Items.MITHRIL_MED_HELM, quantity = 1, slots = 2)
        obj(Items.MITHRIL_CHAINBODY, quantity = 1, slots = 2)
    }

}

table.register(iceWarrior, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.ICE_WARRIOR_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 30
            attackStyle = StyleType.SLASH
        }
        stats {
            hitpoints = 590
            attack = 47
            strength = 47
            defence = 47
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
        aggro {
            radius = 5
        }
    }
}