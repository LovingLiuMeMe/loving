package cn.lovingliu.lovingmall.mbg.model;

import cn.lovingliu.lovingmall.util.serializer.DateToDateFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     * 用户主键id
     *
     * @mbg.generated
     */
    private Long userId;

    /**
     * 用户昵称
     *
     * @mbg.generated
     */
    private String nickName;

    /**
     * 用户名（邮箱）
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * MD5加密后的密码
     *
     * @mbg.generated
     */
    @JsonIgnore
    private String password;

    /**
     * 个性签名
     *
     * @mbg.generated
     */
    private String introduceSign;

    /**
     * 注销标识字段(0-正常 1-已注销)
     *
     * @mbg.generated
     */
    private Byte isDeleted;

    /**
     * 锁定标识字段(0-未锁定 1-已锁定)
     *
     * @mbg.generated
     */
    private Byte lockedFlag;

    /**
     * 注册时间
     *
     * @mbg.generated
     */
    @JsonSerialize(using = DateToDateFormat.class)
    private Date createTime;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIntroduceSign() {
        return introduceSign;
    }

    public void setIntroduceSign(String introduceSign) {
        this.introduceSign = introduceSign;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Byte getLockedFlag() {
        return lockedFlag;
    }

    public void setLockedFlag(Byte lockedFlag) {
        this.lockedFlag = lockedFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", nickName=").append(nickName);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", introduceSign=").append(introduceSign);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", lockedFlag=").append(lockedFlag);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}