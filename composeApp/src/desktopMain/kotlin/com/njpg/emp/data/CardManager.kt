package com.njpg.emp.data

import com.njpg.emp.core.CardPosition
import com.njpg.emp.data.CardPositionStorage.loadAll
import com.njpg.emp.data.CardPositionStorage.saveAll

/**
 * Менеджер позиций карточек на экране.
 *
 * Отвечает за хранение текущих позиций [CardPosition], их обновление и сохранение.
 * Использует [CardPositionStorage] для загрузки и сохранения данных в JSON-файл.
 */
object CardManager {
    private val positions: MutableList<CardPosition> = loadAll().toMutableList()

    /** Список всех сохранённых позиций карточек (только для чтения) */
    val allPositions: List<CardPosition>
        get() = positions

    /**
     * Обновляет позицию карточки.
     *
     * Если карточка с таким [pos.id] уже существует, её позиция заменяется,
     * иначе новая позиция добавляется в список.
     *
     * @param pos [CardPosition] объект с идентификатором и координатами карточки.
     */
    fun updatePosition(pos: CardPosition) {
        val index = positions.indexOfFirst { it.id == pos.id }
        if (index >= 0) {
            positions[index] = pos
        } else {
            positions.add(pos)
        }
    }

    fun savePositions() {
        saveAll(positions)
    }
}