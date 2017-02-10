package gallery.binge.com.demo;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/2/10.
 */

public abstract class RecyclerPagerAdapter<VH extends RecyclerPagerAdapter.ViewHolder> extends PagerAdapter {
    private final SparseArray<VH> mSparseArray = new SparseArray<>();

    public abstract VH onCreateViewHolder(@NonNull ViewGroup container);

    public abstract void onBindViewHolder(@NonNull VH holder, int position);

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        VH holder;
        if (mSparseArray.indexOfKey(position) < 0) {
            holder = onCreateViewHolder(container);
            mSparseArray.put(position, holder);
        } else {
            holder = mSparseArray.get(position);
        }

        container.addView(container, null);
        onBindViewHolder(holder, position);
        return holder;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        VH holder = (VH) object;
        mSparseArray.remove(position);
        container.removeView(holder.itemView);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((VH) object).itemView;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    class ViewHolder {
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            this.itemView = itemView;
        }
    }

    public VH getViewHolder(int position) {
        return mSparseArray.get(position);
    }
}
