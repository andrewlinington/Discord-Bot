package command.commands;

import command.Command;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;

public class Purge extends Command {

    /**
     * Initializes the command's key to "!purge".
     */
    public Purge() {
        super("!purge");
    }

    /**
     * Compares a string to the command's key and checks if that
     * string starts with the key.
     *
     * @param string the user's input being compared to the key
     * @return returns true if the key matches and false otherwise
     */
    @Override
    public boolean keyMatches(String string) {
        return string.toLowerCase().startsWith(getKey());
    }

    /**
     * Deletes a given number of messages in a text channel.
     *
     * @param event the MessageReceivedEvent that triggered the command
     */
    @Override
    public void start(MessageReceivedEvent event) {
        if (!event.getMember().canInteract(event.getGuild().getRoleById("486635066928136194"))) {
            return;
        }

        try {
            int numMessages = Integer.parseInt((event.getMessage().getContentRaw().split(" "))[1]);
            MessageHistory history = new MessageHistory(event.getTextChannel());
            List<Message> messages = history.retrievePast(numMessages).complete();
            event.getTextChannel().deleteMessages(messages).queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
