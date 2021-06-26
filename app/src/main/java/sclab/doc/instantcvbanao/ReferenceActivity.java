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



public class ReferenceActivity extends AppCompatActivity {

    EditText et1,et2,et3,et4,et5;
    Button insertBtn,deleteBtn;
    TextView body,head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        et4=findViewById(R.id.et4);
        et5=findViewById(R.id.et5);


        insertBtn=findViewById(R.id.insertBtnWork);
        deleteBtn=findViewById(R.id.deleteBtnWork);

        //body=findViewById(R.id.work_exp);
        head=findViewById(R.id.academic_info_head);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReferenceDbHelper user_insert=new ReferenceDbHelper(ReferenceActivity.this);
                SQLiteDatabase db_insert=user_insert.getWritableDatabase();
                user_insert.insertData(et1.getText().toString(),et2.getText().toString(),et3.getText().toString(),et4.getText().toString(),et5.getText().toString(),db_insert);

                et1.setText("");
                et2.setText("");
                et3.setText("");
                et4.setText("");
                et5.setText("");


                Toast.makeText(ReferenceActivity.this, "Successfully Added !", Toast.LENGTH_SHORT).show();
                Intent j = new Intent(ReferenceActivity.this,MainActivity.class);
                startActivity(j);
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder abuilder=new AlertDialog.Builder(ReferenceActivity.this);
                abuilder.setTitle("You Want to Delete !");
                abuilder.setMessage("You are going to Erase your Reference Information !\nAre You Sure ?");
                abuilder.setPositiveButton("Yes Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ReferenceDbHelper user = new ReferenceDbHelper(ReferenceActivity.this);
                        SQLiteDatabase db = user.getReadableDatabase();
                        int num_rows=user.deleteAllData(db);
                        if (num_rows==0){
                            Toast.makeText(ReferenceActivity.this,"Problem to Delete Reference Information",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(ReferenceActivity.this,"Reference Information Deleted",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(ReferenceActivity.this,MainActivity.class);
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