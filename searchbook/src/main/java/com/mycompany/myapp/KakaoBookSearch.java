 package com.mycompany.myapp;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class KakaoBookSearch {
	ArrayList<String> titleList;
	ArrayList<String> isbnList;
	ArrayList<String> authorList;
	ArrayList<String> publisherList;
	ArrayList<String> thumbnailList;
	ArrayList<String> datetimeList;
	ArrayList<String> contentList;
	ArrayList<String> EA_ADD_CODE_list;
	public KakaoBookSearch() {
		isbnList=new ArrayList<String>();
		titleList=new ArrayList<String>();
		authorList=new ArrayList<String>();
		publisherList=new ArrayList<String>();
		thumbnailList=new ArrayList<String>();
		datetimeList=new ArrayList<String>();
		contentList=new ArrayList<String>();
		EA_ADD_CODE_list=new ArrayList<String>();
		int count= 0;
		// TODO Auto-generated constructor stub
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();

		// RestTemplate 객체 생성
		RestTemplate rest = new RestTemplate();

		// HttpHeaders 객체 생성
		HttpHeaders headers = new HttpHeaders();

		// hearders에 ContentType을 Json으로 설정한다.
		headers.setContentType(MediaType.APPLICATION_JSON);

		// 사용할 api 키
		String key = "KakaoAK dacb438d04968f33fd5fcc9f9a1c6cd2";

		// headers 에 이름이 "Authorization"인 부분을 키로 설정한다.
		headers.set("Authorization", key);

		// headers를 HttpEntity 객체를 생성한 후 저장한다.
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		String encode = null;

		try {
			encode = URLEncoder.encode(a, "UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		String rawurl = "https://dapi.kakao.com/v3/search/book?target=title,publisher,person&size=50&query=" + encode;
		URI uri = null;
		try {
			uri = new URI(rawurl);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(uri);

		// api를 호출하기위해서 exchange()메소드 사용
		ResponseEntity<String> res = rest.exchange(uri, HttpMethod.GET, entity, String.class);

		// JSON 파서 객체 생성
		JSONParser jsonParser = new JSONParser();
		JSONObject body = null;
		try {
			// res에 저장된 정보를 getBody()를 통해서 가져오고 toString()을 통해
			// 스트링으로 변환하고 JSONObject에 저장한다.
			body = (JSONObject) jsonParser.parse(res.getBody().toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray docu = (JSONArray) body.get("documents");
		System.out.println(docu);
//		System.out.println(docu.size());
		String n="0";
		for (int i = 0; i < docu.size(); i++) {
			JSONObject asd = (JSONObject) docu.get(i);
			// 저장된 부분중에 place_name을 가져온다.
			
			if(Get_EA_ADD_CODE(((String) asd.get("isbn")).split(" ")[1]).length()>=1 & asd.get("thumbnail").toString().length()>=1) {
				System.out.println("-------------------------------------------------");
				
				isbnList.add(((String) asd.get("isbn")).split(" ")[1]);
				
				titleList.add((String) asd.get("title"));
		
				authorList.add(asd.get("authors").toString());
//				authorList.add((String) asd.get("authors"));

				publisherList.add((String) asd.get("publisher"));

				datetimeList.add((String)asd.get("datetime"));
				
				
				contentList.add((String) asd.get("contents"));
				
				thumbnailList.add((String)asd.get("thumbnail"));
				EA_ADD_CODE_list.add(Get_EA_ADD_CODE(((String) asd.get("isbn")).split(" ")[1]));
				
				count+=1;
				
			}
			else {
			
			}
			
		}
		System.out.printf("total  = %d " , count);

	}

	public static String getTagValue(String tag, Element eElement) {
		NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nList.item(0);
		if (nValue == null)
			return null;

		return nValue.getNodeValue();
	}

	public String Get_EA_ADD_CODE(String isbn) {
		String k="";
		try {

			String category = "도서";
			String book = URLEncoder.encode(category, "UTF-8"); // 도서 카테고리 UTF-8로 인코딩

			// api 키
			String bkey = "4bdd93877b64c8f0a612cd9f463d77ba31b0d0739e1854d27ce5773838d31c2f";
			// api url
//			String url="https://www.nl.go.kr/NL/search/openApi/search.do?key="+bkey+"&apiType=xml&srchTarget=total&pageSize=200&pageNum=1&sort=&category="+book+"&isbn="+is;
			String url = "https://www.nl.go.kr/seoji/SearchApi.do?cert_key=" + bkey
					+ "&result_style=xml&page_no=1&page_size=10&isbn=" + isbn;

//			System.out.println(url);
			// DOM 파서는 XML을 파싱 하여 메모리 상에 XML 구조에 대응하는 객체의 트리를 유지한다.
			// DOM 을 사용해서 XML 파싱
			Document documentInfo = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
			documentInfo.getDocumentElement().normalize();
			// NodeList 를 통해서 e 라는 이름을 가진 요소를 가져온다.
			NodeList aList = documentInfo.getElementsByTagName("e");
			// e 의 개수 확인
//			System.out.println(aList.getLength());
			// 9788983921468
			// e라는 이름을 가진 모든 요소의 정보를 가져오기위해 반복문 사용
			for (int temp = 0; temp < aList.getLength(); temp++) {
				// nNode는 NodeList aList 에 저장된 아이템이고 temp 의 값은 인덱스 값이다.
				// ex>aList.itme(1)
				Node nNode = aList.item(temp);
				// nNode의 타입이 특정 타입의 노드로 HTML 문서의 tag를 사용해 작성된 node임을 확인하고
				// 맞다면 원하는 정보를 저장하고있는 태그의 이름을 통해서 정보를 가져온다.
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					k=getTagValue("EA_ADD_CODE", eElement);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return k;
	}
	public ArrayList<String> GetTitleList(){
		return titleList;
	}
	public ArrayList<String> GetIsbnList(){
		return isbnList;
	}
	public ArrayList<String> GetAuthroList(){
		return authorList;
	}
	public ArrayList<String> GetDatetimeList(){
		return datetimeList;
	}
	public ArrayList<String> GetThumbnailList(){
		return thumbnailList;
	}
	public ArrayList<String> GetEA_ADD_CODE_List(){
		return EA_ADD_CODE_list;
	}
	public ArrayList<String> GetContentList(){
		return contentList;
	}
	public ArrayList<String> GetPublisherList(){
		return publisherList;
	}
	

}
