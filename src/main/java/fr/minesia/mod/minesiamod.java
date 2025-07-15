package fr.minesia.mod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = "minesiamod", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {

    @ObjectHolder("minesiamod:rubis")
    public static final Item RUBIS = null;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                new Item(new Item.Properties().tab(ItemGroup.TAB_MISC))
                        .setRegistryName("minesiamod", "rubis")
        );
    }
}
