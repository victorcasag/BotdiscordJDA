package Commands;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import PackageCommands.InterfaceCommand;
import PackageMusic.ClassMusic;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class cmdQueue implements InterfaceCommand {
	
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		ClassMusic.guildGlobal = event.getGuild();
		
		if (ClassMusic.isIdle(ClassMusic.guildGlobal)) {
			event.getTextChannel().sendMessage("A Fila Está Vazia").queue();
		}
		
		int sideNumb = args.length > 1 ? Integer.parseInt(args[1]) : 1;
		
		List<String> tracks = new ArrayList<>();
		List<String> trackSublist;
		
		ClassMusic.getManager(ClassMusic.guildGlobal).getQueue().forEach(AudioInfo -> tracks.add(ClassMusic.buildQueueMessage(AudioInfo)));
		
		if(tracks.size() > 20) 
			trackSublist = tracks.subList((sideNumb-1)*20, (sideNumb-1)*20+20);
		else
			trackSublist = tracks;
		
		String out = trackSublist.stream().collect(Collectors.joining("\n"));
		int sideNumbAll = tracks.size() >=20 ? tracks.size() / 20 : 1;
		
		event.getTextChannel().sendMessage(
				new EmbedBuilder()
					.setDescription(
							"**Fila Atual**\n" + 
							"*[" + out + " Faixas | Página " + sideNumb + " / " + sideNumbAll + "]*" 
							)
					.setColor(Color.cyan)
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
