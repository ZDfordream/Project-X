package com.amxc.project.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amxc.project.R;
import com.amxc.project.Utils.GlideUtils;
import com.amxc.project.Utils.SettingUtil;
import com.amxc.project.home.model.entity.NewsEntity;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<NewsEntity, BaseViewHolder> {

    private int itemWidth;
    private Context mContext;

    public MultipleItemQuickAdapter(Context context, List data) {
        super(data);
        mContext = context;
        itemWidth = (SettingUtil.getScreenWidth(context) - SettingUtil.dip2px(context, 32)) / 3;
        addItemType(NewsEntity.SINGLE_IMAGE, R.layout.item_news);
        addItemType(NewsEntity.MULTI_IMAGES, R.layout.item_news_images);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsEntity item) {
        switch (helper.getItemViewType()) {
            case NewsEntity.SINGLE_IMAGE:
                ImageView imageView = helper.getView(R.id.ivNews);
                helper.setText(R.id.tvTitle, item.getTitle());
                helper.setText(R.id.tvDesc, item.getDigest());
                Glide.with(mContext).load(item.getImgsrc()).into(imageView);
                break;
            case NewsEntity.MULTI_IMAGES:
                helper.setText(R.id.tvTitle, item.getTitle());
                LinearLayout ll_images = helper.getView(R.id.ll_images);
                ll_images.removeAllViews();
                for (int i = 0; i < item.getImgextra().size(); i++) {
                    ImageView imageview = new ImageView(mContext);
                    LinearLayout.LayoutParams paras = new LinearLayout.LayoutParams(itemWidth, itemWidth);
                    if (i == 1) {
                        paras.setMargins(SettingUtil.dip2px(mContext, 4), 0, SettingUtil.dip2px(mContext, 4), 0);
                    }
                    imageview.setLayoutParams(paras);
                    Glide.with(mContext).load(item.getImgextra().get(i).getImgsrc()).into(imageview);
                    ll_images.addView(imageview);
                }
                break;
        }
    }

}
