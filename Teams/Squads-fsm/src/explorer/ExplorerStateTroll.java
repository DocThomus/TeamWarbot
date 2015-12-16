package explorer;

import edu.warbot.agents.agents.WarExplorer;
import fsm.Fsm;
import brain.WarExplorerBrainController;

public class ExplorerStateTroll extends ExplorerState
{
	public ExplorerStateTroll(Fsm fsm, WarExplorerBrainController webc)
	{
		super(fsm, webc);
	}
	
	public String execute()
	{
		return WarExplorer.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		
	}
	
}
