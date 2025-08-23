package com.njpg.emp.data

import com.njpg.emp.core.Card
import com.njpg.emp.data.CardPositionStorage.loadAll
import com.njpg.emp.data.CardPositionStorage.saveAll

/**
 * Менеджер позиций карточек на экране.
 *
 * Отвечает за хранение текущих позиций [Card], их обновление и сохранение.
 * Использует [CardPositionStorage] для загрузки и сохранения данных в JSON-файл.
 */
object CardManager {
    private val _cards: MutableList<Card> = loadAll().toMutableList()

    /** Список всех сохранённых позиций карточек (только для чтения) */
    val cards: List<Card>
        get() = _cards

    /**
     * Обновляет позицию карточки.
     *
     * Если карточка с таким [card.id] уже существует, её позиция заменяется,
     * иначе новая позиция добавляется в список.
     */
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