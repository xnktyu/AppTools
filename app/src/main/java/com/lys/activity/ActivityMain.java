package com.lys.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lys.App;
import com.lys.app.R;
import com.lys.base.utils.FsUtils;
import com.lys.base.utils.HttpUtils;
import com.lys.base.utils.LOG;
import com.lys.base.utils.SysUtils;
import com.lys.kit.activity.ActivityBoard;
import com.lys.kit.activity.KitActivity;
import com.lys.kit.config.Config;
import com.lys.kit.utils.KitUtils;
import com.lys.utils.CommandExecution;

public class ActivityMain extends KitActivity implements View.OnClickListener
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		requestPermission();

		findViewById(R.id.infos).setOnClickListener(this);
		findViewById(R.id.apps).setOnClickListener(this);

		findViewById(R.id.host).setOnClickListener(this);

		findViewById(R.id.getNetIp).setOnClickListener(this);
		findViewById(R.id.getNetIp2).setOnClickListener(this);

		findViewById(R.id.testPen).setOnClickListener(this);
		findViewById(R.id.board).setOnClickListener(this);

		findViewById(R.id.hraShowNav).setOnClickListener(this);
		findViewById(R.id.hraHideNav).setOnClickListener(this);

		findViewById(R.id.checkRoot).setOnClickListener(this);
		findViewById(R.id.adbScreencap).setOnClickListener(this);

		TextView text = findViewById(R.id.text);
//		text.setText("fkjsdkfjskdjfds");
//		text.setText(Html.fromHtml("(3)百度：" + "<a href='http://www.baidu.com'>链接到百度</a> "));

		String str = "Java一《次编译》运不适《行并》到处用Android";
		SpannableString ss = new SpannableString(str);
		int start1 = str.indexOf("《");
		int start2 = str.indexOf("《", start1 + "《".length());
		int end1 = str.indexOf("》");
		int end2 = str.indexOf("》", end1 + "》".length());
		ss.setSpan(new ClickableSpan()
		{
			@Override
			public void onClick(View view)
			{
				LOG.v("1111111111");
			}
		}, start1, end1 + "》".length(), 0);
		ss.setSpan(new ForegroundColorSpan(Color.BLUE), start1, end1 + "》".length(), 0);
		ss.setSpan(new ClickableSpan()
		{
			@Override
			public void onClick(View view)
			{
				LOG.v("2222222");
			}
		}, start2, end2 + "》".length(), 0);
		text.setText(ss);

		text.setMovementMethod(LinkMovementMethod.getInstance());
//		text.setMovementMethod(new MyMovementMethod());
	}

	class MyMovementMethod extends LinkMovementMethod
	{
		@Override
		protected boolean handleMovementKey(TextView widget, Spannable buffer, int keyCode, int movementMetaState, KeyEvent event)
		{
			LOG.v("handleMovementKey : " + buffer);
			return super.handleMovementKey(widget, buffer, keyCode, movementMetaState, event);
		}

		@Override
		public void initialize(TextView widget, Spannable text)
		{
			LOG.v("initialize : " + text);
			super.initialize(widget, text);
		}

		@Override
		public boolean onKeyDown(TextView widget, Spannable text, int keyCode, KeyEvent event)
		{
			LOG.v("onKeyDown : " + text);
			return super.onKeyDown(widget, text, keyCode, event);
		}

		@Override
		public boolean onKeyUp(TextView widget, Spannable text, int keyCode, KeyEvent event)
		{
			LOG.v("onKeyUp : " + text);
			return super.onKeyUp(widget, text, keyCode, event);
		}

		@Override
		public boolean onKeyOther(TextView widget, Spannable text, KeyEvent event)
		{
			LOG.v("onKeyOther : " + text);
			return super.onKeyOther(widget, text, event);
		}

		@Override
		public void onTakeFocus(TextView view, Spannable text, int dir)
		{
			LOG.v("onTakeFocus : " + text);
			super.onTakeFocus(view, text, dir);
		}

		@Override
		public boolean onTrackballEvent(TextView widget, Spannable text, MotionEvent event)
		{
			LOG.v("onTrackballEvent : " + text);
			return super.onTrackballEvent(widget, text, event);
		}

		@Override
		public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event)
		{
			LOG.v("onTouchEvent : " + buffer + ", " + event);
			return super.onTouchEvent(widget, buffer, event);
		}

		@Override
		public boolean onGenericMotionEvent(TextView widget, Spannable text, MotionEvent event)
		{
			LOG.v("onGenericMotionEvent : " + text);
			return super.onGenericMotionEvent(widget, text, event);
		}

		@Override
		public boolean canSelectArbitrarily()
		{
			LOG.v("canSelectArbitrarily : ");
			return super.canSelectArbitrarily();
		}

	}

	@Override
	public void permissionSuccess()
	{
		super.permissionSuccess();
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}

	@Override
	public void onClick(View view)
	{
		if (view.getId() == R.id.infos)
		{
			startActivity(new Intent(this, ActivityInfos.class));
		}
		else if (view.getId() == R.id.apps)
		{
			startActivity(new Intent(this, ActivityApps.class));
		}
		else if (view.getId() == R.id.host)
		{
			App.requestHost(context, 0, new App.OnHostCallback()
			{
				@Override
				public void onResult(String host)
				{
					if (!TextUtils.isEmpty(host))
					{
						LOG.toast(context, "host：" + host);
					}
					else
					{
						LOG.toast(context, "请求失败");
					}
				}
			});
		}
		else if (view.getId() == R.id.getNetIp)
		{
			SysUtils.getNetIp(context, new HttpUtils.OnCallback()
			{
				@Override
				public void onResponse(String jsonStr)
				{
					Button btn = findViewById(R.id.getNetIp);
					btn.setText("getNetIp：" + jsonStr);
				}
			});
		}
		else if (view.getId() == R.id.getNetIp2)
		{
			SysUtils.getNetIp2(context, new HttpUtils.OnCallback()
			{
				@Override
				public void onResponse(String jsonStr)
				{
					Button btn = findViewById(R.id.getNetIp2);
					btn.setText("getNetIp2：" + jsonStr);
				}
			});
		}
		else if (view.getId() == R.id.testPen)
		{
			startActivity(new Intent(this, ActivityTestPen.class));
		}
		else if (view.getId() == R.id.board)
		{
			FsUtils.delete(Config.tmpRecordBoardDir);
			Intent intent = new Intent(context, ActivityBoard.class);
			intent.putExtra("debug", true);
			startActivity(intent);
		}
		else if (view.getId() == R.id.hraShowNav)
		{
			KitUtils.showNavBar(context);
		}
		else if (view.getId() == R.id.hraHideNav)
		{
			KitUtils.hideNavBar(context);
		}
		else if (view.getId() == R.id.checkRoot)
		{
			CommandExecution.execCommand("ifconfig wlan0", true);
		}
		else if (view.getId() == R.id.adbScreencap)
		{
			CommandExecution.execCommand("input tap 287 155", true);
		}
	}
}
