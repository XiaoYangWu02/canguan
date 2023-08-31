package com.it.canguan.controller;

import com.it.canguan.pojo.*;
import com.it.canguan.service.FoodOrderService;
import com.it.canguan.service.FoodService;
import com.it.canguan.service.LocationService;
import com.it.canguan.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController {

    //private List<Food> cartItems = new ArrayList<>();

    @Resource
    private FoodService foodService;
    @Resource
    private LocationService locationService;

    @Resource
    private OrderService orderService;

    @Resource
    private FoodOrderService foodOrderService;

    @GetMapping("/index")
    String index(Model model, HttpSession session){
        List<Food> foodList = foodService.getAllFood();

        Boolean loggedIn;
        Account account = (Account) session.getAttribute("accountSession");
        if(account != null){
            loggedIn = true;
        }
        else {
            loggedIn = false;
        }

        model.addAttribute("foodList", foodList);

        model.addAttribute("loggedIn", loggedIn); // 将登录状态信息传递到模板

        return "index";
    }

    @GetMapping("/toLogin")
    String toLogin(){
        return "login";
    }

    @PostMapping("/searchFood")
    String searchFood(@RequestParam("search")String searchKeyword, Model model, HttpSession session){
        List<Food> searchResults = foodService.searchFoodByName(searchKeyword);

        Boolean loggedIn;
        Account account = (Account) session.getAttribute("accountSession");
        if(account != null){
            loggedIn = true;
        }
        else {
            loggedIn = false;
        }

        model.addAttribute("foodList", searchResults);

        model.addAttribute("loggedIn", loggedIn);


        return "index";
    }

    @GetMapping("/toLocationChoose")
    String toLocationChoose(Model model, HttpSession session){

        Account account = (Account) session.getAttribute("accountSession");
        if(account != null){
            List<Location> locationList = locationService.getAllLocation();
            model.addAttribute("locationList", locationList);
            return "location-choose";
        }
        else {
            return "redirect:/main/toLogin";
        }

    }

    @PostMapping("/chooseLocation")
    String chooseLocation(@RequestParam("location")String selectedLocation, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("selectedLocation", selectedLocation);
        return "redirect:/main/toLocationChoose";
    }

    @PostMapping("/confirmTable")
    String confirmTable(@RequestParam("confirmLocation")String confirmLocation, RedirectAttributes redirectAttributes){
        if (confirmLocation == null || confirmLocation.isEmpty()) {
            return "redirect:/main/toLocationChoose";
        } else {
            redirectAttributes.addFlashAttribute("confirmLocation", confirmLocation);
            return "redirect:/main/toFoodChoose";
        }
    }

    @GetMapping("/toFoodChoose")
    String toFoodChoose(Model model, HttpSession session){

        Account account = (Account) session.getAttribute("accountSession");
        if(account == null){
            return "redirect:/main/toLogin";
        }


        List<Food> cartItems = new ArrayList<>();
        session.setAttribute("cartItems", cartItems);

        Integer cartPrice = 0;
        session.setAttribute("cartPrice", cartPrice);
        model.addAttribute("cartPrice", cartPrice);



        List<Food> foodList = foodService.getAllFood();

        model.addAttribute("foodList", foodList);

        return "food-choose";
    }

    @PostMapping("/addToCart")
    String addToCart(Model model, @RequestParam("selectedFood")String selectedFood, @RequestParam("confirmLocation")String confirmLocation, HttpSession session){
        List<Food> cartItems = (List<Food>) session.getAttribute("cartItems");

        cartItems.add(foodService.getFood(selectedFood).get(0));
        model.addAttribute("cartItems", cartItems);

        Integer cartPrice = (Integer) session.getAttribute("cartPrice") + foodService.getFood(selectedFood).get(0).getFoodAmount();
        model.addAttribute("cartPrice", cartPrice);
        session.setAttribute("cartPrice", cartPrice);

        List<Food> foodList = foodService.getAllFood();

        model.addAttribute("foodList", foodList);

        model.addAttribute("confirmLocation", confirmLocation);

        return "food-choose";
    }

    @PostMapping("/removeFromCart")
    String removeFromCart(@RequestParam("confirmLocation") String confirmLocation,
                          @RequestParam("foodIndex") int foodIndex,
                          HttpSession session, Model model) {

        // 从 session 中获取购物车列表
        List<Food> cartItems = (List<Food>) session.getAttribute("cartItems");

        String foodName;

        // 移除指定索引处的食物
        if (cartItems != null && foodIndex >= 0 && foodIndex < cartItems.size()) {
            foodName = (String) cartItems.get(foodIndex).getFoodName();
            cartItems.remove(foodIndex);


            Integer cartPrice = (Integer) session.getAttribute("cartPrice") - foodService.getFood(foodName).get(0).getFoodAmount();
            model.addAttribute("cartPrice", cartPrice);

            session.setAttribute("cartPrice", cartPrice);

        }

        model.addAttribute("cartItems", cartItems);

        // 更新购物车列表
        //session.setAttribute("cartItems", cartItems);


        List<Food> foodList = foodService.getAllFood();

        model.addAttribute("foodList", foodList);

        model.addAttribute("confirmLocation", confirmLocation);

        return "food-choose";
    }

    @GetMapping ("/placeOrder")
    String placeOrder(Model model, HttpSession session, @RequestParam("confirmLocation") String confirmLocation, RedirectAttributes redirectAttributes){
        List<Food> cartItems = (List<Food>) session.getAttribute("cartItems");
        Account account = (Account) session.getAttribute("accountSession");
        Integer cartPrice = (Integer) session.getAttribute("cartPrice");


        if(confirmLocation == null || confirmLocation.isEmpty()){
            return "redirect:/main/toLocationChoose";
        }

        //System.out.println(order.getOrderId());



        if(cartItems == null || cartItems.size() ==0 ){
            redirectAttributes.addFlashAttribute("confirmLocation", confirmLocation);
            return "redirect:/main/toFoodChoose";
        }
        else {

            Integer customerId = account.getId();

            Integer locationId = locationService.searchLocationByName(confirmLocation).get(0).getId();

            Order order = new Order();
            order.setCustomerId(customerId);
            order.setAmount(cartPrice);
            order.setLocationId(locationId);
            orderService.insertOrder(order);


            String foodStatus = "未上菜";
            for (Food food : cartItems) {
                // 在这里进行针对每个食物对象的操作
                foodOrderService.insertFoodOrder(food.getId(), foodStatus, order.getOrderId());

            }

        }


        //System.out.println(customerId +"价格"+ cartPrice +"座位"+ locationId);

        return "redirect:/main/toForWait";
    }

    @GetMapping("/toForWait")
    String toForWait(Model model, HttpSession session){
        Account account = (Account) session.getAttribute("accountSession");
        if(account == null){
            return "redirect:/main/toLogin";
        }

        Integer customerId = account.getId();
        List<Order> orderList = orderService.searchOrderByCustomerId(customerId);
        Collections.reverse(orderList);
        Order order = orderList.get(0);

        List<FoodOrder> foodOrderList = foodOrderService.getAllFoodOrder();
        List<Food> foodList = foodService.getAllFood();
        List<Location> locationList = locationService.getAllLocation();

        model.addAttribute("order", order);
        model.addAttribute("foodOrderList", foodOrderList);
        model.addAttribute("foodList", foodList);
        model.addAttribute("locationList", locationList);

        return "for-wait";
    }

    @GetMapping("/toUserCenter")
    String toUserCenter(Model model, HttpSession session){
        Account account = (Account) session.getAttribute("accountSession");
        if(account == null){
            return "redirect:/main/toLogin";
        }
        model.addAttribute("user", account);
        return "usercenter";
    }

    @GetMapping("/viewOrders")
    String viewOrders(Model model, HttpSession session){
        Account account = (Account) session.getAttribute("accountSession");
        if(account == null){
            return "redirect:/main/toLogin";
        }

        Integer customerId = account.getId();
        List<Order> orderList = orderService.searchOrderByCustomerId(customerId);

        if(orderList == null || orderList.size() ==0 ){
            return "redirect:/main/toUserCenter";
        }

        Collections.reverse(orderList);

        List<FoodOrder> foodOrderList = foodOrderService.getAllFoodOrder();
        List<Food> foodList = foodService.getAllFood();
        List<Location> locationList = locationService.getAllLocation();

        model.addAttribute("orderList", orderList);
        model.addAttribute("foodOrderList", foodOrderList);
        model.addAttribute("foodList", foodList);
        model.addAttribute("locationList", locationList);

        return "user-order";
    }

    @GetMapping("/toRegister")
    String toRegister(){
        return "register";
    }

}
