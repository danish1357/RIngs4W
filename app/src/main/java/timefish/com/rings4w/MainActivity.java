package timefish.com.rings4w;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


import gun0912.tedbottompicker.TedBottomPicker;
import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity {
    private FancyButton fancyButtonStart;
    private ImageView imageViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fancyButtonStart = (FancyButton) findViewById(R.id.btn_start);
        imageViewLogo = (ImageView) findViewById(R.id.logo);
        animateLogo();
//        animateButton();
        fancyButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                animateButton();
                startGallery();
            }
        });

    }

    private void animateLogo() {
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.fall);
        myAnim.setInterpolator(new AccelerateInterpolator());
        imageViewLogo.startAnimation(myAnim);

    }

    private void startGallery() {
        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(MainActivity.this)
                .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                    @Override
                    public void onImageSelected(Uri uri) {
                        startCropImage(uri);
                    }
                })
                .create();

        tedBottomPicker.show(getSupportFragmentManager());
    }


    private void startCropImage(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON).setCropShape(CropImageView.CropShape.OVAL).setFixAspectRatio(true).setAspectRatio(1, 1)
                .start(this);
    }

    void animateButton() {
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        double animationDuration = 2 * 1000;
        myAnim.setDuration((long) animationDuration);
        BounceInterpolator interpolator = new BounceInterpolator(0.2f, 20.0f);
        myAnim.setInterpolator(interpolator);
        fancyButtonStart.startAnimation(myAnim);
    }

}
