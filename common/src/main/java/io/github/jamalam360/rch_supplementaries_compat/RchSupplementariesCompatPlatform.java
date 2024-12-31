package io.github.jamalam360.rch_supplementaries_compat;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.level.block.Block;

public class RchSupplementariesCompatPlatform {
	@ExpectPlatform
    public static Block getFlaxBlock() {
        throw new AssertionError();
    }
}
