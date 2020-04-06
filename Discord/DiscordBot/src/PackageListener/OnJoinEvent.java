package PackageListener;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class OnJoinEvent extends ListenerAdapter{
	
	public void onGuildMemberJoin (GuildMemberJoinEvent event) {
	
		User username = (User) event.getMember();
		Guild guild = event.getGuild();
		
		event.getGuild().getTextChannelById("432003654506250270").sendMessage(
				new EmbedBuilder()
						.setColor(new Color(1,1,200))
						.setAuthor("Novo Meliante: " + username.getName(), null, username.getAvatarUrl())
						.setDescription("Entrou Um Novo Meliante!" +
										"\n Agora Somos **" +guild.getMemberCount() +"** Meliantes!" )
						.build()
				).queue();
	}
}
