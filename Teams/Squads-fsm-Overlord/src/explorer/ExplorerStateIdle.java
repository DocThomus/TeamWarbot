package explorer;

import brains.WarExplorerBrainController;
import fsm.Fsm;
import fsm.State;
import edu.warbot.agents.agents.WarExplorer;

public class ExplorerStateIdle extends State
{
	public WarExplorerBrainController brain;
	
	public ExplorerStateIdle(Fsm fsm, WarExplorerBrainController brain)
	{
		super(fsm, brain);
	}

	public String execute()
	{
		fsm.pop();
		return WarExplorer.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		
	}

	public void update() 
	{		
		
	}
}
