package base;

import brains.WarBaseBrainController;
import edu.warbot.agents.agents.WarBase;
import fsm.Fsm;

public class BaseStateOverlord extends BaseState {
	// L'overlord est le seul à récupérer de la nourriture.
	// L'overlord envoie des messages 
	
	public BaseStateOverlord(Fsm fsm, WarBaseBrainController brain) {
		super(fsm, brain);
	}
	
	public String execute()
	{
		brain.setDebugString("OVERLORD");
        return WarBase.ACTION_IDLE;
	}

}
