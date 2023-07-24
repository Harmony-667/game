package gg.rsmod.plugins.content.items.books

/**
 * @author Eikenb00m <https://github.com/Eikenb00m>
 */
val multiplier = 4

on_item_option(item = Items.AGILITY_TOME, option = "read") {
    if (player.inventory.remove(Items.AGILITY_TOME).hasSucceeded()) {
        val experience = world.randomDouble(1100.0..1650.0) * multiplier
        player.addXp(skill = Skills.AGILITY, xp = experience, modifiers = false)
        player.queue {
            doubleMessageBox(
                "Your have read the book, and gain some knowledge!",
                "You have been awarded ${experience.toInt()} Agility experience!"
            )
        }
    }
}
on_item_option(item = Items.AGILITY_TOME_7783, option = "read") {
    if (player.inventory.remove(Items.AGILITY_TOME_7783).hasSucceeded()) {
        val experience = world.randomDouble(2035.0..3025.0) * multiplier
        player.addXp(skill = Skills.AGILITY, xp = experience, modifiers = false)
        player.queue {
            doubleMessageBox(
                "Your have read the book, and gain some knowledge!",
                "You have been awarded ${experience.toInt()} Agility experience!"
            )
        }
    }
}
on_item_option(item = Items.AGILITY_TOME_7784, option = "read") {
    if (player.inventory.remove(Items.AGILITY_TOME_7784).hasSucceeded()) {
        val experience = world.randomDouble(4015.0..5005.0) * multiplier

        player.addXp(skill = Skills.AGILITY, xp = experience, modifiers = false)
        player.queue {
            doubleMessageBox(
                "Your have read the book, and gain some knowledge!",
                "You have been awarded ${experience.toInt()} Agility experience!"
            )
        }
    }
}