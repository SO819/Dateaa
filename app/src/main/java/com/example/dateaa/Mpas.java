package com.example.dateaa;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Geocoder;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Mpas extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {
    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private Uri mImageCaptureUri;
    Bitmap b;
    GoogleMap googleMap;
    GoogleMap mMap;
    Button btn171,btn271,btn371,imbtn124124;
    ImageView imageView;
    LinearLayout linearLayout,linearLayout2;
    ImageButton imageButton1;
    String TimeStmp = new SimpleDateFormat("yyyMMdd_Hhmmss").format(new Date());
    String FileNmae = "LoveDay" + TimeStmp;
    EditText editText;
    String st;
    private Geocoder geocoder;
    Button imbtn1,btn3;
    EditText ededed;
    TextView textView;
    BitmapDrawable d;
    int num = 0;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mpas);
        imageButton1 = findViewById(R.id.imbtn2);

        textView=findViewById(R.id.text);
        editText = findViewById(R.id.ED);
        linearLayout = findViewById(R.id.line);
        imbtn1=findViewById(R.id.imbtn1);
        btn3 =findViewById(R.id.btn3);
        imbtn124124=findViewById(R.id.imbtn124124);

        ededed=findViewById(R.id.ededed);


       d = (BitmapDrawable)((ImageView) findViewById(R.id.imbtn2)).getDrawable();

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    public void onMapReady(GoogleMap map) {
        mMap = map;
        this.googleMap = googleMap;
        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();




        MarkerOptions mOptions2 = new MarkerOptions();
        MarkerOptions mOptions3 = new MarkerOptions();
        MarkerOptions mOptions4 = new MarkerOptions();
        MarkerOptions mOptions5 = new MarkerOptions();
        MarkerOptions mOptions6 = new MarkerOptions();
        MarkerOptions mOptions7 = new MarkerOptions();
        MarkerOptions mOptions8 = new MarkerOptions();
        MarkerOptions mOptions9 = new MarkerOptions();
        MarkerOptions mOptions10 = new MarkerOptions();
        MarkerOptions mOptions11 = new MarkerOptions();
        MarkerOptions mOptions12 = new MarkerOptions();


        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        mMap.addMarker(markerOptions);


        mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        mMap.setOnMarkerClickListener(this);



        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        geocoder = new Geocoder(this);




    }

    @Override

    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(Mpas.this,"Ddd",Toast.LENGTH_LONG).show();
        String id = marker.getId();
        textView.setText(st);



        linearLayout.setVisibility(View.VISIBLE);
        geocoder = new Geocoder(this);

        return true;
    }
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.imbtn1:{
                linearLayout.setVisibility(View.VISIBLE);

                break;
            }
            case R.id.btn3:{

                st = editText.getText().toString();

                break;
            }

            case R.id.btn17:{
                Intent i = new Intent(Mpas.this,MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);

                break;
            }

            case R.id.btn27:{
                Load();
                break;
            }
            case R.id.btn37:{
                Intent i = new Intent(Mpas.this,Ka.class);
                startActivity(i);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);

                break;
            }


            case R.id.imbtn2:{
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



                Bitmap b = d.getBitmap();
                imageView.setImageBitmap(b);
                linearLayout.setVisibility(View.GONE);
                break;
            }
        }
    }






    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    private void doTakeAlbumAction()
    {
        // 앨범 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
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

                if(extras != null)
                {

                    Bitmap photo = extras.getParcelable("data");
                    imageButton1.setImageBitmap(photo);
//                    imageView.setImageBitmap(photo);
                Save(photo);


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


public void Save(Bitmap b){
    try{

        File file = new File("TimeStmp.png");
        FileOutputStream fos = openFileOutput("test.png" , 0);
        b.compress(Bitmap.CompressFormat.PNG, 100 , fos);
        fos.flush();
        fos.close();

        Toast.makeText(this, "file ok", Toast.LENGTH_SHORT).show();
    }catch(Exception e) { Toast.makeText(this, "file error", Toast.LENGTH_SHORT).show();}

}
public void Load(){
    try{

        String imgpath = "data/data/com.test.SDCard_Ani/files/TimeStmp.png";
        Bitmap bm = BitmapFactory.decodeFile(imgpath);

        imageView.setImageBitmap(bm);
        Toast.makeText(getApplicationContext(), "load ok", Toast.LENGTH_SHORT).show();
    }catch(Exception e){Toast.makeText(getApplicationContext(), "load error", Toast.LENGTH_SHORT).show();}

}



    public void onBackPressed() {

    if(linearLayout2.getVisibility()==View.VISIBLE){

        linearLayout2.setVisibility(View.GONE);
    }
    else{
        Intent i = new Intent(Mpas.this,MainActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
    }


}
