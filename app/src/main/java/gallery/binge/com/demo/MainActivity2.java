package gallery.binge.com.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;

public class MainActivity2 extends AppCompatActivity {

    private PathView pathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pathView = (PathView) findViewById(R.id.pathView);
//        pathView.useNaturalColors();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                pathView.getPathAnimator()
//                        .delay(100)
                        .duration(1000)
//                        .listenerStart(new AnimationListenerStart())
//                        .listenerEnd(new AnimationListenerEnd())
                        .interpolator(new AccelerateDecelerateInterpolator())
                        .start();
                break;
            case R.id.btn2:
                pathView.getSequentialPathAnimator()
//                        .delay(100)
                        .duration(1000)
//                        .listenerStart(new AnimationListenerStart())
//                        .listenerEnd(new AnimationListenerEnd())
                        .interpolator(new AccelerateDecelerateInterpolator())
                        .start();
                break;
        }
    }
}
