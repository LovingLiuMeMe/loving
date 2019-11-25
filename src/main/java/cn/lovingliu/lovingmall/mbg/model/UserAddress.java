package cn.lovingliu.lovingmall.mbg.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class UserAddress implements Serializable {
    /**
     * 收货地址主键id
     *
     * @mbg.generated
     */
    @JsonProperty("id")
    private Long addressId;

    /**
     * 用户主键id
     *
     * @mbg.generated
     */
    private Long userId;

    /**
     * 收货人姓名
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 收货人姓名
     *
     * @mbg.generated
     */
    @JsonProperty("tel")
    private String telephone;

    /**
     * 默认地址(0-默认 1-非默认)
     *
     * @mbg.generated
     */
    private Byte isDefault;

    /**
     * 省
     *
     * @mbg.generated
     */
    private String province;

    /**
     * 市
     *
     * @mbg.generated
     */
    private String city;

    /**
     * 区
     *
     * @mbg.generated
     */
    private String county;

    /**
     * 详细地址
     *
     * @mbg.generated
     */
    private String addressdetail;

    /**
     * 邮政编码
     *
     * @mbg.generated
     */
    private String postalcode;

    /**
     * 区码
     *
     * @mbg.generated
     */
    private String areacode;

    /**
     * 其他地址信息
     *
     * @mbg.generated
     */
    private String address;

    /**
     * 注册时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddressdetail() {
        return addressdetail;
    }

    public void setAddressdetail(String addressdetail) {
        this.addressdetail = addressdetail;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        sb.append(", addressId=").append(addressId);
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", telephone=").append(telephone);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", county=").append(county);
        sb.append(", addressdetail=").append(addressdetail);
        sb.append(", postalcode=").append(postalcode);
        sb.append(", areacode=").append(areacode);
        sb.append(", address=").append(address);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}