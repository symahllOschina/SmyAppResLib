package com.sym.libs.util;



import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sym.libs.R;

/**
 * Time: 2019/11/12
 * Author:Administrator
 * Description:Toast工具类，支持自定义toast，带图片toast,自定义toast显示位置等
 */
public class ToastUtil extends Toast{

    /**
     * 定义Toast
     */
    private static ToastUtil TOAST;

    /**
     * Toast上的图片
     */
    private static ImageView TOAST_IMG;

    /**
     * 构造
     *
     * @param context
     */
    public ToastUtil(Context context) {
        super(context);
    }


    /**
     * 隐藏当前Toast
     */
    public static void cancelToast() {
        if (TOAST != null) {
            TOAST.cancel();
        }
    }

    @Override
    public void cancel() {
        try {
            super.cancel();
        } catch (Exception e) {

        }
    }

    @Override
    public void show() {
        try {
            super.show();
        } catch (Exception e) {

        }
    }

    /**
     * 初始化Toast
     *
     * @param context 上下文
     * @param text    显示的文本
     *        gravity 1:底部，2：居中
     */
    private static void initToast(Context context, CharSequence text,int gravity) {
        try {
            cancelToast();

            TOAST = new ToastUtil(context);

            // 获取LayoutInflater对象
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // 由layout文件创建一个View对象
            View layout = inflater.inflate(R.layout.img_toast_layout, null);

            // 吐司上的图片
            TOAST_IMG = (ImageView) layout.findViewById(R.id.msg_img);

            // 吐司上的文字
            TextView toast_text = (TextView) layout.findViewById(R.id.msg_text);
            toast_text.setText(text);
            TOAST.setView(layout);
            if(gravity == 1){
                TOAST.setGravity(Gravity.BOTTOM, 0, 70);
            }else if(gravity == 2){
                TOAST.setGravity(Gravity.CENTER, 0, 70);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 图标状态 不显示图标
     */
    private static final int TYPE_HIDE = -1;
    /**
     * 图标状态 显示√
     */
    private static final int TYPE_TRUE = 0;
    /**
     * 图标状态 显示×
     */
    private static final int TYPE_FALSE = 1;

    /**
     * 显示Toast
     *
     * @param context 上下文
     * @param text    显示的文本
     * @param time    显示时长
     * @param imgType 图标状态
     *        gravity 显示位置
     *
     */
    private static void showToast(Context context, CharSequence text, int time, int imgType,int gravity) {
        // 初始化一个新的Toast对象
        initToast(context, text,gravity);

//        // 设置显示时长
//        if (time == Toast.LENGTH_LONG) {
//            toast.setDuration(Toast.LENGTH_LONG);
//        } else {
//            toast.setDuration(Toast.LENGTH_SHORT);
//        }

        // 判断图标是否该显示，显示√还是×
        if (imgType == TYPE_HIDE) {
            TOAST_IMG.setVisibility(View.GONE);
        } else {
            if (imgType == TYPE_TRUE) {
                TOAST_IMG.setBackgroundResource(R.drawable.toast_img_icon);
            } else {
                TOAST_IMG.setBackgroundResource(R.drawable.toast_img_icon);
            }
            TOAST_IMG.setVisibility(View.VISIBLE);

            // 动画
            ObjectAnimator.ofFloat(TOAST_IMG, "rotationY", 0, 360).setDuration(1700).start();
        }

        // 显示Toast
        TOAST.show();
    }


    /**
     * 显示一个纯文本吐司
     *
     * @param context 上下文
     * @param text    显示的文本
     * @param gravity 显示位置
     */
    public static void showText(Context context, CharSequence text,int gravity) {
        showToast(context, text, Toast.LENGTH_SHORT, TYPE_HIDE,gravity);
    }

    /**
     * 显示一个带图标的吐司
     *
     * @param context   上下文
     * @param text      显示的文本
     * @param isSucceed 显示【对号图标】还是【叉号图标】
     */
    public static void showText(Context context, CharSequence text, boolean isSucceed, int gravity) {
        showToast(context, text, Toast.LENGTH_SHORT, isSucceed ? TYPE_TRUE : TYPE_FALSE,gravity);
    }

    /**
     * 显示一个纯文本吐司
     *
     * @param context 上下文
     * @param text    显示的文本
     * @param time    持续的时间
     */
    public static void showText(Context context, CharSequence text, int time,int gravity) {
        showToast(context, text, time, TYPE_HIDE,gravity);
    }

    /**
     * 显示一个带图标的吐司
     *
     * @param context   上下文
     * @param text      显示的文本
     * @param time      持续的时间
     * @param isSucceed 显示【对号图标】还是【叉号图标】
     */
    public static void showText(Context context, CharSequence text, int time, boolean isSucceed,int gravity) {
        showToast(context, text, time, isSucceed ? TYPE_TRUE : TYPE_FALSE,gravity);
    }
}
