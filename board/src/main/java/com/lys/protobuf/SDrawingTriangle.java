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
import com.lys.protobuf.ProtocolBoard.DrawingTriangle;

// 三角形
public class SDrawingTriangle extends SPTData<DrawingTriangle>
{
	private static final SDrawingTriangle DefaultInstance = new SDrawingTriangle();

	public Integer paintColor = 0;
	public Float strokeWidth = 0F;
	public SPoint posStart = null;
	public SPoint posStop = null;

	public static SDrawingTriangle create(Integer paintColor, Float strokeWidth, SPoint posStart, SPoint posStop)
	{
		SDrawingTriangle obj = new SDrawingTriangle();
		obj.paintColor = paintColor;
		obj.strokeWidth = strokeWidth;
		obj.posStart = posStart;
		obj.posStop = posStop;
		return obj;
	}

	public SDrawingTriangle clone()
	{
		return load(saveToBytes());
	}

	public void copyFrom(SDrawingTriangle _other_)
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

	public static SDrawingTriangle load(String str)
	{
		try
		{
			SDrawingTriangle obj = new SDrawingTriangle();
			obj.parse(JsonHelper.getJSONObject(str));
			return obj;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static SDrawingTriangle load(JSONObject json)
	{
		try
		{
			SDrawingTriangle obj = new SDrawingTriangle();
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

	public static List<SDrawingTriangle> loadList(JSONArray ja)
	{
		try
		{
			List<SDrawingTriangle> list = new ArrayList<SDrawingTriangle>();
			for (int i = 0; i < ja.size(); i++)
			{
				JSONObject jo = ja.getJSONObject(i);
				SDrawingTriangle item = SDrawingTriangle.load(jo);
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

	public static JSONArray saveList(List<SDrawingTriangle> list)
	{
		JSONArray ja = new JSONArray();
		for (int i = 0; i < list.size(); i++)
		{
			SDrawingTriangle item = list.get(i);
			JSONObject jo = item.saveToJson();
			ja.add(jo);
		}
		return ja;
	}

	@Override
	public void parse(DrawingTriangle _proto_)
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

	public static SDrawingTriangle load(byte[] bytes)
	{
		try
		{
			SDrawingTriangle obj = new SDrawingTriangle();
			obj.parse(DrawingTriangle.parseFrom(bytes));
			return obj;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static SDrawingTriangle load(DrawingTriangle proto)
	{
		try
		{
			SDrawingTriangle obj = new SDrawingTriangle();
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
	public DrawingTriangle saveToProto()
	{
		DrawingTriangle.Builder _builder_ = DrawingTriangle.newBuilder();
		if (paintColor != null && !paintColor.equals(DrawingTriangle.getDefaultInstance().getPaintColor()))
			_builder_.setPaintColor(paintColor);
		if (strokeWidth != null && !strokeWidth.equals(DrawingTriangle.getDefaultInstance().getStrokeWidth()))
			_builder_.setStrokeWidth(strokeWidth);
		if (posStart != null)
			_builder_.setPosStart(posStart.saveToProto());
		if (posStop != null)
			_builder_.setPosStop(posStop.saveToProto());
		return _builder_.build();
	}
}
