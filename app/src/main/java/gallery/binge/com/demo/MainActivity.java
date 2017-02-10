package gallery.binge.com.demo;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MAdapter.OnImageClickListener {

    private static final String TAG = "MainActivity";

    private final Uri imgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

    private final String[] imgProjection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_MODIFIED};

    private ArrayList<ImageBean> mDataList = new ArrayList<>();

    @BindView(R.id.rcy)
    RecyclerView mRecyclerView;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private MAdapter mRecyclerViewAdapter;
    private ImagePagerAdapter mImagePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initRecyclerView();

        initViewPager();

    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, 16, true));
        mRecyclerViewAdapter = new MAdapter(mDataList, this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    private void initViewPager() {
        mImagePagerAdapter = new ImagePagerAdapter(mDataList);
        mViewPager.setAdapter(mImagePagerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mDataList.isEmpty()){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                checkPermission();
            } else {
                getLocalImages();
            }

            if (mRecyclerViewAdapter != null) {
                mRecyclerViewAdapter.notifyDataSetChanged();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission() {
        int i = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        if (i == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            getLocalImages();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocalImages();
            }
        }
    }

    private void getLocalImages() {
        mDataList.clear();
        ContentResolver cr = getContentResolver();
        Cursor mCursor = cr.query(imgUri, imgProjection, null, null, MediaStore.Images.Media.DATE_MODIFIED + " desc ");
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                String id = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media._ID));
                long modifyTime = mCursor.getLong(mCursor.getColumnIndex(MediaStore.Images.Media.DATE_MODIFIED));

                ImageBean imageBean = new ImageBean();
                imageBean.url = "file://" + path;
                mDataList.add(imageBean);
            }
            mCursor.close();
        }
    }

    @Override
    public void onImageClick(int position) {
        mViewPager.setCurrentItem(position);
        mViewPager.setVisibility(View.VISIBLE);
    }
}
