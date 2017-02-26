package me.wcy.music.executor;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;

import me.wcy.music.R;
import me.wcy.music.application.AppCache;
import me.wcy.music.callback.JsonCallback;
import me.wcy.music.constants.Constants;
import me.wcy.music.model.JDownloadInfo;
import me.wcy.music.model.JLrc;
import me.wcy.music.model.JSearchMusic;
import me.wcy.music.utils.FileUtils;
import me.wcy.music.utils.NetworkUtils;
import me.wcy.music.utils.Preferences;
import okhttp3.Call;

/**
 * 下载搜索的音乐
 * Created by hzwangchenyan on 2016/1/13.
 */
public abstract class DownloadSearchedMusic {
    private Context mContext;
    private JSearchMusic.JSong mJSong;

    public DownloadSearchedMusic(Context context, JSearchMusic.JSong jSong) {
        mContext = context;
        mJSong = jSong;
    }

    public void execute() {
        checkNetwork();
    }

    private void checkNetwork() {
        boolean mobileNetworkDownload = Preferences.enableMobileNetworkDownload();
        if (NetworkUtils.isActiveNetworkMobile(mContext) && !mobileNetworkDownload) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle(R.string.tips);
            builder.setMessage(R.string.download_tips);
            builder.setPositiveButton(R.string.download_tips_sure, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    download();
                }
            });
            builder.setNegativeButton(R.string.cancel, null);
            Dialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        } else {
            download();
        }
    }

    private void download() {
        onPrepare();

        // 获取歌曲下载链接
        OkHttpUtils.get().url(Constants.BASE_URL)
                .addParams(Constants.PARAM_METHOD, Constants.METHOD_DOWNLOAD_MUSIC)
                .addParams(Constants.PARAM_SONG_ID, mJSong.getSongid())
                .build()
                .execute(new JsonCallback<JDownloadInfo>(JDownloadInfo.class) {
                    @Override
                    public void onResponse(final JDownloadInfo response) {
                        if (response == null) {
                            onFail(null, null);
                            return;
                        }
                        long id = FileUtils.downloadMusic(response.getBitrate().getFile_link(), mJSong.getArtistname(), mJSong.getSongname());
                        AppCache.getDownloadList().put(id, mJSong.getSongname());
                        onSuccess();
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        onFail(call, e);
                    }
                });
        // 下载歌词
        String lrcFileName = FileUtils.getLrcFileName(mJSong.getArtistname(), mJSong.getSongname());
        File lrcFile = new File(FileUtils.getLrcDir() + lrcFileName);
        if (!lrcFile.exists()) {
            OkHttpUtils.get().url(Constants.BASE_URL)
                    .addParams(Constants.PARAM_METHOD, Constants.METHOD_LRC)
                    .addParams(Constants.PARAM_SONG_ID, mJSong.getSongid())
                    .build()
                    .execute(new JsonCallback<JLrc>(JLrc.class) {
                        @Override
                        public void onResponse(JLrc response) {
                            if (response == null || TextUtils.isEmpty(response.getLrcContent())) {
                                return;
                            }
                            String lrcPath = FileUtils.getLrcDir() + FileUtils.getLrcFileName(mJSong.getArtistname(), mJSong.getSongname());
                            FileUtils.saveLrcFile(lrcPath, response.getLrcContent());
                        }

                        @Override
                        public void onError(Call call, Exception e) {
                        }
                    });
        }
    }

    public abstract void onPrepare();

    public abstract void onSuccess();

    public abstract void onFail(Call call, Exception e);
}
