package io.github.jamalam360.rch_supplementaries_compat.mixin;

import io.github.jamalam360.rch_supplementaries_compat.RchSupplementariesCompatPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Pseudo
@Mixin(targets = "io.github.jamalam360.rightclickharvest.RightClickHarvest")
public class RightClickHarvestMixin {

    @ModifyArg(
          method = "onBlockUse",
          at = @At(
                value = "INVOKE",
                target = "Lio/github/jamalam360/rightclickharvest/RightClickHarvest;onBlockUse(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;Z)Lnet/minecraft/world/InteractionResult;"
          ),
          index = 3
    )
    private static BlockHitResult rch_supplementaries_compat$modifyForSupplementaries(Player player, Level level, InteractionHand hand, BlockHitResult hitResult, boolean initialCall) {
        System.out.print("rch_supplementaries_compat$modifyForSupplementaries");

        BlockPos belowPos = hitResult.getBlockPos().below();

        if (level.getBlockState(belowPos).is(RchSupplementariesCompatPlatform.getFlaxBlock())) {
            return hitResult.withPosition(belowPos);
        } else {
            return hitResult;
        }
    }
}
