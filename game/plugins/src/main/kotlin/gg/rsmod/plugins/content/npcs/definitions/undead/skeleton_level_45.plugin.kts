package gg.rsmod.plugins.content.npcs.definitions.undeads

import gg.rsmod.game.model.combat.SlayerAssignment
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.global.Gems
import gg.rsmod.plugins.content.drops.global.Herbs

val id = Npcs.SKELETON_93

val table = DropTableFactory
val skeleton = table.build {

    guaranteed {
        obj(Items.BONES)
    }
    main {
        total(total = 128)

        nothing(slots = 8)


    }
}

table.register(skeleton, id)

on_npc_pre_death(id) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.SKELETON_DEATH)
}

on_npc_death(id) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

set_combat_def(id) {
    configs {
        attackSpeed = 4
        respawnDelay = 30
    }
    stats {
        hitpoints = 590
        attack = 32
        strength = 35
        defence = 36
    }
    bonuses {
        attackStab = 15
        attackCrush = 14
        defenceStab = 9
        defenceSlash = 11
        defenceCrush = -2
        defenceMagic = 1
        defenceRanged = 4
    }
    anims {
        attack = 5485
        block = 5489
        death = 5491
    }
}