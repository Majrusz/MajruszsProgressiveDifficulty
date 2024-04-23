package com.majruszsdifficulty.items;

import com.majruszlibrary.events.OnEntityPowderSnowCheck;
import com.majruszsdifficulty.MajruszsDifficulty;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Rarity;

import java.util.function.Supplier;

public class TatteredArmor extends ArmorItem {

	public static Supplier< TatteredArmor > boots() {
		return ()->new TatteredArmor( Type.BOOTS );
	}

	public static Supplier< TatteredArmor > chestplate() {
		return ()->new TatteredArmor( Type.CHESTPLATE );
	}

	public static Supplier< TatteredArmor > helmet() {
		return ()->new TatteredArmor( Type.HELMET );
	}

	public static Supplier< TatteredArmor > leggings() {
		return ()->new TatteredArmor( Type.LEGGINGS );
	}

	private TatteredArmor( Type type ) {
		super( CustomArmorMaterial.TATTERED, type, new Properties().rarity( Rarity.UNCOMMON ) );
	}
}
