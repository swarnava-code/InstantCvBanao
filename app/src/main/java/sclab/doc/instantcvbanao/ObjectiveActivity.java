package sclab.doc.instantcvbanao;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ObjectiveActivity extends AppCompatActivity {

    Button updateBtn,removeBtn;
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objective);

        updateBtn=findViewById(R.id.updateBtn);
        et1=findViewById(R.id.objectiveIn);
        removeBtn=findViewById(R.id.resetBtn);

        //set saved data
        ContactDbHelper user= new ContactDbHelper(ObjectiveActivity.this);
        SQLiteDatabase dbR= user.getReadableDatabase();
        Cursor c = user.viewData(dbR);
        c.moveToFirst();
        et1.setText(c.getString(10));


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactDbHelper user_con=new ContactDbHelper(ObjectiveActivity.this);
                SQLiteDatabase db_con=user_con.getWritableDatabase();
                int num_rows=user_con.updateObjData(et1.getText().toString(),db_con);
                if (num_rows==0){
                    Toast.makeText(ObjectiveActivity.this,"Not Updated, Something Wrong !",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ObjectiveActivity.this,"Successfully Updated",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ObjectiveActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder abuilder=new AlertDialog.Builder(ObjectiveActivity.this);
                abuilder.setTitle("You Want to Delete !");
                abuilder.setMessage("You are going to Erase your Objective !\nAre You Sure ?");
                abuilder.setPositiveButton("Yes Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContactDbHelper user_con=new ContactDbHelper(ObjectiveActivity.this);
                        SQLiteDatabase db_con=user_con.getWritableDatabase();
                        int num_rows=user_con.updateObjData("",db_con);
                        if (num_rows==0){
                            Toast.makeText(ObjectiveActivity.this,"Not Deleted, Something Wrong !",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(ObjectiveActivity.this,"Successfully Deleted",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(ObjectiveActivity.this,MainActivity.class);
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

    public void autoFill(View view){
        EditText et;
        et=findViewById(R.id.objectiveIn);
        et.setText(R.string.objective_sample);
    }
}
