package ch2_17;

import aima.core.agent.Action;
import aima.core.agent.Agent;
import aima.core.agent.EnvironmentState;
import aima.core.agent.Percept;
import aima.core.agent.impl.AbstractEnvironment;
import aima.core.agent.impl.DynamicAction;
import aima.core.environment.vacuum.LocalVacuumEnvironmentPercept;
import aima.core.environment.vacuum.VacuumEnvironment;
import aima.core.environment.vacuum.VacuumEnvironmentState;

import java.util.Random;

/**
 * Created by Chris on 9/9/2015.
 *
 * Based off of VacuumEnvironment.java in the aima-core package.
 * Extends VacuumEnvironment so it integrates with the GUI Demo already available.
 *
 * I added a third state, SemiClean.  That way I'm not just remaking exactly what they already made.  Added the state in VacuumEnvironment.java to preserve working functionality.
 *
 *
 */
public class VacuumEnvironmentC extends VacuumEnvironment {


    protected VacuumEnvironmentState envState = null;
    protected boolean isDone = false;


    public VacuumEnvironmentC(){
        Random r = new Random();
        LocationState[] states = new LocationState[2];
        for(int i = 0; i <= 1; i++){
            switch(r.nextInt(3)){
                case 0:
                    states[i] = LocationState.Clean;
                    break;
                case 1:
                    states[i] = LocationState.SemiClean;
                    break;
                case 2:
                    states[i] = LocationState.Dirty;
                    break;
            }
        }
        envState = new VacuumEnvironmentState(states[0],states[1]);
    }
    @Override
    public EnvironmentState getCurrentState() {
        return envState;
    }

    @Override
    public EnvironmentState executeAction(Agent agent, Action action) {

        if(ACTION_MOVE_RIGHT == action){
            envState.setAgentLocation(agent, LOCATION_B);
            updatePerformanceMeasure(agent, -1);
        }
        else if(ACTION_MOVE_LEFT == action){
            envState.setAgentLocation(agent, LOCATION_A);
            updatePerformanceMeasure(agent, 1);
        }
        else if (ACTION_SUCK == action) {
            if(LocationState.Dirty == envState.getLocationState(envState.getAgentLocation(agent))){
                envState.setLocationState(envState.getAgentLocation(agent), LocationState.SemiClean);
                updatePerformanceMeasure(agent, 5);
            }
            else if(LocationState.SemiClean == envState.getLocationState(envState.getAgentLocation(agent))){
                envState.setLocationState(envState.getAgentLocation(agent), LocationState.Clean);
                updatePerformanceMeasure(agent, 10);
            }
        }
        else if (action.isNoOp()){
            isDone = true;
        }
        return envState;
    }

    @Override
    public Percept getPerceptSeenBy(Agent anAgent) {
        String agentLocation = envState.getAgentLocation(anAgent);
        return new LocalVacuumEnvironmentPercept(agentLocation, envState.getLocationState(agentLocation));
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

            envState.setAgentLocation(a, location);
            super.addAgent(a);
        }

        public LocationState getLocationState(String location) {
            return envState.getLocationState(location);
        }

        public String getAgentLocation(Agent a) {
            return envState.getAgentLocation(a);
        }
}
