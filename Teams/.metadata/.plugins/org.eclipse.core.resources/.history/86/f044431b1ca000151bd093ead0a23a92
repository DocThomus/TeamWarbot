package squads;

import squads.explorer.ExplorerStateCollectorBringBack;
import squads.fsm.Fsm;

import edu.warbot.brains.brains.WarExplorerBrain;

public abstract class WarExplorerBrainController extends WarExplorerBrain
{
	Fsm fsm;
	
    public WarExplorerBrainController()
    {
        super();
        fsm = new Fsm();
        fsm.push(new ExplorerStateCollectorBringBack(fsm, this));
    }

    @Override
    public String action()
    {
        return fsm.execute();
    }

}
