package PackageListener;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter{
   
	public void onRead(ReadyEvent event){
        event.getJDA().getTextChannelById("432003654506250270")
                .sendMessage("Ola, voltei").queue();
    }
	
}