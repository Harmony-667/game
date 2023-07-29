package gg.rsmod.plugins.content.npcs.definitions.animals

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.plugins.content.drops.DropTableFactory

/**
 * @author Alycia <https://github.com/alycii>
 */
val ids = intArrayOf(Npcs.WHITE_WOLF, Npcs.WOLF_4413, Npcs.WOLF_6047)

val table = DropTableFactory
val whiteWolf = table.build {
    guaranteed {
        obj(Items.WOLF_BONES, quantity = 1)
    }
    main {
        total(512)
        obj(Items.STEEL_PICKAXE, quantity = 1, slots = 2)
        obj(Items.MITHRIL_PICKAXE, quantity = 1, slots = 1)
        nothing(slots = 256)
    }
}

table.register(whiteWolf, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.WOLF_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 90
        }
        stats {
            hitpoints = 340
            attack = 20
            strength = 16
            defence = 22
            magic = 1
            ranged = 1
        }
        anims {
            attack = 6579
            death = 6576
            block = 6578
        }
        aggro {
            radius = 4
        }
    }
}