package com.tianxiawushuang.controller;

import com.tianxiawushuang.common.Result;
import com.tianxiawushuang.pojo.dto.MessageDTO;
import com.tianxiawushuang.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {
    
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    
    @Autowired
    private MessageService messageService;
    
    @PostMapping
    public Result<?> saveMessage(@Valid @RequestBody Map<String, Object> request) {
        String content = (String) request.get("content");
        String userIdentifier = (String) request.get("userIdentifier");
        
        logger.info("接收到留言：{}，用户标识：{}", content, userIdentifier);
        
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setContent(content);
        
        boolean success = messageService.saveMessage(messageDTO, userIdentifier);
        if (success) {
            logger.info("留言保存成功");
            return Result.ok("发送成功");
        } else {
            logger.error("留言保存失败");
            return Result.error("发送失败");
        }
    }
}
