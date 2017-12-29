package com.amxc.project.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.amxc.project.R;


//      ┏┛ ┻━━━━━┛ ┻┓
//      ┃　　　　　　 ┃
//      ┃　　　━　　　┃
//      ┃　┳┛　  ┗┳　┃
//      ┃　　　　　　 ┃
//      ┃　　　┻　　　┃
//      ┃　　　　　　 ┃
//      ┗━┓　　　┏━━━┛
//        ┃　　　┃   神兽保佑
//        ┃　　　┃   代码无BUG！
//        ┃　　　┗━━━━━━━━━┓
//        ┃　　　　　　　    ┣┓
//        ┃　　　　         ┏┛
//        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
//          ┃ ┫ ┫   ┃ ┫ ┫
//          ┗━┻━┛   ┗━┻━┛

/**
 * 创建加载对话框,2017.6.16
 */
public class ZProgressHUD extends Dialog {
	private Handler handler = new Handler();
	private View view;
	private Context context;
	private TextView textview_message;
	private static ZProgressHUD dialog;

	public ZProgressHUD(Context context) {
		super(context, R.style.DialogTheme);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		this.setCanceledOnTouchOutside(false);
		this.context = context;
		view = getLayoutInflater().inflate(R.layout.dialog_progress, null);
		textview_message = (TextView) view.findViewById(R.id.textview_message);
		this.setContentView(view);
	}

	public static ZProgressHUD getInstance(Context context) {
		if (dialog == null) {
			dialog = new ZProgressHUD(context);
		}
		return dialog;
	}

	public void setText(String message){
		textview_message.setText(message);
	}

	@Override
	public void dismiss() {
		if (isShowing()){
			super.dismiss();
		}
	}

	@Override
	public void show() {
		if (!isShowing()){
			super.show();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					dismiss();
				}
			}, 15 * 1000);
		}
	}
}
