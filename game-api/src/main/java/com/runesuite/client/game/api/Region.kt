package com.runesuite.client.game.api

data class Region(val x: Int, val y: Int, val plane: Int) {

    internal constructor(packed: Int) : this(packed shr 14 and 255, packed and 255 , packed shr 28)

    val base get() = com.runesuite.client.game.api.GlobalTile(x * com.runesuite.client.game.api.Region.Companion.SIZE, y * com.runesuite.client.game.api.Region.Companion.SIZE, plane)

    val corners get() = base.run { listOf(
            this,
            copy(x = x + com.runesuite.client.game.api.Region.Companion.SIZE - 1),
            copy(x = x + com.runesuite.client.game.api.Region.Companion.SIZE - 1, y = y + com.runesuite.client.game.api.Region.Companion.SIZE - 1),
            copy(y = y + com.runesuite.client.game.api.Region.Companion.SIZE - 1)) }

    internal val packed get() = x shl 14 or y or plane shl 28

    companion object {
        const val SIZE = 64
    }
}