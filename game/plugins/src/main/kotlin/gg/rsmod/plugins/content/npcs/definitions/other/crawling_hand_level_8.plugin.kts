package gg.rsmod.plugins.content.npcs.definitions.other

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Rare

val ids = intArrayOf(Npcs.CRAWLING_HAND, Npcs.CRAWLING_HAND_1649, Npcs.CRAWLING_HAND_1650, Npcs.CRAWLING_HAND_1651)
val table = DropTableFactory
val crawlinghand = table.build {

    guaranteed {
        obj(Items.BONES)
    }
    main {
        total(total = 512)

        nothing(slots = 218)


    }
}

table.register(crawlinghand, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.HAND_DEATH)
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
            hitpoints = 160
            attack = 8
            strength = 4
            defence = 4
            magic = 1
            ranged = 1
        }
        anims {
            attack = 9125
            block = 9127
            death = 9126
        }
        slayer {
            assignment = SlayerAssignment.CRAWLING_HAND
            level = 1
            experience = 12.0
        }
    }
}