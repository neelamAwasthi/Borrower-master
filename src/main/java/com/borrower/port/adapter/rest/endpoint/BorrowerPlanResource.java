package com.borrower.port.adapter.rest.endpoint;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.borrower.conf.port.adapter.aspect.Loggable;
import com.borrower.domain.model.BorrowerPlan;
import com.borrower.domain.model.BorrowerPlanRequest;
import com.borrower.domain.model.BorrowerPlanResponse;
import com.borrower.service.BorrowerPlanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author neelam
 */

@RestController
@Api(tags = "Repayment annunity service", produces = "Calculate a repayment plan for an annuity loan.")
public class BorrowerPlanResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(BorrowerPlanResource.class);

	@Autowired
	BorrowerPlanService borrowerPlanService;

	/**
	 * 
	 * @param request
	 * @return
	 */

	@ApiOperation(value = "Get the list of borrower plan")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Current time sucess") })
	@PostMapping(value = "/generate-plan", produces = "application/json")
	@Loggable
	@ResponseBody
	public ResponseEntity<BorrowerPlanResponse> borrowerPlanCalculation(@Valid @RequestBody BorrowerPlanRequest request)
			throws Exception {
		LOGGER.info("get borrow plan");
		List<BorrowerPlan> plans;
		plans = borrowerPlanService.borrowerPlanCalculation(request);
		return ResponseEntity.ok().body(BorrowerPlanResponse.builder().borrowerPlan(plans).build());
	}
}
