package world.gregs.hestia.social.model

import world.gregs.hestia.core.world.Details
import world.gregs.hestia.social.api.worlds.Worlds

class WorldList : Worlds {
    override val worlds = ArrayList<Details>()

    override fun add(info: Details): Int {
        info.id = getId()
        worlds.add(info)
        return info.id
    }

    override fun remove(id: Int) {
        worlds.removeIf { it.id == id }
    }

    private fun getId(): Int {
        val ids = worlds.map { it.id }
        for(i in 1 until Int.MAX_VALUE) {
            if(!ids.contains(i)) {
                return i
            }
        }
        return -1
    }

    override fun set(id: Int, info: Details) {
        info.id = id
        worlds.add(info)
    }
}