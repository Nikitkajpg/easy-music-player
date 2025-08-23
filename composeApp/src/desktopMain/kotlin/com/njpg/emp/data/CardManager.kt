package com.njpg.emp.data

import com.njpg.emp.core.Card
import com.njpg.emp.data.CardPositionStorage.loadAll
import com.njpg.emp.data.CardPositionStorage.saveAll

object CardManager {
    private val _cards: MutableList<Card> = loadAll().toMutableList()

    val cards: List<Card>
        get() = _cards

    fun update(card: Card) {
        val index = _cards.indexOfFirst { it.id == card.id }
        if (index >= 0) {
            _cards[index] = card
        } else {
            _cards.add(card)
        }
    }

    fun save() {
        saveAll(_cards)
    }
}