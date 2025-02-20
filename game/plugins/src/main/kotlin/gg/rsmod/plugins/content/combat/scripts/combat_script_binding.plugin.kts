package gg.rsmod.plugins.content.combat.scripts

import AberrantSpectreCombatScript
import NezikchendCombatScript
import UngaduluLevel169CombatScript
import gg.rsmod.plugins.content.combat.scripts.impl.*

/**
 * We can use this file to bind the combat scripts for the npcs.
 * Keeps them all in one place.
 * @author Kevin Senez <ksenez94@gmail.com>
 */


/**
 * Sets the [on_npc_combat] for Aberrant Spectres
 */
on_npc_combat(*AbyssalDemonCombatScript.ids) {
    npc.queue {
        AbyssalDemonCombatScript.handleSpecialCombat(this)
    }
}

/**
 * Sets the [on_npc_combat] for Aberrant Spectres
 */
on_npc_combat(*AberrantSpectreCombatScript.ids) {
    npc.queue {
        AberrantSpectreCombatScript.handleSpecialCombat(this)
    }
}

/**
 * Sets the [on_npc_combat] for Regular Dragons
 */
on_npc_combat(*DragonCombatScript.ids) {
    npc.queue {
        DragonCombatScript.handleSpecialCombat(this)
    }
}

/**
 * Sets the [on_npc_combat] for Imps
 */
on_npc_combat(*ImpCombatScript.ids) {
    npc.queue {
        ImpCombatScript.handleSpecialCombat(this)
    }
}

/**
 * Sets the [on_npc_combat] for Highwaymen
 */
on_npc_combat(*HighwaymanCombatScript.ids) {
    npc.queue {
        HighwaymanCombatScript.handleSpecialCombat(this)
    }
}

/**
 * Sets the [on_npc_combat] for Cockatrices
 */
on_npc_combat(*CockatriceCombatScript.ids) {
    npc.queue {
        CockatriceCombatScript.handleSpecialCombat(this)
    }
}

/**
 * Sets the [on_npc_combat] for Banshees
 */
on_npc_combat(*BansheeCombatScript.ids) {
    npc.queue {
        BansheeCombatScript.handleSpecialCombat(this)
    }
}

/**
 * Sets the [on_npc_combat] for Bloodvelds
 */
on_npc_combat(*BloodveldCombatScript.ids) {
    npc.queue {
        BloodveldCombatScript.handleSpecialCombat(this)
    }
}

/**
 * Sets the [on_npc_combat] for Canifis Citizens
 */
on_npc_combat(*CanifisCitizensCombatScript.ids) {
    npc.queue {
        CanifisCitizensCombatScript.handleSpecialCombat(this)
    }
}

/**
 * Sets the [on_npc_combat] for Arzinian Avatar Of Magic Level75
 */
on_npc_combat(*ArzinianAvatarOfMagic75CombatScript.ids) {
    npc.queue {
        ArzinianAvatarOfMagic75CombatScript.handleSpecialCombat(this)
    }
}
/**
 * Sets the [on_npc_combat] for San Tojalon Level 106
 */
on_npc_combat(*SanTojalonCombatScript.ids) {
    npc.queue {
        SanTojalonCombatScript.handleSpecialCombat(this)
    }
}
/**
 * Sets the [on_npc_combat] for Black Knight Titan
 */
on_npc_combat(*BlackKnightTitanCombatScript.ids) {
    npc.queue {
        BlackKnightTitanCombatScript.handleSpecialCombat(this)
    }
}
/**
 * Sets the [on_npc_combat] for Nezikchend
 */
on_npc_combat(*NezikchendCombatScript.ids) {
    npc.queue {
        NezikchendCombatScript.handleSpecialCombat(this)
    }
}
/**
 * Sets the [on_npc_combat] for KBD
 */
on_npc_combat(*KingBlackDragonCombatScript.ids) {
    npc.queue {
        KingBlackDragonCombatScript.handleSpecialCombat(this)
    }
}
/**
 * Sets the [on_npc_combat] for UNGADULU
 */
on_npc_combat(*UngaduluLevel169CombatScript.ids) {
    npc.queue {
        UngaduluLevel169CombatScript.handleSpecialCombat(this)
    }
}