package com.majruszsdifficulty.gamestage.commands;

import com.majruszsdifficulty.gamestage.GameStage;
import com.majruszsdifficulty.gamestage.GameStageHelper;
import com.mlib.annotation.AutoInstance;
import com.mlib.command.Command;
import com.mlib.command.CommandData;
import com.mlib.command.IParameter;
import com.mlib.text.TextHelper;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AutoInstance
public class GameStageCommand {
	static final IParameter< String > GAME_STAGE = Command.string()
		.named( "name" )
		.suggests( ()->GameStageHelper.getGameStages().stream().map( GameStage::getName ).toList() );
	static final IParameter< List< ? extends Entity > > ENTITIES = Command.entities().named( "entities" );

	public GameStageCommand() {
		Command.create()
			.literal( "gamestage", "gamestate" )
			.hasPermission( 4 )
			.parameter( GAME_STAGE )
			.execute( this::set )
			.parameter( ENTITIES )
			.execute( this::set )
			.register();

		Command.create()
			.literal( "gamestage", "gamestate" )
			.hasPermission( 4 )
			.execute( this::get )
			.parameter( ENTITIES )
			.execute( this::get )
			.register();
	}

	private int set( CommandData data ) throws CommandSyntaxException {
		String name = data.get( GAME_STAGE );
		GameStage gameStage = GameStageHelper.getGameStages().stream().filter( stage->stage.is( name ) ).findFirst().orElseThrow();
		Optional< List< ? extends Entity > > entities = data.getOptional( ENTITIES );
		if( GameStageCommand.isInvalid( data, entities.isPresent() ) ) {
			return -1;
		}

		entities.map( GameStageCommand::toPlayers )
			.orElse( GameStageCommand.getNullList() )
			.forEach( player->{
				boolean hasGameStageChanged = player != null ? GameStageHelper.setGameStage( gameStage, player ) : GameStageHelper.setGlobalGameStage( gameStage );
				GameStageCommand.send( data, hasGameStageChanged ? "changed" : "cannot_change", player );
			} );

		return 0;
	}

	private int get( CommandData data ) throws CommandSyntaxException {
		Optional< List< ? extends Entity > > entities = data.getOptional( ENTITIES );
		if( GameStageCommand.isInvalid( data, entities.isPresent() ) ) {
			return -1;
		}

		entities.map( GameStageCommand::toPlayers )
			.orElse( GameStageCommand.getNullList() )
			.forEach( player->GameStageCommand.send( data, "current", player ) );

		return 0;
	}

	private static void send( CommandData data, String id, @Nullable Player player ) {
		String messageId = "commands.gamestage.%s.%s".formatted( player != null ? "player" : "global", id );
		MutableComponent component;
		if( player != null ) {
			component = TextHelper.translatable( messageId, GameStageHelper.getGameStage( player ).getComponent(), player.getDisplayName() );
		} else {
			component = TextHelper.translatable( messageId, GameStageHelper.getGlobalGameStage().getComponent() );
		}

		data.source.sendSuccess( ()->component, true );
	}

	private static boolean isInvalid( CommandData data, boolean arePlayersDefined ) {
		if( !GameStageHelper.isPerPlayerDifficultyEnabled() && arePlayersDefined ) {
			data.source.sendFailure( TextHelper.translatable( "commands.gamestage.player.disabled" ) );

			return true;
		}

		return false;
	}

	private static List< Player > toPlayers( List< ? extends Entity > entities ) {
		return entities.stream().filter( entity->entity instanceof Player ).map( Player.class::cast ).toList();
	}

	private static List< Player > getNullList() {
		ArrayList< Player > players = new ArrayList<>();
		players.add( null );

		return players;
	}
}
