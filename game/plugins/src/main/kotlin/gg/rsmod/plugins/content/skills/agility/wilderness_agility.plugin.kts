package gg.rsmod.plugins.content.skills.agility

import gg.rsmod.game.model.attr.ADVANCED_BARBARIAN_AGILITY_STAGE
import gg.rsmod.game.model.attr.BARBARIAN_AGILITY_STAGE
import gg.rsmod.game.model.attr.WILDERNESS_AGILITY_STAGE

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */

/**
 * TO-DO:
 * Better animations!
 * Add Fail changes
 */

val CLIMB_ANIMATION = 828
val COMPLETION_BONUS_EXPERIENCE = 499.0
val REWARD = Items.AGILITY_ARENA_TICKET
val REWARD_AMOUNT = 10

fun Player.getWildernessAgilityStage(): Int {
    val lastStage = attr[WILDERNESS_AGILITY_STAGE]
    if (lastStage == null) {
        setWildernessAgilityStage(0)
        return getWildernessAgilityStage()
    }
    return lastStage
}

fun Player.setWildernessAgilityStage(stage: Int) {
    attr[WILDERNESS_AGILITY_STAGE] = stage
}

fun increaseStage(player: Player, newStage: Int) {
    val stage = player.getWildernessAgilityStage()
    if (stage + 1 == newStage)
        player.setWildernessAgilityStage(newStage)
}
on_obj_option(obj = Objs.DOOR_2309, option = "open") {
    if (player.skills.getCurrentLevel(Skills.AGILITY) < 53) {
        player.message("You need Agility level 52 to open the door.")
        return@on_obj_option
    }

    handleDoor(player)
}
fun handleDoor(player: Player) {
    val closedDoor = DynamicObject(id = 2309, type = 0, rot = 3, tile = Tile(x = 2998, z = 3917))
    val door = DynamicObject(id = 2309, type = 0, rot = if (player.tile.z == 3916) 0 else 0, tile = Tile(x = 2998, z = 3917))
    player.lock = LockState.DELAY_ACTIONS
    world.remove(closedDoor)
    player.playSound(Sfx.DOOR_OPEN)
    world.spawn(door)

    player.queue {
        val x = 2998
        val z = if (player.tile.z == 3916) 3917 else 3916
        player.walkTo(tile = Tile(x = x, z = z), detectCollision = false)
        wait(3)
        world.remove(door)
        player.lock = LockState.NONE
        world.spawn(closedDoor)
        player.playSound(Sfx.DOOR_CLOSE)
    }
}

on_obj_option(obj = Objs.OBSTACLE_PIPE_2288, option = "Squeeze-through") {
        val obj = player.getInteractingGameObj()
        if (player.tile.z > obj.tile.z)
            return@on_obj_option
        player.lockingQueue() {
            val pipeStartTile = Tile(obj.tile.x, obj.tile.z - 1)
            if (player.tile != pipeStartTile) {
                val distance = player.tile.getDistance(pipeStartTile)
                player.walkTo(pipeStartTile)
                wait(distance + 4)
                player.faceTile(obj.tile)
            }
            player.filterableMessage("You squeeze into the pipe...")
            player.animate(12457)
            val move = ForcedMovement.of(
                player.tile,
                Tile(obj.tile.x, obj.tile.z + 6),
                clientDuration1 = 10,
                clientDuration2 = 70,
                directionAngle = Direction.NORTH.ordinal,
                lockState = LockState.NONE
            )
            wait(2)
            player.forceMove(this, move)
            wait(2)
            val move2 = ForcedMovement.of(
                player.tile,
                Tile(obj.tile.x, obj.tile.z + 8),
                clientDuration1 = 10,
                clientDuration2 = 70,
                directionAngle = Direction.NORTH.ordinal,
                lockState = LockState.NONE
            )
            player.forceMove(this, move2)
            wait(2)
            player.animate(12458)
            val move3 = ForcedMovement.of(
                player.tile,
                Tile(obj.tile.x, obj.tile.z + 12),
                clientDuration1 = 20,
                clientDuration2 = 70,
                directionAngle = Direction.NORTH.ordinal,
                lockState = LockState.NONE
            )
            player.forceMove(this, move3)
            player.setWildernessAgilityStage(1)
            player.addXp(Skills.AGILITY, 12.5)
        }
}
on_obj_option(obj = Objs.ROPESWING_2283, option = "Swing-on") {
    val destination = Tile(3005, 3958, 0)
    val distance = player.tile.getDistance(destination)
    player.lockingQueue(lockState = LockState.FULL) {
        player.filterableMessage("You swing across...")
        val movement = ForcedMovement.of(player.tile, destination, clientDuration1 = 60, clientDuration2 = 65, directionAngle = 3, lockState = LockState.FULL)
        player.animate(751)
        player.faceTile(destination)
        player.swingRopeSwing(movement)
        wait(distance - 4)
        player.resetRenderAnimation()
        player.filterableMessage("... and make it safely to the other side.")
        player.addXp(Skills.AGILITY, 20.0)
        increaseStage(player, 2)
    }
}
on_obj_option(obj = Objs.STEPPING_STONE_37704, option = "Cross") {
    val obj = player.getInteractingGameObj()
    if (player.tile.z > obj.tile.z)
        return@on_obj_option
    player.lockingQueue() {
        val stoneStartTile = Tile(obj.tile.x + 1, obj.tile.z)
        if (player.tile != stoneStartTile) {
            val distance = player.tile.getDistance(stoneStartTile)
            player.walkTo(stoneStartTile)
            wait(distance + 4)
            player.faceTile(obj.tile)
        }
        player.filterableMessage("You jump on the first stone...")
        player.animate(741)
        val move = ForcedMovement.of(
            player.tile,
            Tile(obj.tile.x, obj.tile.z),
            clientDuration1 = 10,
            clientDuration2 = 50,
            directionAngle = Direction.WEST.ordinal,
            lockState = LockState.NONE
        )
        wait(2)
        player.forceMove(this, move)
        wait(2)
        player.animate(741)
        val move2 = ForcedMovement.of(
            player.tile,
            Tile(obj.tile.x - 1, obj.tile.z),
            clientDuration1 = 10,
            clientDuration2 = 50,
            directionAngle = Direction.WEST.ordinal,
            lockState = LockState.NONE
        )
        player.forceMove(this, move2)
        wait(2)
        player.animate(741)
        val move3 = ForcedMovement.of(
            player.tile,
            Tile(obj.tile.x - 2, obj.tile.z),
            clientDuration1 = 20,
            clientDuration2 = 50,
            directionAngle = Direction.WEST.ordinal,
            lockState = LockState.NONE
        )
        player.forceMove(this, move3)
        wait(2)
        player.animate(741)
        val move4 = ForcedMovement.of(
            player.tile,
            Tile(obj.tile.x - 3, obj.tile.z),
            clientDuration1 = 20,
            clientDuration2 = 50,
            directionAngle = Direction.WEST.ordinal,
            lockState = LockState.NONE
        )
        player.forceMove(this, move4)
        wait(2)
        player.animate(741)
        val move5 = ForcedMovement.of(
            player.tile,
            Tile(obj.tile.x - 4, obj.tile.z),
            clientDuration1 = 20,
            clientDuration2 = 50,
            directionAngle = Direction.WEST.ordinal,
            lockState = LockState.NONE
        )
        player.forceMove(this, move5)
        wait(2)
        player.animate(741)
        val move6 = ForcedMovement.of(
            player.tile,
            Tile(obj.tile.x - 5, obj.tile.z),
            clientDuration1 = 20,
            clientDuration2 = 50,
            directionAngle = Direction.WEST.ordinal,
            lockState = LockState.NONE
        )
        player.forceMove(this, move6)
        increaseStage(player, 3)
        player.addXp(Skills.AGILITY, 20.0)
    }
}
fun Player.swingRopeSwing(movement: ForcedMovement) {
    queue {
        playSound(Sfx.SWING_ACROSS)
        animate(751)
        forceMove(this, movement)
    }
}