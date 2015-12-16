package brain;

import java.util.ArrayList;

import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.brains.brains.WarBaseBrain;
import edu.warbot.communications.WarMessage;

import fsm.Fsm;
import base.BaseStateElection;

public abstract class WarBaseBrainController extends WarBaseBrain
{
	Fsm fsm;
	
	public ArrayList<WarMessage> mailbox;
	public ArrayList<WarAgentPercept> percepts;
	
	public int idOfKing;
	
    public WarBaseBrainController()
    {
        super();
        fsm = new Fsm();
        fsm.push(new BaseStateElection(fsm, this));
        
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
