package gg.rsmod.plugins.content.npcs.definitions.other

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.cfg.Npcs
import gg.rsmod.plugins.api.ext.npc
import gg.rsmod.plugins.content.drops.DropTableFactory


val ids = intArrayOf(Npcs.ROCK_CRAB, Npcs.ROCK_CRAB_1267)

val table = DropTableFactory
val rockcrab = table.build {
    guaranteed {
        obj(Items.BONES, quantity = 1)
    }
        main {
            total(512)
            obj(Items.TIN_ORE, quantity = 3, slots = 16)
            obj(Items.IRON_ORE, quantity = 1, slots = 16)
            obj(Items.COAL, quantity = 2, slots = 4)
            obj(Items.COPPER_ORE, quantity = 3, slots = 16)
            obj(Items.SEAWEED, quantity = 1, slots = 16)
            obj(Items.SEAWEED, quantity = 2, slots = 64)
            obj(Items.SEAWEED, quantity = 3, slots = 16)
            obj(Items.SEAWEED, quantity = 5, slots = 4)
            obj(Items.FISHING_BAIT, quantity = 10, slots = 4)
            obj(Items.FISHING_BAIT, quantity = 15, slots = 4)
            obj(Items.WHITE_BERRIES, quantity = 1, slots = 4)
            obj(Items.WHITE_BERRIES, quantity = 5, slots = 2)
            obj(Items.WHITE_BERRIES, quantity = 12, slots = 1)
            obj(Items.LIMPWURT_ROOT, quantity = 1, slots = 4)
            obj(Items.LIMPWURT_ROOT, quantity = 5, slots = 2)
            obj(Items.LIMPWURT_ROOT, quantity = 12, slots = 1)
            obj(Items.EYE_OF_NEWT, quantity = 1, slots = 4)
            obj(Items.EYE_OF_NEWT, quantity = 6, slots = 2)
            obj(Items.EYE_OF_NEWT, quantity = 12, slots = 1)
            nothing(slots = 4)
        }
}

table.register(rockcrab, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.ROCK_CRAB_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 50
            attackStyle = StyleType.CRUSH
        }
        stats {
            hitpoints = 500
            attack = 1
            strength = 1
            defence = 1
        }

        anims {
            attack = 1312
            block = 1311
            death = 1314
        }
    }
}