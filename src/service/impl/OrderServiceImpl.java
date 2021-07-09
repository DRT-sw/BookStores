package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Book;
import bean.Cart;
import bean.CartItem;
import bean.Order;
import bean.OrderItem;
import bean.Page;
import dao.BookDao;
import dao.OrderDao;
import dao.OrderItemDao;
import dao.impl.BookDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemDaoImpl;
import service.OrderService;

public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao;
	private OrderItemDao orderitemDao;
	private BookDao bookDao;

	public OrderServiceImpl() {
		super();
		
		orderDao = new OrderDaoImpl();
		orderitemDao = new OrderItemDaoImpl();
		bookDao = new BookDaoImpl();
	}

	@Override
	public String saveOrder(Cart cart, int userId) throws Exception {
		// 创建订单对象
		Order order = new Order();
		order.setCreateTime(new Date());
		order.setTotalMoney(cart.getTotalMoney());
		order.setUserId(userId);
		order.setStatus(0);
		// 生成订单号
		String orderId = System.currentTimeMillis() + "" + userId;
		order.setOrderId(orderId);
		// 保存订单
		orderDao.saveOrder(order);
		
		// 遍历购物车中有每一个商品
		List<OrderItem> items = new ArrayList<OrderItem>();
		for (CartItem cartItem : cart.getItems().values()) {
			// 生成订单项
			OrderItem orderItem = new OrderItem(0, cartItem.getName(), cartItem.getCount(),
					cartItem.getPrice(), cartItem.getTotalMoney(), orderId);
//			// 保存订单项
//			orderitemDao.saveOrderItem(orderItem);
			items.add(orderItem);
			// 修改图书的库存和销量
			Book book = bookDao.queryBookById(cartItem.getId());
			book.setStock( book.getStock() - cartItem.getCount() );
			book.setSales( book.getSales() + cartItem.getCount() );
			bookDao.update(book);
		}
		// 批量插入
		orderitemDao.batchSaveOrderItem(items);
		// 清空购物车
		cart.clear();
		
		return orderId;
	}

	@Override
	public List<Order> queryMyOrders(int userId) throws Exception {
		// 查找自己的订单
		return orderDao.queryMyOrders(userId);
	}

	@Override
	public List<OrderItem> queryOrderItems(String orderId) throws Exception {
		// 查找某个订单的订单项
		return orderitemDao.queryOrderItems(orderId);
	}

	@Override
	public List<Order> queryAllOrders() throws Exception {
		// 查询所有订单
		return orderDao.queryAllOrders();
	}

	@Override
	public void sendOrder(String orderId) throws Exception {
		// 修改订单状态为已发货
		orderDao.updateOrderStatus(1, orderId);
	}

	@Override
	public void receivedOrder(String orderId) throws Exception {
		// 修改订单状态为已发货
		orderDao.updateOrderStatus(2, orderId);
	}

	@Override
	public Page<Order> page(long pageNo, long pageSize) {
		Page<Order> orderPage = new Page<Order>();
		// 设置一页显示记录数
		orderPage.setPageSize(pageSize);
		
		// 总的记录数 select count(*) from t_book;
		long totalCount = bookDao.queryForPageTotalCount();
		orderPage.setTotalCount(totalCount);
		// 总的页数。公式： 总记录数 / 每页显示记录数 
		long pageTotal = totalCount / orderPage.getPageSize();
		/**
		 * 假设，我有8条记录。每页显示4条， 一共有2页 8 / 4
		 * 假设，我有9条记录。每页显示4条，一共有  1,2,3,4,   5,6,7,8,     9  
		 */
		if (totalCount % pageSize != 0) {
			pageTotal += 1;
		}
		orderPage.setPageTotal(pageTotal);
		
		// 设置分页，的当前页码
		orderPage.setPageNo(pageNo);
		
		long begin = ( orderPage.getPageNo() - 1 ) * orderPage.getPageSize();
		long size = orderPage.getPageSize();
		// 执行select * from t_book limit begin , size;
		List<Order> items = orderDao.queryForPageItems(begin , size);
		// 设置当页显示的数据集合
		orderPage.setItems(items);
		
		return orderPage;
	}

}