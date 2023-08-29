package gg.rsmod.plugins.content.npcs.definitions.demons

import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.plugins.content.drops.DropTableFactory

val ids = intArrayOf(Npcs.IMP, Npcs.IMP_709)

val table = DropTableFactory
val imp = table.build {
    guaranteed {
        obj(Items.IMPIOUS_ASHES)
    }

    main {
        total(1024)
        obj(Items.AIR_TALISMAN, quantity = 1, slots = 36)
        obj(Items.MIND_TALISMAN, quantity = 1, slots = 16)
        obj(Items.WATER_TALISMAN, quantity = 1, slots = 8)
        obj(Items.EARTH_TALISMAN, quantity = 1, slots = 4)
        obj(Items.FIRE_TALISMAN, quantity = 1, slots = 4)
        obj(Items.BODY_TALISMAN, quantity = 1, slots = 4)
        obj(Items.COSMIC_TALISMAN, quantity = 1, slots = 2)
        obj(Items.CHAOS_TALISMAN, quantity = 1, slots = 2)
        obj(Items.NATURE_TALISMAN, quantity = 1, slots = 2)
        obj(Items.LAW_TALISMAN, quantity = 1, slots = 2)
        obj(Items.DEATH_TALISMAN, quantity = 1, slots = 1)
        obj(Items.BLOOD_TALISMAN, quantity = 1, slots = 1)
        obj(Items.PURE_ESSENCE_NOTED, quantity = 3, slots = 8)
        obj(Items.RUNE_ESSENCE, quantity = 5, slots = 36)
        nothing(slots = 128)
    }
}

table.register(imp, *ids)

on_npc_pre_death(*ids) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.IMP_DEATH)
}

on_npc_death(*ids) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

ids.forEach {
    set_combat_def(it) {
        configs {
            attackSpeed = 4
            respawnDelay = 50
            attackStyle = StyleType.SLASH
        }
        stats {
            hitpoints = 80
            attack = 1
            strength = 1
            defence = 1
            magic = 1
            ranged = 1
        }
        bonuses {
            attackStab = -42
            attackCrush = -37
            defenceStab = -42
            defenceSlash = -42
            defenceCrush = -42
            defenceMagic = -42
            defenceRanged = -42
        }
        anims {
            attack = 169
            death = 172
            block = 170
        }
    }
}