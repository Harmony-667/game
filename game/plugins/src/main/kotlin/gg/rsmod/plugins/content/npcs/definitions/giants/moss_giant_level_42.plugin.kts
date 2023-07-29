package gg.rsmod.plugins.content.npcs.definitions.giants

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Gems
import gg.rsmod.plugins.content.drops.global.Herbs
import gg.rsmod.plugins.content.drops.global.Seeds

val ids = intArrayOf(Npcs.MOSS_GIANT, Npcs.MOSS_GIANT_1587, Npcs.MOSS_GIANT_1588, Npcs.MOSS_GIANT_4688)

val table = DropTableFactory
val mossGiant = table.build {
    guaranteed {
        obj(Items.BIG_BONES)
    }
    main {
        total(256)
        obj(Items.ADAMANT_FULL_HELM, quantity = 1, slots = 2)
        obj(Items.ADAMANT_MED_HELM, quantity = 1, slots = 8)
        obj(Items.ADAMANT_PLATELEGS, quantity = 1, slots = 2)
        obj(Items.ADAMANT_PLATEBODY, quantity = 1, slots = 2)
        obj(Items.ADAMANT_BOOTS, quantity = 1, slots = 8)
        obj(Items.ADAMANT_SWORD, quantity = 1, slots = 8)
        obj(Items.ADAMANT_SCIMITAR, quantity = 1, slots = 1)
        obj(Items.ADAMANT_KITESHIELD, quantity = 1, slots = 1)
        obj(Items.ADAMANT_2H_SWORD, quantity = 1, slots = 1)
        obj(Items.ADAMANT_BATTLEAXE, quantity = 1, slots = 1)
        obj(Items.EARTH_RUNE, quantity = 3, slots = 4)
        obj(Items.NATURE_RUNE, quantity = 1, slots = 2)
        obj(Items.IRON_ARROW, quantity = 15, slots = 4)
        obj(Items.STEEL_ARROW, quantity = 5, slots = 2)
        obj(Items.ADAMANT_ARROW, quantity = 2, slots = 1)
        nothing (slots = 128)

        table(Herbs.minorHerbTable, slots = 4)
        table(Seeds.uncommonSeedTable, slots = 4)
        table(Gems.gemTable, slots = 2)
    }
   /* table("Charms") {
        total(1000)
        obj(Items.GOLD_CHARM, quantity = 1, slots = 360)
        obj(Items.GREEN_CHARM, quantity = 1, slots = 30)
        obj(Items.CRIMSON_CHARM, quantity = 1, slots = 30)
        obj(Items.BLUE_CHARM, quantity = 1, slots = 7)
        nothing(slots = 573)
    }*/
}

table.register(mossGiant, *ids)

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
            hitpoints = 600
            attack = 30
            strength = 30
            defence = 30
        }
        bonuses {
            attackStab = 33
            attackCrush = 31
        }
        anims {
            attack = 4652
            death = 4653
            block = 4651
        }
        aggro {
            radius = 4
        }
    }
}