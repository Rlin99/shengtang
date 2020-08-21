package club.banyuan.mall.service;

import java.util.Map;

public interface SendSms {
    public boolean send(String phoneNum, Map<String, Object> code);
}
