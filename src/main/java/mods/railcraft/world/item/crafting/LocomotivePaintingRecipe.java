package mods.railcraft.world.item.crafting;

import java.util.ArrayList;
import mods.railcraft.world.item.LocomotiveItem;
import mods.railcraft.world.item.RailcraftItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;

public class LocomotivePaintingRecipe extends CustomRecipe {

  public LocomotivePaintingRecipe(CraftingBookCategory category) {
    super(category);
  }

  private ItemStack getItemStackInRow(CraftingInput craftingInput, int row) {
    int width = craftingInput.width();
    var result = new ArrayList<ItemStack>();
    for (int i = 0; i < craftingInput.width(); i++) {
      var item = craftingInput.getItem(row * width + i);
      if (!item.isEmpty()) {
        result.add(item);
      }
    }
    return result.size() != 1 ? ItemStack.EMPTY : result.getFirst();
  }

  @Override
  public boolean matches(CraftingInput craftingInput, Level level) {
    if (craftingInput.height() < 3)
      return false;
    var dyePrimary = getItemStackInRow(craftingInput, 0);
    if (!(dyePrimary.getItem() instanceof DyeItem))
      return false;
    var loco = getItemStackInRow(craftingInput, 1);
    if (!(loco.getItem() instanceof LocomotiveItem))
      return false;
    var dyeSecondary = getItemStackInRow(craftingInput, 2);
    return dyeSecondary.getItem() instanceof DyeItem;
  }

  @Override
  public ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider provider) {
    var dyePrimary = getItemStackInRow(craftingInput, 0);
    var loco = getItemStackInRow(craftingInput, 1);
    var dyeSecondary = getItemStackInRow(craftingInput, 2);

    if (!(dyePrimary.getItem() instanceof DyeItem primaryItem)) {
      return ItemStack.EMPTY;
    }
    if (!(loco.getItem() instanceof LocomotiveItem locomotiveItem)) {
      return ItemStack.EMPTY;
    }
    if (!(dyeSecondary.getItem() instanceof DyeItem secondaryItem)) {
      return ItemStack.EMPTY;
    }

    var primaryColor = primaryItem.getDyeColor();
    var secondaryColor = secondaryItem.getDyeColor();
    var result = new ItemStack(locomotiveItem);
    var components = loco.getComponents();
    result.applyComponents(components);
    LocomotiveItem.setItemColorData(result, primaryColor, secondaryColor);
    return result;
  }

  @Override
  public NonNullList<Ingredient> getIngredients() {
    var ingredients = NonNullList.withSize(9, Ingredient.EMPTY);
    ingredients.set(1, Ingredient.of(Tags.Items.DYES));
    ingredients.set(4, Ingredient.of(RailcraftItems.STEAM_LOCOMOTIVE.get()));
    ingredients.set(7, Ingredient.of(Tags.Items.DYES));
    return ingredients;
  }

  @Override
  public ItemStack getResultItem(HolderLookup.Provider provider) {
    return new ItemStack(RailcraftItems.STEAM_LOCOMOTIVE.get());
  }

  @Override
  public boolean canCraftInDimensions(int width, int height) {
    return width >= 1 && height >= 3;
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return RailcraftRecipeSerializers.LOCOMOTIVE_PAINTING.get();
  }
}
