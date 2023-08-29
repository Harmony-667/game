package gg.rsmod.plugins.content.items

import gg.rsmod.game.model.attr.RANDOM_EVENT_GIFT_SLOT
import gg.rsmod.plugins.content.combat.isAttacking
import gg.rsmod.plugins.content.combat.isBeingAttacked
import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.DropTableType
import gg.rsmod.util.Misc

/**
 * @author Alycia <https://github.com/alycii>
 */

// Slot definitions for random event gift items
val cashSlot = 0
val runeSlot = 1
val coalSlot = 2
val essenceSlot = 3
val oreSlot = 4
val barSlot = 5
val gemSlot = 6
val herbSlot = 7
val seedSlot = 8
//val charmSlot = 9
val xpItemSlot = 10
val otherSlot = 24
val surpriseSlot = 25
val emoteSlot = 26

/**
 * Handles the "open" option for the random event gift (item id 14664).
 */
on_item_option(item = Items.RANDOM_EVENT_GIFT_14664, option = "open") {

    if(player.isBeingAttacked() || player.isAttacking()) {
        player.message("You won't be able to choose a reward during combat.")
        return@on_item_option
    }

    if(player.randomEventGift.isEmpty) {
        // Constant for drop offset value
        val dropOffset = 14664

        // Filler tables
        for (slot in 1..9) {
            if (slot == essenceSlot || slot == coalSlot) {
                continue
            }
            val drop = DropTableFactory.getDrop(player, dropOffset + slot, DropTableType.BOX) ?: continue
            player.randomEventGift.add(item = drop[0], beginSlot = slot)
        }

        // Add the experience item
        player.randomEventGift.add(item = Item(id = Items.LAMP), beginSlot = xpItemSlot)

        // Add the other slot
        val other = DropTableFactory.getDrop(player, 14675, DropTableType.BOX) ?: return@on_item_option
        player.randomEventGift.add(item = other[0], beginSlot = otherSlot)

        // Add mystery box
        player.randomEventGift.add(item = Item(Items.MYSTERY_BOX), beginSlot = surpriseSlot)

        // Add essence based on player's mining level
        if (player.skills.getMaxLevel(Skills.MINING) >= 30) {
            player.randomEventGift.add(
                item = Item(id = Items.PURE_ESSENCE, amount = world.random(180..460)),
                beginSlot = essenceSlot
            )
        } else {
            player.randomEventGift.add(
                item = Item(id = Items.RUNE_ESSENCE, amount = world.random(140..210)),
                beginSlot = essenceSlot
            )
        }

        // Add coins based on player's total level
        player.randomEventGift.add(
            item = Item(
                id = Items.COINS_995,
                amount = (player.skills.calculateTotalLevel * 0.330).toInt()
            ), beginSlot = cashSlot
        )

        // Add coal
        player.randomEventGift.add(item = Item(id = Items.COAL, amount = world.random(30..300)), beginSlot = coalSlot)
    }

    // Open the interface
    player.openInterface(interfaceId = 202, dest = InterfaceDestination.MAIN_SCREEN)
    player.setEvents(interfaceId = 202, component = 15, to = player.randomEventGift.rawItems.size * 8, setting = 2)
    player.setEvents(interfaceId = 202, component = 26, to = player.randomEventGift.rawItems.size * 8, setting = 2)

}

// confirm button
on_button(interfaceId = 202, component = 26) {
    val slot = player.attr[RANDOM_EVENT_GIFT_SLOT] ?: return@on_button
    val item = player.randomEventGift[slot]?.toNoted(world.definitions) ?: return@on_button

    if(player.inventory.remove(Items.RANDOM_EVENT_GIFT_14664).hasSucceeded()) {
        player.closeInterface(dest = InterfaceDestination.MAIN_SCREEN)
        if(slot == xpItemSlot) {
            val genie = Npc(Npcs.GENIE, player.findWesternTile(), world)
            world.spawn(genie)
            player.queue {
                player.lock()
                player.facePawn(genie)
                genie.facePawn(player)
                genie.graphic(74)
                wait(2)
                genie.animate(863)
                genie.forceChat("Greetings, ${Misc.formatForDisplay(player.username)}! Enjoy your gift.")
                player.inventory.add(item)
                wait(3)
                world.spawn(TileGraphic(genie.tile, height = 0, id = 74))
                player.unlock()
                world.remove(genie)
            }
        } else {
            player.inventory.add(item)
            player.filterableMessage("Enjoy your gift.")
            player.randomEventGift.removeAll()
        }
    }
}

// item selection
on_button(interfaceId = 202, component = 15) {
    val slot = player.getInteractingSlot() / 7
    player.attr[RANDOM_EVENT_GIFT_SLOT] = slot
}


val charmTable = DropTableFactory.build {
    main {
        total(4)
        obj(Items.GOLD_CHARM, quantity = 25, slots = 1)
        obj(Items.GREEN_CHARM, quantity = 20, slots = 1)
        obj(Items.CRIMSON_CHARM, quantity = 15, slots = 1)
        obj(Items.BLUE_CHARM, quantity = 10, slots = 1)
    }
}
//DropTableFactory.register(charmTable, 14673, type = DropTableType.BOX)


val seedTable = DropTableFactory.build {
    main {
        total(7)
        obj(Items.BARLEY_SEED, quantity = 80, slots = 1)
        obj(Items.SWEETCORN_SEED, quantity = world.random(20..40), slots = 1)
        obj(Items.YANILLIAN_SEED, quantity = 40, slots = 1)
        obj(Items.JUTE_SEED, quantity = 60, slots = 1)
        obj(Items.SNAPDRAGON_SEED, quantity = 10, slots = 1)
        obj(Items.POTATO_SEED, quantity = 80, slots = 1)
        obj(Items.MARIGOLD_SEED, quantity = 10, slots = 1)
    }
}
DropTableFactory.register(seedTable, 14672, type = DropTableType.BOX)

val runeTable = DropTableFactory.build {
    main {
        total(11)
        obj(Items.DEATH_RUNE, quantity = 230, slots = 1)
        obj(Items.CHAOS_RUNE, quantity = world.random(170..260), slots = 1)
        obj(Items.MIND_RUNE, quantity = world.random(170..230), slots = 1)
        obj(Items.BODY_RUNE, quantity = 260, slots = 1)
        obj(Items.NATURE_RUNE, quantity = world.random(170..200), slots = 1)
        obj(Items.COSMIC_RUNE, quantity = world.random(200..230), slots = 1)
        obj(Items.LAW_RUNE, quantity = 200, slots = 1)
        obj(Items.FIRE_RUNE, quantity = world.random(170..260), slots = 1)
        obj(Items.EARTH_RUNE, quantity = world.random(200..400), slots = 1)
        obj(Items.AIR_RUNE, quantity = 260, slots = 1)
        obj(Items.WATER_RUNE, quantity = world.random(200..260), slots = 1)
    }
}
DropTableFactory.register(runeTable, 14665, type = DropTableType.BOX)


val herbTable = DropTableFactory.build {
    main {
        total(11)
        obj(Items.GRIMY_AVANTOE, quantity = world.random(20..40), slots = 1)
        obj(Items.GRIMY_CADANTINE, quantity = 30, slots = 1)
        obj(Items.GRIMY_DWARF_WEED, quantity = 20, slots = 1)
        obj(Items.GRIMY_HARRALANDER, quantity = world.random(30..40), slots = 1)
        obj(Items.GRIMY_IRIT, quantity = world.random(20..40), slots = 1)
        obj(Items.GRIMY_KWUARM, quantity = world.random(20..30), slots = 1)
        obj(Items.GRIMY_LANTADYME, quantity = world.random(10..20), slots = 1)
        obj(Items.GRIMY_RANARR, quantity = world.random(10..20), slots = 1)
        obj(Items.GRIMY_SNAPDRAGON, quantity = world.random(10..30), slots = 1)
        obj(Items.GRIMY_TOADFLAX, quantity = world.random(20..30), slots = 1)
        obj(Items.GRIMY_TARROMIN, quantity = world.random(10..40), slots = 1)
    }
}
DropTableFactory.register(herbTable, 14671, type = DropTableType.BOX)

val barTable = DropTableFactory.build {
    main {
        total(7)
        obj(Items.BRONZE_BAR, quantity = world.random(10..100), slots = 1)
        obj(Items.IRON_BAR, quantity = world.random(20..80), slots = 1)
        obj(Items.SILVER_BAR, quantity = world.random(20..70), slots = 1)
        obj(Items.STEEL_BAR, quantity = world.random(30..70), slots = 1)
        obj(Items.GOLD_BAR, quantity = world.random(5..100), slots = 1)
        obj(Items.MITHRIL_BAR, quantity = world.random(10..60), slots = 1)
        obj(Items.ADAMANT_BAR, quantity = world.random(10..60), slots = 1)
        obj(Items.RUNE_BAR, quantity = world.random(1..25), slots = 1)
    }
}
DropTableFactory.register(barTable, 14669, type = DropTableType.BOX)

val oreTable = DropTableFactory.build {
    main {
        total(8)
        obj(Items.CLAY, quantity = world.random(10..250), slots = 1)
        obj(Items.SOFT_CLAY, quantity = world.random(70..150), slots = 1)
        obj(Items.GOLD_ORE, quantity = world.random(6..100), slots = 1)
        obj(Items.MITHRIL_ORE, quantity = world.random(5..70), slots = 1)
        obj(Items.ADAMANTITE_ORE, quantity = world.random(4..50), slots = 1)
        obj(Items.COPPER_ORE, quantity = world.random(1..250), slots = 1)
        obj(Items.IRON_ORE, quantity = world.random(1..250), slots = 1)
        obj(Items.RUNITE_ORE, quantity = world.random(1..25), slots = 1)
    }
}
DropTableFactory.register(oreTable, 14668, type = DropTableType.BOX)

val gemTable = DropTableFactory.build {
    main {
        total(8)
        obj(Items.UNCUT_SAPPHIRE, quantity = world.random(1..60), slots = 1)
        obj(Items.UNCUT_EMERALD, quantity = world.random(1..20), slots = 1)
        obj(Items.UNCUT_RUBY, quantity = world.random(1..20), slots = 1)
        obj(Items.UNCUT_DIAMOND, quantity = world.random(1..30), slots = 1)
        obj(Items.SAPPHIRE, quantity = world.random(1..30), slots = 1)
        obj(Items.EMERALD, quantity = world.random(1..20), slots = 1)
        obj(Items.RUBY, quantity = world.random(1..20), slots = 1)
        obj(Items.DIAMOND, quantity = world.random(1..20), slots = 1)
    }
}
DropTableFactory.register(gemTable, 14670, type = DropTableType.BOX)

val otherTable = DropTableFactory.build {
    main {
        total(5)
        obj(Items.FLAX, quantity = world.random(640..990), slots = 1)
        obj(Items.STEEL_PLATEBODY, quantity = 1, slots = 1)
        obj(Items.FLIER, quantity = 1, slots = 1)
        obj(Items.CABBAGE, quantity = 1, slots = 1)
        obj(Items.BUCKET, quantity = 1, slots = 1)
    }
}
DropTableFactory.register(otherTable, 14675, type = DropTableType.BOX)


/**
 * Note from Ally:
 * This is used primarily for holidays, or injecting special values into
 * the random events. As we don't currently have a holiday event system
 * (as of April 21st, 2023), this is how we are distributing holiday items.
 */
val specialTable = DropTableFactory.build {
    main {
        // No holidays at the moment...
    }
}
DropTableFactory.register(specialTable, 14680, type = DropTableType.BOX)