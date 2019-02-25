package com.cod4man.eleme.servlet;

import com.cod4man.eleme.pojo.Foods;
import com.cod4man.eleme.pojo.Restaurant;
import com.cod4man.eleme.service.FoodsService;
import com.cod4man.eleme.service.RestaurantService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class RestaurantFindServlet
 */
@WebServlet("/restaurant.do")
public class RestaurantFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("ApplicationContext.xml");
    private static RestaurantService rd =
            (RestaurantService) applicationContext.getBean("RestaurantService");
    private static FoodsService fd =
            (FoodsService) applicationContext.getBean("FoodsService");
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String restaurantName = null;
		//定义储存对象的集合容器
		List<Restaurant> restList = null;
		List<Foods> foodsList = null;
		String info = request.getParameter("info");
		switch (info) {
			case "findAll":
				//调用查询全部方法
				restList = rd.findAllRestauran();
				request.setAttribute("restList", restList);
				request.getRequestDispatcher("/pages/restaurants.jsp").forward(request, response);
				break;
			case "findById":
				String id = request.getParameter("id");
				foodsList = fd.findAllFoodsById(id);
				request.setAttribute("foodsList", foodsList);
				restList = rd.findRestauran_byId(id);
				restaurantName = restList.get(0).getRestaurantName();
				request.setAttribute("restaurantName", restaurantName);
				request.getRequestDispatcher("/pages/restaurant.jsp").forward(request, response);
				break;
			case "findByName":
				String name = request.getParameter("name");
				foodsList =fd.findAllFoodsByName(name);
				request.setAttribute("foodsList", foodsList);
				restList = rd.findRestauran_ByName(name);
				restaurantName = restList.get(0).getRestaurantName();
				request.setAttribute("restaurantName", restaurantName);
				request.getRequestDispatcher("/pages/restaurant.jsp").forward(request, response);
				break;
				default:break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}