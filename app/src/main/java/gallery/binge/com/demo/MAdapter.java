package gallery.binge.com.demo;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/16.
 */
public class MAdapter extends RecyclerView.Adapter<MAdapter.MViewHolder> {

    private static final String TAG = "MAdapter";

    private Context context;
    private ArrayList<ImageBean> dataList;

    public MAdapter(Context context, ArrayList<ImageBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, int position) {
        ImageBean imageBean = dataList.get(position);
        Uri uri = Uri.parse(imageBean.url);
        Glide.with(context)
                .load(uri)
                .crossFade()
//                .centerCrop()
                .into(holder.iv);
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return new MViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;

        public MViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
