package Commands;

import java.awt.Color;

import PackageCommands.InterfaceCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class cmdPing implements InterfaceCommand{
	@Override
	 public boolean called(String[] args, MessageReceivedEvent event) {
       return false;
   }
	@Override
   public void action(String[] args, MessageReceivedEvent event) {
       event.getTextChannel().sendMessage(
    		   new EmbedBuilder()
    		   		.setColor(Color.cyan)
    		   		.setDescription(event.getJDA().getGatewayPing() + " ms\n")
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
