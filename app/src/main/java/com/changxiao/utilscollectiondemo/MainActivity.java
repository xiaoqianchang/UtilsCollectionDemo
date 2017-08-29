package com.changxiao.utilscollectiondemo;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.changxiao.utilscollectiondemo.utils.BitmapUtil;
import com.changxiao.utilscollectiondemo.utils.DeviceInfo;
import com.changxiao.utilscollectiondemo.utils.ImageTools;

public class MainActivity extends AppCompatActivity {

    ImageView imgBgOne;
    ImageView imgBgTwo;
    ImageView imgBgThree;
    Bitmap bgBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBgOne = (ImageView) findViewById(R.id.img_bg_one);
        imgBgTwo = (ImageView) findViewById(R.id.img_bg_two);
        imgBgThree = (ImageView) findViewById(R.id.img_bg_three);

        new AsyncTask<Voice, Voice, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Voice... params) {
                Bitmap image = null;
                try {
                    image = BitmapUtil.getImage("http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=3d2175db3cd3d539d530078052ee8325/b7003af33a87e950c1e1a6491a385343fbf2b425.jpg");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return image;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (null == bitmap) {
                    Toast.makeText(MainActivity.this, "bitmap wei null!", Toast.LENGTH_SHORT).show();
                    return;
                }
                imgBgOne.setImageBitmap(bitmap);
                bgBitmap = BitmapUtil.getBitmap(bitmap, DeviceInfo.getDeviceWidth(), DeviceInfo.dp2px(150));
                imgBgTwo.setImageBitmap(bgBitmap);

                // 该方法会压缩图片显示
                Bitmap bitmapBySize = ImageTools.createBitmapBySize(bitmap, DeviceInfo.getDeviceWidth(), DeviceInfo.dp2px(150));
                imgBgThree.setImageBitmap(bitmapBySize);
            }
        }.execute();



    }
}
