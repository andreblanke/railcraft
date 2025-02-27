package mods.railcraft.world.item.crafting;

import org.jetbrains.annotations.Nullable;
import mods.railcraft.world.item.RailcraftItems;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import vazkii.patchouli.api.PatchouliAPI;

public class PatchouliBookCrafting extends ShapelessRecipe {

  private static final String NBT_KEY = "patchouli:book";
  private static final String NBT_VAL = "railcraft:guide_book";
  @Nullable
  private static Item GUIDE_BOOK;

  public PatchouliBookCrafting(CraftingBookCategory category) {
    super("", category, makeGuideBook(), NonNullList.of(Ingredient.EMPTY,
        Ingredient.of(Items.BOOK), Ingredient.of(RailcraftItems.IRON_CROWBAR.get())));
  }

  private static Item guide_book() {
    if (GUIDE_BOOK == null) {
      GUIDE_BOOK = BuiltInRegistries.ITEM
          .get(ResourceLocation.fromNamespaceAndPath(PatchouliAPI.MOD_ID, "guide_book"));
    }
    return GUIDE_BOOK;
  }

  private static ItemStack makeGuideBook() {
    var book = new ItemStack(guide_book());
    //FIXME
    //var tag = book.getOrCreateTag();
    //tag.putString(NBT_KEY, NBT_VAL);
    return book;
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return RailcraftRecipeSerializers.PATCHOULI_BOOK_CRAFTING.get();
  }
}
