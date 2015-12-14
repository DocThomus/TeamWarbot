package squads;

import java.util.ArrayList;

import squads.engineer.EngineerState;
import squads.fsm.Fsm;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.brains.brains.WarEngineerBrain;
import edu.warbot.communications.WarMessage;

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
