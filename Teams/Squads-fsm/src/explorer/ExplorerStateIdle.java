package explorer;

import brain.WarExplorerBrainController;
import edu.warbot.agents.agents.WarExplorer;
import fsm.Fsm;

public class ExplorerStateIdle extends ExplorerState
{
	public ExplorerStateIdle(Fsm fsm, WarExplorerBrainController web)
	{
		super(fsm, web);
	}

	public String execute()
	{
		fsm.pop();
		return WarExplorer.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		super.reflexe();
	}
}
