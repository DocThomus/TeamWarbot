package squads.explorer;

import squads.WarExplorerBrainController;
import squads.fsm.Fsm;
import edu.warbot.agents.agents.WarExplorer;

public class ExplorerStateWiggle extends ExplorerState
{
	public ExplorerStateWiggle(Fsm fsm, WarExplorerBrainController web)
	{
		super(fsm, web);
	}

	@Override
	public String execute()
	{
		web.setRandomHeading(2);
		fsm.pop();
		return WarExplorer.ACTION_MOVE;
	}

	@Override
	public void reflexe()
	{
		super.reflexe();
	}
}
