package net.uncursed.improved_end_rods.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.uncursed.improved_end_rods.block.ModBlocks;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        // Base endless end rod (white/default)
        dropSelf(ModBlocks.ENDLESS_END_ROD.get());

        // Broken variants (unique textures)
        dropSelf(ModBlocks.BROKEN_END_ROD.get());
        dropSelf(ModBlocks.BROKEN_ENDLESS_END_ROD.get());

        // All colored endless end rods (same model as base, different textures)
        ModBlocks.getColoredEndlessEndRods().forEach(block -> dropSelf(block.get()));

        // All colored regular end rods (same model as each other, different textures)
        ModBlocks.getColoredEndRods().forEach(block -> dropSelf(block.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}