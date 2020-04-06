package PackageCommands;

import java.util.ArrayList;
import PackageUtils.Utils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandParse {
    public commandContainer parse(String raw, MessageReceivedEvent event){
        String beheadded = raw.replaceFirst(Utils.getPrefix, "");
        String[] splitBeheaded = beheadded.split(" ");
        String invoke = splitBeheaded[0];
        ArrayList<String> split = new ArrayList<>();
        for(String s : splitBeheaded){
            split.add(s);
        }
        String[] args  = new String[split.size() - 1];
        split.subList(1, split.size()).toArray(args);
        return new commandContainer(raw,beheadded,splitBeheaded,invoke,args,event);
    }
    public class commandContainer{
        public final String raw;
        public final String beheaded;
        public final String[] splitBeheaded;
        public final String invoke;
        public final String[] args;
        public final MessageReceivedEvent event;

        public commandContainer(String raw, String beheaded, String[] splitBeheaded, String invoke, String[] args, MessageReceivedEvent event) {
            this.raw = raw;
            this.beheaded = beheaded;
            this.splitBeheaded = splitBeheaded;
            this.invoke = invoke;
            this.args = args;
            this.event = event;
        }

    }
}
