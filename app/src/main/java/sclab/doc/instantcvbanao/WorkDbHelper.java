package sclab.doc.instantcvbanao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class WorkDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="cv3";
    private static final int DB_VERSION=1;

    private static final String TABLE_NAME="work_experience";

    private static final String COL1="organisation";
    private static final String COL2="position";
    private static final String COL3="duration";
    private static final String COL4="activities";
    private static final String COL5="exposure";
    private static final String COL6="responsibility";

    private static final String CREATE_QUERY="CREATE TABLE " + TABLE_NAME + " ("  + COL1 + " text, " + COL2 + " text, " + COL3 + " text, " + COL4 + " , " + COL5 + " , " + COL6 +" text)";

    public  WorkDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        Log.i("DB Message","Work Experience Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
        Log.i("DB Message","Work Experience Table Created");
    }

    public void insertData( String organisation, String position , String duration, String activities, String exposure, String responsibility, SQLiteDatabase db){
        ContentValues content = new ContentValues();

        content.put(COL1,organisation);
        content.put(COL2,position);
        content.put(COL3,duration);
        content.put(COL4,activities);
        content.put(COL5,exposure);
        content.put(COL6,responsibility);

        db.insert(TABLE_NAME,null,content);
        Log.i("DB Message","1 data inserted in Work Exp");
    }

    public Cursor viewData(SQLiteDatabase db){
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return  cursor;
    }


    public int deleteAllData( SQLiteDatabase db){
        int num_rows=db.delete(TABLE_NAME,null,null);
        return num_rows;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
