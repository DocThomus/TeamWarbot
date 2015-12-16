package base;

import brains.WarBaseBrainController;
import edu.warbot.agents.agents.WarBase;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import fsm.Fsm;

public class BaseStateOverlord extends BaseState {
	// L'overlord est le seul à récupérer de la nourriture.
	// L'overlord envoie des messages 
	
	public BaseStateOverlord(Fsm fsm, WarBaseBrainController brain) {
		super(fsm, brain);
	}
	
	public String execute()
	{
		brain.setDebugString("OVERLORD");
        return WarBase.ACTION_IDLE;
	}
	
	public void reflexe() {
		
	}
	
	public void update() {
		brain.broadcastMessageToAll("IAmYourMaster", "");
		
		for(WarAgentPercept p : brain.percepts) {
			if(p.getTeamName() != brain.getTeamName()) {
				if(p.getType() == WarAgentType.WarExplorer) {
					
				} else if (p.getType() == WarAgentType.WarRocketLauncher) {
					
				} else if (p.getType() == WarAgentType.WarKamikaze) {
					
				} else if (p.getType() == WarAgentType.WarRocket) {
					
				}
			}
		}
	}
}
