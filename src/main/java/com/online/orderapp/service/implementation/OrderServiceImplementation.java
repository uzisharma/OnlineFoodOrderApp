package com.online.orderapp.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.online.orderapp.dto.BillResponse;
import com.online.orderapp.dto.OrderItemRequest;
import com.online.orderapp.dto.OrderRequest;
import com.online.orderapp.dto.PaymentDto;
import com.online.orderapp.entity.Food;
import com.online.orderapp.entity.Order;
import com.online.orderapp.entity.OrderItem;
import com.online.orderapp.entity.Restaurant;
import com.online.orderapp.exception.PaymentFailedException;
import com.online.orderapp.repository.OrderRepository;
import com.online.orderapp.service.FoodService;
import com.online.orderapp.service.OrderService;
import com.online.orderapp.service.RestaurantService;
import com.online.orderapp.util.OrderStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {

	
	private final RestaurantService restaurantService;
	private final FoodService foodService;
	private final OrderRepository orderRepository;
	
	
	@Override
	public BillResponse generatedBill(OrderRequest orderRequest) {
		Restaurant restaurant = restaurantService.fetchById(orderRequest.getRestaurantId());
		StringBuilder summary = new StringBuilder();
		float totalPrice = 0;
		
		for(OrderItemRequest orderItem : orderRequest.getOrderItems()) {
			Food food =foodService.getFoodById(orderItem.getFoodId());
			float itemPrice = food.getPrice() * orderItem.getQuantity();
			totalPrice+=itemPrice;
			summary.append(food.getFoodName())
					.append(" X ")
					.append(orderItem.getQuantity())
					.append(" = ")
					.append(itemPrice)
					.append("\n");
		}
		
		return new BillResponse(restaurant.getRestaurantName(), summary.toString(), totalPrice);
	}


	@Override
	public String payAndPlaceOrder(PaymentDto payment) {
		//simulate payment
		if(payment.isPaymentSuccessful()) {
			Order order = new Order();
			order.setStatus(OrderStatus.PLACED);
			
			Restaurant restaurant = restaurantService.fetchById(payment.getRestaurantId());
			//set restaurant to order
			order.setRestaurant(restaurant);
			
			List<OrderItem> items = new ArrayList<>();
			double totalPrice=0;
			
			for(OrderItemRequest request : payment.getOrderItems()) {
				Food food = foodService.getFoodById(request.getFoodId());
				
				OrderItem orderItem = new OrderItem();
				orderItem.setFood(food);
				orderItem.setQuantity(request.getQuantity());
				orderItem.setOrder(order);				
				items.add(orderItem);
				
				double price = food.getPrice() * request.getQuantity();
				totalPrice += price;
			}
			
			order.setTotalPrice(totalPrice);
			order.setOrderItems(items);
			orderRepository.save(order);
			return "Order has been placed";
		}
		else {
			throw new PaymentFailedException("Payment was not successful, hence order cannot be placed");
		}
	}
}
