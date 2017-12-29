package com.amxc.project.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.amxc.project.R;
import com.amxc.project.Utils.GlideUtils;
import com.amxc.project.movie.model.entity.MovieEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by zhudong.
 */

public class MovieAdapter extends BaseQuickAdapter<MovieEntity.V9LG4B3A0Bean, BaseViewHolder> {

    private Context mcontext;
    public MovieAdapter(Context context, List<MovieEntity.V9LG4B3A0Bean> dataSize) {
        super(R.layout.item_video, dataSize);
        mcontext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieEntity.V9LG4B3A0Bean item) {
        helper.setText(R.id.title, item.getTitle());
        helper.setText(R.id.source, item.getVideosource());
        ImageView imageView = helper.getView(R.id.cover);
        CircleImageView circleImageView = helper.getView(R.id.topicImg);
        GlideUtils.displayImage(mcontext,item.getCover(),imageView);
        GlideUtils.displayImage(mcontext,item.getTopicImg(),circleImageView);
    }
}
