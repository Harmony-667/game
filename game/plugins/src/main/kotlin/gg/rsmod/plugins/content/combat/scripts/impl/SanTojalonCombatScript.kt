package gg.rsmod.plugins.content.combat.scripts.impl

import gg.rsmod.game.model.combat.CombatClass
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.game.model.combat.WeaponStyle
import gg.rsmod.game.model.entity.Player
import gg.rsmod.game.model.queue.QueueTask
import gg.rsmod.plugins.api.HitType
import gg.rsmod.plugins.api.cfg.Npcs
import gg.rsmod.plugins.api.ext.*
import gg.rsmod.plugins.content.combat.*
import gg.rsmod.plugins.content.combat.formula.MeleeCombatFormula
import gg.rsmod.plugins.api.ext.npc
import gg.rsmod.plugins.api.ext.prepareAttack


object SanTojalonCombatScript {

    val ids = intArrayOf(Npcs.SAN_TOJALON)
    suspend fun handleSpecialCombat(it: QueueTask) {
        val npc = it.npc
        var chatExecuted = false
        var target = npc.getCombatTarget() ?: return

        while (npc.canEngageCombat(target)) {
            npc.facePawn(target)
            if (npc.moveToAttackRange(it, target, distance = 1, projectile = false) && npc.isAttackDelayReady()) {
                if (!chatExecuted) {
                    npc.forceChat("Prepare to Die!")
                    chatExecuted = true
                }
                npc.prepareAttack(CombatClass.MELEE, StyleType.SLASH, WeaponStyle.ACCURATE)
                npc.animate(npc.combatDef.attackAnimation)
                npc.dealHit(target = target, formula = MeleeCombatFormula, delay = 1, type = HitType.MELEE)
                npc.postAttackLogic(target)
            }
            if (npc.moveToAttackRange(it, target, distance = 1, projectile = false) && npc.isAttackDelayReady()) {
                npc.prepareAttack(CombatClass.MAGIC, StyleType.MAGIC_MELEE, WeaponStyle.ACCURATE)
                if (target is Player) {
                    val player = target
                        npc.animate(npc.combatDef.attackAnimation)
                        npc.dealHit(target = target, formula = MeleeCombatFormula, delay = 1, type = HitType.MAGIC)
                }
                npc.postAttackLogic(target)
            }
            it.wait(4)
            target = npc.getCombatTarget() ?: break
        }
        npc.resetFacePawn()
        npc.removeCombatTarget()
    }
}