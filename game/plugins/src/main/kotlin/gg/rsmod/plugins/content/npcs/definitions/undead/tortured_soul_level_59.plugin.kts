package gg.rsmod.plugins.content.npcs.definitions.undeads

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType

val ids = intArrayOf(Npcs.TORTURED_SOUL)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.GHOST_DEATH)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 30
            attackStyle = StyleType.CRUSH
        }
        stats {
            hitpoints = 510
            attack = 52
            strength = 62
            defence = 38
        }
        anims {
            attack = 422
            death = 836
            block = 404
        }
        slayer {
            assignment = SlayerAssignment.TORTURED_SOUL
            experience = 51.0
            level = 1
        }

    }
}
