package base;

import brains.WarBaseBrainController;
import edu.warbot.agents.agents.WarBase;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import fsm.Fsm;

public class BaseStateIdle extends BaseState {

	public BaseStateIdle(Fsm fsm, WarBaseBrainController brain) {
		super(fsm, brain);
	}
	
	public String execute()
	{
		brain.setDebugString("IDLE");
		brain.setNextAgentToCreate(WarAgentType.WarEngineer);
        return WarBase.ACTION_CREATE;
	}
	
	public void reflexe() {
		
	}
	
	public void update() {
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
