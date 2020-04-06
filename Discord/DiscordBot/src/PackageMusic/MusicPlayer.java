package PackageMusic;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.bandcamp.BandcampAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.http.HttpAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.local.LocalAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.soundcloud.SoundCloudAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.twitch.TwitchStreamAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.vimeo.VimeoAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.GuildManager;

public class MusicPlayer extends ListenerAdapter {

    public static final int DEFAULT_VOLUME = 100; //(0 - 150, where 100 is default max volume)

    private final AudioPlayerManager playerManager;
    private final Map<String, GuildManager> musicManagers;

    public MusicPlayer(){
        java.util.logging.Logger.getLogger("org.apache.http.client.protocol.ResponseProcessCookies").setLevel(Level.OFF);

        this.playerManager = new DefaultAudioPlayerManager();
        playerManager.registerSourceManager(new YoutubeAudioSourceManager());
        playerManager.registerSourceManager(new SoundCloudAudioSourceManager(false, null, null, null, null));
        playerManager.registerSourceManager(new BandcampAudioSourceManager());
        playerManager.registerSourceManager(new VimeoAudioSourceManager());
        playerManager.registerSourceManager(new TwitchStreamAudioSourceManager());
        playerManager.registerSourceManager(new HttpAudioSourceManager());
        playerManager.registerSourceManager(new LocalAudioSourceManager());

        musicManagers = new HashMap<String, GuildManager>();
    }
    
    private GuildMusicManager getMusicManager(Guild guild){
    	
        String guildId = guild.getId();
        GuildMusicManager mng = (GuildMusicManager) musicManagers.get(guildId);
        if (mng == null){
        	
            synchronized (musicManagers){
            	
                mng = (GuildMusicManager) musicManagers.get(guildId);
                if (mng == null){
                	
                    mng = new GuildMusicManager(playerManager);
                    mng.player.setVolume(DEFAULT_VOLUME);
                    musicManagers.put(guildId, (GuildManager) mng);
                }
            }
        }
        return mng;
    }
    
    public void onMessageReceived(MessageReceivedEvent event) {
    	Guild guild = event.getGuild();
    	GuildManager mng = (GuildManager) getMusicManager(guild);
    	AudioPlayer player = (AudioPlayer) mng;
    	
    	String[] command = event.getMessage().getContentDisplay().split(" ", 2);

    	try{
            int newVolume = Math.max(10, Math.min(100, Integer.parseInt(command[1])));
            int oldVolume = player.getVolume();
            player.setVolume(newVolume);
            event.getChannel().sendMessage("Player volume changed from `" + oldVolume + "` to `" + newVolume + "`").queue();
        }
        catch (NumberFormatException e){
            event.getChannel().sendMessage("`" + command[1] + "` is not a valid integer. (10 - 100)").queue();
        }
    }
}