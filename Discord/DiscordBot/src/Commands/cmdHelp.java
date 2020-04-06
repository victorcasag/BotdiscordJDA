package Commands;

import java.awt.Color;

import PackageCommands.InterfaceCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class cmdHelp implements InterfaceCommand {
	@Override
	 public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }
	@Override
    public void action(String[] args, MessageReceivedEvent event) {		
		event.getTextChannel().sendMessage(
				new EmbedBuilder()
					.setDescription("**Comandos**" +
	                        "\n /help - Ajuda" +
	                        "\n /ping - Ping Server" +
	                        "\n /play - Toca Musica" +
	                        "\n /pause - Pausa M�sica" +
	                        "\n /stop - Para a M�sica" + 
	                        "\n /queue - Fila M�sicas" + 
	                        "\n /now - M�sica Tocando Agora" + 
	                        "\n /shuffle - Misturar Playlist" + 
	                        "\n /volume - Altera o volume")
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
