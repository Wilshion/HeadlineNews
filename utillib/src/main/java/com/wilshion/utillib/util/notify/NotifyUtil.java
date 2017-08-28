package com.wilshion.utillib.util.notify;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.wilshion.utillib.util.Utils;
import com.wilshion.utillib.util.notify.builder.BigPicBuilder;
import com.wilshion.utillib.util.notify.builder.BigTextBuilder;
import com.wilshion.utillib.util.notify.builder.MailboxBuilder;
import com.wilshion.utillib.util.notify.builder.MediaBuilder;
import com.wilshion.utillib.util.notify.builder.ProgressBuilder;
import com.wilshion.utillib.util.notify.builder.SingleLineBuilder;


/**
 * Created by Wilshion on 2017/8/6 17:10.
 * [description : 在git 上找到的这个类，感谢作者 hss01248]
 *
 * @link {https://github.com/hss01248/NotifyUtil}
 * [version : 1.0]
 */
public class NotifyUtil {
    private Context context;
    private static NotifyUtil mInstance;
    private static NotificationManager nm;

    private NotifyUtil() {
        context = Utils.getContext();
        nm = (NotificationManager) context
                .getSystemService(Activity.NOTIFICATION_SERVICE);
    }

    public static NotifyUtil getInstance() {
        if (mInstance == null) {
            synchronized (NotifyUtil.class) {
                if (mInstance == null) {
                    mInstance = new NotifyUtil();
                }
            }
        }
        return mInstance;
    }


    public static NotificationManager getNm() {
        return nm;
    }

    public Context getContext() {
        return context;
    }

    public static SingleLineBuilder buildSimple(int id, int smallIcon, CharSequence contentTitle, CharSequence contentText, PendingIntent contentIntent) {
        SingleLineBuilder builder = new SingleLineBuilder();
        builder.setBase(smallIcon, contentTitle, contentText)
                .setId(id)
                .setContentIntent(contentIntent);
        return builder;
    }

    @Deprecated
    public static ProgressBuilder buildProgress(int id, int smallIcon, CharSequence contentTitle, int progress, int max) {
        ProgressBuilder builder = new ProgressBuilder();
        builder.setBase(smallIcon, contentTitle, progress + "/" + max)
                .setId(id);
        builder.setProgress(max, progress, false);
        return builder;
    }

    public static ProgressBuilder buildProgress(int id, int smallIcon, CharSequence contentTitle, int progress, int max, String format) {
        ProgressBuilder builder = new ProgressBuilder();
        builder.setBase(smallIcon, contentTitle, progress + "/" + max)
                .setId(id);
        builder.setProgressAndFormat(progress, max, false, format);
        return builder;
    }

    public static BigPicBuilder buildBigPic(int id, int smallIcon, CharSequence contentTitle, CharSequence contentText, CharSequence summaryText) {
        BigPicBuilder builder = new BigPicBuilder();
        builder.setBase(smallIcon, contentTitle, contentText).setId(id);
        builder.setSummaryText(summaryText);
        return builder;
    }

    public static BigTextBuilder buildBigText(int id, int smallIcon, CharSequence contentTitle, CharSequence contentText) {
        BigTextBuilder builder = new BigTextBuilder();
        builder.setBase(smallIcon, contentTitle, contentText).setId(id);
        //builder.setSummaryText(summaryText);
        return builder;
    }

    public static MailboxBuilder buildMailBox(int id, int smallIcon, CharSequence contentTitle) {
        MailboxBuilder builder = new MailboxBuilder();
        builder.setBase(smallIcon, contentTitle, "").setId(id);
        return builder;
    }

    public static MediaBuilder buildMedia(int id, int smallIcon, CharSequence contentTitle, CharSequence contentText) {
        MediaBuilder builder = new MediaBuilder();
        builder.setBase(smallIcon, contentTitle, contentText).setId(id);
        return builder;
    }
    /*public static CustomViewBuilder buildCustomView(BigPicBuilder builder){

    }*/

    public static void notify(int id, Notification notification) {
        nm.notify(id, notification);
    }

    public static PendingIntent buildIntent(Class clazz) {
        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        Intent intent = new Intent(NotifyUtil.getInstance().getContext(), clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pi = PendingIntent.getActivity(NotifyUtil.getInstance().getContext(), 0, intent, flags);
        return pi;
    }

    public static void cancel(int id) {
        if (nm != null) {
            nm.cancel(id);
        }
    }

    public static void cancelAll() {
        if (nm != null) {
            nm.cancelAll();
        }
    }
}
