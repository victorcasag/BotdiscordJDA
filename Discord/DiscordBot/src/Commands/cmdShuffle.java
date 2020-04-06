package Commands;

import PackageCommands.InterfaceCommand;
import PackageMusic.ClassMusic;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class cmdShuffle implements InterfaceCommand{

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		ClassMusic.guildGlobal = event.getGuild();
		if (ClassMusic.isIdle(ClassMusic.guildGlobal)) return;
		ClassMusic.getManager(ClassMusic.guildGlobal).shuffleQueue();	
	}

	@Override
	public void executed(boolean sucess, MessageReceivedEvent event) {
		
	}

	@Override
	public String help() {
		return null;
	}
	

}
