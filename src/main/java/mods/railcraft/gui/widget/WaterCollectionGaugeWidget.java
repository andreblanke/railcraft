package mods.railcraft.gui.widget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import mods.railcraft.RailcraftConfig;
import mods.railcraft.Translations;
import mods.railcraft.util.HumanReadableNumberFormatter;
import mods.railcraft.world.level.material.StandardTank;
import mods.railcraft.world.module.WaterCollectionModule;
import net.minecraft.ChatFormatting;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.server.level.ServerPlayer;

public class WaterCollectionGaugeWidget extends FluidGaugeWidget {

  private final Collection<WaterCollectionModule> modules;

  private List<Component> tooltip;

  public WaterCollectionGaugeWidget(Collection<WaterCollectionModule> modules, StandardTank tank,
      int x, int y, int u, int v, int w, int h) {
    super(tank, x, y, u, v, w, h);
    this.modules = modules;
  }

  @Override
  public List<Component> getTooltip() {
    return this.tooltip;
  }

  private void refresh() {
    int count = 0;
    double temperaturePenalty = 0;
    double humidityMultiplier = 0;
    double precipitationMultiplier = 0;

    for (var module : this.modules) {
      var state = module.getState();
      if (state.skyVisible()) {
        count++;
        temperaturePenalty += state.temperaturePenalty();
        humidityMultiplier += state.humidityMultiplier();
        precipitationMultiplier += state.precipitationMultiplier();
      }
    }

    if (count > 0) {
      humidityMultiplier /= count;
      precipitationMultiplier /= count;
    }

    this.tooltip = new ArrayList<>();

    var baseRate = RailcraftConfig.SERVER.waterCollectionRate.get();

    this.tooltip.addAll(this.tank.getTooltip());
    this.tooltip.add(Component.empty());
    this.tooltip.add(Component.translatable(Translations.Screen.WATER_TANK_SEE_SKY,
            HumanReadableNumberFormatter.format(count))
        .withStyle(ChatFormatting.GRAY));
    this.tooltip.add(Component.translatable(Translations.Screen.WATER_TANK_BASE_RATE,
            HumanReadableNumberFormatter.format(baseRate * count))
        .withStyle(ChatFormatting.GRAY));
    this.tooltip.add(Component.translatable(Translations.Screen.WATER_TANK_HUMIDITY,
            HumanReadableNumberFormatter.format(humidityMultiplier))
        .withStyle(ChatFormatting.GRAY));
    this.tooltip.add(Component.translatable(Translations.Screen.WATER_TANK_PRECIPITATION,
            HumanReadableNumberFormatter.format(precipitationMultiplier))
        .withStyle(ChatFormatting.GRAY));
    this.tooltip.add(Component.translatable(Translations.Screen.WATER_TANK_TEMP,
            HumanReadableNumberFormatter.format(-temperaturePenalty))
        .withStyle(ChatFormatting.GRAY));
    this.tooltip.add(Component.translatable(Translations.Screen.WATER_TANK_FINAL_RATE,
            HumanReadableNumberFormatter.format(
                (baseRate * count * humidityMultiplier * precipitationMultiplier)
                    - temperaturePenalty))
        .withStyle(ChatFormatting.GRAY));
  }

  @Override
  public void writeToBuf(ServerPlayer player, RegistryFriendlyByteBuf out) {
    super.writeToBuf(player, out);
    this.refresh();
    out.writeVarInt(this.tooltip.size());
    this.tooltip.forEach(component -> ComponentSerialization.STREAM_CODEC.encode(out, component));
  }

  @Override
  public void readFromBuf(RegistryFriendlyByteBuf in) {
    super.readFromBuf(in);
    this.tooltip = new ArrayList<>();
    int size = in.readVarInt();
    for (int i = 0; i < size; i++) {
      this.tooltip.add(ComponentSerialization.STREAM_CODEC.decode(in));
    }
  }
}
