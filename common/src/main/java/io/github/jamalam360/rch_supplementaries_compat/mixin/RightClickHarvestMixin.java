package io.github.jamalam360.rch_supplementaries_compat.mixin;

import io.github.jamalam360.rch_supplementaries_compat.RchSupplementariesCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Pseudo
@Mixin(targets = "io.github.jamalam360.rightclickharvest.RightClickHarvest")
public abstract class RightClickHarvestMixin {

    @Shadow
    private static InteractionResult completeHarvest(Level level, BlockState state, BlockPos pos, Player player, InteractionHand hand, ItemStack stackInHand, boolean hoeInUse, boolean removeReplant, Runnable setBlockAction) {
        throw new AbstractMethodError();
    }

    @Shadow
    private static BlockState getReplantState(BlockState state) {
        throw new AbstractMethodError();
    }

    @Redirect(
            method = "onBlockUse",
            at = @At(
                    value = "INVOKE",
                    target = "Lio/github/jamalam360/rightclickharvest/RightClickHarvest;completeHarvest(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;ZZLjava/lang/Runnable;)Lnet/minecraft/world/InteractionResult;",
                    ordinal = 1
            )
    )
    private static InteractionResult rch_supplementaries_compat$modifyForSupplementaries(Level level, BlockState state, BlockPos pos, Player player, InteractionHand hand, ItemStack stackInHand, boolean hoeInUse, boolean removeReplant, Runnable setBlockAction) {
        Block flaxBlock = RchSupplementariesCompat.getFlaxBlock();
        if (state.is(flaxBlock)) {
            BlockPos belowPos = pos.below();
            BlockState belowState = level.getBlockState(belowPos);
            if (belowState.is(flaxBlock)) {
                state = belowState;
                pos = belowPos;
                if (removeReplant)
                    setBlockAction = () -> level.setBlockAndUpdate(belowPos, getReplantState(belowState));
                else
                    setBlockAction = () -> level.removeBlock(belowPos, false);
            }
        }

        return completeHarvest(level, state, pos, player, hand, stackInHand, hoeInUse, removeReplant, setBlockAction);
    }
}
