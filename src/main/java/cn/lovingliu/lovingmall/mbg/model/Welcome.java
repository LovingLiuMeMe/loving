package cn.lovingliu.lovingmall.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class Welcome implements Serializable {
    /**
     * 欢迎页轮播图主键id
     *
     * @mbg.generated
     */
    private Integer welcomeId;

    /**
     * 欢迎页轮播图链接地址
     *
     * @mbg.generated
     */
    private String welcomeUrl;

    /**
     * 排序值(字段越大越靠前)
     *
     * @mbg.generated
     */
    private Integer welcomeRank;

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

    public Integer getWelcomeId() {
        return welcomeId;
    }

    public void setWelcomeId(Integer welcomeId) {
        this.welcomeId = welcomeId;
    }

    public String getWelcomeUrl() {
        return welcomeUrl;
    }

    public void setWelcomeUrl(String welcomeUrl) {
        this.welcomeUrl = welcomeUrl;
    }

    public Integer getWelcomeRank() {
        return welcomeRank;
    }

    public void setWelcomeRank(Integer welcomeRank) {
        this.welcomeRank = welcomeRank;
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
        sb.append(", welcomeId=").append(welcomeId);
        sb.append(", welcomeUrl=").append(welcomeUrl);
        sb.append(", welcomeRank=").append(welcomeRank);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}