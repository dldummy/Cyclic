package com.lothrazar.cyclicmagic.item;

import com.lothrazar.cyclicmagic.Const;
import com.lothrazar.cyclicmagic.PotionRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemRuneSlowfall  extends ItemRuneBase {
	public ItemRuneSlowfall() {
		super();
	}

	private final static int seconds = 20;
	private final static float falldistance = 5;

	@Override
	protected void trigger(World world,Entity entityIn ) {
		//apply slowfall after falling for a while
		if(entityIn instanceof EntityLivingBase && entityIn.fallDistance >= falldistance){
			EntityLivingBase entity = (EntityLivingBase)entityIn;
			
			if(entity.isPotionActive(PotionRegistry.slowfall) == false){
				PotionRegistry.addOrMergePotionEffect(entity, new PotionEffect(PotionRegistry.slowfall.id,seconds * Const.TICKS_PER_SEC));
			}
		}
	}
}