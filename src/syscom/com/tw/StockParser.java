package syscom.com.tw;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class StockParser {

	public static Map Parsing(String number) throws Exception {
		URL url = new URL("https://tw.stock.yahoo.com/q/q?s="+number); 
		Document xmlDoc =  Jsoup.parse(url, 3000); //�ϥ�Jsoup jar �h�ѪR����
		//(�n�ѪR�����,timeout)
		Map map=new HashMap();
		Elements td = xmlDoc.select("td");  //�n�ѪR��tag������td
		for (int i=0; i<td.size(); i++){
			map.put("field"+i, td.get(i).text());
			//System.out.println("fiedld"+i+": " + td.get(i).text());
		}
		return map;
	}
	
	
	public static JSONObject stockData(List stockNumber) throws Exception{
		JSONObject jsonObj= new JSONObject();
		
		for(int i=0; i<stockNumber.size();i++){
    		Map stockMap=StockParser.Parsing(stockNumber.get(i).toString());
    		JSONArray Jarray = new JSONArray();
//        	jsonObj.put("price_"+i, stockMap.get("field27").toString());//����
//        	jsonObj.put("upanddown_"+i, stockMap.get("field30").toString());//���^
//        	jsonObj.put("count_"+i, stockMap.get("field31").toString());//����i��
//        	jsonObj.put("open_"+i, stockMap.get("field33").toString());//�}�L��
//        	jsonObj.put("top_"+i, stockMap.get("field34").toString());//�̰���
//        	jsonObj.put("bottom_"+i, stockMap.get("field35").toString());//�̧C�� 	
        	Jarray.put(stockMap.get("field27").toString());//����
        	Jarray.put(stockMap.get("field30").toString());//���^
        	Jarray.put(stockMap.get("field31").toString());//����i��
        	Jarray.put(stockMap.get("field33").toString());//�}�L��
        	Jarray.put(stockMap.get("field34").toString());//�̰���
        	Jarray.put(stockMap.get("field35").toString());//�̧C�� 	
        	jsonObj.put("JsonArray"+i, Jarray);
    	}
		return jsonObj;
		
	}
}
