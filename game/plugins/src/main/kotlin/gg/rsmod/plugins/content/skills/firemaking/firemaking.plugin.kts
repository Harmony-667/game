package gg.rsmod.plugins.content.skills.firemaking


val firemakingDefinitions = FiremakingData.firemakingDefinitions
val logIds = FiremakingData.values.map { data -> data.raw }.toIntArray()

logIds.forEach {
    val item = it
    on_item_on_item(item1 = Items.TINDERBOX_590, item2 = item) {
        player.interruptQueues()
        player.resetInteractions()
        player.queue {
            val def = firemakingDefinitions[item] ?: return@queue
            FiremakingAction.burnLog(this, def, ground = false)
        }
    }

    on_ground_item_option(it, 2) {
        player.interruptQueues()
        player.resetInteractions()
        player.queue {
            val def = firemakingDefinitions[item] ?: return@queue
            FiremakingAction.burnLog(this, def, ground = true)
        }
    }
}
