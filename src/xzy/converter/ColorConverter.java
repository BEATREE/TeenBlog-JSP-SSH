package xzy.converter;

import java.util.Map;


import ognl.DefaultTypeConverter;
import xzy.beans.Color;

public class ColorConverter extends DefaultTypeConverter {

	@SuppressWarnings("unchecked")
	@Override
	public Object convertValue(Map context, Object value, Class toType) {
		if (toType == Color.class) {	//
			// 系统的请求参数是一个字符串数组
			String[] params = (String[])value;
			//获取字符串数组中的第一个字符串，进行相关的分离
			String[] rgb = params[0].split(",");
			//定义最终返回的字符串
			Color color = new Color();
			String fcolor = "#";

			for (int i = 0; i < rgb.length; i++) {
				//获取转化后的参数，并转化为int类型
				int a = Integer.parseInt(rgb[i]);
				if (a<=255 && a>=0) {	//判断是否合法
					//将10进制通过调用Integer.toHexString函数转化为16进制
					fcolor += Integer.toHexString(a);
				}else{
					fcolor="输入数据类型有误！";
					break;
				}
			}
			color.setRgb(fcolor);
			context.put("color", color);
			return color;
		}
		else if (toType == String.class )
		{
			// 将需要转换的值强制类型转换为Color实例
			Color color = (Color) value;
			return  color.getRgb() ;
		}
		return super.convertValue(context, value, toType);
	}

	

}
