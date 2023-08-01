package gg.rsmod.plugins.content.npcs.definitions.other

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.cfg.Npcs
import gg.rsmod.plugins.api.ext.npc
import gg.rsmod.plugins.content.drops.DropTableFactory


val ids = intArrayOf(Npcs.GIANT_ROCK_CRAB, Npcs.GIANT_ROCK_CRAB_2885)

val table = DropTableFactory
val rockcrab = table.build {
    guaranteed {
        obj(Items.BIG_BONES, quantity = 1)
    }
    main {
        total(1024)
        obj(Items.TIN_ORE, quantity = 3, slots = 32)
        obj(Items.IRON_ORE, quantity = 1, slots = 32)
        obj(Items.COAL, quantity = 2, slots = 8)
        obj(Items.COPPER_ORE, quantity = 3, slots = 32)
        obj(Items.SEAWEED, quantity = 1, slots = 32)
        obj(Items.SEAWEED, quantity = 2, slots = 128)
        obj(Items.SEAWEED, quantity = 3, slots = 32)
        obj(Items.SEAWEED, quantity = 5, slots = 8)
        obj(Items.FISHING_BAIT, quantity = 10, slots = 8)
        obj(Items.FISHING_BAIT, quantity = 15, slots = 8)
        obj(Items.WHITE_BERRIES, quantity = 1, slots = 2)
        obj(Items.WHITE_BERRIES, quantity = 5, slots = 4)
        obj(Items.WHITE_BERRIES, quantity = 12, slots = 2)
        obj(Items.LIMPWURT_ROOT, quantity = 1, slots = 8)
        obj(Items.LIMPWURT_ROOT, quantity = 5, slots = 4)
        obj(Items.LIMPWURT_ROOT, quantity = 12, slots = 2)
        obj(Items.EYE_OF_NEWT, quantity = 1, slots = 8)
        obj(Items.EYE_OF_NEWT, quantity = 6, slots = 4)
        obj(Items.EYE_OF_NEWT, quantity = 12, slots = 2)
        obj(Items.ROCKSHELL_HELM, quantity = 1, slots = 1)
        obj(Items.ROCKSHELL_PLATE, quantity = 1, slots = 1)
        obj(Items.ROCKSHELL_LEGS, quantity = 1, slots = 1)
        obj(Items.ROCKSHELL_BOOTS, quantity = 1, slots = 1)
        obj(Items.ROCKSHELL_GLOVES, quantity = 1, slots = 1)
        nothing(slots = 512)
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
            respawnDelay = 300
            attackStyle = StyleType.CRUSH
        }
        stats {
            hitpoints = 1800
            attack = 50
            strength = 80
            defence = 200
        }
        bonuses {
            defenceStab = 225
            defenceSlash = 200
            defenceCrush = 175
            defenceMagic = 10
            defenceRanged = 250
        }

        anims {
            attack = 1312
            block = 1311
            death = 1314
        }
    }
}