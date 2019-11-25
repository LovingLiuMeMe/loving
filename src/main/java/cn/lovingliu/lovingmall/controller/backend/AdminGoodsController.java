package cn.lovingliu.lovingmall.controller.backend;

import cn.lovingliu.lovingmall.common.CommonPage;
import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.dto.GoodsInfoDTO;
import cn.lovingliu.lovingmall.enums.CommonCodeEnum;
import cn.lovingliu.lovingmall.mbg.model.GoodsInfo;
import cn.lovingliu.lovingmall.service.FileService;
import cn.lovingliu.lovingmall.service.GoodsInfoService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-10-30
 */
@Api(tags = "AdminGoodsController", description = "卖家商品管理")
@RestController
@RequestMapping("/adminGoods")
@Slf4j
public class AdminGoodsController {
    @Autowired
    private GoodsInfoService goodsInfoService;

    @Autowired
    private FileService fileService;

    @Value("${ftp.image.prefix}")
    private String imagePrefix;

    @ApiOperation("查询商品列表(可带条件)")
    @GetMapping("/list")
    public ServerResponse<CommonPage<GoodsInfo>> list(
            @ApiParam("当前页码")
            @RequestParam(value = "pageNum",defaultValue = "1")
                    Integer pageNum,

            @ApiParam("页面容量")
            @RequestParam(value = "pageSize",defaultValue = "10")
                    Integer pageSize,

            @ApiParam("排序条件")
            @RequestParam(value = "orderBy",defaultValue = "create_time")
                    String orderBy,

            @ApiParam("排序方式(升序/降序)")
            @RequestParam(value = "orderType",defaultValue = "desc")
                    String orderType,

            @ApiParam("查询条件")
            @RequestParam(value = "search",required = false)
                    String keyword){
        List<GoodsInfo> goodsInfoList = goodsInfoService.listByAdmin(pageNum,pageSize,orderBy,orderType,keyword);
        return ServerResponse.createBySuccess("获取成功", CommonPage.restPage(goodsInfoList));
    }

    @ApiOperation("商品上下架")
    @PostMapping("/updateSellStatus")
    /**
     * @Desc 注意@RequestParam 要用于接收前端发送的信息 必须使用qs.stringifty(params)
     * @Author LovingLiu
    */
    public ServerResponse updateSellStatus(@ApiParam("商品ID")
                                           @RequestParam("GoodsInfoId")
                                           Long GoodsInfoId){
        int count = goodsInfoService.updateGoodsInfoSellStatus(GoodsInfoId);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("更新成功");
        }else{
            return ServerResponse.createByErrorMessage("更新失败");
        }
    }
    @ApiOperation("商品创建/修改")
    @PostMapping("/save")
    public ServerResponse save(@ApiParam("商品详情")
                               @Valid @RequestBody GoodsInfoDTO goodsInfoDTO, BindingResult result){
        if(result.hasErrors()){
            return ServerResponse.createByErrorMessage(result.getFieldError().getDefaultMessage());
        }
        int count = goodsInfoService.saveGoodsInfo(goodsInfoDTO);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("操作成功");
        }
        return ServerResponse.createByErrorMessage("操作失败");
    }

    @ApiOperation("商品详情")
    @GetMapping("/info")
    public ServerResponse info(@ApiParam("商品ID")
                               @RequestParam("GoodsInfoId")
                               Long GoodsInfoId){
        return ServerResponse.createBySuccess("获取成功",goodsInfoService.findByGoodsId(GoodsInfoId));
    }
    @ApiOperation("删除指定商品")
    @PostMapping("/delete")
    public ServerResponse delete(@ApiParam("商品ID")
                                 @RequestParam("GoodsInfoId")
                                 Long goodsInfoId){
        List<Long> goodsInfoIdList = Lists.newArrayList(goodsInfoId);

        int count = goodsInfoService.removeGoodsInfo(goodsInfoIdList);
        log.info("删除商品的数量: {}",count);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @ApiOperation("图片上传(适用于所富文本和)")
    @PostMapping("/imageUpload")
    public ServerResponse imageUpload(@ApiParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("upload");// 拿到的是和WEB-INF同级的（服务器的发布环境）
        String targetFileName = fileService.upload(uploadFile,path);
        String imageUrl = imagePrefix+targetFileName;
        Map<String,Object> map = new HashMap<>();
        map.put("url",imageUrl);
        return ServerResponse.createBySuccess("上传成功", map);
    }

    @ApiOperation("删除指定名称的图片")
    @PostMapping("/imageDelete")
    /**
     * @Desc 图片的上传 注意 前后端数组数据的交互 https://blog.csdn.net/pifutan/article/details/86320705
     * @Author LovingLiu
    */
    public ServerResponse imageDelete(@ApiParam("图片名称") @RequestParam String[] fileNames){
        Boolean is_delete = fileService.delete(fileNames);
        if(is_delete){
            return ServerResponse.createBySuccess("删除成功");
        }
        return ServerResponse.createBySuccess("删除失败");
    }

    @ApiOperation("查询全部商品的基本信息")
    @GetMapping("/listAll")
    public ServerResponse listAll(){
        List<GoodsInfo> goodsInfoList = goodsInfoService.listAllBySellStatus(CommonCodeEnum.PRODUCT_STATUS_ON_SELL.getCode());
        return ServerResponse.createBySuccess("查询成功",goodsInfoList);
    }
}
