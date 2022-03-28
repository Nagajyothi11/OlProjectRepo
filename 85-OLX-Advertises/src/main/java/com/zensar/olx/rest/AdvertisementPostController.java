package com.zensar.olx.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zensar.olx.bean.AdvertisementPost;
import com.zensar.olx.bean.AdvertisementStatus;
import com.zensar.olx.bean.Category;
import com.zensar.olx.bean.NewAdvertisementPostRequest;
import com.zensar.olx.bean.NewAdvertisementPostResponse;
import com.zensar.olx.bean.OlxUser;
import com.zensar.olx.service.AdvertisementPostService;

@RestController
public class AdvertisementPostController {
	@Autowired
	AdvertisementPostService service;
    /**
     * 8.This is the rest specification for posting new advertisement.
     * @param request
     * @param userName
     * @return
     */
	@PostMapping("/advertise/{un}")
	public NewAdvertisementPostResponse add(@RequestBody NewAdvertisementPostRequest request,
			@PathVariable(name = "un") String userName) {
		AdvertisementPost post = new AdvertisementPost();
		post.setTitle(request.getTitle());
		post.setPrice(request.getPrice());
		post.setDescription(request.getDescription());
		System.out.println(request);
		int categoryId = request.getCategoryId();
		System.out.println(categoryId);
		RestTemplate restTemplate = new RestTemplate();
		Category category;
		String url = "http://localhost:9052/advertise/getcategory/" + categoryId;
		category = restTemplate.getForObject(url, Category.class);
		System.out.println(category);
		post.setCategory(category);

		url = "http://localhost:9051/user/find/" + userName;
		OlxUser olxUser = restTemplate.getForObject(url, OlxUser.class);
		post.setOlxUser(olxUser);

		AdvertisementStatus adverstisementStatus = new AdvertisementStatus(1, "OPEN");
		post.setAdvertisementStatus(adverstisementStatus);

		AdvertisementPost advertisementPost = this.service.addAdvertisement(post);

		NewAdvertisementPostResponse response = new NewAdvertisementPostResponse();
		response.setId(advertisementPost.getId());
		response.setTitle(advertisementPost.getTitle());
		response.setPrice(advertisementPost.getPrice());
		response.setCategory(advertisementPost.getCategory().getName());
		response.setDescription(advertisementPost.getDescription());
		response.setUserName(advertisementPost.getOlxUser().getUserName());
		response.setStatus(advertisementPost.getAdvertisementStatus().getStatus());
		return response;
	}
     /**
      * 9.This is the rest specification for update the advertisements.
      * @param request
      * @param id
      * @param userName
      * @return
      */
	@PutMapping("/advertise/{aid}/{userName}")
	public NewAdvertisementPostResponse f2(@RequestBody NewAdvertisementPostRequest request,
			@PathVariable(name = "aid") int id, @PathVariable(name = "userName") String userName) {
		AdvertisementPost post = this.service.getAdvertisementById(id);
		post.setTitle(request.getTitle());
		post.setDescription(request.getDescription());
		post.setPrice(request.getPrice());
		int categoryId = request.getCategoryId();

		RestTemplate restTemplate = new RestTemplate();
		Category category;
		String url = "http://localhost:9052/advertise/getcategory/" + categoryId;
		category = restTemplate.getForObject(url, Category.class);
		System.out.println(category);
		post.setCategory(category);

		url = "http://localhost:9051/user/find/" + userName;
		OlxUser olxUser = restTemplate.getForObject(url, OlxUser.class);
		post.setOlxUser(olxUser);

		url = "http://localhost:9052/advertise/status/" + request.getStatusId();
		System.out.println(url);
		AdvertisementStatus advertisementStatus;
		advertisementStatus = restTemplate.getForObject(url, AdvertisementStatus.class);
		post.setAdvertisementStatus(advertisementStatus);

		AdvertisementPost advertisementPost = this.service.updateAdvertisement(post);

		NewAdvertisementPostResponse postResponse = new NewAdvertisementPostResponse();
		postResponse.setId(advertisementPost.getId());
		postResponse.setTitle(advertisementPost.getTitle());
		postResponse.setDescription(advertisementPost.getDescription());
		postResponse.setPrice(advertisementPost.getPrice());
		postResponse.setUserName(advertisementPost.getOlxUser().getUserName());
		postResponse.setCategory(advertisementPost.getCategory().getName());
		postResponse.setCreatedDate(advertisementPost.getCreatedDate());
		postResponse.setModifiedDate(advertisementPost.getModifiedDate());
		postResponse.setStatus(advertisementPost.getAdvertisementStatus().getStatus());

		return postResponse;

	}
    /**
     * 10.This is the rest specification for read all advertisement.
     * @param userName
     * @return
     */
	@GetMapping("/user/advertise/{userName}")
	public List<NewAdvertisementPostResponse> f3(@PathVariable(name = "userName") String userName) {
		List<AdvertisementPost> advPost = this.service.getAllAdvertisements();
		RestTemplate restTemplate = new RestTemplate();
		List<AdvertisementPost> filterList = new ArrayList<>();
		String url = "http://localhost:9051/user/find/" + userName;
		OlxUser olxUser = restTemplate.getForObject(url, OlxUser.class);
		// ;
		for (AdvertisementPost post : advPost) {
			Category category;
			url = "http://localhost:9052/advertise/getcategory/" + post.getCategory().getId();
			category = restTemplate.getForObject(url, Category.class);
			post.setCategory(category);
			System.out.println("Category-------" + post);
			url = "http://localhost:9052/advertise/status/" + post.getAdvertisementStatus().getId();
			AdvertisementStatus advertisementStatus;
			advertisementStatus = restTemplate.getForObject(url, AdvertisementStatus.class);
			post.setAdvertisementStatus(advertisementStatus);
			System.out.println("AdvertisementStatus" + post);
			if (olxUser.getOlxUserId() == post.getOlxUser().getOlxUserId()) {
				post.setOlxUser(olxUser);
				filterList.add(post);
			}
		}
		System.out.println("List--------------" + filterList);
		List<NewAdvertisementPostResponse> responseList = new ArrayList<>();
		for (AdvertisementPost advertisementPost : filterList) {
			NewAdvertisementPostResponse postRespone = new NewAdvertisementPostResponse();
			postRespone.setId(advertisementPost.getId());
			postRespone.setTitle(advertisementPost.getTitle());
			postRespone.setDescription(advertisementPost.getDescription());
			postRespone.setPrice(advertisementPost.getPrice());
			postRespone.setUserName(advertisementPost.getOlxUser().getUserName());
			postRespone.setCategory(advertisementPost.getCategory().getName());
			postRespone.setCreatedDate(advertisementPost.getCreatedDate());
			postRespone.setModifiedDate(advertisementPost.getModifiedDate());
			postRespone.setStatus(advertisementPost.getAdvertisementStatus().getStatus());
			responseList.add(postRespone);
		}
		return responseList;
	}
	/**
	 *11. This is the rest specification for reads the specific advertisement.
	 * @param request
	 * @param id
	 * @param userName
	 * @return
	 */

	@GetMapping("/user/advertise/{advertiseId}/{userName}")
	public NewAdvertisementPostResponse f4(@RequestBody NewAdvertisementPostRequest request,
			@PathVariable(name = "advertiseId") int id, @PathVariable(name = "userName") String userName) {

		AdvertisementPost post = this.service.getAdvertisementById(id);

		RestTemplate restTemplete = new RestTemplate();

		String url = "http://localhost:9051/user/find/" + userName;
		OlxUser olxUser = restTemplete.getForObject(url, OlxUser.class);
		post.setOlxUser(olxUser);

		url = "http://localhost:9052/advertise/getcategory/" + request.getCategoryId();
		Category category = restTemplete.getForObject(url, Category.class);
		post.setCategory(category);

		url = "http://localhost:9052/advertise/status/" + request.getStatusId();
		AdvertisementStatus advertisementStatus;
		advertisementStatus = restTemplete.getForObject(url, AdvertisementStatus.class);
		post.setAdvertisementStatus(advertisementStatus);

		AdvertisementPost advertisementPost = this.service.getAdvertisementById(id);

		NewAdvertisementPostResponse postResponse;
		postResponse = new NewAdvertisementPostResponse();

		postResponse.setId(advertisementPost.getId());
		postResponse.setTitle(advertisementPost.getTitle());
		postResponse.setDescription(advertisementPost.getDescription());
		postResponse.setPrice(advertisementPost.getPrice());
		postResponse.setUserName(advertisementPost.getOlxUser().getUserName());
		postResponse.setCategory(advertisementPost.getCategory().getName());
		postResponse.setCreatedDate(advertisementPost.getCreatedDate());
		postResponse.setModifiedDate(advertisementPost.getModifiedDate());
		postResponse.setStatus(advertisementPost.getAdvertisementStatus().getStatus());

		return postResponse;

	}
    /**
     * 12.This is the rest specification for deletes the specific advertisement
     * @param id
     * @param userName
     * @return
     */
	@DeleteMapping("/user/advertise/{advertiseId}/{userName}")
	public boolean deleteAdvertisementById(@PathVariable(name = "advertiseId") int id,
			@PathVariable(name = "userName") String userName) {

		boolean res = false;
		List<AdvertisementPost> allPosts = this.service.getAllAdvertisements();

		RestTemplate restTemplate = new RestTemplate();
		String url = null;

		for (AdvertisementPost advertisementPost : allPosts) {

			url = "http://localhost:9051/user/find/" + userName;
			OlxUser olxUser = restTemplate.getForObject(url, OlxUser.class);
			if (olxUser.getUserName().equals(userName)) {

				if (advertisementPost.getId() == id) {

					res = this.service.deleteAdvertisementPost(advertisementPost);
				}
			}

		}
		return res;
	}
	/**
	 * 13.This is the rest specification for Search Advertisement based upon given filter criteria.
	 * @param searchText
	 * @return
	 */
	@GetMapping("/advertise/search/filtercriteria/{searchAll}")
	public List<NewAdvertisementPostResponse> searchBasedOnAll(@PathVariable(name="searchAll") String searchText) {
		List<AdvertisementPost> allPost=this.service.getAllAdvertisements();
		RestTemplate restTemplate=new RestTemplate();
		for(AdvertisementPost advertisementPost:allPost)
		{
			String url = null;
			url = "http://localhost:9051/user/" + advertisementPost.getOlxUser().getOlxUserId();
			OlxUser olxUser = restTemplate.getForObject(url, OlxUser.class);
			advertisementPost.setOlxUser(olxUser);

			Category category;
			url = "http://localhost:9052/advertise/getcategory/" + advertisementPost.getCategory().getId();
			category = restTemplate.getForObject(url, Category.class);
			advertisementPost.setCategory(category);

			url = "http://localhost:9052/advertise/status/" + advertisementPost.getAdvertisementStatus().getId();
			AdvertisementStatus advertisementStatus;
			advertisementStatus = restTemplate.getForObject(url, AdvertisementStatus.class);
			advertisementPost.setAdvertisementStatus(advertisementStatus);
		}
		List<AdvertisementPost>filterPosts=new ArrayList<>();
		for(AdvertisementPost advertisementPost:allPost)
		{
			if((advertisementPost.getCategory().getName().contains(searchText))||
					(advertisementPost.getTitle().contains(searchText))
					||(advertisementPost.getDescription().contains(searchText))||
					(advertisementPost.getAdvertisementStatus().getStatus().contains(searchText))||
					(advertisementPost.getCreatedDate().toString().contains(searchText))||
					(advertisementPost.getModifiedDate().toString().contains(searchText))
					)
			{
				filterPosts.add(advertisementPost);
			}
		}

		List<NewAdvertisementPostResponse> responce=new ArrayList<>();
		for(AdvertisementPost advertisementPost:filterPosts)
		{
			NewAdvertisementPostResponse postRespone = new NewAdvertisementPostResponse();
			

			postRespone.setId(advertisementPost.getId());
			postRespone.setTitle(advertisementPost.getTitle());
			postRespone.setUserName(advertisementPost.getOlxUser().getUserName());
			postRespone.setDescription(advertisementPost.getDescription());
			postRespone.setPrice(advertisementPost.getPrice());
			postRespone.setCategory(advertisementPost.getCategory().getName());
			postRespone.setCreatedDate(advertisementPost.getCreatedDate());
			postRespone.setModifiedDate(advertisementPost.getModifiedDate());
			postRespone.setStatus(advertisementPost.getAdvertisementStatus().getStatus());
			responce.add(postRespone);
		}
		return responce;

	}
	/**
	 * 14.This is the rest specification for searching the advertise
	 * @param searchText
	 * @return
	 */
	@GetMapping("/advertise/{search}")
	public List<NewAdvertisementPostResponse> f7(@PathVariable(name="search")String searchText) {
	List<AdvertisementPost>allPost=this.service.getAllAdvertisements();
	System.out.println(allPost);
	RestTemplate restTemplate=new RestTemplate();
	for(AdvertisementPost advertisementPost:allPost)
	{
	String url = null;
	url = "http://localhost:9051/user/" + advertisementPost.getOlxUser().getOlxUserId();
	OlxUser olxUser = restTemplate.getForObject(url, OlxUser.class);
	advertisementPost.setOlxUser(olxUser); Category category;
	url = "http://localhost:9052/advertise/getcategory/" + advertisementPost.getCategory().getId();
	category = restTemplate.getForObject(url, Category.class);
	advertisementPost.setCategory(category); url = "http://localhost:9052/advertise/status/" + advertisementPost.getAdvertisementStatus().getId();
	AdvertisementStatus advertisementStatus;
	advertisementStatus = restTemplate.getForObject(url, AdvertisementStatus.class);
	advertisementPost.setAdvertisementStatus(advertisementStatus);
	}
	List<AdvertisementPost>filterPosts=new ArrayList<>();
	for(AdvertisementPost advertisementPost:allPost)
	{
	if((advertisementPost.getCategory().getName().toLowerCase().contains(searchText.toLowerCase())||
	(advertisementPost.getTitle().toLowerCase().contains(searchText.toLowerCase()))
	||(advertisementPost.getDescription().toLowerCase().contains(searchText.toLowerCase()))||
	(advertisementPost.getAdvertisementStatus().getStatus().toLowerCase().contains(searchText.toLowerCase()))
	))
	{
	filterPosts.add(advertisementPost);
	}
	}
	List<NewAdvertisementPostResponse> responce=new ArrayList<>();
	for(AdvertisementPost advertisementPost:filterPosts)
	{
	NewAdvertisementPostResponse postRespone = new NewAdvertisementPostResponse(); postRespone.setId(advertisementPost.getId());
	postRespone.setTitle(advertisementPost.getTitle());
	postRespone.setUserName(advertisementPost.getOlxUser().getUserName());
	postRespone.setDescription(advertisementPost.getDescription());
	postRespone.setPrice(advertisementPost.getPrice());
	postRespone.setCategory(advertisementPost.getCategory().getName());
	postRespone.setCreatedDate(advertisementPost.getCreatedDate());
	postRespone.setModifiedDate(advertisementPost.getModifiedDate());
	postRespone.setStatus(advertisementPost.getAdvertisementStatus().getStatus());
	responce.add(postRespone);
	}
	return responce;
	}
	/**
	 *15. This is the rest specification for return advertise details using advertiseId.
	 * @param id
	 * @return
	 */
	@GetMapping("/user/advertiseById/{aid}")
	public NewAdvertisementPostResponse getAdvertismentByUserId(@PathVariable(name = "aid") int id) {
		AdvertisementPost advertisementPost = this.service.getAdvertisementById(id);
		RestTemplate restTemplate = new RestTemplate();

		String url = null;
		url = "http://localhost:9051/user/" + advertisementPost.getOlxUser().getOlxUserId();
		OlxUser olxUser = restTemplate.getForObject(url, OlxUser.class);
		advertisementPost.setOlxUser(olxUser);

		Category category;
		url = "http://localhost:9052/advertise/getcategory/" + advertisementPost.getCategory().getId();
		category = restTemplate.getForObject(url, Category.class);
		advertisementPost.setCategory(category);

		url = "http://localhost:9052/advertise/status/" + advertisementPost.getAdvertisementStatus().getId();
		AdvertisementStatus advertisementStatus;
		advertisementStatus = restTemplate.getForObject(url, AdvertisementStatus.class);
		advertisementPost.setAdvertisementStatus(advertisementStatus);

		System.out.println(advertisementPost);

		NewAdvertisementPostResponse postRespone = new NewAdvertisementPostResponse();

		postRespone.setId(advertisementPost.getId());
		postRespone.setTitle(advertisementPost.getTitle());
		postRespone.setUserName(advertisementPost.getOlxUser().getUserName());
		postRespone.setDescription(advertisementPost.getDescription());
		postRespone.setPrice(advertisementPost.getPrice());
		postRespone.setCategory(advertisementPost.getCategory().getName());
		postRespone.setCreatedDate(advertisementPost.getCreatedDate());
		postRespone.setModifiedDate(advertisementPost.getModifiedDate());
		postRespone.setStatus(advertisementPost.getAdvertisementStatus().getStatus());

		System.out.println(postRespone);
		return postRespone;
	}

	
}
