package com.online.orderapp.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.online.orderapp.dto.BillResponse;
import com.online.orderapp.dto.PaymentDto;
import com.online.orderapp.dto.orderDto.OrderItemRequest;
import com.online.orderapp.dto.orderDto.OrderRequest;
import com.online.orderapp.entity.Food;
import com.online.orderapp.entity.Order;
import com.online.orderapp.entity.OrderItem;
import com.online.orderapp.entity.Restaurant;
import com.online.orderapp.entity.User;
import com.online.orderapp.exception.PaymentFailedException;
import com.online.orderapp.repository.FoodRepository;
import com.online.orderapp.repository.OrderRepository;
import com.online.orderapp.repository.UserRepository;
import com.online.orderapp.service.OrderService;
import com.online.orderapp.service.RestaurantService;
//import com.online.orderapp.service.UserService;
import com.online.orderapp.util.OrderStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {

	
	private final RestaurantService restaurantService;
	private final OrderRepository orderRepository;
	private final FoodRepository foodRepository;
	
	private final UserRepository userRepository;
	
	
	@Override
	public BillResponse generatedBill(OrderRequest orderRequest) {
		Restaurant restaurant = restaurantService.fetchById(orderRequest.getRestaurantId());
		StringBuilder summary = new StringBuilder();
		float totalPrice = 0;
		
		for(OrderItemRequest orderItem : orderRequest.getOrderItems()) {
			Food food =foodRepository.findById(orderItem.getFoodId())
					.orElseThrow(()-> new NoSuchElementException("Unable to find food"));
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
			
			//set user to order
//			User user = userService.getUser(payment.getUserId());
			User user = userRepository.findById(payment.getUserId()).orElseThrow(()-> new NoSuchElementException("User not found"));
			order.setUser(user);
			
			List<OrderItem> items = new ArrayList<>();
			double totalPrice=0;
			
			for(OrderItemRequest request : payment.getOrderItems()) {
				Food food =foodRepository.findById(request.getFoodId())
						.orElseThrow(()-> new NoSuchElementException("Unable to find food"));
				
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


	@Override
	public void deleteOrder(Integer id) {
		// TODO Auto-generated method stub
		Order order = getOrder(id);
		orderRepository.delete(order);
	}


	@Override
	public Order getOrder(Integer id) {
		// TODO Auto-generated method stub
		Optional<Order> order = orderRepository.findById(id);
		if(order.isPresent()) {
			return order.get();
		}else {
			throw new NoSuchElementException("Order with Id : "+id+" does not exist");
		}
	}


	@Override
	public Order updateStatusByAdmin(OrderStatus status, Integer id) {
		Order order = getOrder(id);
		order.setStatus(status);
		return orderRepository.save(order);
	}


	@Override
	public String cancelOrder(Integer id) {
		// TODO Auto-generated method stub
		Order order = getOrder(id);
		order.setStatus(OrderStatus.CANCELLED);
		return "Order with Id : "+id+" got cancelled, your money will be refunded in two days";
	}
}
