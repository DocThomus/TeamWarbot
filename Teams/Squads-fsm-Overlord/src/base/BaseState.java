package base;

import java.util.ArrayList;
import java.util.HashMap;

import brains.WarBaseBrainController;
import util.Vecteur;
import edu.warbot.agents.agents.WarBase;
import edu.warbot.agents.enums.WarAgentType;
//import edu.warbot.communications.WarMessage;
import fsm.Fsm;
import fsm.State;

public class BaseState extends State
{
	protected WarBaseBrainController wbbc;
	protected HashMap<Integer, Vecteur> enemyBases; // ID -> Position
	protected int tickActuel;
	private ArrayList<Integer> idBasesAllies;
	
	public BaseState(Fsm fsm, WarBaseBrainController wbbc)
	{
		super(fsm, wbbc);
		this.wbbc = wbbc;
		this.tickActuel = 1;
		this.idBasesAllies = new ArrayList<Integer>();
		this.enemyBases = new HashMap<Integer, Vecteur>();
	}
	
	// Consiste en l'élection de l'overlord, puis au changement d'état : overlord/Idle
	public String execute()
	{
		update();
		reflexe();

        return WarBase.ACTION_CREATE;
	}
	
	// Envoi un message 
	public void reflexe()
	{
		if (this.tickActuel == 50) {
    		int minID = wbbc.getID();
    		for (Integer i : idBasesAllies) {
    			if(i.intValue() < minID) {
    				minID = i.intValue();
    			}
    		}
    		if (this.idBasesAllies.isEmpty() || wbbc.getID() == minID) {
    			fsm.pop();
    			fsm.push(new BaseStateOverlord(fsm, wbbc));
			} else {
				fsm.pop();
    			fsm.push(new BaseStateIdle(fsm, wbbc));
			}
    	}
	}

	
	public void update()
	{
		// Initialisations au premier tick de l'agent (ce qui est impossible dans le constructeur).
    	if (this.tickActuel == 1) {
    		this.idBasesAllies.add(new Integer(wbbc.getID()));
    	}
	}
}
