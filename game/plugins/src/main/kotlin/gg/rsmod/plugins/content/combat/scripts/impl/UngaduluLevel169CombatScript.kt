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
import gg.rsmod.plugins.api.cfg.Sfx
import gg.rsmod.plugins.api.ext.*
import gg.rsmod.plugins.content.combat.*
import gg.rsmod.plugins.content.combat.formula.MagicCombatFormula
import gg.rsmod.plugins.content.combat.strategy.MagicCombatStrategy

object UngaduluLevel169CombatScript {

    val ids = intArrayOf(Npcs.UNGADULU_930)
    private val skills = intArrayOf(Skills.ATTACK, Skills.STRENGTH, Skills.DEFENCE, Skills.PRAYER)
    suspend fun handleSpecialCombat(it: QueueTask) {
        val npc = it.npc
        var target = npc.getCombatTarget() ?: return

        while (npc.canEngageCombat(target)) {
            npc.facePawn(target)
            if (npc.moveToAttackRange(it, target, distance = 8, projectile = true) && npc.isAttackDelayReady()) {
                npc.prepareAttack(CombatClass.MAGIC, StyleType.MAGIC, WeaponStyle.NONE)
                if (target is Player) {
                    val player = target
                    val protectionItems = listOf("DRUIDIC_MAGE_HOOD_0", "DRUIDIC_MAGE_TOP_0", "DRUIDIC_MAGE_BOTTOM_0")
                    val UNGADULU_SPELL = npc.createProjectile(player, 2716, type = ProjectileType.MAGIC)
                    val UNGADULU_SPELL_HIT_GFX = Graphic(2726, 110, UNGADULU_SPELL.lifespan)
                    if (!player.hasEquippedWithName(protectionItems.toTypedArray())) {
                        npc.animate(id = 419, priority = true)
                        player.world.spawn(UNGADULU_SPELL)
                        player.graphic(UNGADULU_SPELL_HIT_GFX)
                        val hitDelay = MagicCombatStrategy.getHitDelay(npc.getCentreTile(), target.getCentreTile())
                        npc.animate(id = 419, priority = true)
                        if (target is Player) target.playSound(Sfx.EARTHWAVE_CAST_AND_FIRE, delay = 2)
                        npc.dealHit(target = player, minHit = 2.00, maxHit = 35.00, landHit = true, delay = hitDelay, hitType = HitType.CRIT_MAGIC)
                    } else {
                        npc.animate(id = 419, priority = true)
                        player.world.spawn(UNGADULU_SPELL)
                        player.graphic(UNGADULU_SPELL_HIT_GFX)
                        val hitDelay = MagicCombatStrategy.getHitDelay(npc.getCentreTile(), target.getCentreTile())
                        if (target is Player) target.playSound(Sfx.EARTHWAVE_CAST_AND_FIRE, delay = 2)
                        npc.dealHit(target = target, formula = MagicCombatFormula, delay = hitDelay, type = HitType.MAGIC)
                        skills.forEach {
                            val drain = player.skills.getMaxLevel(it) * 0.45
                            player.skills.decrementCurrentLevel(it, drain.toInt(), capped = false)
                        }
                    }
                }
                npc.postAttackLogic(target)
            }
            it.wait(3)
            target = npc.getCombatTarget() ?: break
        }
        npc.resetFacePawn()
        npc.removeCombatTarget()
    }
}