package gg.rsmod.plugins.content.npcs.definitions.dragons

import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Gems
import gg.rsmod.plugins.content.drops.global.Herbs

val ids = intArrayOf(
    Npcs.BLUE_DRAGON, Npcs.BLUE_DRAGON_4681, Npcs.BLUE_DRAGON_4682, Npcs.BLUE_DRAGON_4683, Npcs.BLUE_DRAGON_4684, Npcs.BLUE_DRAGON_5178)

val table = DropTableFactory
val dragon = table.build {
    guaranteed {
        obj(Items.DRAGON_BONES)
        obj(Items.BLUE_DRAGONHIDE)
    }

    main {
        total(1024)

        nothing(5)
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
            hitpoints = 1050
            attack = 95
            strength = 95
            defence = 95
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