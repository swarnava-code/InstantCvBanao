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


public class InterestActivity extends AppCompatActivity {

    EditText et1;
    Button insertBtn,deleteBtn;
    TextView body,head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        et1=findViewById(R.id.et1);


        insertBtn=findViewById(R.id.insertBtnWork);
        deleteBtn=findViewById(R.id.deleteBtnWork);

        //body=findViewById(R.id.work_exp);
        head=findViewById(R.id.academic_info_head);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InterestDbHelper user_insert=new InterestDbHelper(InterestActivity.this);
                SQLiteDatabase db_insert=user_insert.getWritableDatabase();
                user_insert.insertData(et1.getText().toString(),db_insert);

                et1.setText("");


                Toast.makeText(InterestActivity.this, "Successfully Added !", Toast.LENGTH_SHORT).show();
                Intent j = new Intent(InterestActivity.this,MainActivity.class);
                startActivity(j);
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder abuilder=new AlertDialog.Builder(InterestActivity.this);
                abuilder.setTitle("You Want to Delete !");
                abuilder.setMessage("You are going to Erase your Skills !\nAre You Sure ?");
                abuilder.setPositiveButton("Yes Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        InterestDbHelper user = new InterestDbHelper(InterestActivity.this);
                        SQLiteDatabase db = user.getReadableDatabase();
                        int num_rows=user.deleteAllData(db);
                        if (num_rows==0){
                            Toast.makeText(InterestActivity.this,"Problem to Delete Interest",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(InterestActivity.this,"Interest Deleted",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(InterestActivity.this,MainActivity.class);
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