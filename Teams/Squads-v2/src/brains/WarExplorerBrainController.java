package brains;

import java.util.ArrayList;

import fsm.Fsm;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.brains.brains.WarExplorerBrain;
import edu.warbot.communications.WarMessage;
import explorer.ExplorerStateElection;

public abstract class WarExplorerBrainController extends WarExplorerBrain
{
	Fsm fsm;
	
	public ArrayList<WarMessage> mailbox;
	public ArrayList<WarAgentPercept> percepts;
	public int idOverlord = -1;
	public double distanceBase;
	public double directionBase;
	
    public WarExplorerBrainController()
    {
        super();
        fsm = new Fsm();
        fsm.push(new ExplorerStateElection(fsm, this));
    }

    @Override
    public String action()
    {
    	this.update();
        return fsm.execute();
    }
    
    public void update()
	{
		mailbox = new ArrayList<WarMessage>(getMessages());
		percepts = new ArrayList<WarAgentPercept>(getPercepts());
	}
}
