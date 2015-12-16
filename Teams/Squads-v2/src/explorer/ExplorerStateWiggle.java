package explorer;

import brains.WarExplorerBrainController;
import edu.warbot.agents.agents.WarExplorer;
import fsm.Fsm;

public class ExplorerStateWiggle extends ExplorerState
{
	public ExplorerStateWiggle(Fsm fsm, WarExplorerBrainController webc)
	{
		super(fsm, webc);
	}

	@Override
	public String execute()
	{
		//webc.setRandomHeading(2);
		fsm.pop();
		return WarExplorer.ACTION_MOVE;
	}

	@Override
	public void reflexe()
	{
		super.reflexe();
	}
}
