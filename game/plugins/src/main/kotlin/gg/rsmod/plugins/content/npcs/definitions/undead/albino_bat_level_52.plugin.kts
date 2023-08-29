package gg.rsmod.plugins.content.npcs.definitions.undeads

import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.game.model.combat.StyleType


val ids = intArrayOf(Npcs.ALBINO_BAT)
val table = DropTableFactory
val albinobat = table.build {

    guaranteed {
        obj(Items.BONES)
    }
    main {
        total(total = 128)

        nothing(slots = 3)

    }

}

table.register(albinobat, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.BAT_DEATH)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 35
            attackStyle = StyleType.STAB
        }
        stats {
            hitpoints = 330
            attack = 57
            strength = 57
            defence = 30
        }
        anims {
            attack = 4915
            death = 4918
            block = 4916
        }
    }
}
