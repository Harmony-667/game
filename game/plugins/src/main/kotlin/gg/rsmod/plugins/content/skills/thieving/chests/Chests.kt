package gg.rsmod.plugins.content.skills.thieving.chests

import gg.rsmod.game.model.entity.DynamicObject
import gg.rsmod.game.model.entity.GameObject
import gg.rsmod.game.model.entity.Player
import gg.rsmod.game.model.queue.QueueTask
import gg.rsmod.plugins.api.Skills
import gg.rsmod.plugins.api.cfg.Objs
import gg.rsmod.plugins.api.cfg.Sfx
import gg.rsmod.plugins.api.ext.filterableMessage
import gg.rsmod.plugins.api.ext.message
import gg.rsmod.plugins.api.ext.playSound
import gg.rsmod.plugins.api.ext.player
import gg.rsmod.plugins.content.combat.isBeingAttacked
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.DropTableType

object Chests {

    private const val waitTime = 2

    suspend fun stealFromChest(task: QueueTask, target: GameObject, targetInfo: ChestTarget) {
        val player = task.player
        if (canSteal(player, target, targetInfo)) {
            player.animate(832)
            player.playSound(Sfx.PICK2)
            task.wait(waitTime)
            handleSuccess(player, target, targetInfo)
        }
    }

    fun handleSuccess(player: Player, target: GameObject, targetInfo: ChestTarget) {
        player.world.let { world ->
            world.queue {
                if (world.isSpawned(target)) {
                    val emptyChest = DynamicObject(target, targetInfo.getEmpty(target.id) ?: Objs.OPEN_CHEST_378)
                    world.remove(target)
                    world.spawn(emptyChest)
                    wait(targetInfo.respawnTicks)
                    world.remove(emptyChest)
                    world.spawn(DynamicObject(target))
                }
            }
            DropTableFactory.createDropInventory(player, target.id, DropTableType.CHEST)
            player.addXp(Skills.THIEVING, targetInfo.xp)
            player.filterableMessage(targetInfo.message)
        }
    }

    private fun canSteal(player: Player, target: GameObject, targetInfo: ChestTarget): Boolean {
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
            player.message("You can't steal from the Chest during combat")
            return false
        }
        return true
    }

}