package com.majruszsdifficulty.items;

import com.majruszlibrary.text.TextHelper;
import com.majruszsdifficulty.MajruszsDifficulty;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class CreativeModeTabs {
	private static final Component PRIMARY = TextHelper.translatable( "itemGroup.majruszsdifficulty.primary" );

	public static Supplier< CreativeModeTab > primary() {
		return ()->CreativeModeTab.builder( CreativeModeTab.Row.TOP, 0 )
			.title( PRIMARY )
			.displayItems( CreativeModeTabs::definePrimaryItems )
			.icon( ()->new ItemStack( MajruszsDifficulty.UNDEAD_BATTLE_STANDARD_ITEM.get() ) )
			.build();
	}

	private static void definePrimaryItems( CreativeModeTab.ItemDisplayParameters params, CreativeModeTab.Output output ) {
		Stream.of(
			MajruszsDifficulty.INFERNAL_SPONGE_ITEM,
			MajruszsDifficulty.SOAKED_INFERNAL_SPONGE_ITEM,
			MajruszsDifficulty.FRAGILE_END_STONE_ITEM,
			MajruszsDifficulty.INFESTED_END_STONE_ITEM,
			MajruszsDifficulty.BANDAGE_ITEM,
			MajruszsDifficulty.GOLDEN_BANDAGE_ITEM,
			MajruszsDifficulty.CLOTH_ITEM,
			MajruszsDifficulty.UNDEAD_BATTLE_STANDARD_ITEM,
			MajruszsDifficulty.SOUL_JAR_ITEM,
			MajruszsDifficulty.RECALL_POTION_ITEM,
			MajruszsDifficulty.EVOKER_FANG_SCROLL_ITEM,
			MajruszsDifficulty.SONIC_BOOM_SCROLL_ITEM,
			MajruszsDifficulty.CERBERUS_FANG_ITEM
		).map( item->new ItemStack( item.get() ) ).forEach( output::accept );

		Stream.of(
			MajruszsDifficulty.WITHER_SWORD_ITEM,
			MajruszsDifficulty.ANGLER_TREASURE_BAG_ITEM,
			MajruszsDifficulty.ELDER_GUARDIAN_TREASURE_BAG_ITEM,
			MajruszsDifficulty.ENDER_DRAGON_TREASURE_BAG_ITEM,
			MajruszsDifficulty.PILLAGER_TREASURE_BAG_ITEM,
			MajruszsDifficulty.UNDEAD_ARMY_TREASURE_BAG_ITEM,
			MajruszsDifficulty.WARDEN_TREASURE_BAG_ITEM,
			MajruszsDifficulty.WITHER_TREASURE_BAG_ITEM,
			MajruszsDifficulty.CERBERUS_SPAWN_EGG_ITEM,
			MajruszsDifficulty.CREEPERLING_SPAWN_EGG_ITEM,
			MajruszsDifficulty.CURSED_ARMOR_SPAWN_EGG_ITEM,
			MajruszsDifficulty.GIANT_SPAWN_EGG_ITEM,
			MajruszsDifficulty.ILLUSIONER_SPAWN_EGG_ITEM,
			MajruszsDifficulty.TANK_SPAWN_EGG_ITEM
		).map( item->new ItemStack( item.get() ) ).forEach( output::accept );
	}
}
