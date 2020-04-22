package com.unionpay.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class KafkaController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);
//       http://localhost:8080/spring/welcome
	@RequestMapping("welcome")
	public ModelAndView welcome() {
		System.out.println("--------welcome--------");
		logger.info("aaaaaaaaaaaaaaaa");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("welcome");
		kafkaTemplate.send("aaa","bbbbbbb");
		return mv;
	}

	/**
	 * @Controller
	 * @RequestMapping("/user")
	 * public class UserController {
	 *
	 *     @RequestMapping("/{id}")
	 * @return
	 */

	@RequestMapping(value = "/sendmessage", method = RequestMethod.GET)
	public ModelAndView sendMessage() {
		System.out.println("--------sendmessage--------");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(date);

		ModelAndView mv = new ModelAndView();
		mv.addObject("time", now);
		mv.setViewName("kafka_send");
		return mv;
	}

	@RequestMapping("onsend")
	public ModelAndView onsend() {
		System.out.println("--------onsend--------");

		ModelAndView mv = new ModelAndView();
		mv.setViewName("welcome");
		return mv;
	}

	@RequestMapping(value = "/receive")
	public ModelAndView receive() {
		System.out.println("--------receive--------");
		

		ModelAndView mv = new ModelAndView();
		mv.setViewName("kafka_receive");
		return mv;
	}

	@RequestMapping(value="/ad/extend/noticeKsiOS", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String noticeKuaiShouiOS(@RequestBody String  data, HttpServletRequest request)
	{
		//return doNotice(appid, idfaMD5, channel, callback, timestamp, signature, request, WebConstant.SUPPORT_MD5_VERIFY);
		System.out.println(data);
		JSONObject json = JSONObject.parseObject(data);

		JSONObject json2 =new  JSONObject();
		json2.put("msg","success");
		json2.put("code",0);
		return json2.toJSONString();
	}

}
