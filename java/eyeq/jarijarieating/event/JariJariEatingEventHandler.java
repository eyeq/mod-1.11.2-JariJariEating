package eyeq.jarijarieating.event;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import eyeq.jarijarieating.JariJariEating;

import java.util.Random;

public class JariJariEatingEventHandler {
    @SubscribeEvent
    public void onLivingEntityUseItemFinish(LivingEntityUseItemEvent.Finish event) {
        EntityLivingBase entity = event.getEntityLiving();
        World world = entity.getEntityWorld();
        if(world.isRemote) {
            return;
        }
        if(event.getItem().getItemUseAction() != EnumAction.EAT) {
            return;
        }

        boolean isJariUma = entity.getName().equals("RavenTofu");
        Random random = entity.getRNG();
        BlockPos pos = entity.getPosition();
        for(int i = 0; random.nextFloat() < JariJariEating.prob && i < 64; i++) {
            if(isJariUma) {
                entity.heal(10);
            } else {
                entity.attackEntityFrom(DamageSource.IN_WALL, 1);
            }
            world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.GRAVEL)));
            ITextComponent text = new TextComponentTranslation(JariJariEating.I18n_JARIJARI);
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(text);
        }
    }
}
