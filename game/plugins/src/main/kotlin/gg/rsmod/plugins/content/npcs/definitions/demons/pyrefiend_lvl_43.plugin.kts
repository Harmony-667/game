package gg.rsmod.plugins.content.npcs.definitions.demons

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Gems

val ids = intArrayOf(Npcs.PYREFIEND, Npcs.PYREFIEND_1634, Npcs.PYREFIEND_1635, Npcs.PYREFIEND_1636)

val table = DropTableFactory
val pyrefiend = table.build {
    guaranteed {
        obj(Items.IMPIOUS_ASHES)
    }

    main {
        total(128)
        nothing(1)
    }
}

table.register(pyrefiend, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.PYREFIEND_DEATH)
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
            hitpoints = 450
            attack = 52
            strength = 30
            defence = 22
            magic = 1
            ranged = 1
        }
        bonuses {
            defenceStab = 10
            defenceSlash = 10
            defenceCrush = 10
            defenceMagic = 0
            defenceRanged = 10
        }
        anims {
            attack = 8080
            death = 11211
        }
        slayer {
            assignment = SlayerAssignment.PYREFIEND
            experience = 45.0
            level = 1
        }
    }
}