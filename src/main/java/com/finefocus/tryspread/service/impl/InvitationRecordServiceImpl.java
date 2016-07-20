package com.finefocus.tryspread.service.impl;

import com.finefocus.tryspread.dao.InvitationRecordDao;
import com.finefocus.tryspread.pojo.InvitationRecordBean;
import com.finefocus.tryspread.service.InvitationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.service.impl
 * @date 2016/6/15
 * @Description: ${todo}
 */
@Service(value = "invitationRecordService")
public class InvitationRecordServiceImpl implements InvitationRecordService {
    @Autowired
    private InvitationRecordDao invitationRecordDao;

    public List<InvitationRecordBean> getInvitationRecordByUserId(int parentId) {

        return invitationRecordDao.getInvitationRecordByUserId(parentId);
    }

    public Integer getParentIdByUserId(int userId) {
        return invitationRecordDao.getParentIdByUserId(userId);
    }

    public Integer getGrandpaIdByUserId(int userId) {
        Integer parentId = invitationRecordDao.getParentIdByUserId(userId);
        if (parentId != null) {
            return invitationRecordDao.getParentIdByUserId(parentId);
        } else {
            return null;
        }

    }

    public void saveInvitationRecord(int userId, Integer parentId) {
        invitationRecordDao.saveInvitationRecord(userId, parentId, new Date());
    }
}
