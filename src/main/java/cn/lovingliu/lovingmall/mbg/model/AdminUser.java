package cn.lovingliu.lovingmall.mbg.model;

import java.io.Serializable;

public class AdminUser implements Serializable {
    /**
     * 管理员id
     *
     * @mbg.generated
     */
    private Integer adminUserId;

    /**
     * 管理员登陆名称
     *
     * @mbg.generated
     */
    private String loginUserName;

    /**
     * 管理员登陆密码
     *
     * @mbg.generated
     */
    private String loginPassword;

    /**
     * 管理员显示昵称
     *
     * @mbg.generated
     */
    private String nickName;

    /**
     * 是否锁定 0未锁定 1已锁定无法登陆
     *
     * @mbg.generated
     */
    private Byte locked;

    private static final long serialVersionUID = 1L;

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Byte getLocked() {
        return locked;
    }

    public void setLocked(Byte locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adminUserId=").append(adminUserId);
        sb.append(", loginUserName=").append(loginUserName);
        sb.append(", loginPassword=").append(loginPassword);
        sb.append(", nickName=").append(nickName);
        sb.append(", locked=").append(locked);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}