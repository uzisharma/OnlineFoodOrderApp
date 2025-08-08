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
		if(payment.isPaymentSuccessful()) {
			Order order = new Order();
			order.setStatus(OrderStatus.PLACED);
			
			Restaurant restaurant = restaurantService.fetchById(1);
			List<OrderItem> items = new ArrayList<>();
		}else {
			throw new PaymentFailedException();
		}
		return null;
	}
}
