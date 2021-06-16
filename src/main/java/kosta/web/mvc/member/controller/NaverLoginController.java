package kosta.web.mvc.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kosta.web.mvc.member.domain.Member;
import kosta.web.mvc.member.domain.OauthId;
import kosta.web.mvc.member.service.OauthIdService;

@Controller
@RequestMapping("/member")
public class NaverLoginController {

	private String CLIENT_ID = "9SxeotjOrDqv6uYd3S9c"; // 애플리케이션 클라이언트 아이디값";
	private String CLI_SECRET = "WMfFi6q5um"; // 애플리케이션 클라이언트 시크릿값";

	@Autowired
	OauthIdService oauthIdService;
	/**
	 * 로그인 화면이 있는 페이지 컨트롤
	 * 
	 * @param session
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws UnknownHostException
	 */
	@RequestMapping("/naver")
	public String testNaver(HttpSession session, Model model)
			throws UnsupportedEncodingException, UnknownHostException {

		String redirectURI = URLEncoder.encode("http://localhost:8000/member/naver/callback1", "UTF-8");

		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		apiURL += String.format("&client_id=%s&redirect_uri=%s&state=%s", CLIENT_ID, redirectURI, state);
		session.setAttribute("state", state);

		model.addAttribute("apiURL", apiURL);
		return "page/member/naverlogin";
	}

	/**
	 * 콜백 페이지 컨트롤러
	 * 
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("/naver/callback1")
	public String naverCallback1(HttpSession session, HttpServletRequest request, Model model)
			throws IOException, ParseException {

		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String redirectURI = URLEncoder.encode("http://localhost:8000/member/naver/callback1", "UTF-8");

		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + CLIENT_ID;
		apiURL += "&client_secret=" + CLI_SECRET;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;
		System.out.println("apiURL=" + apiURL);

		String res = requestToServer(apiURL);
		if (res != null && !res.equals("")) {
			
			model.addAttribute("res", res);
			Map<String, Object> parsedJson = new JSONParser(res).parseObject();
			System.out.println(parsedJson);
			
			if(parsedJson.get("access_token") != null) {
				String infoStr = getProfileFromNaver(parsedJson.get("access_token").toString());
				Map<String, Object> infoMap = new JSONParser(infoStr).parseObject();
				if(infoMap.get("message").equals("success")) {
					Map<String, Object> infoResp = (Map<String, Object>) infoMap.get("response");
					String uniqueId = infoResp.get("id").toString();
					System.out.println(uniqueId);
					OauthId oauthId= oauthIdService.findOauthIdByNaverId(uniqueId);
					
					if(oauthId == null) {
						System.out.println("네이버 연동정보 없음");
						model.addAttribute("isConnectedToNaver", false);
						model.addAttribute("uniqueIdOfNaver", uniqueId);
					}else {
						System.out.println(oauthId);
						model.addAttribute("isConnectedToNaver", true);
					}
					
				}
			}
			session.setAttribute("currentUser", res);
			session.setAttribute("currentAT", parsedJson.get("access_token"));
			session.setAttribute("currentRT", parsedJson.get("refresh_token"));
			
			model.addAttribute("res", res);
		} else {
			model.addAttribute("res", "Login failed!");
		}

		System.out.println("---------------------------");
		return "page/member/callback";
	}

	/**
	 * 토큰 갱신 요청 페이지 컨트롤러
	 * 
	 * @param session
	 * @param request
	 * @param model
	 * @param refreshToken
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("/naver/refreshToken")
	public String refreshToken(HttpSession session, HttpServletRequest request, Model model, String refreshToken)
			throws IOException, ParseException {

		String apiURL;

		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&";
		apiURL += "client_id=" + CLIENT_ID;
		apiURL += "&client_secret=" + CLI_SECRET;
		apiURL += "&refresh_token=" + refreshToken;

		System.out.println("apiURL=" + apiURL);

		String res = requestToServer(apiURL);
		model.addAttribute("res", res);
		session.invalidate();
		return "page/member/callback";
	}

	/**
	 * 토큰 삭제 컨트롤러
	 * 
	 * @param session
	 * @param request
	 * @param model
	 * @param accessToken
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/naver/deleteToken")
	public String deleteToken(HttpSession session, HttpServletRequest request, Model model, String accessToken)
			throws IOException {

		String apiURL;

		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&";
		apiURL += "client_id=" + CLIENT_ID;
		apiURL += "&client_secret=" + CLI_SECRET;
		apiURL += "&access_token=" + accessToken;
		apiURL += "&service_provider=NAVER";

		System.out.println("apiURL=" + apiURL);

		String res = requestToServer(apiURL);
		model.addAttribute("res", res);
		session.invalidate();
		return "page/member/callback";
	}

	/**
	 * 액세스 토큰으로 네이버에서 프로필 받기
	 * 
	 * @param accessToken
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/naver/getProfile")
	public String getProfileFromNaver(String accessToken) throws IOException {

		// 네이버 로그인 접근 토큰;
		String apiURL = "https://openapi.naver.com/v1/nid/me";
		String headerStr = "Bearer " + accessToken; // Bearer 다음에 공백 추가
		String res = requestToServer(apiURL, headerStr);
		return res;
	}

	/**
	 * 세션 무효화(로그아웃)
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/naver/invalidate")
	public String invalidateSession(HttpSession session) {
		session.invalidate();
		return "redirect:/naver";
	}

	/**
	 * 서버 통신 메소드
	 * 
	 * @param apiURL
	 * @return
	 * @throws IOException
	 */
	private String requestToServer(String apiURL) throws IOException {
		return requestToServer(apiURL, "");
	}

	/**
	 * 서버 통신 메소드
	 * 
	 * @param apiURL
	 * @param headerStr
	 * @return
	 * @throws IOException
	 */
	private String requestToServer(String apiURL, String headerStr) throws IOException {
		URL url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		System.out.println("header Str: " + headerStr);
		if (headerStr != null && !headerStr.equals("")) {
			con.setRequestProperty("Authorization", headerStr);
		}

		int responseCode = con.getResponseCode();
		BufferedReader br;

		System.out.println("responseCode=" + responseCode);

		if (responseCode == 200) { // 정상 호출
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else { // 에러 발생
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		String inputLine;
		StringBuffer res = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			res.append(inputLine);
		}
		br.close();
		if (responseCode == 200) {
			return res.toString();
		} else {
			return null;
		}

	}

	/**
	 * 네이버 계정을 oauth_id 테이블에 할당
	 *//*
		 * @PostMapping("/oauth/assign/naver") public String
		 * addRowToOAuthTableForNaver(HttpSession session, Authentication auth, Model
		 * model, String uniqueId) { 
		 * String username = auth.getName(); 
		 * String provider = "naver"; 
		 * OauthId infoOAuth = sud.getOAuthInfoByProviderAndUniqueId(uniqueId);
		 * int resultCode = 0; if(infoOAuth.size() == 0) { Map<String, String> aRow =
		 * new HashMap<>(); aRow.put("username", username); aRow.put("provider",
		 * provider); aRow.put("unique_id", uniqueId); resultCode =
		 * sud.insertAnUserOAuth(aRow); if(resultCode <= 0) {
		 * session.setAttribute("currentNaverUser", null); } model.addAttribute("task",
		 * "assign-naver"); model.addAttribute("resultCode", resultCode); } return
		 * "redirect:/"; }
		 */

	@RequestMapping("/naverRegister")
	public String naverRegister() {

		return "page/member/naverRegister";
	}
}