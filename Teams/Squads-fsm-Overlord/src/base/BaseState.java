package base;

import java.util.ArrayList;
import java.util.HashMap;

import brains.WarBaseBrainController;
import util.Vecteur;
import edu.warbot.agents.agents.WarBase;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.communications.WarMessage;
//import edu.warbot.communications.WarMessage;
import fsm.Fsm;
import fsm.State;

public class BaseState extends State
{
	protected WarBaseBrainController brain;
	protected HashMap<Integer, Vecteur> enemyBases; // ID -> Position
	protected int tickActuel;
	private ArrayList<Integer> idBasesAllies;
	
	public BaseState(Fsm fsm, WarBaseBrainController brain)
	{
		super(fsm, brain);
		this.brain = brain;
		this.tickActuel = 1;
		this.idBasesAllies = new ArrayList<Integer>();
		this.enemyBases = new HashMap<Integer, Vecteur>();
	}
	
	// Consiste en l'élection de l'overlord, puis au changement d'état : overlord/Idle
	public String execute()
	{
		this.update();
		
		brain.broadcastMessageToAgentType(WarAgentType.WarBase, "OverlordElection", "");
		
        return WarBase.ACTION_IDLE;
	}
	
	public void reflexe()
	{		
		if (this.tickActuel == 50) {
    		int minID = brain.getID();
    		for (Integer i : idBasesAllies) {
    			if(i.intValue() < minID) {
    				minID = i.intValue();
    			}
    		}
    		if (this.idBasesAllies.isEmpty() || brain.getID() == minID) {
    			fsm.pop();
    			fsm.push(new BaseStateOverlord(fsm, brain));
			} else {
				fsm.pop();
    			fsm.push(new BaseStateIdle(fsm, brain));
			}
    	}
	}

	
	public void update()
	{
		this.tickActuel++;
		for(WarMessage m : brain.mailbox) {
			if(m.getSenderType() == WarAgentType.WarBase && !this.idBasesAllies.contains(m.getSenderID())) {
				this.idBasesAllies.add(new Integer(m.getSenderID()));
			}
		}
	}
}
