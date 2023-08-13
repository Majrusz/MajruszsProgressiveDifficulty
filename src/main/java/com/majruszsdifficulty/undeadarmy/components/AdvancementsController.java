package com.majruszsdifficulty.undeadarmy.components;

import com.majruszsdifficulty.Registries;
import com.majruszsdifficulty.undeadarmy.UndeadArmy;

record AdvancementsController( UndeadArmy undeadArmy ) implements IComponent {
	@Override
	public void onWaveFinished() {
		if( this.undeadArmy.isLastWave() ) {
			this.undeadArmy.participants.forEach( participant->Registries.HELPER.triggerAchievement( participant, "army_defeated" ) );
		}
	}
}
