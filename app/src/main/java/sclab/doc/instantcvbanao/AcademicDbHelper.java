package sclab.doc.instantcvbanao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AcademicDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="cv2";
    private static final int DB_VERSION=1;

    private static final String TABLE_NAME="academic_details";

    private static final String COL1="degree";
    private static final String COL2="institute";
    private static final String COL3="percentage";
    private static final String COL4="year";

    private static final String CREATE_QUERY="CREATE TABLE " + TABLE_NAME + " ("  + COL1 + " text, " + COL2 + " text, " + COL3 + " text, " + COL4 + " text)";

    public  AcademicDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        Log.i("DB Message","Academic Details Database Created");
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
        Log.i("DB Message","Academic Details Table Created");
    }

    public void insertData( String degree, String institute , String percentage, String year, SQLiteDatabase db){
        ContentValues content = new ContentValues();

        content.put(COL1,degree);
        content.put(COL2,institute);
        content.put(COL3,percentage);
        content.put(COL4,year);

        db.insert(TABLE_NAME,null,content);
        Log.i("DB Message","1 data inserted in academic details");
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
