package jackpotloot;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.network.chat.Component;

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
					if (Math.random() < 0.4) { // holy scuffed luck code
						drops.add(new ItemStack(Items.COBBLESTONE, 2));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Lucky you!"), false);
					} else if (Math.random() < 0.8){
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Unlucky you!"), false);
					} else if (Math.random() < 0.9) {
						drops.add(new ItemStack(Items.COBBLESTONE, 5));
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("JACKPOT!"), false);
					} else {
						for(var players : context.getLevel().players()) {
							players.hurtServer(context.getLevel(), players.damageSources().generic(), 999999999); // there's no way someone can bypass this without godmode
						}
						context.getLevel().getServer().getPlayerList().broadcastSystemMessage(Component.literal("Very Unlucky"), false);
					}

				}
			}
		});
	}

}