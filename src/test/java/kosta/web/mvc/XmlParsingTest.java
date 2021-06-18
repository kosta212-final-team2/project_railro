package kosta.web.mvc;

import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
@SpringBootTest
public class XmlParsingTest {
	
	/**
	 * 날짜 시간 형식 변환 
	 * */
	@Test
	private static void convert(){
		SimpleDateFormat input_format = new SimpleDateFormat("yyyyMMddHHmmss"); // 년월일시분초 14자리 포멧
		SimpleDateFormat output_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 입력포멧

		// 일시 문자열을 읽고 출력하는 실습
		try {
			String str_source = "20210610083000";         //입력포멧 문자열
			Date date_parsed = input_format.parse(str_source); // 문자열을 파싱해 Date형으로 저장한다
			System.out.println(output_format.format(date_parsed)); // 14자리 포멧으로 출력한다
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	

	// tag값의 정보를 가져오는 메소드
	private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    
	    return nValue.getNodeValue();
	}

	@Test
	public void test() {
		try{
			// parsing할 url 지정(API 키 포함해서)
			String url = "http://openapi.tago.go.kr/openapi/service/TrainInfoService/getStrtpntAlocFndTrainInfo";
			String serviceKey = "slSTe48bawVrHf4Y%2F8dijCFq4cj1coVf1xCNjFMJ7gif3B2zgJZixgD%2FAiYUX5odtTe65pGcn%2FLHQBFYLS5l%2Bg%3D%3D";
			String depPlaceId = "NAT010000"; //출발기차역ID
			String arrPlaceId = "NAT011668"; //도착기차역ID
			String depPlandTime = "20210611"; //출발일
					
			Document documentInfo = DocumentBuilderFactory
					.newInstance()
					.newDocumentBuilder()
					.parse(url+"?ServiceKey="+serviceKey+"&depPlaceId="+depPlaceId+"&arrPlaceId="+arrPlaceId+"&depPlandTime="+depPlandTime);
			
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
					System.out.println("######################");
					System.out.println("adultcharge  : " + getTagValue("adultcharge", eElement));
					System.out.println("arrplacename  : " + getTagValue("arrplacename", eElement));
					System.out.println("arrplandtime  : " + getTagValue("arrplandtime", eElement));
					System.out.println("depplacename  : " + getTagValue("depplacename", eElement));
					System.out.println("depplandtime : " + getTagValue("depplandtime", eElement));
					System.out.println("traingradename  : " + getTagValue("traingradename", eElement));
					System.out.println("trainno : " + getTagValue("trainno", eElement));
				}	
			}	
		} catch (Exception e){	
			e.printStackTrace();
		}
	}

}