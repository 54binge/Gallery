package gallery.binge.com.demo;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/16.
 */
public class MAdapter extends RecyclerView.Adapter<MAdapter.MViewHolder> implements View.OnClickListener {

    private static final String TAG = "MAdapter";

    private ArrayList<ImageBean> dataList;
    private OnImageClickListener onImageClickListener;

    public MAdapter(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    public void setDataList(ArrayList<ImageBean> dataList) {
        this.dataList = dataList;
    }

    @Override
    public void onBindViewHolder(final MViewHolder holder, int position) {
        final ImageBean imageBean = dataList.get(position);
        holder.mCardView.setTag(imageBean);
        MImageLoader.loadImage(imageBean.url, (ImageView) holder.mCardView.findViewById(R.id.iv));
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MViewHolder mViewHolder = new MViewHolder(parent);
        mViewHolder.mCardView.setOnClickListener(this);
        return mViewHolder;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onClick(View v) {
        ImageBean imageBean = (ImageBean) v.getTag();
        if (onImageClickListener != null) {
            onImageClickListener.onImageClick(dataList.indexOf(imageBean));
        }
    }

    class MViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;

        public MViewHolder(ViewGroup parent) {
            super(View.inflate(parent.getContext(), R.layout.item, null));
            mCardView = (CardView) itemView;
        }
    }

    public interface OnImageClickListener {
        void onImageClick(int position);
    }
}
