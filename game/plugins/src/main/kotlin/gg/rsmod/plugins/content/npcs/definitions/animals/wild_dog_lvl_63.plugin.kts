package gg.rsmod.plugins.content.npcs.definitions.animals

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory


val ids = intArrayOf(Npcs.WILD_DOG_1594, Npcs.WILD_DOG)

val table = DropTableFactory
val wilddog = table.build {
    guaranteed {
        obj(Items.BONES, quantity = 1)
    }
    main {
        total(512)
        nothing(slots = 256)
    }
}

table.register(wilddog, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.DOG_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 50
            attackStyle = StyleType.STAB
        }
        stats {
            hitpoints = 620
            attack = 53
            strength = 54
            defence = 54
        }
        anims {
            attack = 6559
            death = 6558
            block = 6557
        }
        slayer {
            assignment = SlayerAssignment.WILDDOG
            experience = 62.0
            level = 1
        }
    }
}