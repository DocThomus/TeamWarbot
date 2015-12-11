package squads;

import java.util.ArrayList;

import squads.explorer.ExplorerStateCollectorBringBack;
import squads.fsm.Fsm;

import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.brains.brains.WarExplorerBrain;
import edu.warbot.communications.WarMessage;

public abstract class WarExplorerBrainController extends WarExplorerBrain
{
	Fsm fsm;
	
	public ArrayList<WarMessage> mailbox;
	public ArrayList<WarAgentPercept> percepts;
	
    public WarExplorerBrainController()
    {
        super();
        fsm = new Fsm();
        fsm.push(new ExplorerStateCollectorBringBack(fsm, this));
    }

    @Override
    public String action()
    {
    	update();
        return fsm.execute();
    }
    
    public void update()
	{
		mailbox = new ArrayList<WarMessage>(getMessages());
		percepts = new ArrayList<WarAgentPercept>(getPercepts());
	}
}
