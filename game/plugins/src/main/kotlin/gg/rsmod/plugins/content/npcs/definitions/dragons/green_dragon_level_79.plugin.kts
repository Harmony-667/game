package gg.rsmod.plugins.content.npcs.definitions.dragons

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Gems
import gg.rsmod.plugins.content.drops.global.Herbs

val ids = intArrayOf(
    Npcs.GREEN_DRAGON ,Npcs.GREEN_DRAGON_4677, Npcs.GREEN_DRAGON_4678, Npcs.GREEN_DRAGON_4679, Npcs.GREEN_DRAGON_4680)

val table = DropTableFactory
val dragon = table.build {
    guaranteed {
        obj(Items.DRAGON_BONES)
        obj(Items.GREEN_DRAGONHIDE)
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
            hitpoints = 750
            attack = 68
            strength = 68
            defence = 68
            magic = 68
            ranged = 1
        }
        bonuses {
            defenceStab = 20
            defenceSlash = 40
            defenceCrush = 40
            defenceMagic = 30
            defenceRanged = 20
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