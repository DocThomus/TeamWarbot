package brains;

import java.util.ArrayList;

import rocketlauncher.RocketLauncherStateIdle;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.brains.brains.WarRocketLauncherBrain;
import edu.warbot.communications.WarMessage;
import fsm.Fsm;

public abstract class WarRocketLauncherBrainController extends WarRocketLauncherBrain {
	
	Fsm fsm;
	
	public ArrayList<WarMessage> mailbox;
	public ArrayList<WarAgentPercept> percepts;
	public int idOverlord = -1;
	public double distanceBase;
	public double directionBase;
	public double distanceEnemyUnit;
	public double angleEnemyUnit;
	public double distanceEnemyBase;
	public double angleEnemyBase;
	public double distanceRLAllielePlusProche;
	public double angleRLAllieLePlusProche;

    public WarRocketLauncherBrainController() {
        super();
        fsm = new Fsm();
        fsm.push(new RocketLauncherStateIdle(fsm, this));
    }

    @Override
    public String action() {
    	this.update();
    	return fsm.execute();
    }
    
    public void update() {
		mailbox = new ArrayList<WarMessage>(getMessages());
		percepts = new ArrayList<WarAgentPercept>(getPercepts());
	}
}