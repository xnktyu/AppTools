package com.lys.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lys.app.R;
import com.lys.base.utils.FsUtils;
import com.lys.base.utils.LOG;

import java.io.File;
import java.util.List;
import java.util.Map;

public class AdapterApp extends RecyclerView.Adapter<AdapterApp.Holder>
{
	private Map<String, ActivityInfo> map = null;
	private List<PackageInfo> installedPackages = null;

	public AdapterApp(Map<String, ActivityInfo> map)
	{
		this.map = map;
	}

	public void setData(List<PackageInfo> installedPackages)
	{
		this.installedPackages = installedPackages;
		notifyDataSetChanged();
	}

	@Override
	public Holder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.app_item, parent, false));
	}

	@Override
	public void onBindViewHolder(final Holder holder, final int position)
	{
		final PackageInfo packageInfo = installedPackages.get(position);
		final Context context = holder.itemView.getContext();

		boolean isSysApp = (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
		String appName = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();

		holder.icon.setImageDrawable(packageInfo.applicationInfo.loadIcon(context.getPackageManager()));
		holder.name.setText(String.format("%3d  %s%s--%s", position, isSysApp ? "[系统]" : "", packageInfo.packageName, appName));
		holder.version.setText(String.format("%s[%d]", packageInfo.versionName, packageInfo.versionCode));

		if (map.containsKey(packageInfo.packageName))
		{
			holder.run.setVisibility(View.VISIBLE);
			holder.run.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View view)
				{
					ActivityInfo activityInfo = map.get(packageInfo.packageName);
					String pkg = activityInfo.packageName;
					String cls = activityInfo.name; // 应用的主activity类
					Intent intent = new Intent();
					intent.setComponent(new ComponentName(pkg, cls));
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent);
				}
			});
		}
		else
		{
			holder.run.setVisibility(View.GONE);
		}

		holder.export.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				new File(FsUtils.SD_CARD + "/apk").mkdirs();
				if (packageInfo.applicationInfo.publicSourceDir.equals(packageInfo.applicationInfo.sourceDir))
				{
					String appName = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
					File file = new File(packageInfo.applicationInfo.publicSourceDir);
					byte[] bytes = FsUtils.readBytes(file);
					FsUtils.writeBytes(new File(String.format("%s/apk/%s-%s.apk", FsUtils.SD_CARD, packageInfo.packageName, appName)), bytes);
					LOG.toast(context, "导出成功");
				}
				else
				{
					LOG.toast(context, "导出错误");
				}
			}
		});

	}

	@Override
	public int getItemCount()
	{
		if (installedPackages != null)
			return installedPackages.size();
		else
			return 0;
	}

	protected class Holder extends RecyclerView.ViewHolder
	{
		public ImageView icon;
		public TextView name;
		public TextView version;
		public Button run;
		public Button export;

		public Holder(View itemView)
		{
			super(itemView);
			icon = itemView.findViewById(R.id.icon);
			name = itemView.findViewById(R.id.name);
			version = itemView.findViewById(R.id.version);
			run = itemView.findViewById(R.id.run);
			export = itemView.findViewById(R.id.export);
		}
	}
}