package brain;

import util.Vecteur;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.brains.brains.WarRocketLauncherBrain;
import edu.warbot.communications.WarMessage;

public abstract class WarRocketLauncherBrainController extends WarRocketLauncherBrain
{


    public WarRocketLauncherBrainController()
    {
        super();
    }

    @Override
    public String action()
    {
        for (WarAgentPercept wp : getPerceptsEnemies())
        {
            if (wp.getType().equals(WarAgentType.WarBase))
            {
            	this.broadcastMessageToAgentType(WarAgentType.WarBase, "EnemyBase", String.valueOf(wp.getID()),
            			String.valueOf(wp.getDistance()), String.valueOf(wp.getAngle()));
                setHeading(wp.getAngle());
                if (isReloaded())
                    return ACTION_FIRE;
                else if (isReloading())
                    return ACTION_IDLE;
                else
                    return ACTION_RELOAD;
            }
        }
        
        this.broadcastMessageToAgentType(WarAgentType.WarBase, "EnemyBase?","");
        
        for(WarMessage message : getMessages())
        {
        	if(message.getSenderType() == WarAgentType.WarBase && message.getMessage() == "EnemyBase")
        	{
        		String s = message.getContent()[0] + " " + message.getContent()[1] + " et "
        				+ message.getDistance() + " " + message.getAngle();
        		Vecteur a = new Vecteur(Double.parseDouble(message.getContent()[0]), Double.parseDouble(message.getContent()[1]));
				Vecteur b = new Vecteur(message.getDistance(), message.getAngle());
				Vecteur c = a.add(b);
				
				setDebugString(s + " et " + c.getLongueur() + " " + c.getAngle());
				
				setHeading(c.getAngle());
        	}
        }
        
        if (isBlocked())
            setRandomHeading();

        return ACTION_MOVE;
    }

}