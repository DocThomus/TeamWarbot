package Test;

import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.brains.brains.WarRocketLauncherBrain;

@SuppressWarnings("unused")
public abstract class WarRocketLauncherBrainController extends WarRocketLauncherBrain {


    public WarRocketLauncherBrainController() {
        super();
    }

    @Override
    public String action() {

        return ACTION_IDLE;
    }

}