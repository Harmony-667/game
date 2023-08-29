package gg.rsmod.plugins.content.npcs.definitions.demons

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Rare
import gg.rsmod.plugins.content.drops.global.Seeds

val ids = intArrayOf(Npcs.TUROTH_1626)

val table = DropTableFactory
val nechryael = table.build {
    guaranteed {
        obj(Items.BONES)
    }

    main {
        total(1024)
        obj(Items.ARMADYL_FULL_HELM, quantity = 1, slots = 2)
        obj(Items.ARMADYL_KITESHIELD, quantity = 1, slots = 2)
        obj(Items.ARMADYL_PLATEBODY, quantity = 1, slots = 2)
        obj(Items.ARMADYL_PLATELEGS, quantity = 1, slots = 2)
        obj(Items.ARMADYL_PLATESKIRT, quantity = 1, slots = 2)
        obj(Items.BANDOS_FULL_HELM, quantity = 1, slots = 2)
        obj(Items.BANDOS_KITESHIELD, quantity = 1, slots = 2)
        obj(Items.BANDOS_PLATEBODY, quantity = 1, slots = 2)
        obj(Items.BANDOS_PLATELEGS, quantity = 1, slots = 2)
        obj(Items.BANDOS_PLATESKIRT, quantity = 1, slots = 2)
        obj(Items.ANCIENT_FULL_HELM, quantity = 1, slots = 2)
        obj(Items.ANCIENT_KITESHIELD, quantity = 1, slots = 2)
        obj(Items.ANCIENT_PLATEBODY, quantity = 1, slots = 2)
        obj(Items.ANCIENT_PLATELEGS, quantity = 1, slots = 2)
        obj(Items.ANCIENT_PLATESKIRT, quantity = 1, slots = 2)
        nothing(slots = 256)

    }

}

table.register(nechryael, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.NECHRAYEL_DEATH )
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 30
            attackStyle = StyleType.STAB
        }
        stats {
            hitpoints = 790
            attack = 56
            strength = 86
            defence = 86
            magic = 1
            ranged = 1
        }
        bonuses {
            defenceStab = 0
            defenceSlash = 20
            defenceCrush = 20
            defenceMagic = 0
            defenceRanged = 0
        }
        anims {
            attack = 9471
            death = 9472
            block = 9473
        }
    }
}