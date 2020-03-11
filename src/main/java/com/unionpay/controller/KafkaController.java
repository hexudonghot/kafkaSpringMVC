package com.unionpay.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

}
