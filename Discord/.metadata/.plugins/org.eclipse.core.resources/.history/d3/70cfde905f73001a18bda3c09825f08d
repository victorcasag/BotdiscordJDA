package PackageMusic;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import PackageAudio.AudioInfo;
import PackageAudio.AudioPlayerSendHandler;
import PackageAudio.TrackManager;
import PackageCommands.InterfaceCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.awt.Color;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class ClassMusic implements InterfaceCommand{

	public final static int PLAYLIST_LIMIT = 1000;
	public static Guild guildGlobal;
	public final static AudioPlayerManager MANAGER = new DefaultAudioPlayerManager();
	public final static Map<Guild, Map.Entry<AudioPlayer, TrackManager>> PLAYERS = new HashMap<Guild, Map.Entry<AudioPlayer,TrackManager>>(); 
	
	public ClassMusic() {
		AudioSourceManagers.registerRemoteSources(MANAGER);
	}
	
	public static AudioPlayer createPlayer (Guild guildLocal) {
		AudioPlayer audioPlayer = MANAGER.createPlayer();
		TrackManager trackManager = new TrackManager(audioPlayer);
		audioPlayer.addListener(trackManager);
		
		guildGlobal.getAudioManager().setSendingHandler(new AudioPlayerSendHandler(audioPlayer));
		
		PLAYERS.put(guildLocal, new AbstractMap.SimpleEntry<>(audioPlayer, trackManager));
		
		return audioPlayer;
	}
	
	public static boolean hasPlayer(Guild guildLocal) {
		return PLAYERS.containsKey(guildLocal);
	}
	
	public static AudioPlayer getPlayer(Guild guildLocal) {
		if (hasPlayer(guildLocal))
			return PLAYERS.get(guildLocal).getKey();
		else
			return createPlayer(guildLocal);
	}
	
	public static TrackManager getManager(Guild guildLocal) {
		return PLAYERS.get(guildLocal).getValue();
	}
	
	public static boolean isIdle(Guild guildLocal) {
		return !hasPlayer(guildLocal) || getPlayer(guildLocal).getPlayingTrack() == null;
	}
	
	public static void loadTrack(String identifier, Member author, Message msg) {
		Guild guild = author.getGuild();
		getPlayer(guild);
		
		MANAGER.setFrameBufferDuration(1000);
		MANAGER.loadItemOrdered(guild, identifier, new AudioLoadResultHandler() {
			
			@Override
			public void trackLoaded(AudioTrack track) {
				getManager(guild).queue(track, author);
			}
			
			@Override
			public void playlistLoaded(AudioPlaylist playlist) {
				if (identifier.contains("playlist")) {
					for(int i=0; i < (playlist.getTracks().size()>PLAYLIST_LIMIT ? PLAYLIST_LIMIT : playlist.getTracks().size()); i++) {
						getManager(guild).queue(playlist.getTracks().get(i), author); 
					}
				} else {
					for(int i=0; i < 1; i++) {
						getManager(guild).queue(playlist.getTracks().get(i), author); 
					}
				}			
			}
			@Override
			public void noMatches() {
				
			}
			@Override
			public void loadFailed(FriendlyException exception) {
				
			}
		});
	}
		
	public static void skip(Guild guild) {
	 getPlayer(guild).stopTrack();	
	}
	
	public static String getTimestamp(long mills) {
		long seconds = mills / 1000;
		long hours = Math.floorDiv(seconds, 3600);
		seconds = seconds - (hours * 3600);
		long mins = Math.floorDiv(seconds, 60);
		seconds = seconds - (mins * 60);
		return (hours == 0 ? "" : hours + ":") + String.format("%02d", mins) + ":" + String.format("%02d", seconds);
	}
	
	public static String buildQueueMessage(AudioInfo info) {
		AudioTrackInfo trackInfo = info.getTrack().getInfo();
		String title = trackInfo.title;
		long length = trackInfo.length;
		return "`[" + getTimestamp(length) + " ]`" + title + "\n";
	}
	public void sendErrorMsg(MessageReceivedEvent event, String Content) {
		event.getTextChannel().sendMessage(
				new EmbedBuilder()
					.setColor(Color.red)
					.setDescription(Content)
					.build()
		).queue();
	}


	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
	}

	@Override
	public String help() {
		return null;
	}
}
