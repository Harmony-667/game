package gg.rsmod.plugins.content.npcs.definitions.animals

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(Npcs.SKELETON_HELLHOUND)

val table = DropTableFactory
val hellhound = table.build {
    guaranteed {
        obj(Items.BONES, quantity = 1)
    }
    main {
        total(1024)
        nothing(slots = 256)
    }

}

table.register(hellhound, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.SKELETAL_HELLHOUND_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 89
        }
        stats {
            hitpoints = 550
            attack = 70
            strength = 110
            defence = 100

        }
        anims {
            attack = 6562
            death = 6564
            block = 6563
        }
    }
}