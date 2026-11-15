package com.example.examplemod.item;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.item.custom.ExampleToolItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Registry de itens. Padrão:
 * <ul>
 *   <li>Item simples: {@code ITEMS.registerSimpleItem("nome")}</li>
 *   <li>Item com propriedades/classe própria: {@code ITEMS.registerItem("nome", props -> new MinhaClasse(props...))}</li>
 * </ul>
 * Lembre de: textura em assets/&lt;modid&gt;/textures/item, model no ModModelProvider,
 * nome no arquivo de lang e (opcional) aba criativa em ModCreativeModeTabs.
 */
public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExampleMod.MOD_ID);

    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item");

    public static final DeferredItem<Item> EXAMPLE_TOOL = ITEMS.registerItem("example_tool",
            properties -> new ExampleToolItem(properties.durability(64)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
