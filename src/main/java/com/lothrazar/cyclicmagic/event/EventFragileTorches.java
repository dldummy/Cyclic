package com.lothrazar.cyclicmagic.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EventFragileTorches{

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event){
		Entity ent = event.getEntity();
		if(ent instanceof EntityLiving == false){
			return;
		}
		EntityLivingBase living = (EntityLivingBase) event.getEntity();
		if(living == null){
			return;
		}
		if(living.worldObj.getBlockState(living.getPosition()).getBlock() == Blocks.torch){
			float oddsWillBreak = 0.01F;// TODO: in config or something? or make this 1/100
			boolean playerCancelled = false;
			if(living instanceof EntityPlayer){
				//EntityPlayer p = (EntityPlayer) living;
				//just dont let players break them. only other mobs.
				//if(p.isSneaking()){
					playerCancelled = true;// torches are safe from breaking
				//}
			}

			if(playerCancelled == false // if its a player, then the player is not sneaking
					&& living.worldObj.rand.nextDouble() < oddsWillBreak && living.worldObj.isRemote == false){

				living.worldObj.destroyBlock(living.getPosition(), true);
			}
		}
	}
}