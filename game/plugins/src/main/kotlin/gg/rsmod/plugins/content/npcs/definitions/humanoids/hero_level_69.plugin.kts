package gg.rsmod.plugins.content.npcs.definitions.humanoids

import gg.rsmod.plugins.content.drops.DropTableFactory


val NPC_ID = Npcs.HERO

val table = DropTableFactory
val hero = table.build {
    guaranteed {
        obj(Items.BONES)
    }
    main {
        total(1024)
        nothing(slots = 64)

    }

}

table.register(hero, NPC_ID)

on_npc_pre_death(NPC_ID) {
    val p = npc.damageMap.getMostDamage()!! as Player
    p.playSound(Sfx.HUMAN_DEATH)
}

on_npc_death(NPC_ID) {
    table.getDrop(world, npc.damageMap.getMostDamage()!! as Player, npc.id, npc.tile)
}

set_combat_def(npc = NPC_ID) {
    configs {
        attackSpeed = 5
        respawnDelay = 30
    }
    stats {
        hitpoints = 820
        attack = 54
        strength = 55
        defence = 44
    }
    bonuses {
        attackBonus = 20
        strengthBonus = 22
        defenceStab = 87
        defenceSlash = 84
        defenceCrush = 76
        defenceMagic = -10
        defenceRanged = 79
    }
    anims {
        attack = 390
        block = 388
        death = 836
    }
}
