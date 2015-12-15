package brains;

import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.brains.brains.WarBaseBrain;
import edu.warbot.communications.WarMessage;

import java.util.ArrayList;

import base.BaseState;
import fsm.Fsm;

public abstract class WarBaseBrainController extends WarBaseBrain
{
	Fsm fsm;
	
	public ArrayList<WarMessage> mailbox;
	public ArrayList<WarAgentPercept> percepts;
	
    public WarBaseBrainController()
    {
        super();
        fsm = new Fsm();
        fsm.push(new BaseState(fsm, this));
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
