package com.lys.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.lys.board.utils.LysBoardUtils;

@SuppressLint("AppCompatCustomView")
public class TestPenView extends TextView
{
	public TestPenView(Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("source = " + event.getSource() + "\n");
		sb.append("deviceId = " + event.getDeviceId() + "\n");
		sb.append("getToolType[0] = " + event.getToolType(0) + "\n");
		sb.append("isPen = " + LysBoardUtils.isPen(event) + "\n");
		setText(sb.toString());
		return super.onTouchEvent(event);
	}

}
