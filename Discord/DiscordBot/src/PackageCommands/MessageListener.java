package PackageCommands;


import PackageUtils.Utils;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter{
	
	public void onMessageReceived(MessageReceivedEvent event){
        if (event.getAuthor().isBot()){
            return;
        }

        //private message
        if (event.getChannelType() == ChannelType.PRIVATE){
            event.getAuthor().openPrivateChannel().complete().sendMessage(
                    "** " + event.getAuthor().getName() + " Nao aceito messagens privadas"
            ).queue();
        }

        String message = event.getMessage().getContentDisplay();
        if(message.startsWith(Utils.getPrefix) && event.getAuthor().getId() != event.getJDA().getSelfUser().getId()){
            CommandHandler.handleCommand(CommandHandler.parser.parse(message,event));
        }


    }
}
