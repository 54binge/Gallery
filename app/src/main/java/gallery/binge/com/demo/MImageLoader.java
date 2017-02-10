package gallery.binge.com.demo;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/2/10.
 */

public class MImageLoader {
    public static void loadImage(@NonNull String uri, @NonNull ImageView targetView) {
        Glide.with(targetView.getContext())
                .load(uri)
//                .crossFade()
                .centerCrop()
                .into(targetView);
    }
}
