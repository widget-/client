package org.runestar.client.plugins.inventorygrid

import org.runestar.client.api.forms.RgbaForm
import org.runestar.client.api.util.DisposablePlugin
import org.runestar.client.game.api.live.LiveCanvas
import org.runestar.client.game.api.live.Widgets
import org.runestar.client.plugins.spi.PluginSettings

class InventoryGrid : DisposablePlugin<InventoryGrid.Settings>() {

    override val defaultSettings = Settings()

    override val name = "Inventory Grid"

    override fun onStart() {
        val color = settings.color.get()
        add(LiveCanvas.repaints.subscribe { g ->
            val inv = Widgets.dragInventory ?: return@subscribe
            if (!inv.isActive) return@subscribe
            g.color = color
            for ((item, shape) in inv.items) {
                if (item == null) g.fill(shape)
            }
        })
    }

    class Settings(
            val color: RgbaForm = RgbaForm(255, 255, 255, 30)
    ) : PluginSettings()
}