package com.lys.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lys.adapter.AdapterApp;
import com.lys.app.R;
import com.lys.kit.activity.KitActivity;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityApps extends KitActivity implements View.OnClickListener
{
	private RecyclerView recyclerView;
	private AdapterApp adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apps);

//		findViewById(R.id.apps).setOnClickListener(this);

		Map<String, ActivityInfo> map = new HashMap<>();

		Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
		mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(mainIntent, 0);
		if (resolveInfos != null)
		{
			for (ResolveInfo resolveInfo : resolveInfos)
			{
				map.put(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo);
			}
		}

		recyclerView = findViewById(R.id.recyclerView);
//		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
//		else
//			recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
		adapter = new AdapterApp(map);
		recyclerView.setAdapter(adapter);

		List<PackageInfo> installedPackages = getPackageManager().getInstalledPackages(0);
		Collections.sort(installedPackages, new Comparator<PackageInfo>()
		{
			@Override
			public int compare(PackageInfo packageInfo1, PackageInfo packageInfo2)
			{
				Boolean isSysApp1 = (packageInfo1.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
				Boolean isSysApp2 = (packageInfo2.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
				return isSysApp2.compareTo(isSysApp1);
			}
		});
		adapter.setData(installedPackages);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
//		if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
//		else
//			recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
	}

	@Override
	public void onClick(View view)
	{
		switch (view.getId())
		{
//		case R.id.apps:
//			break;
		}
	}

}
