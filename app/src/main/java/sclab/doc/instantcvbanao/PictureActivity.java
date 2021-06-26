package sclab.doc.instantcvbanao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



public class PictureActivity extends AppCompatActivity {

    Button next;
    ImageView img;
    int currentIndex=0;
    int[] imageId={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        next=findViewById(R.id.next);
        img=findViewById(R.id.img);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageResource(imageId[currentIndex]);
                currentIndex++;
                currentIndex=currentIndex%imageId.length;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent j = new Intent(this,MainActivity.class);
        startActivity(j);
    }

    public void onClickNav(View view) {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
