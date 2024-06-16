package io.github.jamalam360.rch_supplementaries_compat.forge;

import net.mehvahdjukaar.supplementaries.reg.ModRegistry;
import net.minecraft.world.level.block.Block;

public class RchSupplementariesCompatPlatformImpl {

    public static Block getFlaxBlock() {
        return ModRegistry.FLAX.get();
    }

    public static boolean isFabric() {
        return false;
    }
}
