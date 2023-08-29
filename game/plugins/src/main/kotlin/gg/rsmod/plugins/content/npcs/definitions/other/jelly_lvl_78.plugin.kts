package gg.rsmod.plugins.content.npcs.definitions.other

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.cfg.Items
import gg.rsmod.plugins.api.cfg.Npcs
import gg.rsmod.plugins.api.ext.npc
import gg.rsmod.plugins.content.drops.DropTableFactory

/**
 * @author Alycia <https://github.com/alycii>
 */

val ids = intArrayOf(Npcs.JELLY, Npcs.JELLY_1638, Npcs.JELLY_1639, Npcs.JELLY_1640, Npcs.JELLY_1641, Npcs.JELLY_1642)

val table = DropTableFactory
val jelly = table.build {
    guaranteed {
        obj(Items.COINS_995, quantity = world.random(1..500))
    }
    main {
        total(total = 128)

    }
}

table.register(jelly, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.JELLY_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 15
            attackStyle = StyleType.MAGIC_MELEE
        }
        stats {
            hitpoints = 750
            attack = 45
            strength = 45
            defence = 3120
            magic = 45
        }
        anims {
            attack = 8569
            block = 8560
            death = 11210
        }
        slayer {
            assignment = SlayerAssignment.JELLY
            experience = 75.0
            level = 1
        }
    }
}