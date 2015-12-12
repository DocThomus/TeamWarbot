package Test;

import java.util.List;

import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.agents.enums.WarAgentType;
//import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.agents.resources.WarFood;
import edu.warbot.brains.brains.WarExplorerBrain;
import edu.warbot.communications.WarMessage;
@SuppressWarnings("unused")
public abstract class WarExplorerBrainController extends WarExplorerBrain {

    public WarExplorerBrainController() {
        super();
    }

    @Override
    public String action() {
    	
//    	this.broadcastMessageToAll("test", "");
    	
//    	List<WarMessage> boiteAuxLettres = getMessages();
//    	
//    	for (WarMessage message : boiteAuxLettres) {
//    		this.setDebugString("test");
//    	}
    	
		return WarExplorer.ACTION_IDLE;
    }

}
