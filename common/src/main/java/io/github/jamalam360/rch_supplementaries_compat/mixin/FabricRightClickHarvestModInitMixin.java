package io.github.jamalam360.rch_supplementaries_compat.mixin;

import io.github.jamalam360.rch_supplementaries_compat.RchSupplementariesCompatPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("UnreachableCode")
@Pseudo
@Mixin(targets = "io.github.jamalam360.rightclickharvest.RightClickHarvestModInit")
public class FabricRightClickHarvestModInitMixin {

    @Unique
    private static Level rch_supplementaries_compat$level = null;

    @Inject(
          method = "onBlockUse(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/InteractionResult;",
          at = @At("HEAD")
    )
    private static void rch_supplementaries_compat$fabric$storeLevel(Player player, Level level, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        rch_supplementaries_compat$level = level;
    }

    @ModifyArg(
          method = "onBlockUse(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/InteractionResult;",
          at = @At(
                value = "INVOKE",
                target = "Lio/github/jamalam360/rightclickharvest/RightClickHarvestModInit;onBlockUse(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;Z)Lnet/minecraft/world/InteractionResult;"
          ),
          index = 3
    )
    private static BlockHitResult rch_supplementaries_compat$fabric$modifyForSupplementaries(BlockHitResult hitResult) {
        BlockPos original = hitResult.getBlockPos();

        if (rch_supplementaries_compat$level.getBlockState(original.below()).is(RchSupplementariesCompatPlatform.getFlaxBlock())) {
            return new BlockHitResult(hitResult.getLocation(), hitResult.getDirection(), original.below(), hitResult.isInside());
        } else {
            return hitResult;
        }
    }
}
