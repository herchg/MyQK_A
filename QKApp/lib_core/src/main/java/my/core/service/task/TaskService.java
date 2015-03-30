package my.core.service.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import my.core.service.AbsService;

/**
 * 
 * @author hermeschang
 * 
 */
public class TaskService extends AbsService {

	
	protected final static boolean MAY_INTERRUPT_IF_RUNNING = true;
	
	private static Logger logger = LoggerFactory.getLogger(TaskService.class);
	
	protected ExecutorService mExecutorService;
	protected int mSize;
	
	public TaskService(int size) {
		this.mSize = size;
	}


	public synchronized Future<?> execute(ITask task, ICallback callback) {

		try {
			if (!mExecutorService.isShutdown()) {
				return mExecutorService.submit(new TaskRunner(task, callback));
			}
		} catch (Exception ex) {
			logger.error("execute()", ex);
		}
		
		return null;
	}

	/**
	 * 
	 * @param future
	 * @return false if the task could not be cancelled, typically because it has already completed normally; true otherwise 
	 */
	public boolean cancel(Future<?> future) {

		if (future != null) {
			return future.cancel(MAY_INTERRUPT_IF_RUNNING);
		}

		return false;
	}

    @Override
    protected boolean executeStartService() {
        try {
            mExecutorService = Executors.newFixedThreadPool(mSize);
            return true;
        } catch (Exception ex) {
            logger.error("executeStartService()", ex);
        }

        return false;
    }

    @Override
    protected boolean executeStopService() {
        try {
            mExecutorService.shutdownNow();
            return true;
        } catch (Exception ex) {
            logger.error("executeStopService()", ex);
        }
        return false;
    }


}
