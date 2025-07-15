
package palamod.init;

import palamod.jei_recipes.PaladiummachinerecipetypejeiRecipeCategory;
import palamod.jei_recipes.PaladiummachinerecipetypejeiRecipe;
import palamod.jei_recipes.JobsminercraftjeiRecipeCategory;
import palamod.jei_recipes.JobsminercraftjeiRecipe;
import palamod.jei_recipes.JobsfarmercraftrecipetypeRecipeCategory;
import palamod.jei_recipes.JobsfarmercraftrecipetypeRecipe;
import palamod.jei_recipes.GrinderhighrecipetypejeiRecipeCategory;
import palamod.jei_recipes.GrinderhighrecipetypejeiRecipe;
import palamod.jei_recipes.CrusherrecipetypeRecipeCategory;
import palamod.jei_recipes.CrusherrecipetypeRecipe;

import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;

import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;

import java.util.stream.Collectors;
import java.util.Objects;
import java.util.List;

@JeiPlugin
public class PalamodModJeiPlugin implements IModPlugin {
	public static mezz.jei.api.recipe.RecipeType<CrusherrecipetypeRecipe> Crusherrecipetype_Type = new mezz.jei.api.recipe.RecipeType<>(CrusherrecipetypeRecipeCategory.UID, CrusherrecipetypeRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<PaladiummachinerecipetypejeiRecipe> Paladiummachinerecipetypejei_Type = new mezz.jei.api.recipe.RecipeType<>(PaladiummachinerecipetypejeiRecipeCategory.UID, PaladiummachinerecipetypejeiRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<GrinderhighrecipetypejeiRecipe> Grinderhighrecipetypejei_Type = new mezz.jei.api.recipe.RecipeType<>(GrinderhighrecipetypejeiRecipeCategory.UID, GrinderhighrecipetypejeiRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<JobsminercraftjeiRecipe> Jobsminercraftjei_Type = new mezz.jei.api.recipe.RecipeType<>(JobsminercraftjeiRecipeCategory.UID, JobsminercraftjeiRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<JobsfarmercraftrecipetypeRecipe> Jobsfarmercraftrecipetype_Type = new mezz.jei.api.recipe.RecipeType<>(JobsfarmercraftrecipetypeRecipeCategory.UID, JobsfarmercraftrecipetypeRecipe.class);

	@Override
	public ResourceLocation getPluginUid() {
		return ResourceLocation.parse("palamod:jei_plugin");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new CrusherrecipetypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new PaladiummachinerecipetypejeiRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new GrinderhighrecipetypejeiRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new JobsminercraftjeiRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new JobsfarmercraftrecipetypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
		List<CrusherrecipetypeRecipe> CrusherrecipetypeRecipes = recipeManager.getAllRecipesFor(CrusherrecipetypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(Crusherrecipetype_Type, CrusherrecipetypeRecipes);
		List<PaladiummachinerecipetypejeiRecipe> PaladiummachinerecipetypejeiRecipes = recipeManager.getAllRecipesFor(PaladiummachinerecipetypejeiRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(Paladiummachinerecipetypejei_Type, PaladiummachinerecipetypejeiRecipes);
		List<GrinderhighrecipetypejeiRecipe> GrinderhighrecipetypejeiRecipes = recipeManager.getAllRecipesFor(GrinderhighrecipetypejeiRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(Grinderhighrecipetypejei_Type, GrinderhighrecipetypejeiRecipes);
		List<JobsminercraftjeiRecipe> JobsminercraftjeiRecipes = recipeManager.getAllRecipesFor(JobsminercraftjeiRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(Jobsminercraftjei_Type, JobsminercraftjeiRecipes);
		List<JobsfarmercraftrecipetypeRecipe> JobsfarmercraftrecipetypeRecipes = recipeManager.getAllRecipesFor(JobsfarmercraftrecipetypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(Jobsfarmercraftrecipetype_Type, JobsfarmercraftrecipetypeRecipes);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(PalamodModBlocks.PALADIUM_CRUSHER.get().asItem()), Crusherrecipetype_Type);
		registration.addRecipeCatalyst(new ItemStack(PalamodModBlocks.PALADIUM_MACHINE.get().asItem()), Paladiummachinerecipetypejei_Type);
		registration.addRecipeCatalyst(new ItemStack(PalamodModBlocks.GRINDER_BLOCK.get().asItem()), Grinderhighrecipetypejei_Type);
		registration.addRecipeCatalyst(new ItemStack(PalamodModBlocks.TCV_2.get().asItem()), Grinderhighrecipetypejei_Type);
		registration.addRecipeCatalyst(new ItemStack(PalamodModItems.MINERJOBSITEM.get()), Jobsminercraftjei_Type);
		registration.addRecipeCatalyst(new ItemStack(PalamodModItems.FARMERJOBSITEM.get()), Jobsfarmercraftrecipetype_Type);
	}
}
