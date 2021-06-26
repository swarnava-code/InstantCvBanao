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


public class WorkActivity extends AppCompatActivity {

    EditText et1,et2,et3,et4,et5,et6;
    Button insertBtn,deleteBtn;
    TextView body,head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        et1=findViewById(R.id.organisation);
        et2=findViewById(R.id.position);
        et3=findViewById(R.id.duration);
        et4=findViewById(R.id.activities);
        et5=findViewById(R.id.exposure);
        et6=findViewById(R.id.responsibility);


        insertBtn=findViewById(R.id.insertBtnWork);
        deleteBtn=findViewById(R.id.deleteBtnWork);

       // body=findViewById(R.id.work_exp);
        head=findViewById(R.id.academic_info_head);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkDbHelper user_insert=new WorkDbHelper(WorkActivity.this);
                SQLiteDatabase db_insert=user_insert.getWritableDatabase();
                user_insert.insertData(et1.getText().toString(),et2.getText().toString(),et3.getText().toString(),et4.getText().toString(),et5.getText().toString(),et6.getText().toString(),db_insert);

                et1.setText("");
                et2.setText("");
                et3.setText("");
                et4.setText("");
                et5.setText("");
                et6.setText("");

                Toast.makeText(WorkActivity.this, "Successfully Added !", Toast.LENGTH_SHORT).show();
                Intent j = new Intent(WorkActivity.this,MainActivity.class);
                startActivity(j);
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder abuilder=new AlertDialog.Builder(WorkActivity.this);
                abuilder.setTitle("You Want to Delete !");
                abuilder.setMessage("You are going to Erase your Work Experience Details !\nAre You Sure ?");
                abuilder.setPositiveButton("Yes Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WorkDbHelper user = new WorkDbHelper(WorkActivity.this);
                        SQLiteDatabase db = user.getReadableDatabase();
                        int num_rows=user.deleteAllData(db);
                        if (num_rows==0){
                            Toast.makeText(WorkActivity.this,"Problem to Delete Work Experience Details",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(WorkActivity.this,"Work Experience Deleted",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(WorkActivity.this,MainActivity.class);
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