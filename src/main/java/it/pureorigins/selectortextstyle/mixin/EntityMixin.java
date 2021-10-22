package it.pureorigins.selectortextstyle.mixin;

import it.pureorigins.selectortextstyle.SelectorTextStyle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.HoverEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
  @Inject(method = "getHoverEvent", at = @At("HEAD"), cancellable = true)
  private void getHoverEvent(CallbackInfoReturnable<HoverEvent> callback) {
    if ((Entity) (Object) this instanceof PlayerEntity) {
      var style = SelectorTextStyle.INSTANCE.getDisplayNameStyle((PlayerEntity) (Object) this);
      callback.setReturnValue(style.getHoverEvent());
    }
  }
}
