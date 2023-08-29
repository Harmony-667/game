import gg.rsmod.game.model.Graphic
import gg.rsmod.game.model.combat.CombatClass
import gg.rsmod.game.model.combat.StyleType
import gg.rsmod.game.model.combat.WeaponStyle
import gg.rsmod.game.model.entity.Player
import gg.rsmod.game.model.queue.QueueTask
import gg.rsmod.plugins.api.HitType
import gg.rsmod.plugins.api.ProjectileType
import gg.rsmod.plugins.api.Skills
import gg.rsmod.plugins.api.cfg.Npcs
import gg.rsmod.plugins.api.ext.*
import gg.rsmod.plugins.content.combat.*
import gg.rsmod.plugins.content.combat.formula.MagicCombatFormula
import gg.rsmod.plugins.content.combat.strategy.MagicCombatStrategy

object NezikchendCombatScript {

    val ids = intArrayOf(Npcs.NEZIKCHENED)
    private val skills = intArrayOf(Skills.ATTACK, Skills.STRENGTH, Skills.DEFENCE)
    suspend fun handleSpecialCombat(it: QueueTask) {
        val npc = it.npc
        var target = npc.getCombatTarget() ?: return

        while (npc.canEngageCombat(target)) {
            npc.facePawn(target)
            if (npc.moveToAttackRange(it, target, distance = 8, projectile = true) && npc.isAttackDelayReady()) {
                npc.prepareAttack(CombatClass.MAGIC, StyleType.MAGIC, WeaponStyle.NONE)
                if (target is Player) {
                    val player = target
                    val protectionItems = listOf("INFINITY_TOP", "INIFINTYHAT","INFINITY_BOTTOMS")
                    val NEZIKCHEND_SPELL = npc.createProjectile(player, 2734, type = ProjectileType.MAGIC)
                    val NEZIKCHEND_SPELL_HIT_GFX = Graphic(2740, 40, NEZIKCHEND_SPELL.lifespan)
                    if (!player.hasEquippedWithName(protectionItems.toTypedArray())) {
                        npc.animate(npc.combatDef.attackAnimation)
                        player.world.spawn(NEZIKCHEND_SPELL)
                        player.graphic(NEZIKCHEND_SPELL_HIT_GFX)
                        val hitDelay = MagicCombatStrategy.getHitDelay(npc.getCentreTile(), target.getCentreTile())
                        npc.dealHit(target = player, minHit = 1.00, maxHit = 15.00, landHit = true, delay = hitDelay, hitType = HitType.CRIT_MAGIC)
                    } else {
                        npc.animate(npc.combatDef.attackAnimation)
                        player.world.spawn(NEZIKCHEND_SPELL)
                        player.graphic(NEZIKCHEND_SPELL_HIT_GFX)
                        val hitDelay = MagicCombatStrategy.getHitDelay(npc.getCentreTile(), target.getCentreTile())
                        npc.dealHit(target = target, formula = MagicCombatFormula, delay = hitDelay, type = HitType.MAGIC)
                        skills.forEach {
                            val drain = player.skills.getMaxLevel(it) * 0.25
                            player.skills.decrementCurrentLevel(it, drain.toInt(), capped = false)
                        }
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