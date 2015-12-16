package brain;

import java.util.ArrayList;


import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.brains.brains.WarExplorerBrain;
import edu.warbot.communications.WarMessage;
import explorer.ExplorerStateElection;
import fsm.Fsm;

public abstract class WarExplorerBrainController extends WarExplorerBrain
{
	Fsm fsm;
	
	public ArrayList<WarMessage> mailbox;
	public ArrayList<WarAgentPercept> percepts;
	
	public int idOfKing;
	
    public WarExplorerBrainController()
    {
        super();
        fsm = new Fsm();
        fsm.push(new ExplorerStateElection(fsm, this));
        
        idOfKing = -1;
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
