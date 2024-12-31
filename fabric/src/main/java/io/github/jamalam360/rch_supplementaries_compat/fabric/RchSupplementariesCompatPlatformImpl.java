package io.github.jamalam360.rch_supplementaries_compat.fabric;

import net.mehvahdjukaar.supplementaries.reg.ModRegistry;
import net.minecraft.world.level.block.Block;

public class RchSupplementariesCompatPlatformImpl {
    public static Block getFlaxBlock() {
        return ModRegistry.FLAX.get();
    }
}
