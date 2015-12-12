package Test;

import edu.warbot.agents.agents.WarTurret;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.brains.brains.WarTurretBrain;

import java.util.List;

public abstract class WarTurretBrainController extends WarTurretBrain {

    private int directionVue;

    public WarTurretBrainController() {
        super();

        this.directionVue = 0;
    }

    @Override
    public String action() {

    	this.directionVue += 90;
        if (this.directionVue == 360) {
        	this.directionVue = 0;
        }
        setHeading(this.directionVue);

        List <WarAgentPercept> percepts = getPercepts();
        for (WarAgentPercept p : percepts) {
            switch (p.getType()) {
                case WarRocketLauncher:
                    if (isEnemy(p)) {
                        setHeading(p.getAngle());
                        if (isReloaded()) {
                            return WarTurret.ACTION_FIRE;
                        } else
                            return WarTurret.ACTION_RELOAD;
                    }
                    break;
                default:
                    break;
            }
        }

        return WarTurret.ACTION_IDLE;
    }
}
