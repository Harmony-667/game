package gg.rsmod.plugins.content.combat.scripts.impl

import gg.rsmod.game.model.combat.CombatClass
import gg.rsmod.game.model.combat.CombatScript
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.game.model.combat.WeaponStyle
import gg.rsmod.game.model.entity.Player
import gg.rsmod.game.model.queue.QueueTask
import gg.rsmod.plugins.api.EquipmentType
import gg.rsmod.plugins.api.HitType
import gg.rsmod.plugins.api.Skills
import gg.rsmod.plugins.api.cfg.Items
import gg.rsmod.plugins.api.cfg.Npcs
import gg.rsmod.plugins.api.ext.hasEquipped
import gg.rsmod.plugins.api.ext.npc
import gg.rsmod.plugins.api.ext.prepareAttack
import gg.rsmod.plugins.content.combat.*
import gg.rsmod.plugins.content.combat.formula.MeleeCombatFormula

/**
 * @author Eikenb00m <https://github.com/Eikenb00m>
 */
object BlackKnightTitanCombatScript : CombatScript() {

    override val ids = intArrayOf(Npcs.BLACK_KNIGHT_TITAN)

    private val skills = intArrayOf(Skills.ATTACK, Skills.STRENGTH, Skills.DEFENCE, Skills.RANGED, Skills.AGILITY)
    override suspend fun handleSpecialCombat(it: QueueTask) {
        val npc = it.npc
        var target = npc.getCombatTarget() ?: return

        while (npc.canEngageCombat(target)) {
            npc.facePawn(target)
            if (npc.moveToAttackRange(it, target, distance = 1, projectile = false) && npc.isAttackDelayReady()) {
                npc.prepareAttack(CombatClass.MELEE, StyleType.STAB, WeaponStyle.NONE)
                if (target is Player) {
                    val player = target
                    if (!player.hasEquipped(EquipmentType.AMULET, Items.AMULET_OF_FURY)) {
                        npc.animate(npc.combatDef.attackAnimation)
                        npc.dealHit(target = player, maxHit = 11.0, landHit = true, delay = 1, hitType = HitType.MELEE)
                        skills.forEach {
                            val drain = player.skills.getMaxLevel(it) * 0.25
                            player.skills.decrementCurrentLevel(it, drain.toInt(), capped = false)
                        }
                    } else {
                        npc.animate(npc.combatDef.attackAnimation)
                        npc.dealHit(target = target, formula = MeleeCombatFormula, delay = 1, type = HitType.MELEE)
                    }
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