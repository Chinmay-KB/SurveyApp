package surveyapp.thesmader.com.surveyapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Chinmay on 03-05-2018.
 */

public class DatabaseOperations extends SQLiteOpenHelper {
    public static final int database_version=1;
    public String CREATE_QUERY="CREATE TABLE "+ TableData.TableInfo.TABLE_NAME+"("+ TableData.TableInfo.STREAM+" Text,"+ TableData.TableInfo.MARKS
    public DatabaseOperations(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME,null,database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
