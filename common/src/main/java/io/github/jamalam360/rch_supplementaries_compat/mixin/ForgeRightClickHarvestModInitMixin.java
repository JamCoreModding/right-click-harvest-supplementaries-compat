package io.github.jamalam360.rch_supplementaries_compat.mixin;

import io.github.jamalam360.rch_supplementaries_compat.RchSupplementariesCompatPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@SuppressWarnings("UnreachableCode")
@Pseudo
@Mixin(targets = "io.github.jamalam360.rightclickharvest.RightClickHarvestModInit")
public class ForgeRightClickHarvestModInitMixin {

    @Unique
    private static Level rch_supplementaries_compat$level = null;

    @ModifyArg(
          method = "onBlockUse(Lnet/minecraftforge/event/entity/player/PlayerInteractEvent$RightClickBlock;)V",
          at = @At(
                value = "INVOKE",
                target = "Lio/github/jamalam360/rightclickharvest/RightClickHarvestModInit;onBlockUse(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;Z)Lnet/minecraft/world/InteractionResult;"
          ),
          index = 1
    )
    private Level rch_supplementaries_compat$forge$storeLevel(Level level) {
        rch_supplementaries_compat$level = level;
        return level;
    }

    @ModifyArg(
          method = "onBlockUse(Lnet/minecraftforge/event/entity/player/PlayerInteractEvent$RightClickBlock;)V",
          at = @At(
                value = "INVOKE",
                target = "Lio/github/jamalam360/rightclickharvest/RightClickHarvestModInit;onBlockUse(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;Z)Lnet/minecraft/world/InteractionResult;"
          ),
          index = 3
    )
    private BlockHitResult rch_supplementaries_compat$forge$modifyForSupplementaries(BlockHitResult hitResult) {
        BlockPos original = hitResult.getBlockPos();

        if (rch_supplementaries_compat$level.getBlockState(original.below()).is(RchSupplementariesCompatPlatform.getFlaxBlock())) {
            return new BlockHitResult(hitResult.getLocation(), hitResult.getDirection(), original.below(), hitResult.isInside());
        } else {
            return hitResult;
        }
    }
}
