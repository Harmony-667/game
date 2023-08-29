package gg.rsmod.plugins.content.npcs.definitions.giants

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Gems
import gg.rsmod.plugins.content.drops.global.Herbs
import gg.rsmod.plugins.content.drops.global.Seeds

val ids = intArrayOf(Npcs.HILL_GIANT, Npcs.HILL_GIANT_4689, Npcs.HILL_GIANT_4690, Npcs.HILL_GIANT_4691, Npcs.HILL_GIANT_4692, Npcs.HILL_GIANT_4693)
val table = DropTableFactory
val hillgiant = table.build {

    guaranteed {
        obj(Items.BIG_BONES)
    }
    main {
        total(total = 128)

        nothing(slots = 1)
        obj(Items.LIMPWURT_ROOT, quantity = 1, slots = 11)


    }

}

table.register(hillgiant, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.GIANT_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 6
            respawnDelay = 30
            attackStyle = StyleType.CRUSH
        }
        stats {
            hitpoints = 350
            attack = 18
            strength = 22
            defence = 26
        }
        bonuses {
            attackStab = 18
            attackCrush = 16
        }
        anims {
            attack = 4652
            block = 4651
            death = 4653
        }
        aggro {
            radius = 4
        }
    }
}