package Commands;

import java.awt.Color;

import PackageCommands.InterfaceCommand;
import PackageMusic.MusicPlayer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class cmdVolume implements InterfaceCommand{
	
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return false;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		
		MusicPlayer player = new MusicPlayer();
		
		String message = event.getMessage().getContentDisplay();

        float volume = Float.parseFloat(message.substring("volume ".length()));
        
       // volume = Math.min(1F, Math.max(0F, volume));
        
        player.setVolume(volume);
        
        event.getTextChannel().sendMessage(
				new EmbedBuilder()
					.setDescription("Volume Alterado: " + volume)
					.setColor(Color.CYAN)
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



/*
 * 
 *  if (command.length == 1)
            {
                event.getChannel().sendMessage("Current player volume: **" + player.getVolume() + "**").queue();
            }
            else
            {
                try
                {
                    int newVolume = Math.max(10, Math.min(100, Integer.parseInt(command[1])));
                    int oldVolume = player.getVolume();
                    player.setVolume(newVolume);
                    event.getChannel().sendMessage("Player volume changed from `" + oldVolume + "` to `" + newVolume + "`").queue();
                }
                catch (NumberFormatException e)
                {
                    event.getChannel().sendMessage("`" + command[1] + "` is not a valid integer. (10 - 100)").queue();
                }
            }
        }
 * 
 * */






















