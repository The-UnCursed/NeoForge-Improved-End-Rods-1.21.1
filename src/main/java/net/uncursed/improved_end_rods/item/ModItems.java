package net.uncursed.improved_end_rods.item;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.uncursed.improved_end_rods.ImprovedEndRods;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ImprovedEndRods.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
