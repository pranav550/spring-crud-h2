package in.ashokit.controller;

import java.util.List;
import java.util.Map;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.jboss.logging.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.constants.AppConstants;
import in.ashokit.entity.Plan;
import in.ashokit.props.AppProperties;
import in.ashokit.service.PlanService;

@RestController
public class PlanRestController {
	
   private PlanService planService;
   private AppProperties appProps;
   
   private Map<String,String> messages;
    
    public PlanRestController(PlanService planService,AppProperties appProps) {
    	this.planService = planService;
    	this.messages = appProps.getMessages();
    	System.out.println(this.messages);
    }
    
    @GetMapping("/categories")
    public ResponseEntity<Map<Integer, String>> planCategory(){
    	Map<Integer, String> categories = planService.getPlanCategory();
    	return new ResponseEntity<>(categories,HttpStatus.OK);
    }
    
    @PostMapping("/plan")
    public ResponseEntity<String> savePlan(@RequestBody Plan plan){
    	String responseMsg=AppConstants.EMPTY_STR;
    	boolean isSaved =  planService.savePlan(plan);
    	
    	if(isSaved) {
    		responseMsg= messages.get(AppConstants.PLAN_SAVE_SUCC);
    	}else {
    		responseMsg= messages.get(AppConstants.PLAN_SAVE_FAIL);
    	}
    	
    	return new ResponseEntity<>(responseMsg,HttpStatus.CREATED);
    	
    }
    
    @GetMapping("/plans")
    public ResponseEntity<List<Plan>> plans(){
    List<Plan> allPlans = planService.getAllPlans();
    return new ResponseEntity<>(allPlans,HttpStatus.OK);
    }
    
    @GetMapping("/plan/{planId}")
    public ResponseEntity<Plan> editPlan(@PathVariable Integer planId){
     Plan plan = planService.getPlanById(planId);
     return new ResponseEntity<>(plan, HttpStatus.OK);
    }
    
    @DeleteMapping("/plan/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
      boolean isDeleted = planService.deletePlanById(planId);
      String msg = AppConstants.EMPTY_STR;
  	  if(isDeleted) {
    	  msg=messages.get(AppConstants.PLAN_DELETE_SUCC);
      }else {
    	  msg=messages.get(AppConstants.PLAN_DELETE_FAIL);
      }
      return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
    @PutMapping("plan")
    public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
    	boolean isUpdate = planService.updatePlan(plan);
    	String msg=AppConstants.EMPTY_STR;
    	if(isUpdate) {
    		msg=messages.get(AppConstants.PLAN_UPDATE_SUCC);
    	}else {
    		msg=messages.get(AppConstants.PLAN_UPDATE_FAIL);
    	}
    	return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    @PutMapping("/status-change/{planId}/{status}")
    public ResponseEntity<String> StatusChange(@PathVariable Integer planId, String status){
    	String msg = AppConstants.EMPTY_STR;
    	boolean isStatusChanged = planService.planStatusChange(planId, status);
    	
    	if(isStatusChanged) {
    		msg = messages.get(AppConstants.PLAN_STATUS_CHANGE);
    	}else {
    		msg = messages.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
    	}
    	return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
