package base;

import brains.WarBaseBrainController;
import fsm.Fsm;

public class BaseStateOverlord extends BaseState {
	// L'overlord est le seul à récupérer de la nourriture.
	// L'overlord envoie des messages 
	
	public BaseStateOverlord(Fsm fsm, WarBaseBrainController wbbc) {
		super(fsm, wbbc);
	}

}
