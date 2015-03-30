package my.core.service;

/**
 * 
 * @author hermeschang
 *
 */
public abstract class AbsService implements IService {

	protected State mState;
	
	public AbsService() {
	    
		super();
		
		this.mState = State.Init;
	}
	
	@Override
	public boolean startService() {
		if (executeStartService()) {
			this.mState = State.Start;
			return true;
		} 
		
		return false;
	}

	@Override
	public boolean stopService() {
		if (executeStopService()) {
			this.mState = State.Stop;
			return true;
		} 
		
		return false;
	}
	
	protected abstract boolean executeStartService();
	protected abstract boolean executeStopService();
	
	@Override
	public State getState() {
		return mState;
	}

}
