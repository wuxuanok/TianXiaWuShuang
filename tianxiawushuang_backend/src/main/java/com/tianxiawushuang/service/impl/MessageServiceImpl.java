package com.tianxiawushuang.service.impl;

import com.tianxiawushuang.pojo.Message;
import com.tianxiawushuang.pojo.dto.MessageDTO;
import com.tianxiawushuang.mapper.MessageMapper;
import com.tianxiawushuang.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class MessageServiceImpl implements MessageService {
    
    @Autowired
    private MessageMapper messageMapper;
    
    @Override
    public boolean saveMessage(MessageDTO messageDTO, String userIdentifier) {
        // 将DTO转换为实体类
        Message message = new Message();
        message.setContent(messageDTO.getContent());
        message.setUserIdentifier(userIdentifier);
        message.setCreateTime(LocalDateTime.now());
        return messageMapper.insert(message) > 0;
    }
}

