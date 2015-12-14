package squads.base;

import java.util.ArrayList;

import squads.fsm.Fsm;
import squads.fsm.State;

import edu.warbot.agents.agents.WarBase;
import edu.warbot.brains.brains.WarBaseBrain;
import edu.warbot.communications.WarMessage;

public class BaseState extends State
{
	public WarBaseBrain wbb;
	
	public BaseState(Fsm fsm, WarBaseBrain wbb)
	{
		super(fsm, wbb);
		this.wbb = wbb;
	}
	
	public String execute()
	{
		ArrayList<WarMessage> boiteAuxLettres = new ArrayList<WarMessage>(wbb.getMessages());
		
		reflexe();
		
		for(WarMessage message : boiteAuxLettres)
		{
			if(message.getMessage() == "WhereAreYou")
			{
				wbb.sendMessage(message.getSenderID(), "Here", "");
			}
		}
		
		return WarBase.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		
	}

	public void update()
	{
		
	}
}
