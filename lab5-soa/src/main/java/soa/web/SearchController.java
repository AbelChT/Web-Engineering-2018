package soa.web;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
public class SearchController {

    private final ProducerTemplate producerTemplate;

    @Autowired
    public SearchController(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/search")
    @ResponseBody
    public Object search(@RequestParam("q") String q) {
        Pattern pattern = Pattern.compile("max:([0-9]*)");
        Matcher matcher = pattern.matcher(q);
        if (matcher.find()) {
            Map<String, Object> parameters = new HashMap<>(2);
            parameters.put("CamelTwitterCount", matcher.group(1));
            parameters.put("CamelTwitterKeywords", Arrays.stream(pattern.split(q)).reduce(String::concat).get());
            return producerTemplate.requestBodyAndHeaders("direct:search", "", parameters);
        } else {
            return producerTemplate.requestBodyAndHeader("direct:search", "", "CamelTwitterKeywords", q);
        }
    }
}