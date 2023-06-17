package gg.rsmod.plugins.content.npcs.sage

val FORCE_CHAT_TIMER = TimerKey()
val DELAY = 10..100

on_global_npc_spawn {
    when (npc.id) {
        Npcs.LUMBRIDGE_SAGE, Npcs.LUMBRIDGE_SAGE_3393, Npcs.LUMBRIDGE_SAGE_7929 -> npc.timers[FORCE_CHAT_TIMER] = world.random(DELAY)
    }
}

on_timer(FORCE_CHAT_TIMER) {
    if (!npc.isAlive())
        return@on_timer
    npc.forceChat("Welcome to Project Harmony! Ask me for any help!")
    npc.timers[FORCE_CHAT_TIMER] = DELAY.random()
}