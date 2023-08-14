package com.zlc.springboot.mapper;

import com.zlc.springboot.model.InitOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InitOrderMapper {
    int deleteByPrimaryKey(String outTradeNo);

    int insert(InitOrder record);

    int insertSelective(InitOrder record);

    InitOrder selectByPrimaryKey(String outTradeNo);

    int updateByPrimaryKeySelective(InitOrder record);

    int updateByPrimaryKey(InitOrder record);

    //根据订单号去修改订单的支付状态
    int updateStateById(String outTradeNo);

    //1.根据softid查询对应的文件路径
    String FilePathBySoftID(String orderId);

    //2.根据订单号查询软件编号.
    public Integer SelectIdByOrderID(String orderID);

    //3.查询订单状态为0的数量.
    Integer ZeroByOrderState();

    //4.查询订单状态为1的数量.
    Integer OneByOrderState();

    //5. 查询已下单订单总数和已下单订单总额.
    Map<Object,Object> selectNumAndMoney();

    //6. 查询订单总数和 应该生成分页插件的数量
    Integer selectPageOfAll();
    //7. 分页查询所有订单
    List<InitOrder> selectAllOfPage(Integer start, Integer end);
    //8. 查询所有订单数量以及金钱.
    Map<Object,Object> selectAllNumAndMoney();
    //9. ajxa查询 已下单文件ID和moeny
    List<Map<Object,Object>> selectFileAllOfMoney();
}