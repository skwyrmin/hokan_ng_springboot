package org.freakz.hokan_ng_springboot.bot.jms;

import lombok.extern.slf4j.Slf4j;
import org.freakz.hokan_ng_springboot.bot.jms.api.JmsSender;
import org.freakz.hokan_ng_springboot.bot.service.JmsServiceMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

/**
 *
 * Created by petria on 5.2.2015.
 */
@Component
@Slf4j
public class IoJmsReceiver extends SpringJmsReceiver {

  @Autowired
  private JmsSender jmsSender;

  @Autowired
  private JmsServiceMessageHandler jmsServiceMessageHandler;


  @Override
  public String getDestinationName() {
    return "HokanNGIoQueue";
  }

  @Override
  public void handleJmsMessage(Message message) throws JMSException {
    ObjectMessage objectMessage = (ObjectMessage) message;
    JmsMessage jmsMessage = (JmsMessage) objectMessage.getObject();
    JmsMessage jmsReplyMessage = null;
    try {
      log.info("---->");
      jmsReplyMessage = jmsServiceMessageHandler.handleJmsServiceMessage(jmsMessage);
      log.info("<----");
    } catch (Exception e) {
      jmsReplyMessage = new JmsMessage();
      jmsReplyMessage.addPayLoadObject("REPLY", e.getMessage());
      log.error("Something went wrong!");
    }
    Destination replyTo = message.getJMSReplyTo();
    log.debug("got message: {}, replyTo: {}", jmsMessage, replyTo);
    if (replyTo != null) {
      if (jmsReplyMessage != null) {
        log.info("Sending reply: {}", jmsReplyMessage);
        jmsSender.sendJmsMessage(replyTo, jmsReplyMessage);
      }
    }

  }

}
