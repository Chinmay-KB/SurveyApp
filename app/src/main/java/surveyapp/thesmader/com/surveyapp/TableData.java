package surveyapp.thesmader.com.surveyapp;

import android.provider.BaseColumns;

/**
 * Created by Chinmay on 03-05-2018.
 */

public class TableData {
    TableData()
    {

    }
    public static abstract class TableInfo implements BaseColumns
    {
        public static final String STREAM="stream";
        public static final String MARKS="marks";
        public static final String MAIN_PAPER="paper0";
        public static final String PAPER_1="paper1";
        public static final String PAPER_2="paper2";
        public static final String PAPER_3="paper3";
        public static final String DATABASE_NAME="SurveyDB";
        public static final String TABLE_NAME="info";
    }
}
