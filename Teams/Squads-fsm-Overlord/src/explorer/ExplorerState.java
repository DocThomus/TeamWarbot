package explorer;

import java.util.List;

import brains.WarExplorerBrainController;
import edu.warbot.communications.WarMessage;
import fsm.Fsm;
import fsm.MovableAgentState;

public abstract class ExplorerState extends MovableAgentState
{
	protected WarExplorerBrainController webc;
	
	protected int idOverlord = -1;
	protected List<WarMessage> boiteAuxLettres;
	
	public ExplorerState(Fsm fsm, WarExplorerBrainController webc)
	{
		super(fsm, webc);
		this.webc = webc;
	}

	public void update()
	{
		boiteAuxLettres = webc.getMessages();
		
		for (WarMessage m : boiteAuxLettres) {
			if (m.getMessage() == "ImTheOverlord") {
				this.idOverlord = m.getSenderID();
			}
		}
	}
}