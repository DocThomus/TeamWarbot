package base;

import brains.WarBaseBrainController;
import edu.warbot.agents.agents.WarBase;
import edu.warbot.agents.enums.WarAgentType;
import fsm.Fsm;

public class BaseStateIdle extends BaseState {

	public BaseStateIdle(Fsm fsm, WarBaseBrainController wbbc) {
		super(fsm, wbbc);
	}
	
	public String execute()
	{
        wbbc.setNextAgentToCreate(WarAgentType.WarEngineer);
        return WarBase.ACTION_CREATE;
		
	}

}
