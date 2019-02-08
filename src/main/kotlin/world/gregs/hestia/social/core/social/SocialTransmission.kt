package world.gregs.hestia.social.core.social

import world.gregs.hestia.social.api.Connection
import world.gregs.hestia.social.api.Player
import world.gregs.hestia.social.api.Players

class SocialTransmission(private val players: Players): Connection {

    /**
     * Notifies other players with [player] on their friends list that they have connected
     * @param player The player to connect
     */
    override fun connect(player: Player) {
        transmit(player)
    }

    /**
     * Notifies other players with [player] on their friends list that they have disconnected
     * @param player The player to disconnect
     */
    override fun disconnect(player: Player) {
        transmit(player)
    }

    private fun transmit(player: Player) {
        //For all players which have [player] as a friend and isn't on [player]s ignore list (or is and is an admin)
        players.all
                .filter { other -> other != player && other.friends(player) && ((!player.ignores(other) && player.statusOnline(other)) || other.admin) }
                .forEach { other ->
                    //Notify them that [player] has logged out
                    other.sendFriend(player.names, players.get(player.names))
                }
    }
}