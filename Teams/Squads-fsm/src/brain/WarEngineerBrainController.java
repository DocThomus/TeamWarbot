package brain;

import java.util.ArrayList;

import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.brains.brains.WarEngineerBrain;
import edu.warbot.communications.WarMessage;
import engineer.EngineerState;
import fsm.Fsm;

public abstract class WarEngineerBrainController extends WarEngineerBrain
{
	Fsm fsm;
	
	public ArrayList<WarMessage> mailbox;
	public ArrayList<WarAgentPercept> percepts;
	
    public WarEngineerBrainController()
    {
    	super();
        fsm = new Fsm();
        fsm.push(new EngineerState(fsm, this));
    }

    @Override
    public String action()
    {
    	update();
        return fsm.execute();
    }

	private void update()
	{
		mailbox = new ArrayList<WarMessage>(getMessages());
		percepts = new ArrayList<WarAgentPercept>(getPercepts());
	}
}
