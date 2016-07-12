package com.finefocus.tryspread.dao;

import com.finefocus.tryspread.pojo.InvitationRecordBean;

import java.util.List;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.dao
 * @date 2016/6/15
 * @Description: 操作邀请记录表的dao
 */
@QfBatisRepository
public interface InvitationRecordDao {
    /**
     * 邀请记录
     *
     * @param userId 用户id
     * @return 当前用户所邀请的记录
     */
    List<InvitationRecordBean> getInvitationRecordByUserId(int parentId);

    Integer getParentIdByUserId(int userId);
}
