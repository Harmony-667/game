package gg.rsmod.plugins.content.npcs.definitions.animals

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(Npcs.HELLHOUND)

val table = DropTableFactory
val hellhound = table.build {
    guaranteed {
        obj(Items.BONES, quantity = 1)
    }
    main {
        total(1024)
        obj(Items.ZAMORAK_FULL_HELM, quantity = 1, slots = 2)
        obj(Items.ZAMORAK_KITESHIELD, quantity = 1, slots = 2)
        obj(Items.ZAMORAK_PLATEBODY, quantity = 1, slots = 2)
        obj(Items.ZAMORAK_PLATELEGS, quantity = 1, slots = 2)
        obj(Items.ZAMORAK_PLATESKIRT, quantity = 1, slots = 2)
        obj(Items.GUTHIX_FULL_HELM, quantity = 1, slots = 2)
        obj(Items.GUTHIX_KITESHIELD, quantity = 1, slots = 2)
        obj(Items.GUTHIX_PLATEBODY, quantity = 1, slots = 2)
        obj(Items.GUTHIX_PLATELEGS, quantity = 1, slots = 2)
        obj(Items.GUTHIX_PLATESKIRT, quantity = 1, slots = 2)
        obj(Items.SARADOMIN_FULL_HELM, quantity = 1, slots = 2)
        obj(Items.SARADOMIN_KITESHIELD, quantity = 1, slots = 2)
        obj(Items.SARADOMIN_PLATEBODY, quantity = 1, slots = 2)
        obj(Items.SARADOMIN_PLATELEGS, quantity = 1, slots = 2)
        obj(Items.SARADOMIN_PLATESKIRT, quantity = 1, slots = 2)
        nothing(slots = 256)
    }

}

table.register(hellhound, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.SKELETAL_HELLHOUND_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 89
        }
        stats {
            hitpoints = 1160
            attack = 105
            strength = 104
            defence = 102
            magic = 1
            ranged = 1
        }
        anims {
            attack = 6562
            death = 6564
            block = 6563
        }
        aggro {
            radius = 4
        }
    }
}