package gg.rsmod.plugins.content.npcs.definitions.dragons

import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Gems
import gg.rsmod.plugins.content.drops.global.Herbs

val ids = intArrayOf(Npcs.RED_DRAGON, Npcs.RED_DRAGON_4669, Npcs.RED_DRAGON_4670, Npcs.RED_DRAGON_4671, Npcs.RED_DRAGON_4672)

val table = DropTableFactory
val dragon = table.build {
    guaranteed {
        obj(Items.DRAGON_BONES)
        obj(Items.RED_DRAGONHIDE)
    }

    main {
        total(1024)
        nothing(1000)
    }


}

table.register(dragon, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.DRAGON_DEATH)
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
        species {
            NpcSpecies.BASIC_DRAGON
            NpcSpecies.FIERY
        }
        stats {
            hitpoints = 1400
            attack = 130
            strength = 130
            defence = 130
            magic = 1
            ranged = 1
        }
        bonuses {
            defenceStab = 50
            defenceSlash = 70
            defenceCrush = 70
            defenceMagic = 60
            defenceRanged = 50
        }
        anims {
            attack = 12252
            block = 12251
            death = 12250
        }
        aggro {
            radius = 4
        }
    }
}