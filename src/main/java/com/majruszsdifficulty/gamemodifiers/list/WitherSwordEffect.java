package com.majruszsdifficulty.gamemodifiers.list;

import com.majruszsdifficulty.MajruszsDifficulty;
import com.majruszsdifficulty.gamemodifiers.CustomConfigs;
import com.majruszsdifficulty.items.WitherSwordItem;
import com.mlib.effects.EffectHelper;
import com.mlib.gamemodifiers.Config;
import com.majruszsdifficulty.gamemodifiers.GameModifier;
import com.mlib.gamemodifiers.Condition;
import com.mlib.gamemodifiers.contexts.OnDamagedContext;
import com.mlib.items.ItemHelper;
import net.minecraft.world.effect.MobEffects;

public class WitherSwordEffect extends GameModifier {
	public static final Config.Effect WITHER = new Config.Effect( "Wither", 1, 6.0 );
	static final OnDamagedContext ON_DAMAGED = new OnDamagedContext();

	static {
		ON_DAMAGED.addCondition( new Condition.Context<>( OnDamagedContext.Data.class, data->ItemHelper.hasInMainHand( data.attacker, WitherSwordItem.class ) ) );
		ON_DAMAGED.addCondition( new OnDamagedContext.DirectDamage() );
		ON_DAMAGED.addConfig( WITHER );
	}

	public WitherSwordEffect() {
		super( GameModifier.DEFAULT, "WitherSwordEffect", "Wither sword inflicts wither effect.", ON_DAMAGED );
	}

	@Override
	public void execute( Object data ) {
		if( data instanceof OnDamagedContext.Data damagedData ) {
			EffectHelper.applyEffectIfPossible( damagedData.target, MobEffects.WITHER, WITHER.getDuration(), WITHER.getAmplifier() );
		}
	}
}
