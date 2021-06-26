package sclab.doc.instantcvbanao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ReferenceDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="cv5";
    private static final int DB_VERSION=1;

    private static final String TABLE_NAME="ref_info";

    private static final String COL1="name";
    private static final String COL2="designation";
    private static final String COL3="organisation";
    private static final String COL4="email";
    private static final String COL5="description";


    private static final String CREATE_QUERY="CREATE TABLE " + TABLE_NAME + " ("  + COL1 + " text, " + COL2 + " text, " + COL3 + " text, " + COL4 + " , " + COL5 + " text)";

    public  ReferenceDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        Log.i("DB Message","Project info Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
        Log.i("DB Message","Project info Table Created");
    }

    public void insertData( String pass1, String pass2 , String pass3,  String pass4, String pass5, SQLiteDatabase db){
        ContentValues content = new ContentValues();

        content.put(COL1,pass1);
        content.put(COL2,pass2);
        content.put(COL3,pass3);
        content.put(COL4,pass4);
        content.put(COL5,pass5);

        db.insert(TABLE_NAME,null,content);
        Log.i("DB Message","1 data inserted in project info");
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
