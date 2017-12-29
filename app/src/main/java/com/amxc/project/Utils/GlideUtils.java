package com.amxc.project.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.amxc.project.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by zhudong on 2017/3/23.
 */

public class GlideUtils {
    public static void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        try {
            Glide.with(context)
                    .load(path)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .error(R.mipmap.ic_empty_picture)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
