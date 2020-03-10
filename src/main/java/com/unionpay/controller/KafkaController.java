package com.unionpay.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unionpay.consumer.KafkaConsumerDemo;
import com.unionpay.producer.KafkaProducerDemo;

@Controller
public class KafkaController {

	@Resource(name = "kafkaProducerDemo")
	KafkaProducerDemo producer;

	@Resource(name = "kafkaConsumerDemo")
	KafkaConsumerDemo consumer;

	private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);
//       http://localhost:8080/spring/welcome
	@RequestMapping("welcome")
	public ModelAndView welcome() {
		System.out.println("--------welcome--------");
		logger.info("aaaaaaaaaaaaaaaa");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("welcome");
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
		mv.setViewName("/WEB-INF/kafka_send.jsp");
		return mv;
	}

	@RequestMapping(value = "/onsend", method = RequestMethod.POST)
	public ModelAndView onsend(@RequestParam("message") String msg) {
		System.out.println("--------onsend--------");
		producer.sendMessage(msg);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("/WEB-INF/welcome.jsp");
		return mv;
	}

	@RequestMapping(value = "/receive")
	public ModelAndView receive() {
		System.out.println("--------receive--------");
		
		String msg = consumer.receive();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", msg);
		mv.setViewName("/WEB-INF/kafka_receive.jsp");
		return mv;
	}

}
