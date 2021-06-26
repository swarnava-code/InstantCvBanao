package sclab.doc.instantcvbanao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class PrintActivity extends AppCompatActivity {

    //int currentIndex=0, listItemHeight=35;
    //String folderName;
    String bullet = "\uD83D\uDD18  ";

   // private static final int PICK_IMAGE = 100;
    //Uri imageUri;
    ImageView profileImageView;
    TextView tv;
    //boolean landscape=false;

    /*
    String[] id=new String[10];
    String[] degree = new String[10];
    String[] institute = new String[10];
    String[] percentage = new String[10];
    String[] year = new String[10];
     */

    String[] degree=new String[20], institute=new String[20], percentage=new String[20], year = new String[20];

    String[] organisation=new String[20], position=new String[20], duration=new String[20], activities=new String[20], exposure=new String[20], responsibility = new String[20];

    String[] project_title=new String[20], project_description=new String[20], role=new String[20], project_duration=new String[20], team_size=new String[20];

    String[] ref_person=new String[20], ref_designation=new String[20], ref_organisation=new String[20], ref_email=new String[20], ref_description=new String[20];

    public int aca = 0, work=0, proj=0, ref = 0;
    //ListView lv_academic, lv_work;

    int deviceWidth, deviceHeight;

    String currentDateTime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_print);

        profileImageView = findViewById(R.id.profileImageView);

        //MainActivity obj = new MainActivity();

        contact_view();
        academic_view();
        work_view();
        project_view();
        reference_view();
        skills_view();
        interest_view();
        hobbies_view();
        strength_view();
        achievement_view();
        objective_view();
        declaration_view();

         Handler handler = new Handler();
         handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                savePdfIntoGallery();
                openPdfFile();
                onBackPressed();
            }
         }, 500);



    }

    @SuppressLint("SourceLockedOrientationActivity")
    public void savePdfIntoGallery(){

        //Toast.makeText(PrintActivity.this, "Wait...", Toast.LENGTH_LONG).show();


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {

            //Handler handler = new Handler();
           // handler.postDelayed(new Runnable() {
                //@Override
                //public void run() {

                    //LinearLayout imageView=findViewById(R.id.cv);
                    ScrollView scrollView=findViewById(R.id.scrollView);
                    LinearLayout footer = findViewById(R.id.footer);
                    int pageWidth = 595, pageHeight = 842, imgWidth = pageWidth-60, imgHeight = pageHeight-100, pageLeft=30, pageTop=50;


                    int svBottom = scrollView.getBottom();
                    int y = 0;

                    PdfDocument document = null;

                    ActivityCompat.requestPermissions(PrintActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                    document = new PdfDocument();

                    //before taking the ss, set the orientation landscape

                    // crate a page description
                    PdfDocument.PageInfo pageInfo ;//= new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNo).create();
                    // create 1st  page
                    PdfDocument.Page page;// = document.startPage(pageInfo);
                    Canvas canvas ;//= page.getCanvas();
                    //scrollView.scrollTo(0, 0);
                    //Bitmap image1 = screenshot.takescreenshotOfRootView(imageView);
                    Bitmap image ;//= Bitmap.createScaledBitmap(image1, imgWidth/2, imgHeight, true);
                    //canvas.drawBitmap(image, pageLeft, pageTop, null);
                    //document.finishPage(page);




                    for(int pageNo=1; pageNo<20; pageNo++){
                        //Log.d("sc",pageNo+"");
                        if(y < footer.getBottom()) {
                            // Create Pages
                            pageInfo = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNo).create();
                            page = document.startPage(pageInfo);
                            canvas = page.getCanvas();
                            scrollView.scrollTo(0, y);
                            y += svBottom;
                            Bitmap image4 = screenshot.takescreenshotOfRootView(scrollView);
                            image = Bitmap.createScaledBitmap(image4, imgWidth, imgHeight/3, true);
                            canvas.drawBitmap(image, pageLeft, pageTop, null);
                            //document.finishPage(page);


                            if(y < footer.getBottom()) {
                                // Create Pages
                                //pageInfo = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, i).create();
                                //page = document.startPage(pageInfo);
                                //canvas = page.getCanvas();
                                scrollView.scrollTo(0, y);
                                y += svBottom;
                                Bitmap image5 = screenshot.takescreenshotOfRootView(scrollView);
                                image = Bitmap.createScaledBitmap(image5, imgWidth, imgHeight/3, true);
                                canvas.drawBitmap(image, pageLeft, (imgHeight/3)+50, null);
                                //document.finishPage(page);
                            }
                            if(y < footer.getBottom()) {
                                // Create Pages
                                //pageInfo = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, i).create();
                                //page = document.startPage(pageInfo);
                                //canvas = page.getCanvas();
                                scrollView.scrollTo(0, y);
                                y += svBottom;
                                Bitmap image5 = screenshot.takescreenshotOfRootView(scrollView);
                                image = Bitmap.createScaledBitmap(image5, imgWidth, imgHeight/3, true);
                                canvas.drawBitmap(image, pageLeft, ((imgHeight/3)*2)+50, null);
                                //document.finishPage(page);
                            }


                            document.finishPage(page);


                        }
                        else
                            break;

                        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                    }



                    // write the document content in internal memory
                    String directory_path = Environment.getExternalStorageDirectory().getPath() + "/InstantCVBanao/";
                    File file = new File(directory_path);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    currentDateTime = java.text.DateFormat.getDateTimeInstance().format(new Date());
                    String targetPdf = directory_path + currentDateTime +".pdf";
                    File filePath = new File(targetPdf);
                    try {
                        document.writeTo(new FileOutputStream(filePath));
                        Toast.makeText(PrintActivity.this, "PDF Saved : "+targetPdf, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Log.e("main", "error " + e.toString());
                        Toast.makeText(PrintActivity.this, "PDF not saved, Something wrong ! -"+e.toString(), Toast.LENGTH_LONG).show();
                    }
                    // close the document
                    document.close();
                    //landscape=false;

                //}
            //}, 2000);




/*
            File openfile = new  File(directory_path+currentDateTime+".pdf");
            Uri path = Uri.fromFile(openfile);
            Intent pdfOpenIntent = new Intent(Intent.ACTION_VIEW);
            pdfOpenIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pdfOpenIntent.setDataAndType(path, "application/pdf");
            try{
                startActivity(pdfOpenIntent);
            }catch(ActivityNotFoundException e){

            }

 */


        }
        else{
            Toast.makeText(this, "This feature not support in your device, min. req. os ver. KitKat ", Toast.LENGTH_LONG).show();
        }
    }

    private void openPdfFile(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //Try to open the file which recently created
                final File files = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"/InstantCVBanao/"+currentDateTime+".pdf");
                Uri path = Uri.fromFile(files);
                Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
                pdfOpenintent.setDataAndType(path, "application/pdf");
                pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                Intent intent = Intent.createChooser(pdfOpenintent,"Open PDF File");
                try{
                    startActivity(intent);
                }catch(Exception e){
                    //Toast.makeText(PrintActivity.this,"fail to open pdf file : "+ e.toString(), Toast.LENGTH_LONG).show();

                }

            }
        }, 100);
    }



    public void contact_view(){
        TextView contact_info=findViewById(R.id.contact_info);

        ContactDbHelper user= new ContactDbHelper(PrintActivity.this);
        SQLiteDatabase dbR= user.getReadableDatabase();

        Cursor c = user.viewData(dbR);

        if(c.getCount() == 0){
            //Insert Blank data in Contact
            SQLiteDatabase dbW = user.getWritableDatabase();
            user.insertData("","","","","","","","","",null,dbW);
            Toast.makeText(PrintActivity.this,"Blank Form Generated, Please Fill it",Toast.LENGTH_LONG).show();

            user.updateObjData("",dbW);
            user.updateDecData("",dbW);
        }
        else{
            //View Contact
            StringBuffer sb= new StringBuffer();
            c.moveToFirst();

            if (c.getString(1).length()!=0){
                sb.append("Name : " + c.getString(1) + "\n");
            }
            if (c.getString(2).length()!=0)
                sb.append("Gender : " + c.getString(2) + "\n");
            if (c.getString(3).length()!=0)
                sb.append("Phone no.: " + c.getString(3) + "\n");
            if (c.getString(4).length()!=0)
                sb.append("Email : " + c.getString(4) + "\n");
            if (c.getString(5).length()!=0)
                sb.append("Language : " + c.getString(5) + "\n");
            if (c.getString(6).length()!=0)
                sb.append("DOB : " + c.getString(6) + "\n");
            if (c.getString(7).length()!=0)
                sb.append("Nationality : " + c.getString(7) + "\n");
            if (c.getString(8).length()!=0)
                sb.append("Address : " + c.getString(8) + "\n");
            if (c.getString(9).length()!=0)
                sb.append("Website : " + c.getString(9) + "\n");

            try{
                //byte[] image=c.getBlob(12);
                Bitmap bmp = StringToBitmap(c.getString(12));
                profileImageView.setImageBitmap(bmp);
                //profileImageView.setImageURI(Uri.parse(c.getString(12)));
            }catch (Exception e){}

            contact_info.setText(sb.toString());
        }
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

    public void academic_view(){
        //TextView academic_info=findViewById(R.id.academic_info);
        TextView academic_info_head=findViewById(R.id.academic_info_head);
        ListView lv = findViewById(R.id.lv_academic);
        //LinearLayout table_head = findViewById(R.id.table_head_aca);
        //boolean deg=false, college=false, per=false, yearF=false;


        TextView aca_cer_tbl_head = findViewById(R.id.certificate);
        TextView aca_ins_tbl_head = findViewById(R.id.institute);
        TextView aca_per_tbl_head = findViewById(R.id.percentage);
        TextView aca_yea_tbl_head = findViewById(R.id.passing_year);

        aca_cer_tbl_head.setVisibility(View.INVISIBLE);
        aca_ins_tbl_head.setVisibility(View.INVISIBLE);
        aca_per_tbl_head.setVisibility(View.INVISIBLE);
        aca_yea_tbl_head.setVisibility(View.INVISIBLE);


        AcademicDbHelper user_aca= new AcademicDbHelper(PrintActivity.this);
        SQLiteDatabase db_aca= user_aca.getReadableDatabase();
        Cursor c_aca = user_aca.viewData(db_aca);
        if(c_aca.getCount()>0) {
            //StringBuffer sb_aca= new StringBuffer();
            c_aca.moveToFirst();

            do{
                if (c_aca.getString(0).length()!=0){
                    degree[aca]=c_aca.getString(0);
                    //sb_aca.append("Degree / Certificate : " + c_aca.getString(0) + "\n");
                    academic_info_head.setVisibility(View.VISIBLE);
                    aca_cer_tbl_head.setVisibility(View.VISIBLE);
                    // tv1.setVisibility(View.VISIBLE);


                    //academic_info.setVisibility(View.VISIBLE);
                }
                if (c_aca.getString(1).length()!=0) {
                    institute[aca]=c_aca.getString(1);
                    //sb_aca.append("Institute : " + c_aca.getString(1) + "\n");
                    // tv2.setVisibility(View.VISIBLE);
                    aca_ins_tbl_head.setVisibility(View.VISIBLE);


                }
                if (c_aca.getString(2).length()!=0) {
                    percentage[aca]=c_aca.getString(2);
                    //sb_aca.append("Percentage / CGPA : " + c_aca.getString(2) + "\n");
                    // tv3.setVisibility(View.VISIBLE);
                    aca_per_tbl_head.setVisibility(View.VISIBLE);


                }
                if (c_aca.getString(3).length()!=0) {
                    year[aca]=c_aca.getString(3);
                    //tv4.setVisibility(View.VISIBLE);
                    aca_yea_tbl_head.setVisibility(View.VISIBLE);


                }
                aca++;
                //sb_aca.append("---------------------------------------------------\n");
            }while (c_aca.moveToNext());

            //academic_info.setText(sb_aca.toString());

            //################# new
            PrintActivity.CustomAdapter_academic cadapter = new PrintActivity.CustomAdapter_academic();
            lv.setAdapter(cadapter);


            //Set the height of list view
            ListAdapter listAdapter = lv.getAdapter();
            if(listAdapter!=null){
                int numberOfItems = aca;
                int totalItemsHeight = 0;
                for(int itemPos=0; itemPos<numberOfItems;itemPos++){
                    View item = listAdapter.getView(itemPos, null, lv);
                    item.measure(0,0);
                    totalItemsHeight+=item.getMeasuredHeight();
                }
                int totalDividerHeight = lv.getDividerHeight()*(numberOfItems-1);
                ViewGroup.LayoutParams params = lv.getLayoutParams();
                params.height = totalItemsHeight+totalDividerHeight;
                lv.setLayoutParams(params);
                lv.requestLayout();
            }


        }
        else{
            //academic_info.setVisibility(View.GONE);
            academic_info_head.setVisibility(View.GONE);
        }
    }


    public class CustomAdapter_academic extends BaseAdapter {
        @Override
        public int getCount() {
            return degree.length;
        }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.academic_lv_elements,null);
            tv = view.findViewById(R.id.degree);
            tv.setText(degree[i]);

            tv = view.findViewById(R.id.institute);
            tv.setText(institute[i]);

            tv = view.findViewById(R.id.percentage);
            tv.setText(percentage[i]);

            tv = view.findViewById(R.id.passing_year);
            tv.setText(year[i]);
            return view;
        }
    }

    public void work_view(){
        //TextView result=findViewById(R.id.work_exp);
        TextView head=findViewById(R.id.work_exp_head);
        ListView lv = findViewById(R.id.lv_work);



        TextView work_org_tbl_head = findViewById(R.id.organisation);
        TextView work_pos_tbl_head = findViewById(R.id.position);
        TextView work_dur_tbl_head = findViewById(R.id.duration);
        TextView work_act_tbl_head = findViewById(R.id.activities);
        TextView work_exp_tbl_head = findViewById(R.id.exposure);
        TextView work_res_tbl_head = findViewById(R.id.responsibility);


        work_org_tbl_head.setVisibility(View.INVISIBLE);
        work_pos_tbl_head.setVisibility(View.INVISIBLE);
        work_dur_tbl_head.setVisibility(View.INVISIBLE);
        work_act_tbl_head.setVisibility(View.INVISIBLE);
        work_exp_tbl_head.setVisibility(View.INVISIBLE);
        work_res_tbl_head.setVisibility(View.INVISIBLE);




        WorkDbHelper user_exp= new WorkDbHelper(PrintActivity.this);
        SQLiteDatabase db= user_exp.getReadableDatabase();

        Cursor c_exp = user_exp.viewData(db);

        if(c_exp.getCount()>0){
            //StringBuffer sb= new StringBuffer();
            c_exp.moveToFirst();

            do{
                if (c_exp.getString(0).length()!=0){
                    organisation[work]=c_exp.getString(0);
                    //sb.append("Organisation : " + c_exp.getString(0) + "\n");
                    head.setVisibility(View.VISIBLE);
                    //result.setVisibility(View.VISIBLE);
                    work_org_tbl_head.setVisibility(View.VISIBLE);

                }
                if (c_exp.getString(1).length()!=0) {
                    position[work] = c_exp.getString(1);
                    //sb.append("Position : " + c_exp.getString(1) + "\n");
                    work_pos_tbl_head.setVisibility(View.VISIBLE);

                }
                if (c_exp.getString(2).length()!=0) {
                    duration[work] = c_exp.getString(2);
                    //sb.append("Duration : " + c_exp.getString(2) + "\n");
                    work_dur_tbl_head.setVisibility(View.VISIBLE);

                }
                if (c_exp.getString(3).length()!=0) {
                    activities[work] = c_exp.getString(3);
                    //sb.append("Activities : " + c_exp.getString(3) + "\n");
                    work_act_tbl_head.setVisibility(View.VISIBLE);

                }
                if (c_exp.getString(4).length()!=0) {
                    exposure[work] = c_exp.getString(4);
                    //sb.append("Industrial Exposure : " + c_exp.getString(4) + "\n");
                    work_exp_tbl_head.setVisibility(View.VISIBLE);

                }
                if (c_exp.getString(5).length()!=0) {
                    responsibility[work] = c_exp.getString(5);
                    //sb.append("Job Responsibility : " + c_exp.getString(5) + "\n");
                    work_res_tbl_head.setVisibility(View.VISIBLE);

                }
                //sb.append("---------------------------------------------------\n");

                work++;
            }while (c_exp.moveToNext());

            //result.setText(sb.toString());


            PrintActivity.CustomAdapter_work cadapter = new PrintActivity.CustomAdapter_work();
            lv.setAdapter(cadapter);




            //Set the height of list view
            ListAdapter listAdapter = lv.getAdapter();
            if(listAdapter!=null){
                int numberOfItems = work;
                int totalItemsHeight = 0;
                for(int itemPos=0; itemPos<numberOfItems;itemPos++){
                    View item = listAdapter.getView(itemPos, null, lv);
                    item.measure(0,0);
                    totalItemsHeight+=item.getMeasuredHeight();
                }
                int totalDividerHeight = lv.getDividerHeight()*(numberOfItems-1);
                ViewGroup.LayoutParams params = lv.getLayoutParams();
                params.height = totalItemsHeight+totalDividerHeight;
                lv.setLayoutParams(params);
                lv.requestLayout();
            }


        }
        else{
            ///result.setVisibility(View.GONE);
            head.setVisibility(View.GONE);
        }
    }


    public class CustomAdapter_work extends BaseAdapter {
        @Override
        public int getCount() {
            return degree.length;
        }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.work_lv_elements,null);

            tv = view.findViewById(R.id.organisation);
            tv.setText(organisation[i]);

            tv = view.findViewById(R.id.position);
            tv.setText(position[i]);

            tv = view.findViewById(R.id.duration);
            tv.setText(duration[i]);

            tv = view.findViewById(R.id.activities);
            tv.setText(activities[i]);

            tv = view.findViewById(R.id.exposure);
            tv.setText(exposure[i]);

            tv = view.findViewById(R.id.responsibility);
            tv.setText(responsibility[i]);

            return view;
        }
    }

    public void project_view(){
        //TextView body=findViewById(R.id.proj_info);
        TextView head=findViewById(R.id.proj_info_head);

        ListView lv = findViewById(R.id.lv_project);





        TextView work_tit_tbl_head = findViewById(R.id.project_title);
        TextView work_des_tbl_head = findViewById(R.id.project_description);
        TextView work_rol_tbl_head = findViewById(R.id.role);
        TextView work_dur_tbl_head = findViewById(R.id.project_duration);
        TextView work_siz_tbl_head = findViewById(R.id.team_size);


        work_tit_tbl_head.setVisibility(View.INVISIBLE);
        work_des_tbl_head.setVisibility(View.INVISIBLE);
        work_rol_tbl_head.setVisibility(View.INVISIBLE);
        work_dur_tbl_head.setVisibility(View.INVISIBLE);
        work_siz_tbl_head.setVisibility(View.INVISIBLE);



        ProjectDbHelper user= new ProjectDbHelper(PrintActivity.this);
        SQLiteDatabase db= user.getReadableDatabase();
        Cursor c = user.viewData(db);

        if(c.getCount()>0){
            //StringBuffer sb= new StringBuffer();
            c.moveToFirst();
            do{
                if (c.getString(0).length()!=0){
                    project_title[proj]=c.getString(0);
                    //sb.append("Project Title : " + c.getString(0) + "\n");
                    head.setVisibility(View.VISIBLE);
                    //body.setVisibility(View.VISIBLE);
                    work_tit_tbl_head.setVisibility(View.VISIBLE);

                }
                if (c.getString(1).length()!=0) {
                    project_description[proj]=c.getString(1);
                    //sb.append("Description : " + c.getString(1) + "\n");
                    work_des_tbl_head.setVisibility(View.VISIBLE);

                }
                if (c.getString(2).length()!=0) {
                    role[proj]=c.getString(2);
                    //sb.append("Role : " + c.getString(2) + "\n");
                    work_rol_tbl_head.setVisibility(View.VISIBLE);

                }
                if (c.getString(3).length()!=0) {
                    project_duration[proj]=c.getString(3);
                    //sb.append("Duration : " + c.getString(3) + "\n");
                    work_dur_tbl_head.setVisibility(View.VISIBLE);

                }
                if (c.getString(4).length()!=0) {
                    team_size[proj]=c.getString(4);
                    //sb.append("Team Size : " + c.getString(4) + "\n");
                    work_siz_tbl_head.setVisibility(View.VISIBLE);

                }
                //sb.append("---------------------------------------------------\n");
                proj++;
            }while (c.moveToNext());
            //body.setText(sb.toString());


            PrintActivity.CustomAdapter_project cadapter = new PrintActivity.CustomAdapter_project();
            lv.setAdapter(cadapter);

            //Set the height of list view
            ListAdapter listAdapter = lv.getAdapter();
            if(listAdapter!=null){
                int numberOfItems = proj;
                int totalItemsHeight = 0;
                for(int itemPos=0; itemPos<numberOfItems;itemPos++){
                    View item = listAdapter.getView(itemPos, null, lv);
                    item.measure(0,0);
                    totalItemsHeight+=item.getMeasuredHeight();
                }
                int totalDividerHeight = lv.getDividerHeight()*(numberOfItems-1);
                ViewGroup.LayoutParams params = lv.getLayoutParams();
                params.height = totalItemsHeight+totalDividerHeight;
                lv.setLayoutParams(params);
                lv.requestLayout();
            }



        }
        else{
            head.setVisibility(View.GONE);
            //body.setVisibility(View.GONE);
        }
    }

    public class CustomAdapter_project extends BaseAdapter {
        @Override
        public int getCount() {
            return proj;
        }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.project_lv_elements,null);

            tv = view.findViewById(R.id.project_title);
            tv.setText(project_title[i]);

            tv = view.findViewById(R.id.project_description);
            tv.setText(project_description[i]);

            tv = view.findViewById(R.id.role);
            tv.setText(role[i]);

            tv = view.findViewById(R.id.project_duration);
            tv.setText(project_duration[i]);

            tv = view.findViewById(R.id.team_size);
            tv.setText(team_size[i]);

            return view;
        }
    }

    public void reference_view(){
        //TextView body=findViewById(R.id.ref_info);
        TextView head=findViewById(R.id.ref_info_head);
        ListView lv = findViewById(R.id.lv_ref);


        TextView ref_per_tbl_head = findViewById(R.id.ref_person);
        TextView ref_desi_tbl_head = findViewById(R.id.ref_designation);
        TextView ref_org_tbl_head = findViewById(R.id.ref_organisation);
        TextView ref_ema_tbl_head = findViewById(R.id.ref_email);
        TextView ref_desc_tbl_head = findViewById(R.id.ref_description);


        ref_per_tbl_head.setVisibility(View.INVISIBLE);
        ref_desi_tbl_head.setVisibility(View.INVISIBLE);
        ref_org_tbl_head.setVisibility(View.INVISIBLE);
        ref_ema_tbl_head.setVisibility(View.INVISIBLE);
        ref_desc_tbl_head.setVisibility(View.INVISIBLE);



        ReferenceDbHelper user= new ReferenceDbHelper(PrintActivity.this);
        SQLiteDatabase db= user.getReadableDatabase();
        Cursor c = user.viewData(db);

        if(c.getCount()>0){
            //StringBuffer sb= new StringBuffer();
            c.moveToFirst();
            do{
                if (c.getString(0).length()!=0){
                    ref_person[ref]=c.getString(0);
                    //sb.append("Name of Person : " + c.getString(0) + "\n");
                    head.setVisibility(View.VISIBLE);
                    //body.setVisibility(View.VISIBLE);
                    ref_per_tbl_head.setVisibility(View.VISIBLE);

                }
                if (c.getString(1).length()!=0) {
                    ref_designation[ref]=c.getString(1);
                    //sb.append("Reference's Designation : " + c.getString(1) + "\n");
                    ref_desi_tbl_head.setVisibility(View.VISIBLE);

                }
                if (c.getString(2).length()!=0) {
                    ref_organisation[ref]=c.getString(2);
                    //sb.append("Reference's Organization : " + c.getString(2) + "\n");
                    ref_org_tbl_head.setVisibility(View.VISIBLE);

                }
                if (c.getString(3).length()!=0) {
                    ref_email[ref]=c.getString(3);
                    //sb.append("Email : " + c.getString(3) + "\n");
                    ref_ema_tbl_head.setVisibility(View.VISIBLE);

                }
                if (c.getString(4).length()!=0) {
                    ref_description[ref]=c.getString(4);
                    //sb.append("Description : " + c.getString(4) + "\n");
                    ref_desc_tbl_head.setVisibility(View.VISIBLE);

                }
                //sb.append("---------------------------------------------------\n");
                ref++;
            }while (c.moveToNext());
            //body.setText(sb.toString());

            PrintActivity.CustomAdapter_ref cadapter = new PrintActivity.CustomAdapter_ref();
            lv.setAdapter(cadapter);

            //Set the height of list view
            ListAdapter listAdapter = lv.getAdapter();
            if(listAdapter!=null){
                int numberOfItems = ref;
                int totalItemsHeight = 0;
                for(int itemPos=0; itemPos<numberOfItems;itemPos++){
                    View item = listAdapter.getView(itemPos, null, lv);
                    item.measure(0,0);
                    totalItemsHeight+=item.getMeasuredHeight();
                }
                int totalDividerHeight = lv.getDividerHeight()*(numberOfItems-1);
                ViewGroup.LayoutParams params = lv.getLayoutParams();
                params.height = totalItemsHeight+totalDividerHeight;
                lv.setLayoutParams(params);
                lv.requestLayout();
            }

        }
        else{
            head.setVisibility(View.GONE);
            //body.setVisibility(View.GONE);
        }
    }
    public class CustomAdapter_ref extends BaseAdapter {
        @Override
        public int getCount() {
            return ref;
        }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.reference_lv_elements,null);

            tv = view.findViewById(R.id.ref_person);
            tv.setText(ref_person[i]);

            tv = view.findViewById(R.id.ref_designation);
            tv.setText(ref_designation[i]);

            tv = view.findViewById(R.id.ref_organisation);
            tv.setText(ref_organisation[i]);

            tv = view.findViewById(R.id.ref_email);
            tv.setText(ref_email[i]);

            tv = view.findViewById(R.id.ref_description);
            tv.setText(ref_description[i]);

            return view;
        }
    }

    public void skills_view(){
        TextView body=findViewById(R.id.skill);
        TextView head=findViewById(R.id.skill_head);

        SkillDbHelper user= new SkillDbHelper(PrintActivity.this);
        SQLiteDatabase db= user.getReadableDatabase();
        Cursor c = user.viewData(db);

        if(c.getCount()>0){
            StringBuffer sb= new StringBuffer();
            c.moveToFirst();
            do{
                if (c.getString(0).length()!=0){
                    sb.append(bullet + c.getString(0) + "\n");
                    head.setVisibility(View.VISIBLE);
                    body.setVisibility(View.VISIBLE);
                }
            }while (c.moveToNext());
            body.setText(sb.toString());
        }
        else{
            head.setVisibility(View.GONE);
            body.setVisibility(View.GONE);
        }
    }

    public void interest_view(){
        TextView body=findViewById(R.id.interest);
        TextView head=findViewById(R.id.interest_head);

        InterestDbHelper user= new InterestDbHelper(PrintActivity.this);
        SQLiteDatabase db= user.getReadableDatabase();
        Cursor c = user.viewData(db);

        if(c.getCount()>0){
            StringBuffer sb= new StringBuffer();
            c.moveToFirst();
            do{
                if (c.getString(0).length()!=0){
                    sb.append(bullet + c.getString(0) + "\n");
                    head.setVisibility(View.VISIBLE);
                    body.setVisibility(View.VISIBLE);
                }
            }while (c.moveToNext());
            body.setText(sb.toString());
        }
        else{
            head.setVisibility(View.GONE);
            body.setVisibility(View.GONE);
        }
    }

    public void hobbies_view(){
        TextView body=findViewById(R.id.hobbies);
        TextView head=findViewById(R.id.hobbies_head);

        HobbiesDbHelper user= new HobbiesDbHelper(PrintActivity.this);
        SQLiteDatabase db= user.getReadableDatabase();
        Cursor c = user.viewData(db);

        if(c.getCount()>0){
            StringBuffer sb= new StringBuffer();
            c.moveToFirst();
            do{
                if (c.getString(0).length()!=0){
                    sb.append(bullet + c.getString(0) + "\n");
                    head.setVisibility(View.VISIBLE);
                    body.setVisibility(View.VISIBLE);
                }
            }while (c.moveToNext());
            body.setText(sb.toString());
        }
        else{
            head.setVisibility(View.GONE);
            body.setVisibility(View.GONE);
        }
    }

    public void strength_view(){
        TextView body=findViewById(R.id.strength);
        TextView head=findViewById(R.id.strength_head);

        StrengthDbHelper user= new StrengthDbHelper(PrintActivity.this);
        SQLiteDatabase db= user.getReadableDatabase();
        Cursor c = user.viewData(db);

        if(c.getCount()>0){
            StringBuffer sb= new StringBuffer();
            c.moveToFirst();
            do{
                if (c.getString(0).length()!=0){
                    sb.append(bullet + c.getString(0) + "\n");
                    head.setVisibility(View.VISIBLE);
                    body.setVisibility(View.VISIBLE);
                }
            }while (c.moveToNext());
            body.setText(sb.toString());
        }
        else{
            head.setVisibility(View.GONE);
            body.setVisibility(View.GONE);
        }
    }

    public void achievement_view(){
        TextView body=findViewById(R.id.achievement);
        TextView head=findViewById(R.id.achievement_head);

        AchievementDbHelper user= new AchievementDbHelper(PrintActivity.this);
        SQLiteDatabase db= user.getReadableDatabase();
        Cursor c = user.viewData(db);

        if(c.getCount()>0){
            StringBuffer sb= new StringBuffer();
            c.moveToFirst();
            do{
                if (c.getString(0).length()!=0){
                    sb.append(bullet + c.getString(0) + "\n");
                    head.setVisibility(View.VISIBLE);
                    body.setVisibility(View.VISIBLE);
                }
            }while (c.moveToNext());
            body.setText(sb.toString());
        }
        else{
            head.setVisibility(View.GONE);
            body.setVisibility(View.GONE);
        }
    }

    public void objective_view(){
        TextView body=findViewById(R.id.objective);
        TextView head=findViewById(R.id.objective_head);

        ContactDbHelper user= new ContactDbHelper(PrintActivity.this);
        SQLiteDatabase db= user.getReadableDatabase();
        Cursor c = user.viewData(db);

        if(c.getCount()==1){
            c.moveToFirst();

            if(c.getString(10)!=null)
                if (c.getString(10).length()!=0){
                    body.setText(c.getString(10));
                    head.setVisibility(View.VISIBLE);
                    body.setVisibility(View.VISIBLE);
                }
                else{
                    head.setVisibility(View.GONE);
                    body.setVisibility(View.GONE);
                }
        }
    }

    public void declaration_view(){
        TextView body=findViewById(R.id.declaration);
        TextView head=findViewById(R.id.declaration_head);

        ContactDbHelper user= new ContactDbHelper(PrintActivity.this);
        SQLiteDatabase db= user.getReadableDatabase();
        Cursor c = user.viewData(db);

        if(c.getCount()==1){
            c.moveToFirst();

            if(c.getString(11)!=null)
                if (c.getString(11).length()>=1){
                    Log.d("sc",c.getString(11) );
                    body.setText(c.getString(11));
                    head.setVisibility(View.VISIBLE);
                    body.setVisibility(View.VISIBLE);
                }
                else{
                    head.setVisibility(View.GONE);
                    body.setVisibility(View.GONE);
                }
        }
    }

    public void signature(View view){
        Toast.makeText(this,"Fill this after print",Toast.LENGTH_SHORT).show();
    }
    public void contact(View view){
        Intent i = new Intent(PrintActivity.this,ContactActivity.class);
        startActivity(i);
    }
    public void academic(View view){
        Intent i = new Intent(PrintActivity.this,AcademicActivity.class);
        startActivity(i);
    }
    public void experience(View view){
        Intent i = new Intent(PrintActivity.this,WorkActivity.class);
        startActivity(i);
    }
    public void project(View view){
        Intent i = new Intent(PrintActivity.this,ProjectActivity.class);
        startActivity(i);
    }
    public void reference(View view){
        Intent i = new Intent(PrintActivity.this,ReferenceActivity.class);
        startActivity(i);
    }
    public void skill(View view){
        Intent i = new Intent(PrintActivity.this,SkillActivity.class);
        startActivity(i);
    }
    public void interest(View view){
        Intent i = new Intent(PrintActivity.this,InterestActivity.class);
        startActivity(i);
    }
    public void hobbies(View view){
        Intent i = new Intent(PrintActivity.this,HobbiesActivity.class);
        startActivity(i);
    }
    public void strength(View view){
        Intent i = new Intent(PrintActivity.this,StrengthActivity.class);
        startActivity(i);
    }
    public void achievement(View view){
        Intent i = new Intent(PrintActivity.this,AchievementActivity.class);
        startActivity(i);
    }
    public void objective(View view){
        Intent i = new Intent(PrintActivity.this,ObjectiveActivity.class);
        startActivity(i);
    }
    public void declaration(View view){
        Intent i = new Intent(PrintActivity.this,DeclarationActivity.class);
        startActivity(i);
    }
    public void updateMore(View view){
        Intent i = new Intent(PrintActivity.this,UpdateActivity.class);
        startActivity(i);
    }




    @Override
    public void onBackPressed() {
        Intent j = new Intent(PrintActivity.this, MainActivity.class);
        startActivity(j);
        finish();
    }




}
