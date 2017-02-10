package gallery.binge.com.demo;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2017/2/10.
 */

public class ImagePagerAdapter extends RecyclerPagerAdapter<ImagePagerAdapter.ViewHolder> {
    private List<ImageBean> mDataList = new ArrayList<>();

    public ImagePagerAdapter(List<ImageBean> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup container) {
        return new ViewHolder(container);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MImageLoader.loadImage(mDataList.get(position).url, (PhotoView) holder.itemView);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    class ViewHolder extends RecyclerPagerAdapter.ViewHolder {

        public ViewHolder(@NonNull ViewGroup parent) {
            super(View.inflate(parent.getContext(), R.layout.item_preview, null));
        }
    }
}
