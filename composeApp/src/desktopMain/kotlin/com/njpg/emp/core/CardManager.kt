package com.njpg.emp.core

object CardManager {
    private var _positions: List<CardPosition> = loadAllPositions()
    val positions: List<CardPosition>
        get() = _positions

    fun updatePosition(pos: CardPosition) {
        _positions = _positions.filter { it.id != pos.id } + pos
    }

    fun savePositions() {
        saveAllPositions(_positions)
    }
}