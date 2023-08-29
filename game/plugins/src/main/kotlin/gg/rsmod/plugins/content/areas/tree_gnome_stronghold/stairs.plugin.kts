package gg.rsmod.plugins.content.areas.tree_gnome_stronghold
/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */
on_obj_option(obj = Objs.STAIRCASE_1744, option = "Climb-Down", lineOfSightDistance = -1) {
    if (pawn.tile.x == 2445 && pawn.tile.z == 3433 && pawn.tile.height == 1) {
        player.handleLadder(x = 2444, z = 3434, height = 0) //Bank
    }
    else if (pawn.tile.x == 2445 && pawn.tile.z == 3416 && pawn.tile.height == 1) {
        player.handleLadder(x = 2445, z = 3416, height = 0) //Bank
    }
    else if (pawn.tile.x == 2457 && pawn.tile.z == 3417 && pawn.tile.height == 1) {
        player.handleLadder(x = 2457, z = 3417, height = 0) //Gnome Statue
    }
    else if (pawn.tile.x == 2460 && pawn.tile.z == 3417 && pawn.tile.height == 1) {
        player.handleLadder(x = 2460, z = 3417, height = 0) //Gnome statue
    }
    else if (pawn.tile.x == 2475 && pawn.tile.z == 3399 && pawn.tile.height == 1) {
        player.handleLadder(x = 2474, z = 3400, height = 0) //Spinning wheel south west
    }
    else if (pawn.tile.x == 2479 && pawn.tile.z == 3407 && pawn.tile.height == 1) {
        player.handleLadder(x = 2478, z = 3407, height = 0)
    }
    else if (pawn.tile.x == 2489 && pawn.tile.z == 3409 && pawn.tile.height == 1) {
        player.handleLadder(x = 2487, z = 3407, height = 0) //Spinning wheel north east
    }
    else if (pawn.tile.x == 2485 && pawn.tile.z == 3401 && pawn.tile.height == 1) {
        player.handleLadder(x = 2484, z = 3402, height = 0) //Spinning wheel south east
    }
    else if (pawn.tile.x == 2440 && pawn.tile.z == 3403 && pawn.tile.height == 1) {
        player.handleLadder(x = 2439, z = 3404, height = 0)
    }
    else if (pawn.tile.x == 2418 && pawn.tile.z == 3416 && pawn.tile.height == 1) {
        player.handleLadder(x = 2417, z = 3417, height = 0)
    }
    else if (pawn.tile.x == 2415 && pawn.tile.z == 3446 && pawn.tile.height == 1) {
        player.handleLadder(x = 2415, z = 3445, height = 0)
    }
    else if (pawn.tile.x == 2400 && pawn.tile.z == 3450 && pawn.tile.height == 1) {
        player.handleLadder(x = 2401, z = 3448, height = 0)
    }
    else if (pawn.tile.x == 2396 && pawn.tile.z == 3476 && pawn.tile.height == 1) {
        player.handleLadder(x = 2394, z = 3474, height = 0)
    }
    else if (pawn.tile.x == 2421 && pawn.tile.z == 3473 && pawn.tile.height == 1) {
        player.handleLadder(x = 2419, z = 3471, height = 0)
    }
    else if (pawn.tile.x == 2412 && pawn.tile.z == 3489 && pawn.tile.height == 1) {
        player.handleLadder(x = 2412, z = 3488, height = 0)
    }
    else if (pawn.tile.x == 2418 && pawn.tile.z == 3492 && pawn.tile.height == 1) {
        player.handleLadder(x = 2416, z = 3490, height = 0)
    }
    else if (pawn.tile.x == 2396 && pawn.tile.z == 3500 && pawn.tile.height == 1) {
        player.handleLadder(x = 2395, z = 3501, height = 0)
    }
    else if (pawn.tile.x == 2383 && pawn.tile.z == 3506 && pawn.tile.height == 1) {
        player.handleLadder(x = 2383, z = 3506, height = 0)
    }
    else if (pawn.tile.x == 2388 && pawn.tile.z == 3513 && pawn.tile.height == 1) {
        player.handleLadder(x = 2388, z = 3512, height = 0)
    }
    else if (pawn.tile.x == 2397 && pawn.tile.z == 3513 && pawn.tile.height == 1) {
        player.handleLadder(x = 2397, z = 3512, height = 0)
    }
    else {
        player.message("This Stairs is not Working! Post it on Discord!")
    }
}
on_obj_option(obj = Objs.STAIRCASE_1742, option = "Climb-Up", lineOfSightDistance = 1) {
    if (pawn.tile.x == 2444 && pawn.tile.z == 3434 && pawn.tile.height == 0) {
        player.handleLadder(x = 2445, z = 3433, height = 1) //Bank
    }
    else if (pawn.tile.x == 2443 && pawn.tile.z == 3414 && pawn.tile.height == 0) {
        player.handleLadder(x = 2445, z = 3416, height = 1) //Bank
    }
    else if (pawn.tile.x == 2455 && pawn.tile.z == 3416 && pawn.tile.height == 0) {
        player.handleLadder(x = 2457, z = 3417, height = 1) //Gnome statue
    }
    else if (pawn.tile.x == 2460 && pawn.tile.z == 3416 && pawn.tile.height == 0) {
        player.handleLadder(x = 2460, z = 3417, height = 1) // Gnome statue
    }
    else if (pawn.tile.x == 2474 && pawn.tile.z == 3400 && pawn.tile.height == 0) {
        player.handleLadder(x = 2475, z = 3399, height = 1) // Spinning Wheel south west
    }
    else if (pawn.tile.x == 2478 && pawn.tile.z == 3408 && pawn.tile.height == 0) {
        player.handleLadder(x = 2479, z = 3407, height = 1)
    }
    else if (pawn.tile.x == 2487 && pawn.tile.z == 3407 && pawn.tile.height == 0) {
        player.handleLadder(x = 2489, z = 3409, height = 1) //Sping wheel north east
    }
    else if (pawn.tile.x == 2484 && pawn.tile.z == 3402 && pawn.tile.height == 0) {
        player.handleLadder(x = 2485, z = 3401, height = 1) //Sping wheel south east
    }
    else if (pawn.tile.x == 2439 && pawn.tile.z == 3404 && pawn.tile.height == 0) {
        player.handleLadder(x = 2440, z = 3403, height = 1)
    }
    else if (pawn.tile.x == 2417 && pawn.tile.z == 3417 && pawn.tile.height == 0) {
        player.handleLadder(x = 2418, z = 3416, height = 1)
    }
    else if (pawn.tile.x == 2415 && pawn.tile.z == 3445 && pawn.tile.height == 0) {
        player.handleLadder(x = 2415, z = 3446, height = 1)
    }
    else if (pawn.tile.x == 2401 && pawn.tile.z == 3448 && pawn.tile.height == 0) {
        player.handleLadder(x = 2400, z = 3450, height = 1)
    }
    else if (pawn.tile.x == 2394 && pawn.tile.z == 3474 && pawn.tile.height == 0) {
        player.handleLadder(x = 2396, z = 3476, height = 1)
    }
    else if (pawn.tile.x == 2419 && pawn.tile.z == 3471 && pawn.tile.height == 0) {
        player.handleLadder(x = 2421, z = 3473, height = 1)
    }
    else if (pawn.tile.x == 2412 && pawn.tile.z == 3488 && pawn.tile.height == 0) {
        player.handleLadder(x = 2412, z = 3489, height = 1)
    }
    else if (pawn.tile.x == 2416 && pawn.tile.z == 3490 && pawn.tile.height == 0) {
        player.handleLadder(x = 2418, z = 3492, height = 1)
    }
    else if (pawn.tile.x == 2395 && pawn.tile.z == 3501 && pawn.tile.height == 0) {
        player.handleLadder(x = 2396, z = 3500, height = 1)
    }
    else if (pawn.tile.x == 2383 && pawn.tile.z == 3506 && pawn.tile.height == 0) {
        player.handleLadder(x = 2383, z = 3506, height = 1)
    }
    else if (pawn.tile.x == 2388 && pawn.tile.z == 3512 && pawn.tile.height == 0) {
        player.handleLadder(x = 2388, z = 3513, height = 1)
    }
    else if (pawn.tile.x == 2397 && pawn.tile.z == 3512 && pawn.tile.height == 0) {
        player.handleLadder(x = 2397, z = 3513, height = 1)
    }
    else {
        player.message("This Stairs is not Working! Post it on Discord!")
    }
}

