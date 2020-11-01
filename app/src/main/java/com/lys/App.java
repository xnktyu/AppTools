package com.lys;

import android.content.Context;
import android.text.TextUtils;

import com.lys.app.BuildConfig;
import com.lys.base.utils.HttpUtils;
import com.lys.base.utils.LOG;
import com.lys.kit.AppKit;
import com.lys.kit.manager.CrashHandler;

public class App extends AppKit
{

	private static String hostFileAddress(Context context, int index)
	{
		if (index == 0)
			return String.format("http://cloud.k12-eco.com/files/host/%s--%s.txt", context.getPackageName(), BuildConfig.BUILD_TYPE);
		else if (index == 1)
			return String.format("http://47.96.82.69/files/host/%s--%s.txt", context.getPackageName(), BuildConfig.BUILD_TYPE);
		else if (index == 2)
			return String.format("http://39.104.58.109/files/host/%s--%s.txt", context.getPackageName(), BuildConfig.BUILD_TYPE);
		else
			return null;
	}

	public interface OnHostCallback
	{
		void onResult(String host);
	}

	public static void requestHost(final Context context, final int index, final OnHostCallback callback)
	{
		String url = App.hostFileAddress(context, index);
		if (!TextUtils.isEmpty(url))
		{
			LOG.v("requestHost : " + url);
			HttpUtils.doHttpGet(context, url, new HttpUtils.OnCallback()
			{
				@Override
				public void onResponse(String host)
				{
					if (!TextUtils.isEmpty(host))
					{
						if (callback != null)
							callback.onResult(host);
					}
					else
					{
						requestHost(context, index + 1, callback);
					}
				}
			});
		}
		else
		{
			if (callback != null)
				callback.onResult(null);
		}
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		new CrashHandler().init(this);
	}
}
