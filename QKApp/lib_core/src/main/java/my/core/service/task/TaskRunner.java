package my.core.service.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author hermeschang
 * 
 */
public class TaskRunner implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(TaskRunner.class);

	protected ITask mTask;
	protected ICallback mCallback;

	public TaskRunner(ITask task, ICallback callback) {
		mTask = task;
		mCallback = callback;
	}

	@Override
	public void run() {

		/*
		 * 1. do preTask
		 * 2. doTask and callback
		 * 3. do postTask
		 */
		if (mTask != null) {

			try {
				/* pre task */
				mTask.doPreTask();
			} catch (Exception ex) {
				logger.error("doPreTask(): " + ex.getMessage());
			}

			try {

				/* do task */
				if (mCallback != null) {
					mCallback.callback(mTask.doTask());
				} else {
					mTask.doTask();
				}
			} catch (Exception ex) {
				logger.error("doTask() & callback(): " + ex.getMessage());
			}

			try {
				/* post task */
				mTask.doPostTask();
			} catch (Exception ex) {
				logger.error("doPostTask(): " + ex.getMessage());
			}
		}

	}

}
