package gg.rsmod.plugins.content.npcs.definitions.animals

import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.api.cfg.Npcs



val ids = intArrayOf(Npcs.POISON_SCORPION)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.SCORPION_DEATH)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 25
            attackStyle = StyleType.STAB
            poisonDamage = 3
        }
        stats {
            hitpoints = 230
            attack = 16
            strength = 17
            defence = 15
        }
        bonuses {
            defenceStab = 5
            defenceSlash = 15
            defenceCrush = 15
            defenceMagic = 0
            defenceRanged = 5
        }
        anims {
            attack = 6254
            block = 6255
            death = 6256
        }
        aggro {
            radius = 4
        }
    }
}