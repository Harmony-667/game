package gg.rsmod.plugins.content.npcs.definitions.other

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.cfg.Npcs
import gg.rsmod.plugins.api.ext.npc
import gg.rsmod.plugins.content.drops.DropTableFactory


/**
 * @author Alycia <https://github.com/alycii>
 */

val ids = intArrayOf(Npcs.CAVE_CRAWLER, Npcs.CAVE_CRAWLER_1601, Npcs.CAVE_CRAWLER_1602, Npcs.CAVE_CRAWLER_1603)

val table = DropTableFactory
val caveCrawler = table.build {
    main {
        total(total = 128)

        nothing(slots = 29)

    }
}

table.register(caveCrawler, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.CAVE_CRAWLER_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 15
            attackStyle = StyleType.STAB
            poisonDamage = 8
        }
        stats {
            hitpoints = 220
            attack = 22
            strength = 18
            defence = 18
        }
        bonuses {
            defenceStab = 10
            defenceSlash = 10
            defenceCrush = 5
            defenceMagic = 5
            defenceRanged = 10
        }
        anims {
            attack = 266
            block = 267
            death = 265
        }
        slayer {
            assignment = SlayerAssignment.CAVE_CRAWLER
            experience = 22.0
            level = 1
        }
    }
}