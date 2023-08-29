package gg.rsmod.plugins.content.mechanics.shops

import gg.rsmod.plugins.api.cfg.Items

/**
 * @author Tom <rspsmods@gmail.com>
 */
class CoinCurrency : ItemCurrency(Items.COINS_995, singularCurrency = "coin", pluralCurrency = "coins")
class AgilityTickets : ItemCurrency(Items.AGILITY_ARENA_TICKET, singularCurrency = "agility arena ticket", pluralCurrency = "agility arena tickets")