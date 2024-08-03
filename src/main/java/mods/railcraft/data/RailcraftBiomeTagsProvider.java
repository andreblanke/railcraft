package mods.railcraft.data;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;
import mods.railcraft.api.core.RailcraftConstants;
import mods.railcraft.tags.RailcraftTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class RailcraftBiomeTagsProvider extends BiomeTagsProvider {

    public RailcraftBiomeTagsProvider(PackOutput output,
            CompletableFuture<HolderLookup.Provider> provider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, RailcraftConstants.ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(RailcraftTags.Biomes.HAS_SULFUR)
            .addTag(BiomeTags.IS_BADLANDS)
            .addTag(BiomeTags.IS_HILL)
            .addTag(BiomeTags.IS_MOUNTAIN)
            .addTag(Tags.Biomes.IS_PLATEAU)
            .add(Biomes.DEEP_DARK)
            .add(Biomes.WINDSWEPT_SAVANNA);
    }
}
