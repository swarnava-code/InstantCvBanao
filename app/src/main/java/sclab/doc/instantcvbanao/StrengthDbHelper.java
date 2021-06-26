package sclab.doc.instantcvbanao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StrengthDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="cv9";
    private static final int DB_VERSION=1;

    private static final String TABLE_NAME="strengths";

    private static final String COL1="strength";


    private static final String CREATE_QUERY="CREATE TABLE " + TABLE_NAME + " ("  + COL1  + " text)";

    public StrengthDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        Log.i("DB Message","Strength Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
        Log.i("DB Message","Strength Table Created");
    }

    public void insertData( String pass1, SQLiteDatabase db){
        ContentValues content = new ContentValues();

        content.put(COL1,pass1);

        db.insert(TABLE_NAME,null,content);
        Log.i("DB Message","1 data inserted in Strength");
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
