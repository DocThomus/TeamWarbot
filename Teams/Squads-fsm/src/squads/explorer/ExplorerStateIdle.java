package squads.explorer;

import squads.WarExplorerBrainController;
import squads.fsm.Fsm;
import edu.warbot.agents.agents.WarExplorer;

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
