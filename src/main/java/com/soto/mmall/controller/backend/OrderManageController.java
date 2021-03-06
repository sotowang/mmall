package com.soto.mmall.controller.backend;

import com.github.pagehelper.PageInfo;
import com.soto.mmall.common.Const;
import com.soto.mmall.common.ResponseCode;
import com.soto.mmall.common.ServerResponse;
import com.soto.mmall.pojo.User;
import com.soto.mmall.service.IOrderService;
import com.soto.mmall.service.IUserService;
import com.soto.mmall.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/order")
public class OrderManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IOrderService iOrderService;


    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderList(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆,请选登陆");
        }
        //校验是否为管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            return iOrderService.manageList(pageNum, pageSize);

        } else {
            return ServerResponse.createByErrorMessage("需要管理员权限");
        }
    }
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<OrderVo> orderDetail(HttpSession session, Long orderNo) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆,请选登陆");
        }
        //校验是否为管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            return iOrderService.manageDetail(orderNo);

        } else {
            return ServerResponse.createByErrorMessage("需要管理员权限");
        }
    }
    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderSearch(HttpSession session, Long orderNo, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆,请选登陆");
        }
        //校验是否为管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            return iOrderService.manageSearch(orderNo, pageNum, pageSize);

        } else {
            return ServerResponse.createByErrorMessage("需要管理员权限");
        }
    }

    @RequestMapping("send_goods.do")
    @ResponseBody
    public ServerResponse<String> orderSendGoods(HttpSession session, Long orderNo) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆,请选登陆");
        }
        //校验是否为管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //是管理员
            return iOrderService.manageSendGoods(orderNo);

        } else {
            return ServerResponse.createByErrorMessage("需要管理员权限");
        }
    }


}
