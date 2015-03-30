package my.core.service;

/**
 * @author hermeschang
 * 
 */
public interface IService {
	
	public enum State {
		Init, Start, Stop;
	}

	public boolean startService();
	public boolean stopService();

	public State getState();

}
