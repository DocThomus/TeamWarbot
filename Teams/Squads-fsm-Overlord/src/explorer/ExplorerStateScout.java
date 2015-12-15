package explorer;

import brains.WarExplorerBrainController;
import edu.warbot.agents.agents.WarExplorer;
import fsm.Fsm;

public class ExplorerStateScout extends ExplorerState{

	public ExplorerStateScout(Fsm fsm, WarExplorerBrainController webc) {
		super(fsm, webc);
	}

	public String execute()
	{
		webc.setDebugString("StateScout");
		fsm.pop();
		return WarExplorer.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		super.reflexe();
	}
}
