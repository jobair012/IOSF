package nu.ist.iosf.core.service.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class SmsService {

//    http://brandsms.mimsms.com/smsapi?api_key=(APIKEY)&type=text&contacts=(NUMBER)&senderid=(Approved Sender ID)&msg=(Message Content)

    @Value("${sms.url}")
    private String smsApiUrl;

    @Value("${sms.api.key}")
    private String smsAppKey;

    @Value("${sms.type}")
    private String smsType;

    @Value("${sms.senderid}")
    private String smsSenderId;

    public String sendSMS(String contactNumber, String message){

        try{
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(smsApiUrl)
                    .queryParam("api_key", smsAppKey)
                    .queryParam("type", smsType)
                    .queryParam("contacts", contactNumber)
                    .queryParam("senderid", smsSenderId)
                    .queryParam("msg", message);

            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(uriComponentsBuilder.build().toUri(), String.class);
        }catch (Exception ex){
            ex.printStackTrace();
            return "sms not send";
        }
    }

}
