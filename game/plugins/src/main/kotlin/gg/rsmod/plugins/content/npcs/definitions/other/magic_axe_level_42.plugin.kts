package gg.rsmod.plugins.content.npcs.definitions.other

import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.game.model.combat.StyleType


val ids = intArrayOf(Npcs.MAGIC_AXE)
val table = DropTableFactory
val magicaxe = table.build {

    guaranteed {
        obj(Items.IRON_BATTLEAXE)
    }
    main {
        total(total = 128)

        nothing(slots = 3)

    }

}

table.register(magicaxe, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.EQUIP_BAXE)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 30
            attackStyle = StyleType.SLASH
        }
        stats {
            hitpoints = 440
            attack = 38
            strength = 38
            defence = 29
        }
        anims {
            attack = 185
            death = 188
            block = 186
        }
    }
}
