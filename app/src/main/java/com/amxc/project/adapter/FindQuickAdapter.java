package com.amxc.project.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.amxc.project.R;
import com.amxc.project.Utils.GlideUtils;
import com.amxc.project.find.model.entity.WXItemBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class FindQuickAdapter extends BaseQuickAdapter<WXItemBean, BaseViewHolder> {

    private Context mcontext;
    public FindQuickAdapter(Context context, List<WXItemBean> dataSize) {
        super(R.layout.item_wechat, dataSize);
        mcontext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, WXItemBean item) {
        GlideUtils.displayImage(mcontext,item.getPicUrl(),(ImageView) helper.getView(R.id.iv_android_pic));
        helper.setText(R.id.tv_android_des,item.getTitle());
        helper.setText(R.id.tv_android_who,item.getDescription());
        helper.setText(R.id.tv_android_time,item.getCtime());
    }
}
