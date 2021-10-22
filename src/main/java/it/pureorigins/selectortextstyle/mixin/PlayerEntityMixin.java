package it.pureorigins.selectortextstyle.mixin;

import it.pureorigins.selectortextstyle.SelectorTextStyle;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
  /**
   * @author AgeOfWar
   * @reason Mod feature
   */
  @Overwrite
  private MutableText addTellClickEvent(MutableText component) {
    var style = SelectorTextStyle.INSTANCE.getDisplayNameStyle((PlayerEntity) (Object) this);
    return component.fillStyle(style);
  }
}
