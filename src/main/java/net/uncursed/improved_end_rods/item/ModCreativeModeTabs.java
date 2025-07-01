package net.uncursed.improved_end_rods.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.uncursed.improved_end_rods.ImprovedEndRods;
import net.uncursed.improved_end_rods.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ImprovedEndRods.MOD_ID);

    public static final Supplier<CreativeModeTab> IMPROVED_END_RODS_TAB = CREATIVE_MODE_TAB.register("end_rod_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.ENDLESS_END_ROD.get()))
                    .title(Component.translatable("creativetab.improvedendrods.end_rod_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.ENDLESS_END_ROD);
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
