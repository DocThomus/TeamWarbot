package base;

import brains.WarBaseBrainController;
import edu.warbot.agents.agents.WarBase;
import fsm.Fsm;

public class BaseStateIdle extends BaseState {

	public BaseStateIdle(Fsm fsm, WarBaseBrainController brain) {
		super(fsm, brain);
	}
	
	public String execute()
	{
		brain.setDebugString("IDLE");
        return WarBase.ACTION_IDLE;
	}
}
