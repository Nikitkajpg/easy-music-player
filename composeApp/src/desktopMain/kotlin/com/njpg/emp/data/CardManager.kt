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
    private val _positions: MutableList<CardPosition> = loadAll().toMutableList()

    /** Список всех сохранённых позиций карточек (только для чтения) */
    val positions: List<CardPosition>
        get() = _positions

    /**
     * Обновляет позицию карточки.
     *
     * Если карточка с таким [pos.id] уже существует, её позиция заменяется,
     * иначе новая позиция добавляется в список.
     *
     * @param pos [CardPosition] объект с идентификатором и координатами карточки.
     */
    fun update(pos: CardPosition) {
        val index = _positions.indexOfFirst { it.id == pos.id }
        if (index >= 0) {
            _positions[index] = pos
        } else {
            _positions.add(pos)
        }
    }

    fun save() {
        saveAll(_positions)
    }
}