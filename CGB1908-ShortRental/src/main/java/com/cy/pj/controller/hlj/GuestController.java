package com.cy.pj.controller.hlj;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.cy.common.config.AlipayConfig;
import com.cy.common.vo.JsonResult;
import com.cy.pj.entity.tb_userinfo;
import com.cy.pj.entity.user;
import com.cy.pj.service.hlj.GuestService;

import com.cy.pj.vo.hlj.Guest;
import com.cy.pj.vo.hlj.Order;

import net.sf.json.JSONObject;
@RequestMapping("/")
@Controller
public class GuestController {
	public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    public static final String APPKEY ="3db8a56c1ac24cacbedec676ebbd1ebe";
	@Autowired
	private GuestService guestService;
	
	@RequestMapping("addGuest")
	@ResponseBody
	public JsonResult guest(Guest g) {
		
		try {
			Guest guest = guestService.selectByIdCard(g.getIdcard());
			
			if(guest != null) {
				
				guestService.updateByIdCard(g.getReal_name(),g.getIdcard());
			}else {
				guestService.addGuest(g);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
		return new JsonResult(g);
	}
	@RequestMapping("insertGuest")
	@ResponseBody
	public JsonResult insertGuest(Guest g) {
		try {
			Guest guest = guestService.selectByIdCard1(g.getIdcard());
			if(guest!=null) {
				guestService.updateByIdCard1(g.getReal_name(), g.getIdcard());
			}else {
				
				guestService.insertGuest(g);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
		
		return new JsonResult(g);
	}
	public String code() {
		String str="0123456789";
		StringBuilder sb=new StringBuilder(6);
		for(int i=0;i<6;i++)
		{
		char ch=str.charAt(new Random().nextInt(str.length()));
		sb.append(ch);
		}
		System.out.println(sb.toString());
		return sb.toString();

		

		}
	@RequestMapping("tijiao")
	@ResponseBody
	public JsonResult tijiao(Order order,String validcode,String code,HttpServletRequest req,HttpServletResponse res) {
		System.out.println("tijiao:"+validcode);
		HttpSession session = req.getSession(false);
		 System.out.println("heliangjiong:"+session);
		String sessionCode  = (String) session.getAttribute("code");//获取session的短信验证码
		String sessionValidCode =  (String) session.getAttribute("validation_code");//获取session的图片验证码
		System.out.println(sessionCode);
		System.out.println(sessionValidCode);
		if(validcode == null) {return new JsonResult("图片验证码为空,请填写");}
		if(code == null) {return new JsonResult("短信验证码为空,请填写");}
		if(!validcode.toLowerCase().equals(sessionValidCode.toLowerCase())) {
			return new JsonResult("图片验证码错误,请重新输入");
		}
		/* org.apache.catalina.session.StandardSessionFacade@142f3a5a
		 * if(!code.equals(sessionCode)) { return new JsonResult("短信验证码错误,请重新输入"); }
		 * 
		 * 84FD90D855097033405CAA8F05C14318  lina
		 * 0477ED4EFB5F74997B4FC95A2A59A001  heliangjiong
		 */
		tb_userinfo u = (tb_userinfo) session.getAttribute("USER");
		System.out.println("he亮炯："+u);
		//order.setGuest_id(u.getUserid());
		order.setGuest_id(u.getUserid());
		//待获取前端页面提供的 house_id,目前house_id和房东id和guest_id都是在js写死的
		order.setOrder_num(generateOrderNumber());
		guestService.addOrder(order);
		//同步修改tb_house的房间使用状态为已售  1  
		Integer house_id = order.getHouse_id();
		guestService.updateByHouseId(house_id);
		Cookie cookie1 = new Cookie("orderNumber",order.getOrder_num());
		Cookie cookie2 = new Cookie("money",order.getGross_price().toString());
		Cookie cookie3 = new Cookie("userid",order.getGuest_id().toString());
		res.addCookie(cookie1);
		res.addCookie(cookie2);
		res.addCookie(cookie3);
		return new JsonResult("订单提交成功");
		
	}
	
	/*生成8位数的订单编号*/
	public String generateOrderNumber() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		  Date date = new Date();
		  String str = simpleDateFormat.format(date);
		  Random random = new Random();
		  int rannum = (int) (random.nextDouble() * (99999999 - 10000000 + 1)) + 10000000;// 获取5位随机数
		  return rannum + str;// 当前时间
	}

	@RequestMapping("yzm")
	@ResponseBody
	public JsonResult yzm(String tenantmobile,HttpServletRequest req) {
		
		 String result =null;
	     String url ="http://v.juhe.cn/sms/send";//请求接口地址
	     String code = code();
	     Map params = new HashMap();//请求参数
	         params.put("mobile",tenantmobile);//接收短信的手机号码
	         params.put("tpl_id","200217");//短信模板ID，请参考个人中心短信模板设置
	         params.put("tpl_value","#code#="+code);//变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
	         params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
	         params.put("dtype","json");//返回数据的格式,xml或json，默认json
	         HttpSession session = req.getSession(false);
	         session.setAttribute("code", code);

	     try {
	         result =net(url, params, "GET");
	         JSONObject object = JSONObject.fromObject(result);
	         if(object.getInt("error_code")==0){
	             System.out.println(object.get("result"));
	         }else{
	             System.out.println(object.get("error_code")+":"+object.get("reason"));
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	     
		return new JsonResult();
	}
	
		public static String net(String strUrl, Map params,String method) throws Exception {
		    HttpURLConnection conn = null;
		    BufferedReader reader = null;
		    String rs = null;
		    try {
		        StringBuffer sb = new StringBuffer();
		        if(method==null || method.equals("GET")){
		            strUrl = strUrl+"?"+urlencode(params);
		        }
		        URL url = new URL(strUrl);
		        conn = (HttpURLConnection) url.openConnection();
		        if(method==null || method.equals("GET")){
		            conn.setRequestMethod("GET");
		        }else{
		            conn.setRequestMethod("POST");
		            conn.setDoOutput(true);
		        }
		        conn.setRequestProperty("User-agent", userAgent);
		        conn.setUseCaches(false);
		        conn.setConnectTimeout(DEF_CONN_TIMEOUT);
		        conn.setReadTimeout(DEF_READ_TIMEOUT);
		        conn.setInstanceFollowRedirects(false);
		        conn.connect();
		        if (params!= null && method.equals("POST")) {
		            try {
		                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		                    out.writeBytes(urlencode(params));
		            } catch (Exception e) {
		            }
		        }
		        InputStream is = conn.getInputStream();
		        reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
		        String strRead = null;
		        while ((strRead = reader.readLine()) != null) {
		            sb.append(strRead);
		        }
		        rs = sb.toString();
		    } catch (IOException e) {
		        e.printStackTrace();
		    } finally {
		        if (reader != null) {
		            reader.close();
		        }
		        if (conn != null) {
		            conn.disconnect();
		        }
		    }
		    return rs;
		}
		public static String urlencode(Map<String,Object>data) {
		    StringBuilder sb = new StringBuilder();
		    for (Map.Entry i : data.entrySet()) {
		        try {
		            sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
		        } catch (UnsupportedEncodingException e) {
		            e.printStackTrace();
		        }
		    }
		    return sb.toString();
		}

		

	
	@RequestMapping("guest")
	public String userorder(String houseid,Double money,Integer landlordid,Model model) {
		System.out.println(houseid);
		System.out.println(money);
		model.addAttribute("houseid", houseid);
		model.addAttribute("money", money);
		model.addAttribute("landlordid", landlordid);
		return "xiadangye";
		
	}
	@RequestMapping("topay")
	public String toPay() {
		return "pay";
		
	}
	
	@RequestMapping("alipay")
	public void alipay(String order_num,Double money,HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = new String(order_num.getBytes("ISO-8859-1"),"UTF-8");
		//付款金额，必填
		String total_amount = new String(money.toString().getBytes("ISO-8859-1"),"UTF-8");
		//订单名称，必填
		String subject = new String("此房源为速订房,支付后即可预定成功,请放心支付".getBytes(),"UTF-8");
		//商品描述，可空
		String body = new String("");
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求
		String result;
		try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
			PrintWriter out = response.getWriter();
			out.println(result);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		
	}

}
