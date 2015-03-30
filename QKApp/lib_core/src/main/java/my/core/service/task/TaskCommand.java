package my.core.service.task;

/**
 * @author hermeschang
 *
 */
public class TaskCommand {

	public enum CommandCode {
		DoTask;
	}
	
	protected CommandCode mCommandCode;
	protected ITask mTask;
	protected ICallback mCallback;
	protected IFutureSetter mFutureSetter;
	protected int mTicket;

	/**
	 * Constructor
	 * 
	 */
	public TaskCommand(CommandCode commandCode, ITask task, ICallback callback, IFutureSetter futureSetter, int ticket) {
		this.mCommandCode = commandCode;
		this.mTask = task;
		this.mCallback = callback;
		this.mFutureSetter = futureSetter;
		this.mTicket = ticket;
	}

	public CommandCode getCommandCode() {
		return mCommandCode;
	}

	public ITask getTask() {
		return mTask;
	}

	public ICallback getCallback() {
		return mCallback;
	}

	public IFutureSetter getFutureSetter() {
		return mFutureSetter;
	}

	public int getTicket() {
		return mTicket;
	}


}
