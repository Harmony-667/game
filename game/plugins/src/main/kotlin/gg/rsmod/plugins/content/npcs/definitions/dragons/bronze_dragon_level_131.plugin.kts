package gg.rsmod.plugins.content.npcs.definitions.dragons

import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(
    Npcs.BRONZE_DRAGON)

val table = DropTableFactory
val dragon = table.build {
    guaranteed {
        obj(Items.DRAGON_BONES)
        obj(Items.BRONZE_BAR, 1..5)
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
            respawnDelay = 60
        }
        species {
            NpcSpecies.METAL_DRAGON
            NpcSpecies.FIERY
        }
        stats {
            hitpoints = 1220
            attack = 112
            strength = 112
            defence = 112
            magic = 100
            ranged = 1
        }
        bonuses {
            defenceStab = 50
            defenceSlash = 70
            defenceCrush = 70
            defenceMagic = 30
            defenceRanged = 90
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