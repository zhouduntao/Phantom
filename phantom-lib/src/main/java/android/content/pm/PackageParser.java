package android.content.pm;

import java.util.ArrayList;

/**
 * Created by zhouduntao on 2017/5/26.
 */

public class PackageParser {


    public final static class Package {
        public final ArrayList<Activity> activities = new ArrayList<Activity>(0);
    }

    public final static class Activity {
        //        extends Component<ActivityIntentInfo>
        public ActivityInfo info;
    }

}
