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

val ids = intArrayOf(Npcs.CAVE_SLIME)

val table = DropTableFactory
val caveSlime = table.build {
    guaranteed {
        obj(Items.SWAMP_TAR, quantity = world.random(1..6))
    }
    main {
        total(total = 128)

    }
}

table.register(caveSlime, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.SLIME_DEATH)
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
            poisonDamage = 3
        }
        stats {
            hitpoints = 250
            attack = 13
            strength = 13
            defence = 35
            magic = 13
        }
        anims {
            attack = 1789
            death = 1792
        }
        slayer {
            assignment = SlayerAssignment.CAVE_SLIME
            experience = 25.0
            level = 1
        }
    }
}