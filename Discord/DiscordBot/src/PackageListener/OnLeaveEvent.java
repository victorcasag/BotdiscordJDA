package PackageListener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.awt.Color;

@SuppressWarnings("deprecation")
public class OnLeaveEvent extends ListenerAdapter{
	
	public void onGuildMemberLeave (GuildMemberLeaveEvent event) {
		
		User username = (User) event.getMember();
		Guild guild = event.getGuild();
		
		event.getGuild().getTextChannelById("432003654506250270").sendMessage(
				new EmbedBuilder()
						.setColor(new Color(1,1,200))
						.setAuthor("Menos um CPF: " + username.getName(), null, username.getAvatarUrl())
						.setDescription("Entrou Um Novo Meliante!" +
										"\n Agora Somos **" +guild.getMembers().size() +"** Meliantes!" )		
						.build()
				).queue();	
	}
}
