package ai.getdosage.android.dosage;

/**
 * Created by Eskimopies on 27/10/2016.
 */

public class DoseDbSchema {

    public static final class DoseTable {
        public static final String NAME = "doses";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DURATION = "duration";
            public static final String LOCATION = "location";
        }

    }

}
