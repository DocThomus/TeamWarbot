package explorer;

import brains.WarExplorerBrainController;
import edu.warbot.agents.agents.WarExplorer;
import fsm.Fsm;
import fsm.State;

public class ExplorerStateBringFood extends State
{
	public WarExplorerBrainController brain;
	
	public ExplorerStateBringFood(Fsm fsm, WarExplorerBrainController brain)
	{
		super(fsm, brain);
	}
	
	public String execute()
	{
		brain.setDebugString("StateBringFood");
		return WarExplorer.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		
	}
	
	public void update()
	{
		
	}
}
