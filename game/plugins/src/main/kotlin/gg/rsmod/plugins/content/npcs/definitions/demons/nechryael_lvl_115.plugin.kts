package gg.rsmod.plugins.content.npcs.definitions.demons

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Rare
import gg.rsmod.plugins.content.drops.global.Seeds

val ids = intArrayOf(Npcs.NECHRYAEL)

val table = DropTableFactory
val nechryael = table.build {
    guaranteed {
        obj(Items.INFERNAL_ASHES)
    }

    main {
        total(1024)
        obj(Items.SHARK, quantity = 1, slots = 32)
        obj(Items.LOBSTER, quantity = 1, slots = 32)
        obj(Items.SWORDFISH, quantity = 1, slots = 32)
        obj(Items.MONKFISH, quantity = 1, slots = 32)
        obj(Items.RED_SPIDERS_EGGS_NOTED, quantity = 1, slots = 8)
        obj(Items.SNAPE_GRASS_NOTED, quantity = 1, slots = 8)
        obj(Items.WHITE_BERRIES_NOTED, quantity = 1, slots = 8)
        obj(Items.DRAGON_MED_HELM, quantity = 1, slots = 4)
        obj(Items.GRANITE_MAUL, quantity = 1, slots = 4)
        obj(Items.SKELETAL_HELM, quantity = 1, slots = 2)
        obj(Items.SKELETAL_TOP, quantity = 1, slots = 2)
        obj(Items.SKELETAL_BOTTOMS, quantity = 1, slots = 2)
        obj(Items.SKELETAL_BOOTS, quantity = 1, slots = 2)
        obj(Items.SKELETAL_GLOVES, quantity = 1, slots = 2)
        obj(Items.CAPE_OF_LEGENDS, quantity = 1, slots = 4)
        obj(Items.ABYSSAL_WHIP, quantity = 1, slots = 2)
        nothing(slots = 128)

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
            respawnDelay = 59
            attackStyle = StyleType.SLASH
        }
        stats {
            hitpoints = 1250
            attack = 97
            strength = 127
            defence = 105
            magic = 1
            ranged = 1
        }
        bonuses {
            defenceStab = 20
            defenceSlash = 20
            defenceCrush = 20
            defenceMagic = 0
            defenceRanged = 20
        }
        anims {
            attack = 9487
            death = 9488
            block = 9489
        }
    }
}