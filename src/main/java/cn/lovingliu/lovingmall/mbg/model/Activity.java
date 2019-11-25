package cn.lovingliu.lovingmall.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class Activity implements Serializable {
    /**
     * 主页活动id
     *
     * @mbg.generated
     */
    private Long activityId;

    /**
     * 主页活动名称
     *
     * @mbg.generated
     */
    private String activityName;

    /**
     * 排序值(字段越大越靠前)
     *
     * @mbg.generated
     */
    private Integer activityRank;

    /**
     * 删除标识字段(0-未删除 1-已删除)
     *
     * @mbg.generated
     */
    private Byte isDeleted;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 创建者id
     *
     * @mbg.generated
     */
    private Integer createUser;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * 修改者id
     *
     * @mbg.generated
     */
    private Integer updateUser;

    private static final long serialVersionUID = 1L;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getActivityRank() {
        return activityRank;
    }

    public void setActivityRank(Integer activityRank) {
        this.activityRank = activityRank;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", activityId=").append(activityId);
        sb.append(", activityName=").append(activityName);
        sb.append(", activityRank=").append(activityRank);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}