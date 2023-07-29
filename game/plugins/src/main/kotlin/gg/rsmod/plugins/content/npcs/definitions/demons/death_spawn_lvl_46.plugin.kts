package gg.rsmod.plugins.content.npcs.definitions.demons

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(Npcs.DEATH_SPAWN)

val table = DropTableFactory
val deathspawn = table.build {
    guaranteed {
        obj(Items.IMPIOUS_ASHES)
    }

    main {
        total(1024)
        nothing(slots = 128)
    }
}

table.register(deathspawn, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.IMP_DEATH)
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
            hitpoints = 60
            attack = 67
            strength = 7
            defence = 30
        }
        bonuses {
            defenceStab = 20
            defenceSlash = 20
            defenceCrush = 20
            defenceRanged = 20
        }
        anims {
            attack = 9459
            death = 9460
            block = 9461
        }
        slayer {
            assignment = SlayerAssignment.DEATHSPAWN
            experience = 50.0
            level = 1
        }
    }
}