package com.finefocus.tryspread.pojo;

import java.util.Date;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package com.finefocus.tryspread.pojo
 * @date 2016/6/14
 * @Description: 邀请记录表
 */
public class InvitationRecordBean {
    private String userId;//用户ID
    private Date invitationTime;//邀请时间
    //    private Integer invitationClass;//邀请等级
    private Integer id;//邀请记录表id
    private Integer beInvitedId;//parentId

    public Integer getBeInvitedId() {
        return beInvitedId;
    }

    public void setBeInvitedId(Integer beInvitedId) {
        this.beInvitedId = beInvitedId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getInvitationTime() {
        return invitationTime;
    }

    public void setInvitationTime(Date invitationTime) {
        this.invitationTime = invitationTime;
    }

}
