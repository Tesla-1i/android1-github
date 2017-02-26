package me.wcy.music.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.wcy.music.R;
import me.wcy.music.application.AppCache;
import me.wcy.music.constants.Constants;
import me.wcy.music.model.Music;

/**
 * 文件工具类
 * Created by wcy on 2016/1/3.
 */
public class FileUtils {
    private static String getAppDir() {
        return Environment.getExternalStorageDirectory() + "/PonyMusic/";
    }

    public static String getMusicDir() {
        String dir = getAppDir() + "Music/";
        return mkdirs(dir);
    }

    public static String getLrcDir() {
        String dir = getAppDir() + "Lyric/";
        return mkdirs(dir);
    }

    public static String getLogDir() {
        String dir = getAppDir() + "Log/";
        return mkdirs(dir);
    }

    public static String getSplashDir(Context context) {
        String dir = context.getFilesDir() + "/splash/";
        return mkdirs(dir);
    }

    public static String getRelativeMusicDir() {
        String dir = "PonyMusic/Music/";
        return mkdirs(dir);
    }

    /**
     * 获取歌词路径
     * 先从已下载文件夹中查找，如果不存在，则从歌曲文件所在文件夹查找
     */
    public static String getLrcFilePath(Music music) {
        String lrcFilePath = getLrcDir() + music.getFileName().replace(Constants.FILENAME_MP3, Constants.FILENAME_LRC);
        if (!new File(lrcFilePath).exists()) {
            lrcFilePath = music.getUri().replace(Constants.FILENAME_MP3, Constants.FILENAME_LRC);
        }
        return lrcFilePath;
    }

    private static String mkdirs(String dir) {
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }

    public static String getMp3FileName(String artist, String title) {
        artist = stringFilter(artist);
        title = stringFilter(title);
        if (TextUtils.isEmpty(artist)) {
            artist = AppCache.getContext().getString(R.string.unknown);
        }
        if (TextUtils.isEmpty(title)) {
            title = AppCache.getContext().getString(R.string.unknown);
        }
        return artist + " - " + title + Constants.FILENAME_MP3;
    }

    public static String getLrcFileName(String artist, String title) {
        artist = stringFilter(artist);
        title = stringFilter(title);
        if (TextUtils.isEmpty(artist)) {
            artist = AppCache.getContext().getString(R.string.unknown);
        }
        if (TextUtils.isEmpty(title)) {
            title = AppCache.getContext().getString(R.string.unknown);
        }
        return artist + " - " + title + Constants.FILENAME_LRC;
    }

    public static String getArtistAndAlbum(String artist, String album) {
        if (TextUtils.isEmpty(artist) && TextUtils.isEmpty(album)) {
            return "";
        } else if (!TextUtils.isEmpty(artist) && TextUtils.isEmpty(album)) {
            return artist;
        } else if (TextUtils.isEmpty(artist) && !TextUtils.isEmpty(album)) {
            return album;
        } else {
            return artist + " - " + album;
        }
    }

    /**
     * 过滤特殊字符(\/:*?"<>|)
     */
    private static String stringFilter(String str) {
        if (str == null) {
            return null;
        }
        String regEx = "[\\/:*?\"<>|]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static float b2mb(int b) {
        String mb = String.format(Locale.getDefault(), "%.2f", b / 1024f / 1024);
        return Float.valueOf(mb);
    }

    public static void saveLrcFile(String path, String content) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write(content);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long downloadMusic(String url, String artist, String song) {
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        String mp3FileName = FileUtils.getMp3FileName(artist, song);
        request.setDestinationInExternalPublicDir(FileUtils.getRelativeMusicDir(), mp3FileName);
        request.setMimeType(MimeTypeMap.getFileExtensionFromUrl(url));
        request.allowScanningByMediaScanner();
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setAllowedOverRoaming(false);// 不允许漫游
        DownloadManager downloadManager = (DownloadManager) AppCache.getContext().getSystemService(Context.DOWNLOAD_SERVICE);
        return downloadManager.enqueue(request);
    }
}
