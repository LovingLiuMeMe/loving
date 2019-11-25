package cn.lovingliu.lovingmall.controller.portal;

import cn.lovingliu.lovingmall.common.ServerResponse;
import cn.lovingliu.lovingmall.mbg.model.UserAddress;
import cn.lovingliu.lovingmall.service.UserAddressService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-11-17
 */
@Api(tags = "UserAddressController", description = "用户收货地址")
@RestController
@RequestMapping("/userAddress")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @GetMapping("/list")
    public ServerResponse list(@RequestParam("userId") Long userId){
        List<UserAddress> userAddressList = userAddressService.listByUserId(userId);
        return ServerResponse.createBySuccess(userAddressList);
    }

    @PostMapping("/save")
    public ServerResponse save(@RequestBody UserAddress userAddress){
        int count = userAddressService.save(userAddress);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("操作成功");
        }else {
            return ServerResponse.createByErrorMessage("操作失败");
        }
    }
    @PostMapping("/delete/{addressId}")
    public ServerResponse delete(@PathVariable Long addressId){
        int count = userAddressService.deleteById(addressId);
        if(count > 0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }else{
            return ServerResponse.createBySuccessMessage("删除失败");
        }
    }
}
