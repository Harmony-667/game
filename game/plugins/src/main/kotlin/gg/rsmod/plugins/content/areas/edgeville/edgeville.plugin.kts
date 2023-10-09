package gg.rsmod.plugins.content.areas.edgeville

/**
 * @author Eikenb00m <https://github.com/eikenb00m>
 */

private val EDGEVILLE_REGIONS = intArrayOf(12342) //Multi combat
EDGEVILLE_REGIONS.forEach { set_multi_combat_region(region = it) }