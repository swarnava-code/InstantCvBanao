package sclab.doc.instantcvbanao;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;

public class ContactActivity extends AppCompatActivity {

    EditText et1,et2,et3,et4,et5,et6,et7,et8,et9;
    Button updateBtn, resetBtn;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    ImageView profileImageView;
    String photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        et1=findViewById(R.id.updateName);
        et2=findViewById(R.id.updateGender);
        et3=findViewById(R.id.updatePhone);
        et4=findViewById(R.id.updateEmail);
        et5=findViewById(R.id.updateLanguage);
        et6=findViewById(R.id.updateDob);
        et7=findViewById(R.id.updateNationality);
        et8=findViewById(R.id.updateAddress);
        et9=findViewById(R.id.updateWebsite);
        updateBtn=findViewById(R.id.updateBtn);
        resetBtn=findViewById(R.id.resetBtn);
        profileImageView = findViewById(R.id.profilePhoto);

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


        //set saved data
        ContactDbHelper user= new ContactDbHelper(ContactActivity.this);
        SQLiteDatabase dbR= user.getReadableDatabase();
        Cursor c = user.viewData(dbR);
        c.moveToFirst();

        et1.setText(c.getString(1));
        et2.setText(c.getString(2));
        et3.setText(c.getString(3));
        et4.setText(c.getString(4));
        et5.setText(c.getString(5));
        et6.setText(c.getString(6));
        et7.setText(c.getString(7));
        et8.setText(c.getString(8));
        et9.setText(c.getString(9));
        try{
            Bitmap bmp = StringToBitmap(c.getString(12));
            profileImageView.setImageBitmap(bmp);
        }catch (Exception e){}


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String saveToDb=null;

                try {
                    Bitmap image = screenshot.takescreenshotOfRootView(profileImageView);
                    saveToDb = BitMapToString(image);
                    photo = imageUri.toString();
                }catch (Exception e){
                }
                ContactDbHelper user_con=new ContactDbHelper(ContactActivity.this);
                SQLiteDatabase db_con=user_con.getWritableDatabase();
                int num_rows=user_con.updateData(et1.getText().toString(),et2.getText().toString(),et3.getText().toString(),et4.getText().toString(),et5.getText().toString(),et6.getText().toString(),et7.getText().toString(),et8.getText().toString(),et9.getText().toString(), saveToDb,db_con);
                if (num_rows==0){
                    Toast.makeText(ContactActivity.this,"Not Updated, Something Wrong !",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ContactActivity.this,"Successfully Updated",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ContactActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder abuilder=new AlertDialog.Builder(ContactActivity.this);
                abuilder.setTitle("You Want to Delete !");
                abuilder.setMessage("You are going to Erase your Contacts !\nAre You Sure ?");
                abuilder.setPositiveButton("Yes Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ContactDbHelper user_con=new ContactDbHelper(ContactActivity.this);
                        SQLiteDatabase db_con=user_con.getWritableDatabase();
                        int num_rows=user_con.updateData("","","","","","","","","",null,db_con);
                        if (num_rows==0){
                            Toast.makeText(ContactActivity.this,"Not Reset, Something Wrong !",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(ContactActivity.this,"Successfully Reset",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(ContactActivity.this,MainActivity.class);
                            startActivity(i);
                        }

                    }
                });
                abuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog mydialog=abuilder.create();
                mydialog.show();
            }
        });

    }

    private  void  openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
            profileImageView.setImageURI(imageUri);
        }
    }

    @Override
    public void onBackPressed() {
        Intent j = new Intent(ContactActivity.this,MainActivity.class);
        startActivity(j);
        finish();
    }

    public void onClickNav(View view) {
        Intent i = new Intent(ContactActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b= baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public Bitmap StringToBitmap(String encodedString){
        try{
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getStackTrace();
            return  null;
        }
    }

}
