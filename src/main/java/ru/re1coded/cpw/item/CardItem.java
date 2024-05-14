package ru.re1coded.cpw.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class CardItem extends Item {
    public CardItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        pPlayer.getInventory().add(new ItemStack(ModItems.SUIT_HELMET.get()));
        pPlayer.getInventory().add(new ItemStack(ModItems.SUIT_CHESTPLATE.get()));
        pPlayer.getInventory().add(new ItemStack(ModItems.SUIT_LEGGINGS.get()));
        pPlayer.getInventory().add(new ItemStack(ModItems.SUIT_BOOTS.get()));
        itemStack.shrink(1);
        return InteractionResultHolder.consume(itemStack);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return Rarity.UNCOMMON;
    }
}
