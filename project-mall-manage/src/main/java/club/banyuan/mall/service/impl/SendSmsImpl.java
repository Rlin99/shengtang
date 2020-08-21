package club.banyuan.mall.service.impl;

import club.banyuan.mall.service.SendSms;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SendSmsImpl implements SendSms {
    //@Value("${accessKeyId}")
    private String accessKeyId;
    //@Value("${secret}")
    private String secret;
    //@Value("${signName}")
    private String signName;
    //@Value("${templateCode}")
    private String templateCode;

    @Override
    public boolean send(String phoneNum, Map<String, Object> code) {
        //连接阿里云
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);


        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        //自定义的参数(手机号,验证码,签名,模板!)
        request.putQueryParameter("PhoneNumbers", phoneNum);
        request.putQueryParameter("SignName", signName);//签名（要和之前申请的保持一致）
        request.putQueryParameter("TemplateCode", templateCode);//模板管理里的模板CODE

        //构建一个短信的验证码
        request.putQueryParameter("TemplateParam", JSON.toJSONString(code));
        request.putQueryParameter("RegionId", "cn-hangzhou");


//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
//            return response.getHttpResponse().isSuccess();
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//
//        }
        return false;
    }
}
