    package com.example.dateaa;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.ColorRes;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

    public class MainActivity extends Activity {
    private static final int REQUEST_ACT = 2;
        SeekBar bar;
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private Uri mImageCaptureUri;
    private ImageButton mPhotoImageView,mPhotoImageView2;
    private DBhelp db;
    private Cursor all_cursor;
    private ArrayList<String> list = new ArrayList();
    long mNow;
    Date mDate;
    int numm=0;
    TextView Tday;
    int num;
    SimpleDateFormat myear = new SimpleDateFormat("yyyy");
    SimpleDateFormat mmonth = new SimpleDateFormat("MM");
    SimpleDateFormat mdayOfMonth = new SimpleDateFormat("dd");
    int year;
    int month;
    TextView tdday,lovenum,lovenum2;
    int dayOfMonth;
    TextView  tete1,tete2, tete3,tete4, tete5, tete6, tete7, tete8, tete9, tete10;
    Button btn_first,love32,dDay;
    TextView textView;
    ViewPager vp;
    ImageButton loveButton;
    int Tyear,Tmonth,Tdayof;
        String value;
        TextView tttt[]= new TextView[11];

    ListView listview = null ;
    Button btn171,btn271,btn371;
    private ListView lvNavList;


    private ViewPager flContainer;


    private DrawerLayout dlDrawer;
String love;
   private Button btn,btn2,imagebtn1;
    DBhelp dBhelp;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        String dbName = "Memo.db";
        int dbVersion = 1;
        setContentView(R.layout.activity_main);
        tdday = findViewById(R.id.protext);
        tdday.setText("d");
        mPhotoImageView2 = findViewById(R.id.btn4);
        mPhotoImageView = findViewById(R.id.btn5);
        Display display = getWindowManager().getDefaultDisplay();
        loveButton=findViewById(R.id.loveButton);
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        bar = (SeekBar) findViewById(R.id.progresbar);
        dDay = findViewById(R.id.dDay);
        mPhotoImageView.setBackground(new ShapeDrawable(new OvalShape()));
        if(Build.VERSION.SDK_INT >= 21) {
            mPhotoImageView.setClipToOutline(true);

        }
        mPhotoImageView2.setBackground(new ShapeDrawable(new OvalShape()));
        if(Build.VERSION.SDK_INT >= 21) {
            mPhotoImageView2.setClipToOutline(true);
        }
//
             love32=findViewById(R.id.loveday);
            love = love32.getText().toString();

         tete1 = findViewById(R.id.tete1);

        tete2 = findViewById(R.id.tete2);

        tete3 = findViewById(R.id.tete3);

        tete4 = findViewById(R.id.tete4);
        tete5 = findViewById(R.id.tete5);
        tete6 = findViewById(R.id.tete6);

        tete7 = findViewById(R.id.tete7);

        tete8 = findViewById(R.id.tete8);
        tete9 = findViewById(R.id.tete9);
        tete10 = findViewById(R.id.tete10);

            lovenum=findViewById(R.id.lovenum);
            lovenum2 = findViewById(R.id.lovenum2);


//
//        for(int i =1;i<11;i++){
//            TextView tete[] = new TextView[11];
//            int ear= 50*i;
//            tete[i].setText(""+ear);
//        }



        bar.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });


    }




    /**
     * 앨범에서 이미지 가져오기
     */
    private void doTakeAlbumAction()
    {
        // 앨범 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }


    public void ProgressBar(){



        getD_day();

        Log.d("테스트트트프로그레스",""+num);
        bar.setProgress(num);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode != RESULT_OK)
        {
            return;
        }

        switch(requestCode)
        {
            case CROP_FROM_CAMERA:
            {
                // 크롭이 된 이후의 이미지를 넘겨 받습니다.
                // 이미지뷰에 이미지를 보여준다거나 부가적인 작업 이후에
                // 임시 파일을 삭제합니다.
                final Bundle extras = data.getExtras();

                if(numm==1) {


                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        mPhotoImageView.setImageBitmap(photo);
                    }
                }if(numm==2) {


                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    mPhotoImageView2.setImageBitmap(photo);
                }
            }if(numm==3) {


                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    loveButton.setImageBitmap(photo);
                }
            }
                // 임시 파일 삭제
                File f = new File(mImageCaptureUri.getPath());
                if(f.exists())
                {
                    f.delete();
                }

                break;
            }

            case PICK_FROM_ALBUM:
            {
                // 이후의 처리가 카메라와 같으므로 일단  break없이 진행합니다.
                // 실제 코드에서는 좀더 합리적인 방법을 선택하시기 바랍니다.

                mImageCaptureUri = data.getData();
            }

            case PICK_FROM_CAMERA:
            {
                // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
                // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.

                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");

                intent.putExtra("fi", 2000);
                intent.putExtra("outputY", 2000);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_CAMERA);

                break;
            }
        }
    }

    @Override
    protected void onStart() {
        Toast.makeText(MainActivity.this,"온 스타트 실행",Toast.LENGTH_LONG).show();
        getD_day();
        dDay.setText(""+num);
        proTime();
        ProgressBar();
        super.onStart();
    }
protected void onStop() {
    Toast.makeText(MainActivity.this,"온 스트ㅏㅂ 실행",Toast.LENGTH_LONG).show();
    super.onStop();
}

    @Override
    protected void onResume() {
        Toast.makeText(MainActivity.this,"온 리머시기 실행",Toast.LENGTH_LONG).show();
    ;

        super.onResume();
    }

    @Override
    protected void onPause() {
        Toast.makeText(MainActivity.this,"온 퍼지 실행",Toast.LENGTH_LONG).show();
        super.onPause();
    }


    public void proTime(){
        getD_day();
        Log.d("테스트프로그래스",""+year);
        Log.d("테스트프로그래스",""+month);
        Log.d("테스트프로그래스",""+dayOfMonth);

        int yearr = num/365;
        int monthr = (num%365)/30;
        int dayOfMonthr = (num%365)%30;

        Log.d("테스트프로그래스",""+yearr);
        Log.d("테스트프로그래스",""+monthr);
        Log.d("테스트프로그래스",""+dayOfMonthr);

        if(num>0){
            if (num > 360) {

                tdday.setText(yearr+"년째"+"  "+monthr+"달째"+"  "+dayOfMonthr+"일째");
            }
            else if(num>30){
                tdday.setText(monthr+"달째"+"  "+dayOfMonthr+"일째");
            }
            else {
                tdday.setText(dayOfMonthr+"일째");
            }

        }


        int vaer = 1000-num;
        lovenum.setText(""+vaer+"째");
        lovenum2.setText("1000일");

    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.loveButton:{
                numm=3;
                DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakeAlbumAction();
                    }
                };

                DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
                new AlertDialog.Builder(this)
                        .setTitle("업로드할 이미지 선택")
                        .setNeutralButton("앨범선택", albumListener)
                        .setNegativeButton("취소", cancelListener)
                        .show();
                break;
            }
            case R.id.loveday:{
                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);

                // 제목 설정

// EditText 삽입하기
                final EditText et = new EditText(MainActivity.this);
//                et.setText(love);
                ad.setView(et);
                et.setText(""+love32);
// 확인 버튼 설정
                ad.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        // Text 값 받아서 로그 남기기
                        value = et.getText().toString();

                    love32.setText(value);
                        dialog.dismiss();     //닫기
                        // Event
                    }
                });


// 취소 버튼 설정
                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();     //닫기
                        // Event
                    }
                });
                ad.show();
                break;
            }
            case R.id.btn17:{

                break;
            }


            case R.id.btn27:{
                Intent i = new Intent(MainActivity.this,DialogActivity.class);
                startActivity(i);


                break;
            }
            case R.id.btn37:{
                Intent i = new Intent(MainActivity.this,Ka.class);
                startActivity(i);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);

                break;
            }



            case R.id.dDay:{

                Intent i = new Intent(MainActivity.this,D_day2.class);
                startActivity(i);


                break;
            }


            case R.id.btn4 :{
                numm=2;
                DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakeAlbumAction();
                    }
                };

                DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
                new AlertDialog.Builder(this)
                        .setTitle("업로드할 이미지 선택")
                        .setNeutralButton("앨범선택", albumListener)
                        .setNegativeButton("취소", cancelListener)
                        .show();
                break;
            }
            case R.id.btn5 :{
                numm=1;
                DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        doTakeAlbumAction();
                    }
                };

                DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
                new AlertDialog.Builder(this)
                        .setTitle("업로드할 이미지 선택")
                        .setNeutralButton("앨범선택", albumListener)
                        .setNegativeButton("취소", cancelListener)
                        .show();




            }
        }







    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public int countdday(int year, int mmonth, int mday) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Calendar todaCal = Calendar.getInstance(); //오늘날자 가져오기
            Calendar ddayCal = Calendar.getInstance(); //오늘날자를 가져와 변경시킴

//            mmonth -= 1; // 받아온날자에서 -1을 해줘야함.

            ddayCal.set(year, mmonth, mday);// D-day의 날짜를 입력
            Log.e("테스트", simpleDateFormat.format(todaCal.getTime()) + "");
            Log.e("테스트", simpleDateFormat.format(ddayCal.getTime()) + "");

            long today = todaCal.getTimeInMillis() / 86400000; //->(24 * 60 * 60 * 1000) 24시간 60분 60초 * (ms초->초 변환 1000)
            long dday = ddayCal.getTimeInMillis() / 86400000;
            long count = dday - today; // 오늘 날짜에서 dday 날짜를 빼주게 됩니다.
            num = (int) count * -1;
            Log.d("테스트","씨이ㅇ이이이발"+num);

            db = new DBhelp(this);
            db.updateCoin(num);
            String sql = String.format("INSERT INTO Memo VALUES(NULL,%s);", num);

            return (int) count;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void Cursul() {

        db = new DBhelp(this);
        db.open();

        while (true) {
            try {
                String num = all_cursor.getString(all_cursor.getColumnIndex("COIN"));
                dDay.setText(num);
                Log.d("DBDDDD", "코인값받아옴"+list.get(0));
                Log.d("DDBDDDD",num);
                if (!all_cursor.moveToNext())
                    break;
            } catch (Exception e) {

            }

        }
    }
    public void getD_day() {



        Intent intent = getIntent();
        year = intent.getIntExtra("년도", year);
        month = intent.getIntExtra("월", month);
        dayOfMonth = intent.getIntExtra("일", dayOfMonth);
        Log.d("씨발",""+year);
        Toast.makeText(this, year + "/" + (month + 1) + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
        Log.e("테스트",
                countdday(year, month, dayOfMonth) + "");


    }
}