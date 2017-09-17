package com.wilshion.headlinenews.ui.activity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.wilshion.headlinenews.R;
import com.wilshion.headlinenews.model.bean.NewsDetail;
import com.wilshion.headlinenews.util.VideoPathDecoder;
import com.wilshion.utillib.util.ResouceUtil;
import com.wilshion.utillib.util.StatusBarUtil;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Wilshion on 2017/9/14 15:57.
 * [description : 新闻详情页 -- 视频]
 * [version : 1.0]
 */
public class VideoDetailActivity extends BaseNewsDetailActivity {
    private JCVideoPlayerStandard mVideoPlayer;
    private int mProgress;
    private SensorManager mSensorManager;
    private JCVideoPlayer.JCAutoFullscreenListener mSensorEventListener;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_detail;
    }

    @Override
    protected void initViews() {
        super.initViews();
        StatusBarUtil.setPaddingSmart(this, findViewById(R.id.id_statusbar));
        StatusBarUtil.immersive(this, ResouceUtil.getColor(R.color.gray));
        
        mVideoPlayer = (JCVideoPlayerStandard) findViewById(R.id.id_video_player);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();

        mVideoPlayer.setAllControlsVisible(GONE, GONE, VISIBLE, GONE, VISIBLE, VISIBLE, GONE);
        mVideoPlayer.titleTextView.setVisibility(GONE);

        findViewById(R.id.id_back_iv).setOnClickListener(this);
        findViewById(R.id.id_comment_count_iv).setOnClickListener(this);
        findViewById(R.id.id_collect_iv).setOnClickListener(this);
        findViewById(R.id.id_share_iv).setOnClickListener(this);
    }

    @Override
    protected void initData(Intent intent) {
        super.initData(intent);
        mProgress = intent.getIntExtra(PROGRESS, 0);
    }

    @Override
    public void showNewsDetail(NewsDetail newsDetail) {
        newsDetail.content = "";
        mNewsDetailHeader.setNewsDetail(newsDetail);

        VideoPathDecoder decoder = new VideoPathDecoder() {
            @Override
            public void onSuccess(String url) {
                logE(url);
                mVideoPlayer.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_LIST, newsDetail.title);
                mVideoPlayer.seekToInAdvance = mProgress;//设置进度
                mVideoPlayer.startVideo();
            }

            @Override
            public void onDecodeError() {
            
            }
        };
        decoder.decodePath(newsDetail.url);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorEventListener);
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(mSensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        if (JCVideoPlayer.backPress()) {
            return;
        }
    }
}
