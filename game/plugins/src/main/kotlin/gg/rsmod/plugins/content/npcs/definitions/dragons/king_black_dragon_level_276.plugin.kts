package gg.rsmod.plugins.content.npcs.definitions.dragons

import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Gems
import gg.rsmod.plugins.content.drops.global.Rare

val ids = intArrayOf(Npcs.KING_BLACK_DRAGON)

val table = DropTableFactory
val dragon = table.build {
    guaranteed {
        obj(Items.DRAGON_BONES)
        obj(Items.BLACK_DRAGONHIDE)
    }

    main {
        total(1024)


        nothing(48)
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
            respawnDelay = 300
            //poisonDamage = 6
        }
        species {
            NpcSpecies.BASIC_DRAGON
            NpcSpecies.FIERY
        }
        stats {
            hitpoints = 2400
            attack = 240
            strength = 240
            defence = 240
            magic = 240
            ranged = 1
        }
        bonuses {
            defenceStab = 70
            defenceSlash = 90
            defenceCrush = 90
            defenceMagic = 80
            defenceRanged = 70
        }
        anims {
            attack = 91
            block = 26
            death = 28
        }
        aggro {
            radius = 4
        }
    }
}