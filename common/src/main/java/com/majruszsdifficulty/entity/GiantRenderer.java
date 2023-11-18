package com.majruszsdifficulty.entity;

import com.majruszsdifficulty.MajruszsDifficulty;
import com.mlib.annotation.Dist;
import com.mlib.annotation.OnlyIn;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

@OnlyIn( Dist.CLIENT )
public class GiantRenderer extends MobRenderer< GiantEntity, GiantModel< GiantEntity > > {
	public static final ModelLayerLocation LAYER = MajruszsDifficulty.HELPER.getLayerLocation( "giant" );
	public static final ResourceLocation TEXTURE = MajruszsDifficulty.HELPER.getLocation( "textures/entity/giant.png" );
	public static final float SCALE = 5.0f;

	public GiantRenderer( EntityRendererProvider.Context context ) {
		super( context, new GiantModel<>( context.bakeLayer( LAYER ) ), 0.4f * SCALE );

		this.addLayer( new ItemInHandLayer<>( this, context.getItemInHandRenderer() ) );
		this.addLayer( new HumanoidArmorLayer<>( this, new GiantModel<>( context.bakeLayer( ModelLayers.GIANT_INNER_ARMOR ) ), new GiantModel<>( context.bakeLayer( ModelLayers.GIANT_OUTER_ARMOR ) ), context.getModelManager() ) );
	}

	@Override
	public ResourceLocation getTextureLocation( GiantEntity giant ) {
		return TEXTURE;
	}

	@Override
	protected void scale( GiantEntity giant, PoseStack stack, float p_114777_ ) {
		stack.scale( SCALE, SCALE, SCALE );
	}
}
