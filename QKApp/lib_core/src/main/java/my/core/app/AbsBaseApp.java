package my.core.app;

import android.app.Activity;
import android.app.Application;

import my.core.config.JsonConfig;

/**
 * 
 * @author hermeschang
 *
 * TODO
 *
 */
public abstract class AbsBaseApp extends Application {

    private static AbsBaseApp mInstance;

    private JsonConfig mConfig;

    protected Activity mCurrentActivity;
    
    public static AbsBaseApp getInstance() {
        return mInstance;
    }

    public AbsBaseApp() {
        mInstance = this;

    }
    
    public void onInitApp() {
        onInitService();

        beforeStartService();
        onStartService();
        afterStartService();
    }
    
    public void onFiniApp() {
        beforeStopService();
        onStopService();
        afterStopService();
    }

    protected abstract void onInitService();

    protected abstract void beforeStartService();
    protected abstract void onStartService();
    protected abstract void afterStartService();

    protected abstract void beforeStopService();
    protected abstract void onStopService();
    protected abstract void afterStopService();
}
