package it.pureorigins.selectortextstyle

import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import it.pureorigins.framework.configuration.configFile
import it.pureorigins.framework.configuration.json
import it.pureorigins.framework.configuration.readFileAs
import it.pureorigins.framework.configuration.templateJson
import kotlinx.serialization.Serializable
import net.fabricmc.api.ModInitializer
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.text.Style

object SelectorTextStyle : ModInitializer {
    private lateinit var playerStyle: String
    
    private val gson = GsonBuilder().registerTypeHierarchyAdapter(Style::class.java, Style.Serializer()).disableHtmlEscaping().create()
    fun getDisplayNameStyle(player: PlayerEntity): Style {
        return try {
            gson.fromJson(playerStyle.templateJson("player" to player), Style::class.java) ?: Style.EMPTY
        } catch (e: JsonParseException) {
            Style.EMPTY
        }
    }
    
    override fun onInitialize() {
        val (playerStyle) = json.readFileAs(configFile("selector_text_style.json"), Config())
        this.playerStyle = playerStyle
    }
    
    @Serializable
    data class Config(
        val playerStyle: String = "{}"
    )
}
