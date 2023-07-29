package gg.rsmod.plugins.content.npcs.definitions.giants

import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory


val ids = intArrayOf(Npcs.GIANT_SKELETON)

val table = DropTableFactory
val giantskeleton = table.build {
    guaranteed {
        obj(Items.BIG_BONES)
    }
    main {
        total(256)

        nothing (slots = 128)

    }

}

table.register(giantskeleton, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.GIANT_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 15
            attackStyle = StyleType.CRUSH
        }
        stats {
            hitpoints = 700
            attack = 70
            strength = 70
            defence = 70
        }
        anims {
            attack = 5485
            death = 5491
            block = 5489
        }
        aggro {
            radius = 4
        }
    }
}