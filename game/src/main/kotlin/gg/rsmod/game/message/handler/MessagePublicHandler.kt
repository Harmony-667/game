package gg.rsmod.game.message.handler

import gg.rsmod.game.message.MessageHandler
import gg.rsmod.game.message.impl.MessagePublicMessage
import gg.rsmod.game.message.impl.PublicChatMessage
import gg.rsmod.game.model.World
import gg.rsmod.game.model.entity.Client
import gg.rsmod.game.model.entity.Player
import gg.rsmod.game.model.interf.DisplayMode
import gg.rsmod.game.service.log.LoggerService
import gg.rsmod.util.Misc.formatForDisplay
import gg.rsmod.util.Misc.formatSentence

/**
 * @author Tom <rspsmods@gmail.com>
 */
class MessagePublicHandler : MessageHandler<MessagePublicMessage> {

    override fun handle(client: Client, world: World, message: MessagePublicMessage) {
        val decompressed = ByteArray(256)
        val huffman = world.huffman
        huffman.decompress(message.data, decompressed, message.length)

        val unpacked = String(decompressed, 0, message.length)

        if(unpacked.startsWith("::yell")) {
            client.writeMessage("To talk in the global chat, start your message in public chat with a period (.)")
            return
        }

        if(unpacked.startsWith("::commands")) {
            client.writeMessage("To use commands, press the (`) key on your keyboard. The available commands are:")
            client.writeMessage("-> male, female, players")
            return
        }
        if(unpacked.startsWith("::players")) {
            client.writeMessage("To use commands, press the (`) key on your keyboard. And type players")
            return
        }

        if(unpacked.startsWith(".")) {
            val player = client as Player
            val icon = when (player.privilege.id) {
                1 -> "[<col=C6A500>Premium</col>]"
                2 -> "<img=0>[<col=46C74E>Mod</col>]"
                3 -> "<img=1>[<col=0090C6>Admin</col>]"
                4 -> "<img=1>[<col=FF0070>Developer</col>]"
                5 -> "<img=1>[<col=ff9900>Founder</col>]"
                else -> ""
            }
            world.players.forEach {
                val color = when (it.interfaces.displayMode) {
                    DisplayMode.FIXED -> "0000ff"
                    DisplayMode.RESIZABLE_NORMAL -> "7fa9ff"
                    else -> "#fa9ff"
                }
                it.writeMessage(
                    "[<col=d45b5b>Global</col>] $icon${formatForDisplay(player.username)}: <col=$color>${
                        formatSentence(
                            unpacked.substring(1)
                        )
                    }</col>"
                )
            }
            return
        }

        world.players.forEach {
            if(it.tile.isWithinRadius(client.tile, 30)) {
                it.write(PublicChatMessage(world, formatSentence(unpacked), client.index, client.privilege.icon, message.effect))
            }
        }

        world.getService(LoggerService::class.java, searchSubclasses = true)?.logPublicChat(client, unpacked)
    }
}