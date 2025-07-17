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

import java.util.*;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ImprovedEndRods.MOD_ID);

    // Store registered blocks for easy access
    public static final Map<DyeColor, DeferredBlock<Block>> ENDLESS_END_ROD_COLORED = new HashMap<>();
    public static final Map<DyeColor, DeferredBlock<Block>> END_ROD_COLORED = new HashMap<>();

    // List to store all blocks for easy iteration in datagen
    public static final List<DeferredBlock<Block>> ALL_BLOCKS = new ArrayList<>();

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
        ALL_BLOCKS.add((DeferredBlock<Block>) toReturn); // Add to our list for datagen
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    // Convenience methods for datagen
    public static List<DeferredBlock<Block>> getAllBlocks() {
        return Collections.unmodifiableList(ALL_BLOCKS);
    }

    public static Collection<DeferredBlock<Block>> getColoredEndlessEndRods() {
        return ENDLESS_END_ROD_COLORED.values();
    }

    public static Collection<DeferredBlock<Block>> getColoredEndRods() {
        return END_ROD_COLORED.values();
    }

    // Get all endless end rods (including white/default and colored)
    public static List<DeferredBlock<Block>> getAllEndlessEndRods() {
        List<DeferredBlock<Block>> endlessRods = new ArrayList<>();
        endlessRods.add(ENDLESS_END_ROD); // white/default
        endlessRods.addAll(ENDLESS_END_ROD_COLORED.values()); // colored variants
        return endlessRods;
    }

    // Get all regular end rods (colored only, since vanilla white exists)
    public static List<DeferredBlock<Block>> getAllEndRods() {
        return new ArrayList<>(END_ROD_COLORED.values());
    }

    // Get blocks that share the same model structure
    public static List<DeferredBlock<Block>> getEndRodModelBlocks() {
        return getAllEndRods(); // All regular end rods use same model, different textures
    }

    public static List<DeferredBlock<Block>> getEndlessEndRodModelBlocks() {
        return getAllEndlessEndRods(); // All endless end rods use same model, different textures
    }

    // Get blocks with unique models/textures
    public static List<DeferredBlock<Block>> getUniqueModelBlocks() {
        return List.of(BROKEN_END_ROD, BROKEN_ENDLESS_END_ROD);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}