package com.example.springexampletools.sendmessage;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @ClassName: AliMessageUtil
 */
public class AliMessageUtil {

    //  此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "";
    static final String accessKeySecret = "";
    static final String messageSing = "";
    static final String messageTemplateCode = "";

    public static SendSmsResponse sendSms(String phoneNumber, String code) throws ClientException {
        //设置超时时间(不必修改)
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        //(不必修改)
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient，("***"分别填写自己的AccessKey ID和Secret)
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        //(不必修改)
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        //(不必修改)
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象(不必修改)
        SendSmsRequest request = new SendSmsRequest();
        //****处填写接收方的手机号码
        request.setPhoneNumbers(phoneNumber);
        //****填写已申请的短信签名
        request.setSignName(messageSing);
        //****填写获得的短信模版CODE
        request.setTemplateCode(messageTemplateCode);
        //笔者的短信模版中有${code}, 因此此处对应填写验证码
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        //不必修改
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }


    public static void main(String[] args) throws ClientException, InterruptedException {
        SendSmsResponse response = sendSms("110110", "110110");
        System.out.println("已执行");
    }

}
