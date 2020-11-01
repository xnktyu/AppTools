package com.lys.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import com.lys.app.R;
import com.lys.base.utils.CommonUtils;
import com.lys.base.utils.FsUtils;
import com.lys.base.utils.LOG;
import com.lys.base.utils.SysUtils;
import com.lys.kit.activity.KitActivity;
import com.lys.utils.LysUpload;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;

public class ActivityInfos extends KitActivity
{
	private TextView info;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_infos);

		TextView info = findViewById(R.id.info);

		StringBuilder sb = new StringBuilder();

		sb.append(String.format("屏幕大小：(%d, %d)\n", SysUtils.screenWidth(context), SysUtils.screenHeight(context)));
		sb.append("screenWidth(dp)：" + SysUtils.px2dp(SysUtils.screenWidth(context)) + "\n");
		sb.append("screenWidth(sp)：" + SysUtils.px2sp(SysUtils.screenWidth(context)) + "\n");

		sb.append("\n");

		long total = Environment.getExternalStorageDirectory().getTotalSpace();
		long free = Environment.getExternalStorageDirectory().getFreeSpace();
		sb.append(String.format("存储空间：总共：%s，剩余：%s\n", CommonUtils.formatSize(total), CommonUtils.formatSize(free)));

		ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
		am.getMemoryInfo(memoryInfo);
		sb.append(String.format("内存：总共：%s，剩余：%s\n", CommonUtils.formatSize(memoryInfo.totalMem, 1000), CommonUtils.formatSize(memoryInfo.availMem, 1000)));

		sb.append("\n");

		sb.append("厂商：" + Build.BRAND + "\n");
		sb.append("型号：" + Build.MODEL + "\n");
		sb.append("版本号：" + Build.DISPLAY + "\n");
		sb.append("Android 版本：" + Build.VERSION.RELEASE + "\n");
		sb.append("SDK 版本：" + Build.VERSION.SDK_INT + "\n");

		sb.append("Serial(设备号)：" + Build.SERIAL + "\n");
		sb.append("IMEI：" + SysUtils.getIMEI(context) + "\n");
		sb.append("ANDROID_ID：" + SysUtils.getAndroidId(context) + "\n");

		sb.append("\n");

		collectBuildInfo(sb);

		LOG.v(sb.toString());

		info.setText(sb.toString());

		File file = new File(FsUtils.SD_CARD, "device.info");
		FsUtils.writeText(file, sb.toString());
		String path = String.format("/device_info/%s_%s_%s_%s_%s_%s_%s_%s_%s_%s_%s.info", //
				Build.BRAND, //
				Build.MODEL, //
				Build.VERSION.RELEASE, //
				Build.VERSION.SDK_INT, //
				Build.SERIAL,//
				SysUtils.getIMEI(context),//
				SysUtils.getAndroidId(context),//
				SysUtils.screenWidth(context), //
				SysUtils.screenHeight(context), //
				CommonUtils.formatSize(total), //
				CommonUtils.formatSize(memoryInfo.totalMem, 1000));
		LysUpload.doUpload(context, file, path, null);
	}

	private void collectBuildInfo(StringBuilder sb)
	{
		Field[] fields = Build.class.getFields();
		if (fields != null && fields.length > 0)
		{
			for (Field field : fields)
			{
				field.setAccessible(true);
				try
				{
					if (field.getType().equals(String[].class))
					{
						String[] value= (String[]) field.get(null);
						sb.append(field.getType()).append("  ").append(field.getName()).append(" = ").append(Arrays.toString(value)).append("\n");
					}
					else
					{
						sb.append(field.getType()).append("  ").append(field.getName()).append(" = ").append(field.get(null).toString()).append("\n");
					}
				}
				catch (IllegalAccessException e)
				{
					sb.append(field.getType()).append("  ").append(field.getName()).append(" = ").append("???").append("\n");
				}
			}
		}
	}

}
