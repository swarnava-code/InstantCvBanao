package sclab.doc.instantcvbanao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SkillDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="cv6";
    private static final int DB_VERSION=1;

    private static final String TABLE_NAME="skills";

    private static final String COL1="skill";


    private static final String CREATE_QUERY="CREATE TABLE " + TABLE_NAME + " ("  + COL1  + " text)";

    public SkillDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        Log.i("DB Message","skill Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
        Log.i("DB Message","skill Table Created");
    }

    public void insertData( String pass1, SQLiteDatabase db){
        ContentValues content = new ContentValues();

        content.put(COL1,pass1);

        db.insert(TABLE_NAME,null,content);
        Log.i("DB Message","1 data inserted in skill");
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
