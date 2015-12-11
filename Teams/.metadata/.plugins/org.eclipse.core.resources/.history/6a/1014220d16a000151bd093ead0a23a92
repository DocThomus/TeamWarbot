package squads.explorer;

import squads.fsm.Fsm;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.brains.brains.WarExplorerBrain;

public class ExplorerStateWiggle extends ExplorerState
{
	public ExplorerStateWiggle(Fsm fsm, WarExplorerBrain web)
	{
		super(fsm, web);
	}

	@Override
	public String execute()
	{
		web.setRandomHeading(5);
		fsm.pop();
		return WarExplorer.ACTION_MOVE;
	}

	@Override
	public void reflexe()
	{
		super.reflexe();
	}

}
