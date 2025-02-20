package gg.rsmod.plugins.api.ext

import gg.rsmod.game.fs.def.ItemDef
import gg.rsmod.game.fs.def.VarbitDef
import gg.rsmod.game.message.impl.*
import gg.rsmod.game.model.*
import gg.rsmod.game.model.attr.*
import gg.rsmod.game.model.bits.BitStorage
import gg.rsmod.game.model.bits.StorageBits
import gg.rsmod.game.model.container.ItemContainer
import gg.rsmod.game.model.entity.DynamicObject
import gg.rsmod.game.model.entity.Player
import gg.rsmod.game.model.interf.DisplayMode
import gg.rsmod.game.model.item.Item
import gg.rsmod.game.model.shop.PurchasePolicy
import gg.rsmod.game.model.timer.SKULL_ICON_DURATION_TIMER
import gg.rsmod.game.sync.block.UpdateBlockType
import gg.rsmod.plugins.api.*
import gg.rsmod.plugins.api.cfg.Items
import gg.rsmod.plugins.api.cfg.Sfx
import gg.rsmod.plugins.content.combat.createProjectile
import gg.rsmod.plugins.content.combat.strategy.MagicCombatStrategy
import gg.rsmod.plugins.content.quests.QUEST_POINT_VARP
import gg.rsmod.plugins.content.quests.Quest
import gg.rsmod.plugins.content.skills.crafting.jewellery.JewelleryData
import gg.rsmod.plugins.content.skills.crafting.silver.SilverData
import gg.rsmod.plugins.content.skills.farming.constants.Constants
import gg.rsmod.plugins.content.skills.farming.logic.FarmingManager
import gg.rsmod.plugins.content.skills.smithing.data.BarProducts
import gg.rsmod.plugins.content.skills.smithing.data.BarType
import gg.rsmod.plugins.content.skills.smithing.data.SmithingType
import gg.rsmod.util.BitManipulation
import gg.rsmod.util.Misc
import java.lang.ref.WeakReference
import kotlin.math.floor
import kotlin.math.max

/**
 * The interface key used by inventory overlays
 */
const val INVENTORY_INTERFACE_KEY = 93

/**
 * The id of the script used to initialise the interface overlay options. The 'big' variant of this script
 * is used as it supports up to eight options rather than five.
 *
 * https://github.com/RuneStar/cs2-scripts/blob/master/scripts/[clientscript,interface_inv_init_big].cs2
 */
const val INTERFACE_INV_INIT_BIG = 150

const val MAKE_QUANTITY_VARBIT = 8095

const val MAKE_MAX_QUANTITY_VARBIT = 8094

// Constants representing VARP ids for various containers and currencies
const val CURRENT_CONTAINER_ID_VARP = 118
const val SECONDARY_CONTAINER_ID_VARP = 1496
const val SHOP_CURRENCY_VARP = 532

fun openCharacterCustomizing(player: Player) {
    player.openFullscreenInterface(1028)
    player.setEvents(interfaceId = 1028, component = 65, to = 11)
    player.setEvents(interfaceId = 1028, component = 128, to = 50)
    player.setEvents(interfaceId = 1028, component = 132, to = 250)
    player.setVarbit(8093, if (player.appearance.gender == Gender.MALE) 0 else 1)
    player.setVarc(197, 0)
    player.runClientScript(368, "str2")
    // script_368(widget(1028, 116), str2, getTextWidth(str2, 495) + 30, "");
    // player.setVarbit(8246, 1)
    // player.setVarbit(8247, 0)
    // player.setComponentText(interfaceId = 1028, component = 115, text = "Modify Further")
}

/**
 * Opens a shop interface for the player.
 *
 * @param shop The name of the shop to open.
 * @param points If true, the shop is a points shop and will not display item prices.
 */
fun Player.openShop(shop: String, points: Boolean = false) {
    val currentShop = world.getShop(shop)
    val shopInterface = 620
    val mainStockComponent = 25
    val freeStockComponent = 26

    if (currentShop != null) {
        // Set the current shop attribute
        attr[CURRENT_SHOP_ATTR] = currentShop

        // Set the main stock container id
        setVarp(CURRENT_CONTAINER_ID_VARP, 4)

        // Set the secondary container id if the shop contains samples, otherwise set to -1
        if (currentShop.containsSamples) {
            setVarp(SECONDARY_CONTAINER_ID_VARP, 6)
        } else {
            setVarp(SECONDARY_CONTAINER_ID_VARP, -1)
        }

        // Set the currency for the shop
        if (currentShop.currency.currencyItem > -1) {
            setVarp(SHOP_CURRENCY_VARP, currentShop.currency.currencyItem)
        }

        shopDirty = true
        setVarc(199, -1)

        // Open the shop interfaces
        openInterface(interfaceId = 621, dest = InterfaceDestination.TAB_AREA)
        openInterface(interfaceId = 620, dest = InterfaceDestination.MAIN_SCREEN)

        // Show item prices if the shop isn't a points shop
        if (!points) {
            for (i in 0..40) {
                setVarc(946 + i, 0) // sets price amount on individual item container
            }
        }

        // Enable interface events for main stock items
        setInterfaceEvents(
            interfaceId = shopInterface,
            component = mainStockComponent,
            range = 0..currentShop.items.filterNotNull().size * 6,
            setting = 1150,
        )

        // Enable interface events for free sample items if any
        if (currentShop.sampleItems.filterNotNull().isNotEmpty()) {
            setInterfaceEvents(
                interfaceId = shopInterface,
                component = freeStockComponent,
                range = 0..currentShop.sampleItems.filterNotNull().size * 4,
                setting = 1150,
            )
        }

        // Set the shop name in the interface
        setComponentText(interfaceId = shopInterface, component = 20, text = currentShop.name)

        // Show the "Buy" button if the shop has a purchase policy of "BUY_TRADEABLES"
        if (currentShop.purchasePolicy == PurchasePolicy.BUY_TRADEABLES) {
            setComponentHidden(interfaceId = 620, component = 19, hidden = false)
        }
    } else {
        // Log a warning message if the shop does not exist
        World.logger.warn { "Player \"$username\" is unable to open shop \"$shop\" as it does not exist." }
    }
}


fun Player.transformObject(
    objectId: Int,
    currentX: Int,
    currentZ: Int,
    currentRotation: Int,
    newObjectIdParam: Int,
    nextXParam: Int,
    nextZParam: Int,
    newRotationParam: Int,
    waitTimeParam: Int
) {
    var waitTime = waitTimeParam
    var newObjectId = newObjectIdParam
    var nextX = nextXParam
    var nextZ = nextZParam
    var newRotation = newRotationParam

    if (newRotation == -1) newRotation = currentRotation
    if (newObjectId == -1) newObjectId = objectId
    if (nextX == -1) nextX = currentX
    if (nextZ == -1) nextZ = currentZ
    if (waitTime == -1) waitTime = 2

    val oldObject =
        DynamicObject(id = objectId, type = 0, rot = currentRotation, tile = Tile(x = currentX, z = currentZ))

    lockingQueue(lockState = LockState.DELAY_ACTIONS) {
        val newObject = DynamicObject(id = newObjectId, type = 0, rot = newRotation, tile = Tile(x = nextX, z = nextZ))
        world.remove(oldObject)
        world.spawn(newObject)
        wait(waitTime)
        world.remove(newObject)
        world.spawn(oldObject)
    }
}


fun Player.handleTemporaryDoor(
    obj: DynamicObject,
    moveObjX: Int,
    moveObjZ: Int,
    newDoorId: Int,
    newRotation: Int,
    movePlayerX: Int,
    movePlayerZ: Int,
    waitTime: Int
) {
    val moveX = if (moveObjX == -1) obj.tile.x else moveObjX
    val moveZ = if (moveObjZ == -1) obj.tile.x else moveObjZ
    val nextDoorId = if (newDoorId == -1) obj.id else newDoorId
    val rotation = if (newRotation == -1) obj.rot else newRotation
    val wait = if (waitTime == -1) 2 else waitTime
    lockingQueue(lockState = LockState.DELAY_ACTIONS) {
        world.remove(obj)
        val openDoor = DynamicObject(id = nextDoorId, type = 0, rot = newRotation, tile = Tile(x = moveX, z = moveZ))
        playSound(Sfx.DOOR_OPEN)
        world.spawn(openDoor)
        if (movePlayerX != -1 || movePlayerZ != -1) {
            walkTo(tile = Tile(x = movePlayerX, z = movePlayerZ), detectCollision = false)
        }
        wait(wait)
        world.remove(openDoor)
        world.spawn(obj)
        playSound(Sfx.DOOR_CLOSE)
    }
}


/**
 * Used primarily for firemaking, and finding the next available tile
 * for the player to walk to. Another use-case I found was for bird nests
 * from woodcutting
 */
fun Player.findWesternTile(): Tile {
    return listOf(
        Direction.WEST,
        Direction.EAST,
        Direction.SOUTH,
        Direction.NORTH,
    ).firstNotNullOfOrNull { direction ->
        if (world.collision.isBlocked(tile, direction, false)) {
            null
        } else {
            tile.step(direction, 1).takeUnless(world.collision::isClipped)
        }
    } ?: tile
}

fun Player.message(message: String, type: ChatMessageType = ChatMessageType.GAME_MESSAGE, username: String? = null) {
    write(MessageGameMessage(type = type.id, message = message, username = username))
}

fun Player.filterableMessage(message: String) {
    write(MessageGameMessage(type = ChatMessageType.FILTERED.id, message = message, username = null))
}

fun Player.runClientScript(id: Int, vararg args: Any) {
    write(RunClientScriptMessage(id, *args))
}

fun Player.focusTab(tab: Int) {
    runClientScript(115, tab)
}

fun Player.setInterfaceEvents(interfaceId: Int, component: Int, range: IntRange, setting: Int) {
    write(
        IfSetEventsMessage(
            hash = ((interfaceId shl 16) or component),
            fromChild = range.first,
            toChild = range.last,
            setting = setting
        )
    )
}

fun Player.setComponentText(interfaceId: Int, component: Int, text: String) {
    write(IfSetTextMessage(interfaceId, component, text))
}

fun Player.setVarcString(id: Int, text: String) {
    write(VarcStringMessage(id, text))
}

fun Player.setComponentHidden(interfaceId: Int, component: Int, hidden: Boolean) {
    write(IfSetHideMessage(hash = ((interfaceId shl 16) or component), hidden = hidden))
}

fun Player.setComponentSprite(interfaceId: Int, component: Int, sprite: Int) {
    write(IfSetSpriteMessage(hash = ((interfaceId shl 16) or component), sprite = sprite))
}

fun Player.setComponentItem(interfaceId: Int, component: Int, item: Int, amountOrZoom: Int) {
    write(IfSetObjectMessage(hash = ((interfaceId shl 16) or component), item = item, amount = amountOrZoom))
}

fun Player.setComponentNpcHead(interfaceId: Int, component: Int, npc: Int) {
    write(IfSetNpcHeadMessage(hash = ((interfaceId shl 16) or component), npc = npc))
}

fun Player.setComponentPlayerHead(interfaceId: Int, component: Int) {
    write(IfSetPlayerHeadMessage(hash = ((interfaceId shl 16) or component)))
}

fun Player.setComponentAnim(interfaceId: Int, component: Int, anim: Int) {
    write(IfSetAnimMessage(hash = ((interfaceId shl 16) or component), anim = anim))
}

/**
 * Use this method to open an interface id on top of an [InterfaceDestination]. This
 * method should always be preferred over
 *
 * ```
 * openInterface(parent: Int, child: Int, component: Int, type: Int, isMainComponent: Boolean)
 * ```
 *
 * as it holds logic that must be handled for certain [InterfaceDestination]s.
 */
fun Player.openInterface(interfaceId: Int, dest: InterfaceDestination, fullscreen: Boolean = false) {
    val displayMode = if (!fullscreen) interfaces.displayMode else DisplayMode.FULLSCREEN
    val child = getChildId(dest, displayMode)
    val parent = getDisplayComponentId(displayMode)
    if (displayMode == DisplayMode.FULLSCREEN) {
        openOverlayInterface(displayMode)
    }
    openInterface(
        parent,
        child,
        interfaceId,
        if (dest.clickThrough) 1 else 0,
        isModal = dest == InterfaceDestination.MAIN_SCREEN || dest == InterfaceDestination.MAIN_SCREEN_FULL
    )
}

/**
 * Handles opening of fullscreen interfaces.
 * Example: World Map
 */
fun Player.openFullscreenInterface(interfaceId: Int) {
    interfaces.open(0, 0, interfaceId)
    write(IfOpenTopMessage(interfaceId, 0))
}

fun Player.closeFullscreenInterface() {
    val display = if (interfaces.displayMode.isResizable()) 746 else 548
    write(IfOpenTopMessage(display, 2))
}

/**
 * Use this method to open an interface id on top of an [InterfaceDestination]. This
 * method should always be preferred over
 *
 * ```
 * openInterface(parent: Int, child: Int, component: Int, type: Int, isMainComponent: Boolean)
 * ```
 *
 * as it holds logic that must be handled for certain [InterfaceDestination]s.
 */
fun Player.openChatboxInterface(interfaceId: Int, child: Int, dest: InterfaceDestination, fullscreen: Boolean = false) {
    val displayMode = if (!fullscreen) interfaces.displayMode else DisplayMode.FULLSCREEN
    val parent = dest.interfaceId
    if (displayMode == DisplayMode.FULLSCREEN) {
        openOverlayInterface(displayMode)
    }
    openInterface(
        parent,
        child,
        interfaceId,
        if (dest.clickThrough) 1 else 0,
        isModal = dest == InterfaceDestination.MAIN_SCREEN
    )
}

/**
 * Use this method to "re-open" an [InterfaceDestination]. This method should always
 * be preferred over
 *
 * ```
 * openInterface(parent: Int, child: Int, interfaceId: Int, type: Int, mainInterface: Boolean)
 * ````
 *
 * as it holds logic that must be handled for certain [InterfaceDestination]s.
 */
fun Player.openInterface(dest: InterfaceDestination, autoClose: Boolean = false) {
    val displayMode = if (!autoClose) interfaces.displayMode else DisplayMode.FULLSCREEN
    val child = getChildId(dest, displayMode)
    val parent = getDisplayComponentId(displayMode)
    if (displayMode == DisplayMode.FULLSCREEN) {
        openOverlayInterface(displayMode)
    }
    openInterface(
        parent,
        child,
        dest.interfaceId,
        if (dest.clickThrough) 1 else 0,
        isModal = dest == InterfaceDestination.MAIN_SCREEN
    )
}

fun Player.openInterface(parent: Int, child: Int, interfaceId: Int, type: Int = 0, isModal: Boolean = false) {
    if (isModal) {
        interfaces.openModal(parent, child, interfaceId)
    } else {
        interfaces.open(parent, child, interfaceId)
    }
    write(IfOpenSubMessage(parent, child, interfaceId, type))
}

fun Player.closeInterface(interfaceId: Int) {
    if (interfaceId == interfaces.getModal()) {
        interfaces.setModal(-1)
    }
    val hash = interfaces.close(interfaceId)
    if (hash != -1) {
        write(IfCloseSubMessage(hash))
    }
}

fun Player.closeInterface(dest: InterfaceDestination) {
    val displayMode = interfaces.displayMode
    val child = getChildId(dest, displayMode)
    val parent = getDisplayComponentId(displayMode)
    val hash = interfaces.close(parent, child)
    if (hash != -1) {
        write(IfCloseSubMessage((parent shl 16) or child))
    }
}

fun Player.closeMainInterface() {
    closeInterface(InterfaceDestination.MAIN_SCREEN)
    closeInterface(InterfaceDestination.MAIN_SCREEN_FULL)
    closeInterface(InterfaceDestination.MAIN_SCREEN_OVERLAY)
}

fun Player.closeComponent(parent: Int, child: Int) {
    interfaces.close(parent, child)
    write(IfCloseSubMessage((parent shl 16) or child))
}

fun Player.closeInputDialog() {
    runClientScript(101)
}

fun Player.getInterfaceAt(dest: InterfaceDestination): Int {
    val displayMode = interfaces.displayMode
    val child = getChildId(dest, displayMode)
    val parent = getDisplayComponentId(displayMode)
    return interfaces.getInterfaceAt(parent, child)
}

fun Player.isInterfaceVisible(interfaceId: Int): Boolean = interfaces.isVisible(interfaceId)

fun Player.toggleDisplayInterface(newMode: DisplayMode) {
    if (interfaces.displayMode != newMode) {
        interfaces.displayMode = newMode

        openOverlayInterface(newMode)
        InterfaceDestination.values.filter { pane -> pane.interfaceId != -1 }.forEach { pane ->
            openInterface(pane.interfaceId, pane)
        }
    }
}

fun Player.openOverlayInterface(displayMode: DisplayMode) {
    if (displayMode != interfaces.displayMode) {
        interfaces.setVisible(
            parent = getDisplayComponentId(interfaces.displayMode),
            child = getChildId(InterfaceDestination.MAIN_SCREEN, interfaces.displayMode),
            visible = false
        )
    }
    val component = getDisplayComponentId(displayMode)
    interfaces.setVisible(parent = getDisplayComponentId(displayMode), child = 0, visible = true)
    write(IfOpenTopMessage(component, 1))
}

fun Player.sendItemContainer(key: Int, items: Array<Item?>) {
    write(UpdateInvFullMessage(containerKey = key, items = items, invother = false))
}

fun Player.sendItemContainer(key: Int, container: ItemContainer) = sendItemContainer(key, container.rawItems)

/**
 * Sends a container type referred to as 'invother' in CS2, which is used for displaying a second container with
 * the same container key. An example of this is the trade accept screen, where the list of items being traded is stored
 * in container 90 for both the player's container, and the partner's container. A container becomes 'invother' when it's
 * component hash is less than -70000, which internally translates the container key to (key + 32768). We can achieve this by either
 * sending a component hash of less than -70000, or by setting the key ourselves. I feel like the latter makes more sense.
 *
 * Special thanks to Polar for explaining this concept to me.
 *
 * https://github.com/RuneStar/cs2-scripts/blob/a144f1dceb84c3efa2f9e90648419a11ee48e7a2/scripts/script768.cs2
 *
 *
 * -- Note* Alycia
 * It appears that 667 has a new way of doing this, and that is by sending a byte in the packet
 * to flag if the inventory/container is an "invother". More research into this would provide
 * better documentation, but I assume this is pretty self-explanatory with regard to the above note.
 *
 */
fun Player.sendItemContainerOther(key: Int, container: ItemContainer) {
    write(UpdateInvFullMessage(containerKey = key, items = container.rawItems, invother = true))
}

fun Player.sendRunEnergy(energy: Int) {
    write(UpdateRunEnergyMessage(energy))
}

fun Player.playSound(id: Int, volume: Int = 1, delay: Int = 0) {
    write(SynthSoundMessage(sound = id, volume = volume, delay = delay))
}

fun Player.playJingle(id: Int, volume: Int = 255) {
    write(MusicEffectMessage(id = id, volume = volume))
}

fun Player.playSong(id: Int, name: String = "") {
    setComponentText(interfaceId = 187, component = 4, text = name)
    write(MidiSongMessage(10, id, 255))
}

fun Player.getVarp(id: Int): Int = varps.getState(id)

fun Player.setVarp(id: Int, value: Int) {
    varps.setState(id, value)
}

fun Player.toggleVarp(id: Int) {
    varps.setState(id, varps.getState(id) xor 1)
}
fun Player.getVarbit(id: Int): Int {
    val def = world.definitions.get(VarbitDef::class.java, id)
    return varps.getBit(def.varp, def.startBit, def.endBit)
}

fun Player.setVarbit(id: Int, value: Int) {
    val def = world.definitions.get(VarbitDef::class.java, id)
    varps.setBit(def.varp, def.startBit, def.endBit, value)
}

fun Player.setVarc(id: Int, value: Int) {
    val message =
        if (id in -Byte.MAX_VALUE..Byte.MAX_VALUE) VarcSmallMessage(id, value) else VarcLargeMessage(id, value)
    write(message)
    varcs[id] = value
}

fun Player.getVarc(id: Int): Int {
    return varcs[id]
}

/**
 * Write a varbit message to the player's client without actually modifying
 * its varp value in [Player.varps].
 */
fun Player.sendTempVarbit(id: Int, value: Int) {
    val def = world.definitions.get(VarbitDef::class.java, id)
    val state = BitManipulation.setBit(varps.getState(def.varp), def.startBit, def.endBit, value)
    val message = if (state in -Byte.MAX_VALUE..Byte.MAX_VALUE) VarpSmallMessage(def.varp, state) else VarpLargeMessage(
        def.varp,
        state
    )
    write(message)
}

fun Player.toggleVarbit(id: Int) {
    val def = world.definitions.get(VarbitDef::class.java, id)
    varps.setBit(def.varp, def.startBit, def.endBit, getVarbit(id) xor 1)
}

fun Player.setMapFlag(x: Int, z: Int) {
    write(SetMapFlagMessage(x, z))
}

fun Player.clearMapFlag() {
    setMapFlag(255, 255)
}

fun Player.sendOption(option: String, id: Int, leftClick: Boolean = false) {
    check(id in 1..options.size) { "Option id must range from [1-${options.size}]" }
    val index = id - 1
    options[index] = option
    write(SetOpPlayerMessage(option, index, leftClick, -1))
}

/**
 * Checks if the player has an option with the name [option] (case-sensitive).
 */
fun Player.hasOption(option: String, id: Int = -1): Boolean {
    check(id == -1 || id in 1..options.size) { "Option id must range from [1-${options.size}]" }
    return if (id != -1) options.any { it == option } else options[id - 1] == option
}

/**
 * Removes the option with [id] from this player.
 */
fun Player.removeOption(id: Int) {
    check(id in 1..options.size) { "Option id must range from [1-${options.size}]" }
    val index = id - 1
    write(SetOpPlayerMessage("null", index, false, -1))
    options[index] = null
}

fun Player.getStorageBit(storage: BitStorage, bits: StorageBits): Int = storage.get(this, bits)

fun Player.hasStorageBit(storage: BitStorage, bits: StorageBits): Boolean = storage.get(this, bits) != 0

fun Player.setStorageBit(storage: BitStorage, bits: StorageBits, value: Int) {
    storage.set(this, bits, value)
}

fun Player.toggleStorageBit(storage: BitStorage, bits: StorageBits) {
    storage.set(this, bits, storage.get(this, bits) xor 1)
}

fun Player.heal(amount: Int, capValue: Int = 0) {
    alterLifepoints(value = amount, capValue = capValue)
}

fun Player.hasSpellbook(book: Spellbook): Boolean = getVarbit(4070) == book.id

fun Player.getSpellbook(): Spellbook = Spellbook.values.first { getVarbit(4070) == it.id }

fun Player.setSpellbook(book: Spellbook) = setVarbit(4070, book.id)

fun Player.getWeaponType(): Int = attr[LAST_KNOWN_WEAPON_TYPE] ?: 0

fun Player.getMaximumMakeQuantity(): Int = getVarbit(MAKE_MAX_QUANTITY_VARBIT)

fun Player.getMakeQuantity(): Int = getVarbit(MAKE_QUANTITY_VARBIT)

fun Player.getAttackStyle(): Int = getVarp(43)

fun Player.hasWeaponType(type: WeaponType, vararg others: WeaponType): Boolean =
    getWeaponType() == type.id || others.isNotEmpty() && getWeaponType() in others.map { it.id }

fun Player.hasEquipped(slot: EquipmentType, vararg items: Int): Boolean {
    check(items.isNotEmpty()) { "Items shouldn't be empty." }
    return items.any { equipment.hasAt(slot.id, it) }
}

fun Player.hasEquipped(items: IntArray) = items.all { equipment.contains(it) }

fun Player.hasEquippedWithName(nameKeywords: Array<String>): Boolean {
    return EquipmentType.values.any { slot ->
        val item = getEquipment(slot)
        item?.let { itemName ->
            nameKeywords.any { keyword -> itemName.getName(world.definitions).contains(keyword, ignoreCase = true) }
        } ?: false
    }
}

fun Player.getEquipment(slot: EquipmentType): Item? = equipment[slot.id]

fun Player.setSkullIcon(icon: SkullIcon) {
    skullIcon = icon.id
    addBlock(UpdateBlockType.APPEARANCE)
}

fun Player.skull(icon: SkullIcon, durationCycles: Int) {
    check(icon != SkullIcon.NONE)
    setSkullIcon(icon)
    timers[SKULL_ICON_DURATION_TIMER] = durationCycles
}

fun Player.hasSkullIcon(icon: SkullIcon): Boolean = skullIcon == icon.id

fun Player.isClientResizable(): Boolean =
    interfaces.displayMode == DisplayMode.RESIZABLE_NORMAL || interfaces.displayMode == DisplayMode.FULLSCREEN

fun Player.sendWorldMapTile() {
    runClientScript(1749, tile.as30BitInteger)
}

fun Player.sendWeaponComponentInformation() {
    for (slot in 11..14) {
        setEvents(interfaceId = 884, component = slot, from = -1, to = 0, setting = 2)
    }
    val weapon = getEquipment(EquipmentType.WEAPON)
    if (weapon != null) {
        val definition = world.definitions.get(ItemDef::class.java, weapon.id)
        attr[LAST_KNOWN_WEAPON_TYPE] = max(0, definition.weaponType)
    } else {
        attr[LAST_KNOWN_WEAPON_TYPE] = WeaponType.NONE.id
    }
}

fun Player.getGnomeAgilityStage(): Int {
    val lastStage = attr[GNOME_AGILITY_STAGE]
    if (lastStage == null) {
        setGnomeAgilityStage(0)
        return getGnomeAgilityStage()
    }
    return lastStage
}

fun Player.setGnomeAgilityStage(stage: Int) {
    attr[GNOME_AGILITY_STAGE] = stage
}

fun Player.calculateAndSetCombatLevel(): Boolean {
    val old = combatLevel
    val attack = skills.getMaxLevel(Skills.ATTACK)
    val defence = skills.getMaxLevel(Skills.DEFENCE)
    val strength = skills.getMaxLevel(Skills.STRENGTH)
    val hitpoints = skills.getMaxLevel(Skills.CONSTITUTION)
    val prayer = skills.getMaxLevel(Skills.PRAYER)
    val ranged = skills.getMaxLevel(Skills.RANGED)
    val magic = skills.getMaxLevel(Skills.MAGIC)
    val summoning = skills.getMaxLevel(Skills.SUMMONING)
    val meleeCombat =
        floor(0.25 * (defence + hitpoints + floor((prayer * 0.50)) + floor(summoning * 0.50)) + 0.325 * (attack + strength))
    // floor(0.25 * (attack + strength
    // 1/4 * [1.3 * Max(Att+Str, 2*Mag, 2*Rng) + Def + Hp + Pray/2 + Summ/2]
    val rangingCombat = floor(
        0.25 * (defence + hitpoints + floor((prayer * 0.50)) + floor(summoning * 0.50)) + 0.325 * (floor((ranged * 0.50)) + ranged)
    )
    val magicCombat = floor(
        0.25 * (defence + hitpoints + floor((prayer * 0.50)) + floor(summoning * 0.50)) + 0.325 * (floor((magic * 0.50)) + magic)
    )

    combatLevel = when {
        meleeCombat >= rangingCombat && meleeCombat >= magicCombat -> meleeCombat.toInt()
        rangingCombat >= meleeCombat && rangingCombat >= magicCombat -> rangingCombat.toInt()
        else -> magicCombat.toInt()
    }

    val changed = combatLevel != old
    if (changed) {
        addBlock(UpdateBlockType.APPEARANCE)
        return true
    }
    return false
}


fun Player.buildSmithingInterface(bar: BarType) {
    val type = BarProducts.getBars(bar)
    attr[BAR_TYPE] = bar.item
    val componentIds = arrayOf(266, 169, 65, 97, 81, 89, 209, 161, 169)
    // Hide all the "extra" components, dart tips, lanterns, claws, etc..
    componentIds.forEach { component ->
        setComponentHidden(interfaceId = 300, component = component, hidden = true)
    }

    // Unlocks pickaxe component
    setComponentHidden(interfaceId = 300, component = 266, hidden = false)

    type.filterNotNull().forEach {
        // Unlock "extra" components if the type exists
        when (it.smithingType) {
            SmithingType.TYPE_GRAPPLE_TIP -> setComponentHidden(interfaceId = 300, component = 169, hidden = false)
            SmithingType.TYPE_DART_TIPS -> setComponentHidden(interfaceId = 300, component = 65, hidden = false)
            SmithingType.TYPE_IRON_SPIT -> setComponentHidden(interfaceId = 300, component = 89, hidden = false)
            SmithingType.TYPE_WIRE -> setComponentHidden(interfaceId = 300, component = 81, hidden = false)
            SmithingType.TYPE_CLAWS -> setComponentHidden(interfaceId = 300, component = 209, hidden = false)
            SmithingType.TYPE_OIL_LANTERN -> setComponentHidden(interfaceId = 300, component = 161, hidden = false)
            SmithingType.TYPE_BULLSEYE -> setComponentHidden(interfaceId = 300, component = 161, hidden = false)
            SmithingType.TYPE_STUDS -> setComponentHidden(interfaceId = 300, component = 97, hidden = false)
            else -> {}
        }

        var color = ""

        // If the level requirement matches or exceeds the players current level
        if (skills.getCurrentLevel(Skills.SMITHING) >= it.level) {
            color = "<col=FFFFFF>"
        }

        // Set the items name
        setComponentText(
            interfaceId = 300,
            component = it.smithingType.componentId,
            text = "$color${Misc.formatSentence(it.smithingType.name.replace("TYPE_", "").replace("_", " "))}"
        )

        // If the players inventory contains the proper amount of bars
        if (inventory.getItemCount(it.barType.item) >= it.smithingType.barRequirement) {
            color = "<col=2DE120>"
            val str = "Bar"
            setComponentText(
                interfaceId = 300,
                component = it.smithingType.componentId + 1,
                text = "$color${it.smithingType.barRequirement} ${str.pluralSuffix(it.smithingType.barRequirement)}"
            )
        }

        // Finally, send the item on the interface
        setComponentItem(
            interfaceId = 300,
            component = it.smithingType.componentId - 1,
            item = it.result,
            amountOrZoom = it.smithingType.producedAmount
        )
    }

    // Send the title
    setComponentText(interfaceId = 300, component = 14, text = bar.barName)

    // Open the main interface
    openInterface(dest = InterfaceDestination.MAIN_SCREEN, interfaceId = 300)
}

fun openTanningInterface(player: Player) {
    player.openInterface(dest = InterfaceDestination.MAIN_SCREEN, interfaceId = 324)
}

fun essenceTeleport(player: Player, dialogue: String = "Senventior disthine molenko!", targetTile: Tile) {
    val npc = player.getInteractingNpc()
    npc.attr[INTERACTING_PLAYER_ATTR] = WeakReference(player)
    val p = npc.getInteractingPlayer()
    npc.queue {
        npc.facePawn(npc.getInteractingPlayer())
        npc.forceChat(dialogue)
        npc.graphic(108)
        val projectile = npc.createProjectile(p, 109, ProjectileType.MAGIC)
        p.world.spawn(projectile)
        p.playSound(Sfx.CURSE_CAST_AND_FIRE)
        wait(MagicCombatStrategy.getHitDelay(npc.tile, p.tile) + 1)
        p.attr[ESSENCE_MINE_INTERACTED_WITH] = npc.id
        p.moveTo(targetTile)
        wait(1)
        p.graphic(110)
        p.playSound(Sfx.CURSE_HIT)
    }
}
fun travelTeleport(player: Player, dialogue: String = "Ego te movere", targetTile: Tile) {
    val npc = player.getInteractingNpc()
    npc.attr[INTERACTING_PLAYER_ATTR] = WeakReference(player)
    val p = npc.getInteractingPlayer()
    npc.queue {
        npc.facePawn(npc.getInteractingPlayer())
        npc.forceChat(dialogue)
        npc.graphic(108)
        val projectile = npc.createProjectile(p, 109, ProjectileType.MAGIC)
        p.world.spawn(projectile)
        p.playSound(Sfx.CURSE_CAST_AND_FIRE)
        wait(MagicCombatStrategy.getHitDelay(npc.tile, p.tile) + 1)
        p.moveTo(targetTile)
        wait(1)
        p.graphic(110)
        p.playSound(Sfx.CURSE_HIT)
    }
}
fun sailTeleport(player: Player, dialogue: String = "", targetTile: Tile) {
    val npc = player.getInteractingNpc()
    npc.attr[INTERACTING_PLAYER_ATTR] = WeakReference(player)
    val p = npc.getInteractingPlayer()
    npc.queue {
        npc.facePawn(npc.getInteractingPlayer())
        npc.forceChat(dialogue)
        p.moveTo(targetTile)
        wait(1)
        p.playSound(Sfx.SEASHORE_1)
    }
}

// Note: this does not take ground items, that may belong to the player, into
// account.
fun Player.hasItem(item: Int, amount: Int = 1): Boolean =
    containers.values.firstOrNull { container -> container.getItemCount(item) >= amount } != null

fun Player.isPrivilegeEligible(to: String): Boolean = world.privileges.isEligible(privilege, to)

fun Player.getSummoningBonus(): Int = equipmentBonuses[BonusSlot.SUMMONING_BONUS.id]
fun Player.getStrengthBonus(): Int = equipmentBonuses[BonusSlot.STRENGTH_BONUS.id]

fun Player.getRangedStrengthBonus(): Int = when {
    hasWeaponType(WeaponType.THROWN) || hasWeaponType(WeaponType.CHINCHOMPA) || hasWeaponType(WeaponType.SLING) -> {
        world.definitions.get(ItemDef::class.java, equipment[3]?.id ?: -1).bonuses[BonusSlot.RANGED_STRENGTH_BONUS.id]
    }
    else -> equipmentBonuses[BonusSlot.RANGED_STRENGTH_BONUS.id]
}


fun Player.getMagicDamageBonus(): Int = equipmentBonuses[BonusSlot.MAGIC_DAMAGE_BONUS.id]

fun Player.getPrayerBonus(): Int = equipmentBonuses[BonusSlot.PRAYER_BONUS.id]

fun Player.completedAllQuests(): Boolean {
    return getVarp(QUEST_POINT_VARP) >= Quest.quests.sumOf { it.pointReward }
}

fun Player.checkEquipment() {
    equipment.filterNotNull().forEach { item ->
        if ((item.id == Items.QUEST_POINT_HOOD || item.id == Items.QUEST_POINT_CAPE) && !completedAllQuests()) {
            disableEquipment(item.id)
        }
    }
}

fun Player.disableEquipment(itemId: Int) {
    val itemName = world.definitions.get(ItemDef::class.java, itemId).name
    equipment.remove(itemId)
    val container = if (inventory.hasSpace) inventory else bank
    container.add(itemId)
    message("Your $itemName was removed from your equipment and added to your ${if (container == bank) "bank" else "inventory"}.")
}

fun Player.setSkillTargetEnabled(skill: Int, enabled: Boolean) {
    enabledSkillTarget[skill] = enabled
    refreshSkillTarget()
}

fun Player.setSkillTargetMode(skill: Int, enabled: Boolean) {
    skillTargetMode[skill] = enabled
    refreshSkillTargetMode()
}

fun Player.setSkillTargetValue(skill: Int, value: Int) {
    skillTargetValue[skill] = value
    refreshSkillsTargetsValues()
}

fun Player.refreshSkillTarget() {
    val value: Int = Misc.get32BitValue(enabledSkillTarget, true)
    setVarp(1966, value)
}

fun Player.refreshSkillTargetMode() {
    val value: Int = Misc.get32BitValue(skillTargetMode, true)
    setVarp(1968, value)
}

fun Player.refreshSkillsTargetsValues() {
    for (i in 0..24) {
        setVarp(1969 + i, skillTargetValue[i])
    }
}

fun Player.setSkillTarget(usingLevel: Boolean, skill: Int, target: Int) {
    setSkillTargetEnabled(skill, true)
    setSkillTargetMode(skill, usingLevel)
    setSkillTargetValue(skill, target)
}

fun Player.getWeaponRenderAnimation(): Int {
    val weapon = equipment[3]
    if (weapon != null) {
        val def: Any = weapon.getDef(world.definitions).params.get(644) ?: 1426
        return def as Int
    }
    return 1
}

fun Player.handleLadder(x: Int = -1, z: Int = -1, height: Int = 0, underground: Boolean = false) {
    val climbUp = getInteractingGameObj().getDef(world.definitions).options.any { it?.lowercase() == "climb-up" }
    queue {
        animate(828, idleOnly = true)
        wait(2)
        val zOffset = when (climbUp) {
            true -> -6400
            false -> 6400
        }
        moveTo(
            x = if (x > -1) x else player.tile.x,
            z = if (z > -1) z else if (underground) player.tile.z + zOffset else player.tile.z,
            height = height
        )
    }
}

fun Player.handleStairs(x: Int = -1, z: Int = -1, height: Int = 0, underground: Boolean = false) {
    val climbUp = getInteractingGameObj().getDef(world.definitions).options.any { it?.lowercase() == "climb-up" }
    queue {
        val zOffset = when (climbUp) {
            true -> -6400
            false -> 6400
        }
        moveTo(
            x = if (x > -1) x else player.tile.x,
            z = if (z > -1) z else if (underground) player.tile.z + zOffset else player.tile.z,
            height = height
        )
    }
}


/**
 * Handle the opening of the Jewellery Crafting Interface
 * @author Kevin Senez <ksenez94@gmail.com>
 */
fun Player.openJewelleryCraftingInterface() {
    openInterface(dest = InterfaceDestination.MAIN_SCREEN, interfaceId = 446)

    /**
     * Check for moulds and hide text if there.
     */
    setComponentHidden(interfaceId = 446, component = 17, hidden = inventory.contains(Items.RING_MOULD))
    setComponentHidden(interfaceId = 446, component = 21, hidden = inventory.contains(Items.NECKLACE_MOULD))
    setComponentHidden(interfaceId = 446, component = 26, hidden = inventory.contains(Items.AMULET_MOULD))
    setComponentHidden(interfaceId = 446, component = 30, hidden = inventory.contains(Items.BRACELET_MOULD))

    /**
     * Hide category if inventory doesn't contain mould
     */
    setComponentHidden(interfaceId = 446, component = 18, hidden = !inventory.contains(Items.RING_MOULD))
    setComponentHidden(interfaceId = 446, component = 23, hidden = !inventory.contains(Items.NECKLACE_MOULD))
    setComponentHidden(interfaceId = 446, component = 27, hidden = !inventory.contains(Items.AMULET_MOULD))
    setComponentHidden(interfaceId = 446, component = 31, hidden = !inventory.contains(Items.BRACELET_MOULD))

    /**
     * Hide of show model/option components according to if the player has the required items or not.
     * TODO Handle slayer ring slayer unlock and add accordingly.
     */
    if (inventory.contains(Items.GOLD_BAR)) {
        JewelleryData.values.forEach { data ->
            data.products.forEach { product ->
                if (data != JewelleryData.GOLD) {
                    if (inventory.contains(data.gemRequired) && data != JewelleryData.SLAYER_RING) {
                        setComponentItem(
                            interfaceId = 446,
                            component = product.modelComponent,
                            item = product.resultItem,
                            amountOrZoom = 1
                        )
                    } else {
                        setComponentHidden(interfaceId = 446, component = product.modelComponent, hidden = true)
                        setComponentHidden(interfaceId = 446, component = product.optionComponent, hidden = true)
                    }
                } else {
                    setComponentItem(
                        interfaceId = 446,
                        component = product.modelComponent,
                        item = product.resultItem,
                        amountOrZoom = 1
                    )
                }
            }
        }
    }
}

/**
 * Handle opening of the Silver Crafting interface.
 * @author Kevin Senez <ksenez94@gmail.com>
 */
fun Player.openSilverCraftingInterface() {

    /**
     * Hides the silver key option (not used)
     */
    setComponentHidden(interfaceId = 438, component = 78, hidden = true)

    /**
     * Sends the related data on interface open
     */
    SilverData.values.forEach { data ->
        if (!inventory.contains(data.mould.id)) {
            setComponentHidden(interfaceId = 438, component = data.componentArray[0], hidden = true)
            setComponentHidden(interfaceId = 438, component = data.componentArray[4], hidden = false)
            setComponentItem(
                interfaceId = 438,
                component = data.componentArray[5],
                item = data.mould.id,
                amountOrZoom = 1
            )
        } else {
            // Show the result item in interface
            setComponentItem(
                interfaceId = 438,
                component = data.componentArray[2],
                item = data.resultItem.id,
                amountOrZoom = 1
            )
            // Show the options as available
            setComponentHidden(interfaceId = 438, component = data.componentArray[1], hidden = false)

            // Set text to red if players current crafting level isn't high enough to craft item
            if (skills.getCurrentLevel(Skills.CRAFTING) < data.levelRequired)
                setComponentText(
                    interfaceId = 438,
                    component = data.componentArray[3],
                    text = "<col=ff0000>Make<br><col=ff0000>${
                        world.definitions.get(
                            ItemDef::class.java,
                            data.resultItem.id
                        ).name.replace(" ", "<br><col=ff0000>")
                    }"
                )
        }
    }

    openInterface(dest = InterfaceDestination.MAIN_SCREEN, interfaceId = 438)
}

fun Player.farmingManager(): FarmingManager {
    if (this.attr[Constants.farmingManagerAttr] == null) {
        this.attr[Constants.farmingManagerAttr] = FarmingManager(this)
    }
    return this.attr[Constants.farmingManagerAttr]!!
}

fun Player.sendTabs() {
    InterfaceDestination.values.filter { pane -> pane.interfaceId != -1 }.forEach { pane ->
        openInterface(pane.interfaceId, pane)
    }
}

fun Player.refreshBonuses() {
    val names = listOf(
        "Stab",
        "Slash",
        "Crush",
        "Magic",
        "Ranged",
        "Summoning",
        "Absorb Melee",
        "Absorb Magic",
        "Absorb Ranged",
        "Strength",
        "Ranged Strength",
        "Prayer",
        "Magic Damage"
    )

    setVarc(779, getWeaponRenderAnimation())
    for (i in 0..17) {
        var bonusName: String = StringBuilder(names[if (i <= 4) i else i - 5]).append(": ").toString()
        val bonus: Int = equipmentBonuses[i]
        bonusName = StringBuilder(bonusName).append(if (bonus >= 0) "+" else "").append(bonus).toString()
        if (i == 17 || i in 11..13)//component 42-44 absorb bonuses
            bonusName = StringBuilder(bonusName).append("%").toString()
        setComponentText(667, 31 + i, bonusName)//31 to 48 is bonuses
    }
}