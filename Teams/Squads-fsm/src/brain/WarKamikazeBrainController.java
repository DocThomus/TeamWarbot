package brain;

import edu.warbot.agents.agents.WarKamikaze;
import edu.warbot.brains.brains.WarKamikazeBrain;

public abstract class WarKamikazeBrainController extends WarKamikazeBrain
{
    public WarKamikazeBrainController()
    {
        super();
    }

    @Override
    public String action()
    {
    	return WarKamikaze.ACTION_FIRE;
//        List <WarAgentPercept> percepts = getPercepts();
//
//        for (WarAgentPercept p : percepts)
//        {
//            switch (p.getType())
//            {
//                case WarBase:
//                    if (isEnemy(p))
//                    {
//                        broadcastMessageToAll("Ennemi Base Found", String.valueOf(p.getAngle()), String.valueOf(p.getDistance()));
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//
//        if (isBlocked())
//            setRandomHeading();
//        return WarExplorer.ACTION_MOVE;
    }
}
