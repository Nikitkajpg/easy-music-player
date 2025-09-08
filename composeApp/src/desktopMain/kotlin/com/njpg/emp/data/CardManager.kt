package com.njpg.emp.data

import com.njpg.emp.core.Card

object CardManager {
    private var _cards: MutableMap<String, Card> = mutableMapOf()

    val cards: List<Card>
        get() = _cards.values.toList()

    suspend fun init() {
        _cards = CardPositionStorage.loadAll().associateBy { it.id }.toMutableMap()
    }

    fun update(card: Card) {
        _cards[card.id] = card
    }

    suspend fun save() {
        CardPositionStorage.saveAll(cards)
    }
}