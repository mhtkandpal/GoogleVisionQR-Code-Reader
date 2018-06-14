package com.projectworlds.googlevisonqrreader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class MainActivity extends AppCompatActivity {

    TextView txtview;
    Button click;
    ImageView qrcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtview=(TextView)findViewById(R.id.textview);
        click=(Button)findViewById(R.id.btnScan);
        qrcode=(ImageView)findViewById(R.id.imageview);

        final Bitmap myBitMap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.qrcode);
        qrcode.setImageBitmap(myBitMap);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BarcodeDetector detector =new BarcodeDetector.Builder(getApplicationContext())
                        .setBarcodeFormats(Barcode.QR_CODE)
                        .build();
                Frame frame =new Frame.Builder()
                        .setBitmap(myBitMap).build();
                SparseArray<Barcode> barscode =detector.detect(frame);
                Barcode result =barscode.valueAt(0);
                txtview.setText(result.rawValue);
            }
        });

    }
}
