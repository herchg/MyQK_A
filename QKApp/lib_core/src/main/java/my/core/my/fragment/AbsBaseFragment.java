package my.core.my.fragment;

import android.app.Fragment;
import my.core.config.JsonConfig;

/**
 * Created by hermeschang on 2014/3/17.
 * @author hermeschang
 */
public abstract class AbsBaseFragment extends Fragment {

    private JsonConfig mConfig;

    public AbsBaseFragment() {
        super();

        init();
    }

    private void init() {
        // todo load config from assets/config
    }

}
