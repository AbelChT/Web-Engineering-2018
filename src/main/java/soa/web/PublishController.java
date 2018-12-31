package soa.web;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.twitter.data.ConsumerType;
import org.apache.camel.component.twitter.data.TimelineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PublishController {

  private final ProducerTemplate producerTemplate;

  private final ConsumerTemplate consumerTemplate;

  @Autowired
  public PublishController(ProducerTemplate producerTemplate, ConsumerTemplate consumerTemplate) {
    this.producerTemplate = producerTemplate;
    this.consumerTemplate = consumerTemplate;
  }

  @RequestMapping("/")
  public String index() {
    return "index";
  }

  @RequestMapping(value = "/publish")
  @ResponseBody
  public boolean publish(@RequestParam("q") String q) {
    try{
      producerTemplate.sendBody("direct:timeline", q);
      return true;
    }catch (Exception e){
      return false;
    }
  }

  @RequestMapping(value = "/timeline")
  @ResponseBody
  public Object getTimeline() {
    return consumerTemplate.receiveBody("direct:timeline-consult");
  }
}