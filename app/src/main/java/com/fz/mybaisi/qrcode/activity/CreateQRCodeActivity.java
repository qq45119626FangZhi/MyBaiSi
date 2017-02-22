package com.fz.mybaisi.qrcode.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fz.mybaisi.R;
import com.fz.mybaisi.essence.fragment.EssenceFragment;
import com.fz.mybaisi.utils.DensityUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.fz.mybaisi.qrcode.encoding.EncodingUtils.createQRCode;


/**
 * 生成二维码
 */
public class CreateQRCodeActivity extends AppCompatActivity {

    @BindView(R.id.ibn_back)
    ImageButton ibnBack;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.activity_shared)
    RelativeLayout activityShared;
    /**
     * 图片地址
     */
    private String figure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);
        ButterKnife.bind(this);
        getData();
        //创建二维码
        create();
    }

    private void getData() {
        figure = getIntent().getStringExtra(EssenceFragment.FIGURE);
    }


    /**
     * 创建二维码并将图片保存在本地
     */
    private void create() {
        int width = DensityUtil.dp2px(this, 200);

        Bitmap bitmap = createQRCode(figure,
                width, width, BitmapFactory.decodeResource(getResources(),
                        R.drawable.head_portrait));

        //图片
        ivShare.setImageBitmap(bitmap);

        //把二维码图片保存到本地
        saveBitmap(bitmap);
    }

    /**
     * 将Bitmap保存在本地
     *
     * @param bitmap
     */
    public void saveBitmap(Bitmap bitmap) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(),
                "zxing_image");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "zxing_image" + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(this.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 通知图库更新
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.parse("file://" + "/sdcard/namecard/")));
    }

    @OnClick(R.id.ibn_back)
    public void onClick() {
        finish();
    }
}
