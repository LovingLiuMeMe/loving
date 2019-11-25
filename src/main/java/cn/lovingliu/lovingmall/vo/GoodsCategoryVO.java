package cn.lovingliu.lovingmall.vo;

import cn.lovingliu.lovingmall.mbg.model.GoodsInfo;
import cn.lovingliu.lovingmall.util.serializer.CategoryStatusToDesc;
import cn.lovingliu.lovingmall.util.serializer.DateToDateFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 分类下面的所有的商品
 * @Date：Created in 2019-10-30
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoodsCategoryVO implements Serializable {
    private static final long serialVersionUID = 5712201572051025348L;

    private Long categoryId;

    private String categoryName;

    @JsonSerialize(using = CategoryStatusToDesc.class)
    private Byte isDeleted;

    @JsonSerialize(using = DateToDateFormat.class)
    private Date createTime;

    private Integer createUser;

    @JsonSerialize(using = DateToDateFormat.class)
    private Date updateTime;

    private Integer updateUser;

    private List<GoodsInfo> goodsInfoList;
}
