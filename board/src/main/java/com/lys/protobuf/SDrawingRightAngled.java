package com.lys.protobuf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lys.base.utils.AppDataTool;
import com.lys.base.utils.JsonHelper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.google.protobuf.ByteString;

import com.lys.base.utils.SPTData;
import com.lys.protobuf.ProtocolBoard.DrawingRightAngled;

// 直角三角形
public class SDrawingRightAngled extends SPTData<DrawingRightAngled>
{
	private static final SDrawingRightAngled DefaultInstance = new SDrawingRightAngled();

	public Integer paintColor = 0;
	public Float strokeWidth = 0F;
	public SPoint posStart = null;
	public SPoint posStop = null;

	public static SDrawingRightAngled create(Integer paintColor, Float strokeWidth, SPoint posStart, SPoint posStop)
	{
		SDrawingRightAngled obj = new SDrawingRightAngled();
		obj.paintColor = paintColor;
		obj.strokeWidth = strokeWidth;
		obj.posStart = posStart;
		obj.posStop = posStop;
		return obj;
	}

	public SDrawingRightAngled clone()
	{
		return load(saveToBytes());
	}

	public void copyFrom(SDrawingRightAngled _other_)
	{
		this.paintColor = _other_.paintColor;
		this.strokeWidth = _other_.strokeWidth;
		this.posStart = _other_.posStart;
		this.posStop = _other_.posStop;
	}

	@Override
	public void parse(JSONObject _json_)
	{
		if (_json_.containsKey("paintColor"))
			paintColor = _json_.getInteger("paintColor");
		if (_json_.containsKey("strokeWidth"))
			strokeWidth = _json_.getFloat("strokeWidth");
		if (_json_.containsKey("posStart"))
			posStart = SPoint.load(_json_.getJSONObject("posStart"));
		if (_json_.containsKey("posStop"))
			posStop = SPoint.load(_json_.getJSONObject("posStop"));
	}

	public static SDrawingRightAngled load(String str)
	{
		try
		{
			SDrawingRightAngled obj = new SDrawingRightAngled();
			obj.parse(JsonHelper.getJSONObject(str));
			return obj;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static SDrawingRightAngled load(JSONObject json)
	{
		try
		{
			SDrawingRightAngled obj = new SDrawingRightAngled();
			obj.parse(json);
			return obj;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONObject saveToJson()
	{
		try
		{
			JSONObject _json_ = new JSONObject(true);
			if (paintColor != null)
				_json_.put("paintColor", paintColor);
			if (strokeWidth != null)
				_json_.put("strokeWidth", strokeWidth);
			if (posStart != null)
				_json_.put("posStart", posStart.saveToJson());
			if (posStop != null)
				_json_.put("posStop", posStop.saveToJson());
			return _json_;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static List<SDrawingRightAngled> loadList(JSONArray ja)
	{
		try
		{
			List<SDrawingRightAngled> list = new ArrayList<SDrawingRightAngled>();
			for (int i = 0; i < ja.size(); i++)
			{
				JSONObject jo = ja.getJSONObject(i);
				SDrawingRightAngled item = SDrawingRightAngled.load(jo);
				if (item == null)
					return null;
				list.add(item);
			}
			return list;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static JSONArray saveList(List<SDrawingRightAngled> list)
	{
		JSONArray ja = new JSONArray();
		for (int i = 0; i < list.size(); i++)
		{
			SDrawingRightAngled item = list.get(i);
			JSONObject jo = item.saveToJson();
			ja.add(jo);
		}
		return ja;
	}

	@Override
	public void parse(DrawingRightAngled _proto_)
	{
		if (_proto_.hasPaintColor())
			paintColor = _proto_.getPaintColor();
		if (_proto_.hasStrokeWidth())
			strokeWidth = _proto_.getStrokeWidth();
		if (_proto_.hasPosStart())
			posStart = SPoint.load(_proto_.getPosStart());
		if (_proto_.hasPosStop())
			posStop = SPoint.load(_proto_.getPosStop());
	}

	public static SDrawingRightAngled load(byte[] bytes)
	{
		try
		{
			SDrawingRightAngled obj = new SDrawingRightAngled();
			obj.parse(DrawingRightAngled.parseFrom(bytes));
			return obj;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static SDrawingRightAngled load(DrawingRightAngled proto)
	{
		try
		{
			SDrawingRightAngled obj = new SDrawingRightAngled();
			obj.parse(proto);
			return obj;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DrawingRightAngled saveToProto()
	{
		DrawingRightAngled.Builder _builder_ = DrawingRightAngled.newBuilder();
		if (paintColor != null && !paintColor.equals(DrawingRightAngled.getDefaultInstance().getPaintColor()))
			_builder_.setPaintColor(paintColor);
		if (strokeWidth != null && !strokeWidth.equals(DrawingRightAngled.getDefaultInstance().getStrokeWidth()))
			_builder_.setStrokeWidth(strokeWidth);
		if (posStart != null)
			_builder_.setPosStart(posStart.saveToProto());
		if (posStop != null)
			_builder_.setPosStop(posStop.saveToProto());
		return _builder_.build();
	}
}
