package Commands;

import java.awt.Color;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import PackageCommands.InterfaceCommand;
import PackageMusic.ClassMusic;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class cmdNow implements InterfaceCommand{
	
	
	
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {

		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		
		ClassMusic.guildGlobal = event.getGuild();
		
		if (ClassMusic.isIdle(ClassMusic.guildGlobal)) return;
		
		AudioTrack track = ClassMusic.getPlayer(ClassMusic.guildGlobal).getPlayingTrack();
		AudioTrackInfo info = track.getInfo();
		
		event.getTextChannel().sendMessage(
				new EmbedBuilder()
					.setDescription("**Tocando agora: **")
					.setColor(Color.CYAN)
					.addField("Título Da Música", info.title, false)
					.addField("Duração", "`[ " + ClassMusic.getTimestamp(track.getPosition()) + "/ " + ClassMusic.getTimestamp(track.getDuration()) + " ]`", false)
					.addField("Ator", info.author, false)
					.build()
				).queue();
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {

		
	}

	@Override
	public String help() {

		return null;
	}
	

}
