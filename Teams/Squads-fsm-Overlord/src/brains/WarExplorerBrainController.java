package brains;

import java.util.ArrayList;

import fsm.Fsm;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.brains.brains.WarExplorerBrain;
import edu.warbot.communications.WarMessage;
import explorer.ExplorerStateIdle;

public abstract class WarExplorerBrainController extends WarExplorerBrain
{
	Fsm fsm;
	
	public ArrayList<WarMessage> mailbox;
	public ArrayList<WarAgentPercept> percepts;
	public int idOverlord = -1;
	
    public WarExplorerBrainController()
    {
        super();
        fsm = new Fsm();
        fsm.push(new ExplorerStateIdle(fsm, this));
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
