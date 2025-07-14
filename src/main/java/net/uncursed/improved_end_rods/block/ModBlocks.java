package net.uncursed.improved_end_rods.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EndRodBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.uncursed.improved_end_rods.ImprovedEndRods;
import net.uncursed.improved_end_rods.item.ModItems;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ImprovedEndRods.MOD_ID);

    // Store registered blocks for easy access
    public static final Map<DyeColor, DeferredBlock<Block>> ENDLESS_END_ROD_COLORED = new HashMap<>();
    public static final Map<DyeColor, DeferredBlock<Block>> END_ROD_COLORED = new HashMap<>();

    public static final DeferredBlock<Block> ENDLESS_END_ROD = registerBlock("endless_end_rod",
            () -> new EndRodBlock(BlockBehaviour.Properties.of()
                    .forceSolidOff().instabreak().lightLevel(p -> 14).sound(SoundType.WOOD).noOcclusion()));

    public static final DeferredBlock<Block> BROKEN_END_ROD = registerBlock("broken_end_rod",
            () -> new EndRodBlock(BlockBehaviour.Properties.of()
                    .forceSolidOff().instabreak().lightLevel(p -> 14).sound(SoundType.WOOD).noOcclusion()));

    public static final DeferredBlock<Block> BROKEN_ENDLESS_END_ROD = registerBlock("broken_endless_end_rod",
            () -> new EndRodBlock(BlockBehaviour.Properties.of()
                    .forceSolidOff().instabreak().lightLevel(p -> 14).sound(SoundType.WOOD).noOcclusion()));

    static {
        for (DyeColor color : DyeColor.values()) {
            String colorName = color.getName(); // returns lowercase color string, e.g., "red"

            if (!colorName.equals("white")) {
                DeferredBlock<Block> endlessRod = registerBlock("endless_end_rod_" + colorName,
                        () -> new EndRodBlock(BlockBehaviour.Properties.of()
                                .forceSolidOff().instabreak().lightLevel(p -> 14).sound(SoundType.WOOD).noOcclusion()));

                DeferredBlock<Block> rod = registerBlock("end_rod_" + colorName,
                        () -> new EndRodBlock(BlockBehaviour.Properties.of()
                                .forceSolidOff().instabreak().lightLevel(p -> 14).sound(SoundType.WOOD).noOcclusion()));

                ENDLESS_END_ROD_COLORED.put(color, endlessRod);
                END_ROD_COLORED.put(color, rod);
            }
        }
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}