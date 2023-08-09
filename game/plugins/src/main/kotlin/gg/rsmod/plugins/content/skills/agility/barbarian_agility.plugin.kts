package gg.rsmod.plugins.content.skills.agility

import gg.rsmod.game.model.attr.ADVANCED_BARBARIAN_AGILITY_STAGE
import gg.rsmod.game.model.attr.BARBARIAN_AGILITY_STAGE

val CLIMB_ANIMATION = 828
val COMPLETION_BONUS_EXPERIENCE = 46.2
val ADVANCED_COMPLETION_BONUS_EXPERIENCE = 615
val REWARD = Items.AGILITY_ARENA_TICKET
val REWARD_AMOUNT = 5
val ADVANCED_REWARD_AMOUNT = 20

fun Player.getBarbarianAgilityStage(): Int {
    val lastStage = attr[BARBARIAN_AGILITY_STAGE]
    if (lastStage == null) {
        setBarbarianAgilityStage(0)
        return getBarbarianAgilityStage()
    }
    return lastStage
}
fun Player.getAdvancedBarbarianAgilityStage(): Int {
    val lastStage = attr[ADVANCED_BARBARIAN_AGILITY_STAGE]
    if (lastStage == null) {
        setAdvancedBarbarianAgilityStage(0)
        return getAdvancedBarbarianAgilityStage()
    }
    return lastStage
}

fun Player.setBarbarianAgilityStage(stage: Int) {
    attr[BARBARIAN_AGILITY_STAGE] = stage
}
fun Player.setAdvancedBarbarianAgilityStage(stage: Int) {
    attr[ADVANCED_BARBARIAN_AGILITY_STAGE] = stage
}

fun increaseStage(player: Player, newStage: Int) {
    val stage = player.getBarbarianAgilityStage()
    if (stage + 1 == newStage)
        player.setBarbarianAgilityStage(newStage)
}
fun increaseAdvancedStage(player: Player, newStage: Int) {
    val stage = player.getAdvancedBarbarianAgilityStage()
    if (stage + 1 == newStage)
        player.setAdvancedBarbarianAgilityStage(newStage)
}
on_obj_option(obj = Objs.ROPE_SWING_43526, option = "Swing-on") {
    val destination = Tile(player.tile.x, 3549, 0)
    val distance = player.tile.getDistance(destination)
    player.lockingQueue(lockState = LockState.FULL) {
        player.filterableMessage("You swing across...")
        val movement = ForcedMovement.of(player.tile, destination, clientDuration1 = 60, clientDuration2 = 65, directionAngle = 3, lockState = LockState.FULL)
        player.animate(751)
        player.faceTile(destination)
        player.swingRope_Swing(movement)
        wait(distance)
        player.resetRenderAnimation()
        player.filterableMessage("... and make it safely to the other side.")
        player.addXp(Skills.AGILITY, 22.0)
        player.setBarbarianAgilityStage(1)
        player.setAdvancedBarbarianAgilityStage(1)
    }
}
on_obj_option(obj = Objs.LOG_BALANCE_43595, option = "Walk-across") {
    val destination = Tile(2541, 3546, 0)
    val distance = player.tile.getDistance(destination)
    player.lockingQueue(lockState = LockState.FULL) {
        player.filterableMessage("You walk carefully across the slippery log...")
        player.walkTo(destination, MovementQueue.StepType.FORCED_WALK, detectCollision = false)
        player.setRenderAnimation(155)
        wait(distance + 2)
        player.resetRenderAnimation()
        player.filterableMessage("... and make it safely to the other side.")
        player.addXp(Skills.AGILITY, 13.7)
        player.setBarbarianAgilityStage(2)
        player.setAdvancedBarbarianAgilityStage(2)
    }
}

fun Player.swingRope_Swing(movement: ForcedMovement) {
    queue {
        //player.stopMovement()
        playSound(Sfx.SWING_ACROSS)
        animate(751)
        forceMove(this, movement)
    }
}