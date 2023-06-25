package gg.rsmod.plugins.content.npcs.definitions.undeads

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Gems
import gg.rsmod.plugins.content.drops.global.Herbs
/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */

val ids = intArrayOf(Npcs.MUMMY_2015, Npcs.MUMMY_2016)
val table = DropTableFactory
val mummy = table.build {

    guaranteed {
        obj(Items.BONES)
    }
    main {
        total(total = 128)

        obj(Items.CHEFS_HAT, quantity = 1, slots = 3)
        obj(Items.LIMPWURT_ROOT, quantityRange = 1..3, slots = 5)
        table(Gems.gemTable, slots = 1)
        table(Herbs.minorHerbTable, slots = 15)
        nothing(slots = 3)

    }
    /*table("Charms") {
        total(1000)
        obj(Items.GOLD_CHARM, quantity = 1, slots = 30)
        obj(Items.GREEN_CHARM, quantity = 1, slots = 17)
        obj(Items.CRIMSON_CHARM, quantity = 1, slots = 14)
        obj(Items.BLUE_CHARM, quantity = 1, slots = 2)
        nothing(slots = 937)
    }*/
}

table.register(mummy, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.MUMMY_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 30
        }
        stats {
            hitpoints = 900
            attack = 90
            strength = 30
            defence = 90
        }
        bonuses {
            attackStab = 0
            attackCrush = 0
            strengthBonus = 30
            defenceStab = 90
            defenceSlash = 90
            defenceCrush = 30
            defenceMagic = 90
            defenceRanged = 0
        }
        anims {
            attack = 5554
            block = 5556
            death = 5555
        }
        aggro {
            radius = 5
        }
/*        slayer {
            assignment = SlayerAssignment.MUMMY
            level = 1
            experience = 30.0
        }*/
    }
}