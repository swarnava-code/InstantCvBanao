package sclab.doc.instantcvbanao;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AcademicActivity extends AppCompatActivity {

    EditText et1,et2,et3,et4;
    Button insertBtnAca,deleteBtnAca;
    TextView academic_info,academic_info_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic);

        et1=findViewById(R.id.degree);
        et2=findViewById(R.id.institute);
        et3=findViewById(R.id.percentage);
        et4=findViewById(R.id.year);

        insertBtnAca=findViewById(R.id.insertBtnAca);
        deleteBtnAca=findViewById(R.id.deleteBtnAca);

        //academic_info=findViewById(R.id.academic_info);
        academic_info_head=findViewById(R.id.academic_info_head);

        insertBtnAca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AcademicDbHelper user_aca=new AcademicDbHelper(AcademicActivity.this);
                SQLiteDatabase db_aca=user_aca.getWritableDatabase();
                user_aca.insertData(et1.getText().toString(),et2.getText().toString(),et3.getText().toString(),et4.getText().toString(),db_aca);

                et1.setText("");
                et2.setText("");
                et3.setText("");
                et4.setText("");

                Toast.makeText(AcademicActivity.this, "Successfully Added !", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AcademicActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        deleteBtnAca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder abuilder=new AlertDialog.Builder(AcademicActivity.this);
                abuilder.setTitle("You Want to Delete !");
                abuilder.setMessage("You are going to Erase your Academic Details !\nAre You Sure ?");
                abuilder.setPositiveButton("Yes Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AcademicDbHelper user = new AcademicDbHelper(AcademicActivity.this);
                        SQLiteDatabase db = user.getReadableDatabase();
                        int num_rows=user.deleteAllData(db);
                        if (num_rows==0){
                            Toast.makeText(AcademicActivity.this,"Problem to Delete Academic Details",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(AcademicActivity.this,"Academic Details Deleted",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(AcademicActivity.this,MainActivity.class);
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

    @Override
    public void onBackPressed() {
        Intent j = new Intent(this,MainActivity.class);
        startActivity(j);
        finish();
    }

    public void onClickNav(View view) {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}