package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Herbs.minorHerbTable

val ids = intArrayOf(Npcs.MOURNER_2374)

val table = DropTableFactory
val mourner = table.build {
    guaranteed {
        obj(Items.BONES)
    }

    main {
        total(1024)
        nothing(slots = 16)
    }

}

table.register(mourner, *ids)

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
            respawnDelay = 300
            attackStyle = StyleType.STAB
        }
        stats {
            hitpoints = 1050
            attack = 95
            strength = 95
            defence = 80

        }
        bonuses {
            defenceStab = 50
            defenceSlash = 70
            defenceCrush = 70
            defenceMagic = 60
            defenceRanged = 50
        }
        anims {
            attack = 422
            death = 836
            block = 404
        }
        slayer {
            assignment = SlayerAssignment.MOURNER
            experience = 107.5
            level = 1
        }
    }
}