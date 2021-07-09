package service;

import java.util.List;

import bean.Book;
import bean.Cart;
import bean.Order;
import bean.OrderItem;
import bean.Page;

public interface OrderService {

	/**
	 * 订单的生成
	 * 
	 * @param cart
	 *            购物车信息
	 * @param userId
	 *            用户id信息
	 * @return 返回订单号
	 * @throws Exception 
	 */
	public String saveOrder(Cart cart, int userId) throws Exception;

	/**
	 * 我的订单列表
	 * 
	 * @param userId
	 *            用户id号
	 * @return 返回用户的订单信息
	 * @throws Exception 
	 */
	public List<Order> queryMyOrders(int userId) throws Exception;

	/**
	 * 订单详情
	 * 
	 * @param orderId
	 *            要查询的订单号
	 * @return 订单详细
	 * @throws Exception 
	 */
	public List<OrderItem> queryOrderItems(String orderId) throws Exception;

	/**
	 * 管理员--查看所有订单
	 * @throws Exception 
	 */
	public List<Order> queryAllOrders() throws Exception;

	/**
	 * 确认发货
	 * 
	 * @param orderId
	 *            发货的订单号
	 * @throws Exception 
	 */
	public void sendOrder(String orderId) throws Exception;

	/**
	 * 用户确认收货
	 * 
	 * @param orderId
	 *            收到的订单号
	 * @throws Exception 
	 */
	public void receivedOrder(String orderId) throws Exception;
	
	/**
	 * 查询一页数据
	 * @param pageNo 页数
	 * @param pageSize 每页显示的数据数量
	 * @return
	 */
	public Page<Order> page(long pageNo,long pageSize);

}
