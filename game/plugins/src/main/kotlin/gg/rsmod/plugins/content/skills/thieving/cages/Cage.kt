package gg.rsmod.plugins.content.skills.thieving.cages

import gg.rsmod.game.model.entity.DynamicObject
import gg.rsmod.game.model.entity.GameObject
import gg.rsmod.game.model.entity.Npc
import gg.rsmod.game.model.entity.Player
import gg.rsmod.game.model.queue.QueueTask
import gg.rsmod.plugins.api.Skills
import gg.rsmod.plugins.api.cfg.Npcs
import gg.rsmod.plugins.api.cfg.Objs
import gg.rsmod.plugins.api.cfg.Sfx
import gg.rsmod.plugins.api.ext.filterableMessage
import gg.rsmod.plugins.api.ext.message
import gg.rsmod.plugins.api.ext.playSound
import gg.rsmod.plugins.api.ext.player
import gg.rsmod.plugins.content.combat.isAttacking
import gg.rsmod.plugins.content.combat.isBeingAttacked
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.DropTableType


object Cage {

    private const val waitTime = 2

    val CageGuards = mutableListOf<Npc>()

    suspend fun stealFromCage(task: QueueTask, target: GameObject, targetInfo: CageTarget) {
        val player = task.player
        if (canSteal(player, target, targetInfo) && !caughtByGuard(player, targetInfo)) {
            player.animate(832)
            player.playSound(Sfx.PICK2)
            task.wait(waitTime)
            handleSuccess(player, target, targetInfo)
        }
    }

    fun handleSuccess(player: Player, target: GameObject, targetInfo: CageTarget) {
        player.world.let { world ->
            world.queue {
                if (world.isSpawned(target)) {
                    val emptyCage = DynamicObject(target, targetInfo.getEmpty(target.id) ?: Objs.CAGE)
                    world.remove(target)
                    world.spawn(emptyCage)
                    wait(targetInfo.respawnTicks)
                    world.remove(emptyCage)
                    world.spawn(DynamicObject(target))
                }
            }
            DropTableFactory.createDropInventory(player, target.id, DropTableType.CAGE)
            player.addXp(Skills.THIEVING, targetInfo.xp)
            player.filterableMessage(targetInfo.message)
        }
    }

    private fun caughtByGuard(player: Player, targetInfo: CageTarget): Boolean {
        val guard = CageGuards
                .filter { it.id in targetInfo.guards && !it.isAttacking() && !it.invisible && it.sees(player, 3) }
                .minByOrNull { it.tile.getDistance(player.tile) }
        return if (guard == null) {
            false
        } else {
            guard.attack(player)
            guard.forceChat("Hey! Get your hands off there!")
            true
        }
    }

    private fun canSteal(player: Player, target: GameObject, targetInfo: CageTarget): Boolean {
        if (!player.world.isSpawned(target)) {
            return false
        }
        if (player.skills.getCurrentLevel(Skills.THIEVING) < targetInfo.level) {
            player.message("You need a Thieving level of ${targetInfo.level} to do that.")
            return false
        }
        if (!targetInfo.hasInventorySpace(player)) {
            player.message("You don't have enough inventory space to do that.")
            return false
        }
        if (player.isBeingAttacked()) {
            player.message("You can't steal from the Cage during combat")
            return false
        }
        return true
    }

}