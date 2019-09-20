/**
 * 
 */
package com.borrower.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.borrower.domain.model.BorrowerPlan;
import com.borrower.domain.model.BorrowerPlanRequest;

/**
 * @author neelam
 *
 */
public class BorrowerPlanServiceTest {
	
	@Autowired
	@InjectMocks
	private BorrowerPlanService borrowerPlanService;

	BorrowerPlanRequest  borrowerPlanRequest;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		borrowerPlanRequest = borrowerPlanRequest.builder().loanAmount("5000")
		.nominalRate("5.0").duration(24).startDate("2018-01-01T00:00:01Z").build();
	}

	/**
	 * Test method for {@link com.borrower.service.BorrowerPlanService#borrowerPlanCalculation(com.borrower.domain.model.BorrowerPlanRequest)}.
	 */
	@Test
	public void testBorrowerPlanCalculation() {
		List<BorrowerPlan> borrowList = borrowerPlanService.borrowerPlanCalculation(borrowerPlanRequest);
		assertNotNull(borrowList.size());
	}

}
