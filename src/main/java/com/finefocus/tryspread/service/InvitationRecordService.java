package com.finefocus.tryspread.service;

import com.finefocus.tryspread.pojo.InvitationRecordBean;

import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service
 * @date 2016/6/15
 * @Description: ${todo}
 */
public interface InvitationRecordService {
    List<InvitationRecordBean> getInvitationRecordByUserId(int parentId);
}
