package sclab.doc.instantcvbanao;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class UpdateActivity extends AppCompatActivity {

    Button home,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;
    ImageButton goHome;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        home=findViewById(R.id.home);
        goHome=findViewById(R.id.goHome);

        b1=findViewById(R.id.contact_info_btn);
        b2=findViewById(R.id.academic_info_btn);
        b3=findViewById(R.id.work_exp_btn);
        b4=findViewById(R.id.project_info_btn);
        b5=findViewById(R.id.ref_info_btn);
        b6=findViewById(R.id.skill_btn);
        b7=findViewById(R.id.interest_btn);
        b8=findViewById(R.id.hobbies_btn);
        b9=findViewById(R.id.strength_btn);
        b10=findViewById(R.id.achievement_btn);
        b11=findViewById(R.id.objective_btn);
        b12=findViewById(R.id.declaration_btn);

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateActivity.this,ContactActivity.class);
                startActivity(i);
                finish();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateActivity.this,AcademicActivity.class);
                startActivity(i);
                finish();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateActivity.this,WorkActivity.class);
                startActivity(i);
                finish();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateActivity.this,ProjectActivity.class);
                startActivity(i);
                finish();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateActivity.this,ReferenceActivity.class);
                startActivity(i);
                finish();
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateActivity.this,SkillActivity.class);
                startActivity(i);
                finish();
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateActivity.this,InterestActivity.class);
                startActivity(i);
                finish();
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateActivity.this,HobbiesActivity.class);
                startActivity(i);
                finish();
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateActivity.this,StrengthActivity.class);
                startActivity(i);
                finish();
            }
        });

        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateActivity.this,AchievementActivity.class);
                startActivity(i);
                finish();
            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateActivity.this,ObjectiveActivity.class);
                startActivity(i);
                finish();
            }
        });

        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateActivity.this,DeclarationActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent j = new Intent(UpdateActivity.this,MainActivity.class);
        startActivity(j);
        finish();
    }

}
