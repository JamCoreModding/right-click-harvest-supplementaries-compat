package io.github.jamalam360.rch_supplementaries_compat.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import io.github.jamalam360.rch_supplementaries_compat.RchSupplementariesCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.*;

@Pseudo
@Mixin(targets = "io.github.jamalam360.rightclickharvest.RightClickHarvest")
public abstract class RightClickHarvestMixin {
    @WrapMethod(
            method = "onBlockUse"
    )
    private static InteractionResult rch_supplementaries_compat$modifyForSupplementaries(Player player, Level level, InteractionHand hand, BlockHitResult hitResult, boolean initialCall, Operation<InteractionResult> original) {
        Block flaxBlock = RchSupplementariesCompat.getFlaxBlock();
        BlockPos belowPos = hitResult.getBlockPos().below();

        if (level.getBlockState(belowPos).is(flaxBlock)) {
            hitResult = new BlockHitResult(hitResult.getLocation().subtract(0, 1, 0), hitResult.getDirection(), belowPos, hitResult.isInside());
        }

        return original.call(player, level, hand, hitResult, initialCall);
    }
}
