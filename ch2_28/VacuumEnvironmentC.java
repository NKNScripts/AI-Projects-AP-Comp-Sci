package aima.core.environment.vacuum;

import java.util.Random;

import aima.core.agent.Action;
import aima.core.agent.Agent;
import aima.core.agent.EnvironmentState;
import aima.core.agent.Percept;
import aima.core.agent.impl.AbstractEnvironment;
import aima.core.agent.impl.DynamicAction;
import static aima.core.environment.vacuum.VacuumEnvironment.ACTION_MOVE_LEFT;
import static aima.core.environment.vacuum.VacuumEnvironment.ACTION_MOVE_RIGHT;
import static aima.core.environment.vacuum.VacuumEnvironment.ACTION_SUCK;
import static aima.core.environment.vacuum.VacuumEnvironment.LOCATION_A;
import static aima.core.environment.vacuum.VacuumEnvironment.LOCATION_B;
import aima.core.environment.vacuum.VacuumEnvironment.LocationState;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): pg 58.<br>
 * <br>
 * Let the world contain just two locations. Each location may or may not
 * contain dirt, and the agent may be in one location or the other. There are 8
 * possible world states, as shown in Figure 3.2. The agent has three possible
 * actions in this version of the vacuum world: <em>Left</em>, <em>Right</em>,
 * and <em>Suck</em>. Assume for the moment, that sucking is 100% effective. The
 * goal is to clean up all the dirt.
 * 
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 * Modified by Christopher Shafer 9/14/15
 */
public class VacuumEnvironmentC extends VacuumEnvironment {
	// Allowable Actions within the Vacuum Environment
	

	//
	protected VacuumEnvironmentState envState = null;
	protected boolean isDone = false;
        
        private int cleanCount = 0;

	/**
	 * Constructs a vacuum environment with two locations, in which dirt is
	 * placed at random.
	 */
	public VacuumEnvironmentC() {
		Random r = new Random();
		envState = new VacuumEnvironmentState(
				0 == r.nextInt(2) ? VacuumEnvironment.LocationState.Clean : VacuumEnvironment.LocationState.Dirty,
				0 == r.nextInt(2) ? VacuumEnvironment.LocationState.Clean : VacuumEnvironment.LocationState.Dirty);
	}

	/**
	 * Constructs a vacuum environment with two locations, in which dirt is
	 * placed as specified.
	 * 
	 * @param locAState
	 *            the initial state of location A, which is either
	 *            <em>Clean</em> or <em>Dirty</em>.
	 * @param locBState
	 *            the initial state of location B, which is either
	 *            <em>Clean</em> or <em>Dirty</em>.
	 */
	public VacuumEnvironmentC(VacuumEnvironment.LocationState locAState, VacuumEnvironment.LocationState locBState) {
		envState = new VacuumEnvironmentState(locAState, locBState);
	}
        
        public VacuumEnvironmentC(int a, int b){
            LocationState states[] = new LocationState[2];
            if(a==0)
                states[0] = LocationState.Clean;
            else
                states[0] = LocationState.Dirty;
            if(b==0)
                states[1] = LocationState.Clean;
            else
                states[1] = LocationState.Dirty;
            envState = new VacuumEnvironmentState(states[0], states[1]);
        }

	@Override
	public EnvironmentState getCurrentState() {
		return envState;
	}

	@Override
	public EnvironmentState executeAction(Agent a, Action agentAction) {
                if(cleanCount >= 2){
                    isDone=true;
                    return envState;
                }
		if (ACTION_MOVE_RIGHT == agentAction) {
			envState.setAgentLocation(a, LOCATION_B);
			updatePerformanceMeasure(a, -1);
                        cleanCount++;
                        System.out.println(cleanCount);
		} else if (ACTION_MOVE_LEFT == agentAction) {
			envState.setAgentLocation(a, LOCATION_A);
			updatePerformanceMeasure(a, -1);
                        cleanCount++;
		} else if (ACTION_SUCK == agentAction) {
			if (VacuumEnvironment.LocationState.Dirty == envState.getLocationState(envState
					.getAgentLocation(a))) {
				envState.setLocationState(envState.getAgentLocation(a),
						VacuumEnvironment.LocationState.Clean);
				updatePerformanceMeasure(a, 10);
                                cleanCount++;
			}
		} else if (agentAction.isNoOp()) {
			// In the Vacuum Environment we consider things done if
			// the agent generates a NoOp.
			isDone = true;
		}

		return envState;
	}

	@Override
	public Percept getPerceptSeenBy(Agent anAgent) {
		if (anAgent instanceof NondeterministicVacuumAgent) {
    		// Note: implements FullyObservableVacuumEnvironmentPercept
    		return new VacuumEnvironmentState(this.envState);
    	}
		String agentLocation = envState.getAgentLocation(anAgent);
		return new LocalVacuumEnvironmentPercept(agentLocation,
				envState.getLocationState(agentLocation));
	}

	@Override
	public boolean isDone() {
		return super.isDone() || isDone;
	}

	@Override
	public void addAgent(Agent a) {
		int idx = new Random().nextInt(2);
		envState.setAgentLocation(a, idx == 0 ? LOCATION_A : LOCATION_B);
		super.addAgent(a);
	}

	public void addAgent(Agent a, String location) {
		// Ensure the agent state information is tracked before
		// adding to super, as super will notify the registered
		// EnvironmentViews that is was added.
		envState.setAgentLocation(a, location);
		super.addAgent(a);
	}

	public VacuumEnvironment.LocationState getLocationState(String location) {
		return envState.getLocationState(location);
	}

	public String getAgentLocation(Agent a) {
		return envState.getAgentLocation(a);
	}
        
        public void setAgentLocation(boolean a, Agent agent){
            if(a)
                envState.setAgentLocation(agent, LOCATION_A);
            else
                envState.setAgentLocation(agent, LOCATION_B);
        }
}