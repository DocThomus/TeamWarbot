package explorer;

import brains.WarExplorerBrainController;
import fsm.Fsm;
import edu.warbot.agents.agents.WarExplorer;

public class ExplorerStateWiggle extends ExplorerState
{
	public ExplorerStateWiggle(Fsm fsm, WarExplorerBrainController webc)
	{
		super(fsm, webc);
	}

	@Override
	public String execute()
	{
		webc.setRandomHeading(2);
		fsm.pop();
		return WarExplorer.ACTION_MOVE;
	}

	@Override
	public void reflexe()
	{
		super.reflexe();
	}
}
