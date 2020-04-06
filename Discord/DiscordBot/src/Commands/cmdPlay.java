package Commands;

import PackageCommands.InterfaceCommand;
import PackageMusic.ClassMusic;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.util.Arrays;
import java.util.stream.Collectors;

public class cmdPlay implements InterfaceCommand{
	
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return false;
	}
	
	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		
		ClassMusic.guildGlobal = event.getGuild();
		
		String input = Arrays.stream(args).map(s -> " " + s).collect(Collectors.joining()).substring(1);
		
		if (!(input.startsWith("http://") || input.startsWith("https://")))
			input = "ytsearch: " + input;
		else {
			
			
		}
			
		ClassMusic.loadTrack(input, event.getMember(), event.getMessage());
	}
	
	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		
	}
	@Override
	public String help() {
		return null;
	}
}