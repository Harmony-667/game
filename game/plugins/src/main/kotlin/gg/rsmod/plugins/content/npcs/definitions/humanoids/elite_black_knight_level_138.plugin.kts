package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory


val ids = intArrayOf(Npcs.ELITE_BLACK_KNIGHT)

val table = DropTableFactory
val eliteblackknight = table.build {
    guaranteed {
        obj(Items.BONES)
    }

    main {
        total(128)

        nothing(2)
    }

}

table.register(eliteblackknight, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.HUMAN_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 150
            attackStyle = StyleType.SLASH
        }
        stats {
            hitpoints = 3600
            attack = 72
            strength = 120
            defence = 72
        }
        bonuses {
            attackStab = 18
            attackCrush = 16
            defenceStab = 21
            defenceSlash = 76
            defenceCrush = 70
            attackSlash = 70
            defenceMagic = -21
            defenceRanged = 120
        }
        anims {
            attack = 10854
            death = 10856
            block = 10855
        }
    }
}
