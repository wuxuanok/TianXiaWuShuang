package com.tianxiawushuang.service;

import com.tianxiawushuang.pojo.dto.MessageDTO;

public interface MessageService {
    /**
     * 保存留言
     * @param messageDTO 留言DTO对象
     * @param userIdentifier 用户标识
     * @return 保存结果
     */
    boolean saveMessage(MessageDTO messageDTO, String userIdentifier);
}
