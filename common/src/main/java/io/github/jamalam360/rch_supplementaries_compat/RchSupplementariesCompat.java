package io.github.jamalam360.rch_supplementaries_compat;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.function.Supplier;

public class RchSupplementariesCompat {
	@Nullable
	private static Block flaxBlock = null;

	@Nullable
    public static Block getFlaxBlock() {
        if (flaxBlock != null) {
			return flaxBlock;
        }

		try {
			Class<?> clazz = Class.forName("net.mehvahdjukaar.supplementaries.reg.ModRegistry");
			Field field = clazz.getField("FLAX");
			@SuppressWarnings("unchecked") Supplier<Block> supplier = (Supplier<Block>) field.get(null);
			flaxBlock = supplier.get();
			return flaxBlock;
		} catch (Exception e) {
			return null;
		}
    }
}
