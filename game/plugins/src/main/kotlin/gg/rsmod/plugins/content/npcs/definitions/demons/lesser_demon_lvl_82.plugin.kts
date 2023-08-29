package gg.rsmod.plugins.content.npcs.definitions.demons

import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(Npcs.LESSER_DEMON, Npcs.LESSER_DEMON_4694, Npcs.LESSER_DEMON_4695, Npcs.LESSER_DEMON_4696, Npcs.LESSER_DEMON_4697)

val table = DropTableFactory
val lesserDemon = table.build {
    guaranteed {
        obj(Items.ACCURSED_ASHES)
    }

    main {
        total(1024)
        nothing(1)
    }

}

table.register(lesserDemon, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.DEMON_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 30
            attackStyle = StyleType.SLASH
        }
        stats {
            hitpoints = 790
            attack = 68
            strength = 70
            defence = 71
            magic = 1
            ranged = 1
        }
        bonuses {
            attackStab = 0
            attackCrush = 0
            defenceStab = 0
            defenceSlash = 0
            defenceCrush = 0
            defenceMagic = -10
            defenceRanged = 0
        }
        anims {
            attack = 4630
            death = 67
            block = 65
        }
        aggro {
            radius = 4
        }
    }
}