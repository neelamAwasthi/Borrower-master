/**
 * 
 */
package com.borrower.port.adapter.rest.endpoint;

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.borrower.domain.model.BorrowerPlanRequest;
import com.borrower.domain.model.BorrowerPlanResponse;
import com.borrower.service.BorrowerPlanService;

/**
 * @author neelam
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BorrowerPlanResourceTest {
	
	@Autowired
	@InjectMocks
	private BorrowerPlanResource borrowerPlanResource;
	

	@MockBean(name = "borrowerPlanService")
	private BorrowerPlanService borrowerPlanService;

	BorrowerPlanRequest  borrowerPlanRequest;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	/**
	 * Test method for {@link com.borrower.port.adapter.rest.endpoint.BorrowerPlanResource#borrowerPlanCalculation(com.borrower.domain.model.BorrowerPlanRequest)}.
	 * @throws Exception 
	 */
	@Test
	public void testBorrowerPlanCalculation() throws Exception {
		borrowerPlanRequest = borrowerPlanRequest.builder().loanAmount("5000")
		.nominalRate("5.0").duration(24).startDate("2018-01-01T00:00:01Z").build();
		ResponseEntity<BorrowerPlanResponse> response = 
				borrowerPlanResource.borrowerPlanCalculation(borrowerPlanRequest);
		assertThat((response.getStatusCode().value())).isEqualTo(HttpStatus.OK_200);
	}
	

}
