package jackpotloot;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.network.chat.Component;

import jackpotloot.Settings;
import net.minecraft.world.level.storage.loot.LootPool;

import java.util.Set;

public class Main implements ModInitializer {

	@Override
	public void onInitialize() {
		AttackBlockCallback.EVENT.register((player, level, hand, pos, direction) -> {
			BlockState state = level.getBlockState(pos);

			// spectator check
			if (!player.isSpectator() && player.getMainHandItem().isEmpty() && state.requiresCorrectToolForDrops() && level instanceof ServerLevel serverLevel) {
				player.hurtServer(serverLevel, level.damageSources().generic(), 1.0F);
			}

			return InteractionResult.PASS;
		});

		LootTableEvents.MODIFY_DROPS.register((holder, context, drops) -> {
			if (holder.is(Blocks.STONE.getLootTable().orElseThrow())) {
				if (drops.removeIf(stack -> stack.is(Items.COBBLESTONE))) {
					if (Math.random() < 0.45) { // holy scuffed luck code
						drops.add(new ItemStack(Items.COBBLESTONE, Settings.CobblestoneAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.COBBLESTONE, Settings.CobblestoneAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.COBBLESTONE, Settings.CobblestoneAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 999999999); // there's no way someone can bypass this without godmode
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very Unlucky..."), false);
					}

				}
			} else if (holder.is(Blocks.DEEPSLATE.getLootTable().orElseThrow())) {
				if (drops.removeIf(stack -> stack.is(Items.COBBLED_DEEPSLATE))) {
					if (Math.random() < 0.45) { // holy scuffed luck code
						drops.add(new ItemStack(Items.COBBLED_DEEPSLATE, Settings.DeepslateAmount.normal));
					} else if (Math.random() < 0.6) { // holy scuffed luck code
						drops.add(new ItemStack(Items.COBBLED_DEEPSLATE, Settings.DeepslateAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.COBBLED_DEEPSLATE, Settings.DeepslateAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 999999999); // there's no way someone can bypass this without godmode
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very Unlucky..."), false);
					}

				}
			} else if ((holder.is(Blocks.COAL_ORE.getLootTable().orElseThrow())) || (holder.is(Blocks.DEEPSLATE_COAL_ORE.getLootTable().orElseThrow()))) {
				if (drops.removeIf(stack -> stack.is(Items.COAL))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.COAL, Settings.CoalAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.COAL, Settings.CoalAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.COAL, Settings.CoalAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 999999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very Unlucky..."), false);
					}
				}
			} else if ((holder.is(Blocks.COPPER_ORE.getLootTable().orElseThrow())) || (holder.is(Blocks.DEEPSLATE_COPPER_ORE.getLootTable().orElseThrow()))) {
				if (drops.removeIf(stack -> stack.is(Items.RAW_COPPER))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.RAW_COPPER, Settings.CopperAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.RAW_COPPER, Settings.CopperAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.RAW_COPPER, Settings.CopperAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 99999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very unlucky..."), false);
					}
				}
			} else if ((holder.is(Blocks.IRON_ORE.getLootTable().orElseThrow())) || (holder.is(Blocks.DEEPSLATE_IRON_ORE.getLootTable().orElseThrow()))) {
				if (drops.removeIf(stack -> stack.is(Items.RAW_IRON))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.RAW_IRON, Settings.IronAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.RAW_IRON, Settings.IronAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.RAW_IRON, Settings.IronAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 99999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very unlucky..."), false);
					}
				}
			} else if ((holder.is(Blocks.LAPIS_ORE.getLootTable().orElseThrow())) || (holder.is(Blocks.DEEPSLATE_LAPIS_ORE.getLootTable().orElseThrow()))) {
				if (drops.removeIf(stack -> stack.is(Items.LAPIS_LAZULI))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.LAPIS_LAZULI, Settings.LapisLazuilAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.LAPIS_LAZULI, Settings.LapisLazuilAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.LAPIS_LAZULI, Settings.LapisLazuilAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 99999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very unlucky..."), false);
					}
				}
			} else if ((holder.is(Blocks.REDSTONE_ORE.getLootTable().orElseThrow())) || (holder.is(Blocks.DEEPSLATE_REDSTONE_ORE.getLootTable().orElseThrow()))) {
				if (drops.removeIf(stack -> stack.is(Items.REDSTONE))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.REDSTONE, Settings.RedstoneAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.REDSTONE, Settings.RedstoneAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.REDSTONE, Settings.RedstoneAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 99999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very unlucky..."), false);
					}
				}
			} else if ((holder.is(Blocks.EMERALD_ORE.getLootTable().orElseThrow())) || (holder.is(Blocks.DEEPSLATE_EMERALD_ORE.getLootTable().orElseThrow()))) {
				if (drops.removeIf(stack -> stack.is(Items.EMERALD))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.EMERALD, Settings.EmeraldAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.EMERALD, Settings.EmeraldAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.EMERALD, Settings.EmeraldAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 99999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very unlucky..."), false);
					}
				}
			} else if ((holder.is(Blocks.DIAMOND_ORE.getLootTable().orElseThrow())) || (holder.is(Blocks.DEEPSLATE_DIAMOND_ORE.getLootTable().orElseThrow()))) {
				if (drops.removeIf(stack -> stack.is(Items.DIAMOND))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.DIAMOND, Settings.DiamondAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.DIAMOND, Settings.DiamondAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.DIAMOND, Settings.DiamondAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 99999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very unlucky..."), false);
					}
				}
			} else if ((holder.is(Blocks.GOLD_ORE.getLootTable().orElseThrow())) || (holder.is(Blocks.DEEPSLATE_GOLD_ORE.getLootTable().orElseThrow()))) {
				if (drops.removeIf(stack -> stack.is(Items.RAW_GOLD))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.RAW_GOLD, Settings.GoldAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.RAW_GOLD, Settings.GoldAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.RAW_GOLD, Settings.GoldAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 99999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very unlucky..."), false);
					}
				}
			} else if (holder.is(Blocks.NETHERRACK.getLootTable().orElseThrow())) {
				if (drops.removeIf(stack -> stack.is(Items.NETHERRACK))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.NETHERRACK, Settings.NetherrackAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.NETHERRACK, Settings.NetherrackAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.NETHERRACK, Settings.NetherrackAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 99999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very unlucky..."), false);
					}
				}
			} else if (holder.is(Blocks.NETHER_GOLD_ORE.getLootTable().orElseThrow())) {
				if (drops.removeIf(stack -> stack.is(Items.GOLD_NUGGET))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.GOLD_NUGGET, Settings.GoldNuggetAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.GOLD_NUGGET, Settings.GoldNuggetAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.GOLD_NUGGET, Settings.GoldNuggetAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 99999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very unlucky..."), false);
					}
				}
			} else if (holder.is(Blocks.NETHER_QUARTZ_ORE.getLootTable().orElseThrow())) {
				if (drops.removeIf(stack -> stack.is(Items.QUARTZ))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.QUARTZ, Settings.NetherQuartzAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.QUARTZ, Settings.NetherQuartzAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.QUARTZ, Settings.NetherQuartzAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 99999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very unlucky..."), false);
					}
				}
			} else if (holder.is(Blocks.ANCIENT_DEBRIS.getLootTable().orElseThrow())) {
				if (drops.removeIf(stack -> stack.is(Items.ANCIENT_DEBRIS))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.ANCIENT_DEBRIS, Settings.AncientDebrisAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.ANCIENT_DEBRIS, Settings.AncientDebrisAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.ANCIENT_DEBRIS, Settings.AncientDebrisAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 99999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very unlucky..."), false);
					}
				}
			} else if (holder.is(Blocks.GRAVEL.getLootTable().orElseThrow())) {
				if (drops.removeIf(stack -> stack.is(Items.GRAVEL))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.GRAVEL, Settings.GravelAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.GRAVEL, Settings.GravelAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.GRAVEL, Settings.GravelAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 99999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very unlucky..."), false);
					}
				} else if (drops.removeIf(stack -> stack.is(Items.FLINT))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.FLINT, Settings.FlintAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.FLINT, Settings.FlintAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.FLINT, Settings.FlintAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 99999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very unlucky..."), false);
					}
				}
			} else if (holder.is(Blocks.GRANITE.getLootTable().orElseThrow())) {
				if (drops.removeIf(stack -> stack.is(Items.GRANITE))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.GRANITE, Settings.GraniteAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.GRANITE, Settings.GraniteAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.GRANITE, Settings.GraniteAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 99999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very unlucky..."), false);
					}
				}
			} else if (holder.is(Blocks.DRIPSTONE_BLOCK.getLootTable().orElseThrow())) {
				if (drops.removeIf(stack -> stack.is(Items.DRIPSTONE_BLOCK))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.DRIPSTONE_BLOCK, Settings.DripstoneAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.DRIPSTONE_BLOCK, Settings.DripstoneAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.DRIPSTONE_BLOCK, Settings.DripstoneAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 99999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very unlucky..."), false);
					}
				}
			} else if (holder.is(Blocks.DIORITE.getLootTable().orElseThrow())) {
				if (drops.removeIf(stack -> stack.is(Items.DIORITE))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.DIORITE, Settings.DioriteAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.DIORITE, Settings.DioriteAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.DIORITE, Settings.DioriteAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 99999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very unlucky..."), false);
					}
				}
			} else if (holder.is(Blocks.POINTED_DRIPSTONE.getLootTable().orElseThrow())) {
				if (drops.removeIf(stack -> stack.is(Items.POINTED_DRIPSTONE))) {
					if (Math.random() < 0.45) {
						drops.add(new ItemStack(Items.POINTED_DRIPSTONE, Settings.PointedDripstoneAmount.normal));
					} else if (Math.random() < 0.6) {
						drops.add(new ItemStack(Items.POINTED_DRIPSTONE, Settings.PointedDripstoneAmount.lucky));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8) {
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9999) {
						drops.add(new ItemStack(Items.POINTED_DRIPSTONE, Settings.PointedDripstoneAmount.jackpot));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for (var players: context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 99999999);
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very unlucky..."), false);
					}
				}
			}
		});
	}

}