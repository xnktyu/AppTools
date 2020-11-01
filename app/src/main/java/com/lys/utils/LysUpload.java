package com.lys.utils;

import android.content.Context;
import android.text.TextUtils;

import com.lys.base.utils.HttpUtils;
import com.lys.base.utils.LOG;
import com.lys.kit.utils.Protocol;
import com.lys.protobuf.SProtocol;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class LysUpload
{
	public static void doUpload(final Context context, File file, String path, final Protocol.OnCallback callback)
	{
		MultipartBody.Builder contentBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//		contentBuilder.addFormDataPart("md5", CommonUtils.md5(file));
		contentBuilder.addFormDataPart("path", path);
		contentBuilder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
		RequestBody requestBody = contentBuilder.build();
		LOG.v("upload : " + file + " --> " + path);
		HttpUtils.doHttpPostImpl(context, "http://cloud.k12-eco.com/pair/upload", requestBody, new HttpUtils.OnCallback()
		{
			@Override
			public void onResponse(String jsonStr)
			{
				if (!TextUtils.isEmpty(jsonStr))
				{
					SProtocol trans = null;
					try
					{
						LOG.v("upload result : " + jsonStr);
						trans = SProtocol.load(jsonStr);
					}
					catch (Exception e)
					{
						e.printStackTrace();
//						LOG.toast(context, "解析异常");
					}
					if (trans != null)
					{
						if (trans.code == 200)
						{
							if (callback != null)
								callback.onResponse(trans.code, trans.data, trans.msg);
						}
						else
						{
//							LOG.toast(context, trans.msg);
							if (callback != null)
								callback.onResponse(trans.code, null, trans.msg);
						}
					}
					else
					{
//						LOG.toast(context, "解析失败");
					}
				}
				else
				{
					LOG.v("upload fail : 网络异常");
//					LOG.toast(context, "网络异常");
					if (callback != null)
						callback.onResponse(-100, null, "网络异常");
				}
			}
		});
	}
}
