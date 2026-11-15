package com.example.examplemod.datagen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.registries.MultiRegistryBootstrap;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

/** Receitas (shaped, shapeless, fornalha, cortador de pedra...). */
public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeContext, BootstrapContext<Advancement> advancementContext) {
        super(recipeContext, advancementContext);
    }

    public static MultiRegistryBootstrap create() {
        return RecipeProvider.asBootstrap(ModRecipeProvider::new);
    }

    @Override
    protected void buildRecipes() {
        // 9 itens -> 1 bloco
        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.EXAMPLE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.EXAMPLE_ITEM.get())
                .unlockedBy(getHasName(ModItems.EXAMPLE_ITEM.get()), has(ModItems.EXAMPLE_ITEM))
                .save(output);

        // 1 bloco -> 9 itens (nome explícito porque o resultado é o mesmo item de outras receitas)
        shapeless(RecipeCategory.MISC, ModItems.EXAMPLE_ITEM.get(), 9)
                .requires(ModBlocks.EXAMPLE_BLOCK)
                .unlockedBy(getHasName(ModBlocks.EXAMPLE_BLOCK.get()), has(ModBlocks.EXAMPLE_BLOCK))
                .save(output, ExampleMod.MOD_ID + ":example_item_from_example_block");

        shaped(RecipeCategory.TOOLS, ModItems.EXAMPLE_TOOL.get())
                .pattern(" A ")
                .pattern(" S ")
                .pattern(" S ")
                .define('A', ModItems.EXAMPLE_ITEM.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.EXAMPLE_ITEM.get()), has(ModItems.EXAMPLE_ITEM))
                .save(output);
    }
}
