package kosta.web.mvc.map.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import kosta.web.mvc.map.dto.Item;

@Service
public class XmlParsingTest {
	

	private static Date convert(String input_format){
    SimpleDateFormat dateParser = new SimpleDateFormat("yyyyMMddHHmmss");
    SimpleDateFormat dateFormatter=null;
    Date date=null;
        try {
            date = dateParser.parse(input_format);
            System.out.println(date);

            dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(dateFormatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
		return date;
	}
	
	// tag값의 정보를 가져오는 메소드
	private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    
	    return nValue.getNodeValue();
	}
	public List<Item> test() {
		Item info = null;
		List<Item> infoList = new ArrayList<Item>();
		try{
			// parsing할 url 지정(API 키 포함해서)
			String url = "http://openapi.tago.go.kr/openapi/service/TrainInfoService/getStrtpntAlocFndTrainInfo";
			String serviceKey = "slSTe48bawVrHf4Y%2F8dijCFq4cj1coVf1xCNjFMJ7gif3B2zgJZixgD%2FAiYUX5odtTe65pGcn%2FLHQBFYLS5l%2Bg%3D%3D";
			String numOfRows = "300";
			//String pageNo = "300";
			String depPlaceId = "NAT010000"; //출발기차역ID
			String arrPlaceId = "NAT011668"; //도착기차역ID
			String depPlandTime = "20210611"; //출발일
					
			Document documentInfo = DocumentBuilderFactory
					.newInstance()
					.newDocumentBuilder()
					.parse(url+"?ServiceKey="+serviceKey+"&numOfRows="+numOfRows+"&depPlaceId="+depPlaceId+"&arrPlaceId="+arrPlaceId+"&depPlandTime="+depPlandTime);
			
			// root tag 
			documentInfo.getDocumentElement().normalize();
							System.out.println("Root element :" + documentInfo.getDocumentElement().getNodeName());
			
			// 파싱할 tag
			NodeList nList = documentInfo.getElementsByTagName("item");
							System.out.println("파싱할 리스트 수 : "+ nList.getLength());
			
			for(int temp = 0; temp < nList.getLength(); temp++){
				Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					
					Element eElement = (Element) nNode;
//					System.out.println("######################");
//					System.out.println("adultcharge  : " + getTagValue("adultcharge", eElement));
//					System.out.println("arrplacename  : " + getTagValue("arrplacename", eElement));
//					System.out.println("arrplandtime  : " + getTagValue("arrplandtime", eElement));
//					System.out.println("depplacename  : " + getTagValue("depplacename", eElement));
//					System.out.println("depplandtime : " + getTagValue("depplandtime", eElement));
//					System.out.println("traingradename  : " + getTagValue("traingradename", eElement));
//					System.out.println("trainno : " + getTagValue("trainno", eElement));
					
					
					String adultcharge = getTagValue("adultcharge", eElement);
					String arrplacename = getTagValue("arrplacename", eElement);
					String arrplandtime = getTagValue("arrplandtime", eElement);
					String depplacename = getTagValue("depplacename", eElement);
					String depplandtime = getTagValue("depplandtime", eElement);
					String traingradename = getTagValue("traingradename", eElement);
					String trainno = getTagValue("trainno", eElement);
					
					Date dep = convert(depplandtime);
					Date arr =convert(arrplandtime);
					String duration=null;
					long diff = arr.getTime() - dep.getTime();
					long diffMinutes = diff / (60 * 1000);         
					
					long hours = diffMinutes / 60; 
					long minutes = diffMinutes % 60;
					if(minutes < 10) {
						
					 duration = hours+":0"+minutes;
					}
					 duration = hours+":"+minutes;
				
					info = new Item(Integer.parseInt(adultcharge), arrplacename, convert(arrplandtime), depplacename, convert(depplandtime), traingradename, Integer.parseInt(trainno), duration);
					infoList.add(info);
				}	
			}	
		} catch (Exception e){	
			e.printStackTrace();
		}
		return infoList;
	}

}