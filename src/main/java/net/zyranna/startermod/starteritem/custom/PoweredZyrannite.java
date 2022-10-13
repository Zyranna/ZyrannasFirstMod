package net.zyranna.startermod.starteritem.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.Position;
import java.util.List;

public class PoweredZyrannite extends Item {
    public PoweredZyrannite(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND){
            //do a thing, maybe lightning idk
            BlockPos summonPos = player.blockPosition();
            //grab the player's current block position and store it in summonPos


            //summon a chicken
            Chicken chicky = new Chicken(EntityType.CHICKEN, level);
            //create a chicken entity
            chicky.moveTo(summonPos.getX()+0.5,summonPos.getY(),
                    summonPos.getZ()+0.5);
            //set the chicken entity's position to the coordinates of summonpos
            level.addFreshEntity(chicky);
            //add the chicken to the level

            level.playSound(null, summonPos , SoundEvents.AMETHYST_BLOCK_CHIME,
                    SoundSource.HOSTILE,1,1);
            //play music at source of rightclick position


            player.getCooldowns().addCooldown(this,20);
            //cooldown between summons
        }
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()){
            components.add(Component.literal("This is just a placeholder for info").withStyle(ChatFormatting.OBFUSCATED).withStyle(ChatFormatting.DARK_PURPLE));

        } else {
            components.add(Component.literal("press SHIFT for more information").withStyle(ChatFormatting.LIGHT_PURPLE));
        }

        super.appendHoverText(stack, level, components, flag);
    }

    private void outputRandomNumber(Player player){
        player.sendSystemMessage(Component.literal("Test output :" + getRandomNumber()));
    }

    private int getRandomNumber(){
        return RandomSource.createNewThreadLocalInstance().nextInt(20);
    }


}
