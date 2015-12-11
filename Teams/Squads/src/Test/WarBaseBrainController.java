package Squads; 

import edu.warbot.agents.agents.WarBase;
import edu.warbot.agents.enums.WarAgentCategory;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.brains.brains.WarBaseBrain;
import edu.warbot.communications.WarMessage;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public abstract class WarBaseBrainController extends WarBaseBrain 
{

    public WarBaseBrainController() 
    {
        super();
    }

    @Override
    public String action() 
    {
    	String idMessagesRecus = "";
    	
    	this.broadcastMessageToAgentType(WarAgentType.WarBase, "test", "");
    	
    	List<WarMessage> boiteAuxLettres = getMessages();
    	
    	for (WarMessage message : boiteAuxLettres) {
    		if(message.getMessage() == "test") {
    			idMessagesRecus += message.getSenderID() + ";";
    		}
    	}
    	
    	this.setDebugString("" + idMessagesRecus);
    	
        return WarBase.ACTION_IDLE;
    }

}
