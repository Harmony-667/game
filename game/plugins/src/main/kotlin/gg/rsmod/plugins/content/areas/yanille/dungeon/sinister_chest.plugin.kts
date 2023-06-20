package gg.rsmod.plugins.content.areas.yanille.dungeon

import gg.rsmod.plugins.content.drops.DropTableFactory
import gg.rsmod.plugins.content.drops.DropTableType

val SINISTER_KEY = Items.SINISTER_KEY
val SINISTER_CHEST = Objs.CLOSED_CHEST_377

on_item_on_obj(obj = SINISTER_CHEST, item = SINISTER_KEY) {
    val obj = player.getInteractingGameObj()
    player.inventory.remove(SINISTER_KEY, 1)
    player.faceTile(obj.tile)
    player.lockingQueue(TaskPriority.STRONG) {
        val closedChest = DynamicObject(obj)
        val openChest =
            DynamicObject(id = 377, type = obj.type, rot = obj.rot, tile = Tile(x = obj.tile.x, z = obj.tile.z))
        world.remove(closedChest)
        world.spawn(openChest)

        player.animate(536)
        player.message("You unlock the chest with your key.")

        wait(2)

        world.remove(openChest)
        world.spawn(closedChest)
        val drop = DropTableFactory.createDropInventory(player, SINISTER_KEY, DropTableType.CHEST)
        if (drop != null) player.message("You find some treasure in the chest!")

    }
}

on_obj_option(obj = SINISTER_CHEST, option = "open") {
    player.message("This chest is securely locked shut.")
}
val table = DropTableFactory
val mainTable = table.build {
    val toothDrop = world.percentChance(50.0)
    val male = player.appearance.gender.isMale()
    guaranteed {
        obj(Items.UNCUT_DRAGONSTONE)
    }
    main {
        total(10240)
        objs( //Common Drop
            Item(id = Items.SPINACH_ROLL),
            Item(id = Items.COINS_995, amount = 2000),
            slots = 80
        )
        objs( //Common Drop
            Item(id = Items.BREAD),
            Item(id = Items.COINS_995, amount = 1000),
            slots = 80
        )
        objs( //Common Drop
            Item(id = Items.IRON_ARROW, amount = 100),
            Item(id = Items.STEEL_ARROW, amount = 100),
            slots = 80
        )
        objs( //Uncommon Drop
            Item(id = Items.CHAOS_RUNE, amount = 10),
            Item(id = Items.DEATH_RUNE, amount = 10),
            Item(id = Items.COSMIC_RUNE, amount = 10),
            Item(id = Items.NATURE_RUNE, amount = 10),
            Item(id = Items.LAW_RUNE, amount = 10),
            slots = 40
        )
        objs( //Uncommon Drop
            Item(id = Items.RUNE_SCIMITAR),
            Item(id = Items.RUNE_KITESHIELD),
            slots = 40
        )
        objs( //Uncommon Drop
            Item(id = Items.RUNE_PLATEBODY),
            Item(id = Items.RUNE_BOOTS),
            slots = 40
        )
        objs( //Uncommon Drop
            Item(id = Items.RUNE_PLATELEGS),
            Item(id = Items.RUNE_GAUNTLETS),
            slots = 40
        )
        objs( //Rare Drop
            Item(id = Items.RUBY, amount = 2),
            Item(id = Items.DIAMOND, amount = 2),
            Item(id = Items.SINISTER_KEY),
            slots = 20
        )
        objs( //Rare Drop
            Item(id = Items.DRAGON_CHAINBODY),
            Item(id = Items.SINISTER_KEY),
            slots = 20
        )
        objs(
            Item(id = Items.RUNE_BAR, amount = 3),
            Item(id = Items.DRAGON_PLATELEGS),
            slots = 2
        )
        objs(
            Item(id = Items.RUNE_BAR, amount = 3),
            Item(id = Items.DRAGON_PLATEBODY),
            slots = 2
        )
        objs(
            Item(id = Items.COINS_995, amount = 750),
            Item(id = Items.DRAGON_2H_SWORD),
            slots = 2
        )
        objs(
            Item(id = Items.COINS_995, amount = 750),
            Item(id = Items.DRAGON_SCIMITAR),
            slots = 2
        )
        objs(
            Item(id = Items.COINS_995, amount = 20000),
            Item(id = Items.DRAGON_GAUNTLETS),
            Item(id = Items.DRAGON_BOOTS),
            Item(id = Items.DRAGON_FULL_HELM),
            slots = 1
        )
        objs(
            Item(id = Items.COINS_995, amount = 25000),
            Item(id = Items.DRAGON_HATCHET),
            slots = 1
        )
        objs(
            Item(id = Items.COINS_995, amount = 25000),
            Item(id = Items.DRAGON_PICKAXE),
            slots = 1
        )
    }
}

table.register(mainTable, SINISTER_KEY, type = DropTableType.CHEST)