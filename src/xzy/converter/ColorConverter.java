package xzy.converter;

import java.util.Map;


import ognl.DefaultTypeConverter;
import xzy.beans.Color;

public class ColorConverter extends DefaultTypeConverter {

	@SuppressWarnings("unchecked")
	@Override
	public Object convertValue(Map context, Object value, Class toType) {
		if (toType == Color.class) {	//
			// ϵͳ�����������һ���ַ�������
			String[] params = (String[])value;
			//��ȡ�ַ��������еĵ�һ���ַ�����������صķ���
			String[] rgb = params[0].split(",");
			//�������շ��ص��ַ���
			Color color = new Color();
			String fcolor = "#";

			for (int i = 0; i < rgb.length; i++) {
				//��ȡת����Ĳ�������ת��Ϊint����
				int a = Integer.parseInt(rgb[i]);
				if (a<=255 && a>=0) {	//�ж��Ƿ�Ϸ�
					//��10����ͨ������Integer.toHexString����ת��Ϊ16����
					fcolor += Integer.toHexString(a);
				}else{
					fcolor="����������������";
					break;
				}
			}
			color.setRgb(fcolor);
			context.put("color", color);
			return color;
		}
		else if (toType == String.class )
		{
			// ����Ҫת����ֵǿ������ת��ΪColorʵ��
			Color color = (Color) value;
			return  color.getRgb() ;
		}
		return super.convertValue(context, value, toType);
	}

	

}
