package com.online.orderapp.service.implementation;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.online.orderapp.dto.foodDto.FoodRequestDto;
import com.online.orderapp.dto.foodDto.FoodResponseDto;
import com.online.orderapp.entity.Food;
import com.online.orderapp.entity.Restaurant;
import com.online.orderapp.mapper.FoodMapper;
import com.online.orderapp.repository.FoodRepository;
import com.online.orderapp.repository.RestaurantRepository;
import com.online.orderapp.service.FoodService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodServiceImplementation implements FoodService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;
    


	
	@Override
	public FoodResponseDto createFood(FoodRequestDto request) {
		
		Food response= foodRepository.save(foodMapper.toEntity(request));
		return foodMapper.toDto(response);
	}

	@Override
	public FoodResponseDto getFoodById(Integer id) {
		// TODO Auto-generated method stub
		Food response= foodRepository.findById(id).orElseThrow(()->new NoSuchElementException("Food is not present"));
		return foodMapper.toDto(response);
	}

	@Override
	public Page<FoodResponseDto> getAllFood(int pageNum, int pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Food> page = foodRepository.findAll(pageable);
		return page.map(foodMapper::toDto);
	}

	@Override
	public FoodResponseDto updateFood(FoodRequestDto request, Integer id) {
		Food fetchedFood = foodRepository.findById(id)
				.orElseThrow(()->new NoSuchElementException("Unable to find food with id :"+ id));
		Food food = foodMapper.toEntity(request);
		
		if(fetchedFood!=null) {
			fetchedFood.setFoodName(food.getFoodName());
			fetchedFood.setDescription(food.getDescription());
			fetchedFood.setPrice(food.getPrice());
			foodRepository.save(fetchedFood);
		}
		return foodMapper.toDto(fetchedFood);
	}

	@Override
	public void deleteFood(Integer id) {
		Food food = foodRepository.findById(id)
				.orElseThrow(()->new NoSuchElementException("Unable to find food with id :"+ id));
		List<Restaurant> restaurants = food.getRestaurants();
		
		if(restaurants.size()==0) {
			foodRepository.delete(food);
			return;
		}
		
		for(Restaurant restaurant : restaurants) {
			restaurant.getFood().remove(food);
		}
		restaurantRepository.saveAll(restaurants);
		foodRepository.delete(food);
	}

	@Override
	public String uploadImage(MultipartFile file, Integer id) throws IOException {
		// TODO Auto-generated method stub
		
		byte[] image = file.getBytes();
		Food food = foodRepository.findById(id)
			.orElseThrow(()-> new NoSuchElementException("Restaurant is not available with id : "+ id));
		
		food.setFoodImage(image);
		foodRepository.save(food);
		
		return "Image Uploaded";
	}

}
