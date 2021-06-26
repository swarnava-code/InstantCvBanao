package sclab.doc.instantcvbanao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SettingDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="cvSetting123";
    private static final int DB_VERSION=1;

    private static final String TABLE_NAME="setting_details";

    private static final String COL0="id";
    private static final String COL1="orientation";
    private static final String COL2="gender";
    private static final String COL3="contact";
    private static final String COL4="email";
    private static final String COL5="language";
    private static final String COL6="dob";
    private static final String COL7="nationality";
    private static final String COL8="address";
    private static final String COL9="website";
    private static final String COL10="objective";
    private static final String COL11="declaration";
    private static final String COL12="photo";

    private static final String CREATE_QUERY="CREATE TABLE " + TABLE_NAME + " (" + COL0 + " , " + COL1 + " text, " + COL2 + " text, " + COL3 + " text, " + COL4 + " text, " + COL5 + " text, " + COL6 + " text, " + COL7  + " text, " + COL8 + " text, " + COL9 + " text, " + COL10 + " text, " + COL11 + " text," + COL12 + " text)";

    public  SettingDbHelper(Context context){
        super(context, DB_NAME,null, DB_VERSION);
        Log.i("DB Message","Contact Details Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
        Log.i("DB Message","Contact Details Table Created");
    }

    public void insertData(String name, String gender , String contact, String email, String language , String dob, String nationality, String address , String website, String photo, SQLiteDatabase db){
        ContentValues content = new ContentValues();

        content.put(COL0,"415");
        content.put(COL1,name);
        content.put(COL2,gender);
        content.put(COL3,contact);
        content.put(COL4,email);
        content.put(COL5,language);
        content.put(COL6,dob);
        content.put(COL7,nationality);
        content.put(COL8,address);
        content.put(COL9,website);
        content.put(COL12,photo);

        db.insert(TABLE_NAME,null,content);
        Log.i("DB Message","1 data inserted in contact details");
    }

    public Cursor viewData(SQLiteDatabase db){
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return  cursor;
    }


    public int updateData(String name, String gender , String contact, String email, String language , String dob, String nationality, String address , String website, String photo, SQLiteDatabase db ){
        ContentValues content=new ContentValues();

        content.put(COL1,name);
        content.put(COL2,gender);
        content.put(COL3,contact);
        content.put(COL4,email);
        content.put(COL5,language);
        content.put(COL6,dob);
        content.put(COL7,nationality);
        content.put(COL8,address);
        content.put(COL9,website);
        content.put(COL12,photo);

        String where="id=?";

        String[] whereArgs={String.valueOf("415")};

        int num_rows=db.update(TABLE_NAME,content,where,whereArgs);

        return num_rows;

    }

    public int updateObjData(String obj , SQLiteDatabase db ){
        ContentValues content=new ContentValues();

        content.put(COL10,obj);

        String where="id=?";

        String[] whereArgs={String.valueOf("415")};

        int num_rows=db.update(TABLE_NAME,content,where,whereArgs);

        return num_rows;
    }
    public int updateDecData(String declaration , SQLiteDatabase db ){
        ContentValues content=new ContentValues();

        content.put(COL11,declaration);

        String where="id=?";

        String[] whereArgs={String.valueOf("415")};

        int num_rows=db.update(TABLE_NAME,content,where,whereArgs);

        return num_rows;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
