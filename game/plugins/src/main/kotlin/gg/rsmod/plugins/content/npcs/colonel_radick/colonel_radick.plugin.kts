package gg.rsmod.plugins.content.npcs.colonel_radick

val FORCE_CHAT_TIMER = TimerKey()
val DELAY = 10..100

on_global_npc_spawn {
    when (npc.id) {
        Npcs.COLONEL_RADICK -> npc.timers[FORCE_CHAT_TIMER] = world.random(DELAY)
    }
}

on_timer(FORCE_CHAT_TIMER) {
    if (!npc.isAlive())
        return@on_timer
    npc.forceChat("The trolls have the cave!")
    npc.timers[FORCE_CHAT_TIMER] = DELAY.random()
}