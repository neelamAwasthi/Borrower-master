package com.borrower.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.borrower.conf.port.adapter.aspect.Loggable;
import com.borrower.domain.model.BorrowerPlan;
import com.borrower.domain.model.BorrowerPlanRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * @author neelam
 *
 */

@Slf4j
@Service
@Loggable
public class BorrowerPlanService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BorrowerPlanService.class);
	/**
	 * Calculation of the borrower plan according to the input parameters.
	 * 
	 * @param loanAmount
	 * @param nominalRate
	 * @param duration
	 * @param startDate
	 * @return the list of plans.
	 */
	public List<BorrowerPlan> borrowerPlanCalculation(BorrowerPlanRequest request){
		LOGGER.info("calculate borrower plan");
		List<BorrowerPlan> plans = new ArrayList<BorrowerPlan>();
		double loanAmount = Double.parseDouble(request.getLoanAmount());
		double duration = request.getDuration();
		while (loanAmount > 0) {
			double mensualyRate = (Double.parseDouble(request.getNominalRate()) / 100) / 12;
			double annuity = loanAmount * mensualyRate / (1 - Math.pow(1 + mensualyRate, -duration));
			annuity = Math.round(annuity * 100.0) / 100.0;
			
			double interest = Double.parseDouble(request.getNominalRate()) * 30 * loanAmount / 360 / 100;
			interest = Math.round(interest * 100.0) / 100.0;
			
			double principalAmount = annuity - interest;
			principalAmount = Math.round(principalAmount * 100.0) / 100.0;
			
			double remainingOutstandingPrincipal = loanAmount - principalAmount;
			remainingOutstandingPrincipal = Math.round(remainingOutstandingPrincipal * 100.0) / 100.0;
			
			LocalDateTime startDate = LocalDateTime.parse(request.getStartDate(), DateTimeFormatter.ISO_DATE_TIME);
	        
			BorrowerPlan plan = buildBorrowerPlanObject(loanAmount, startDate, annuity, interest, principalAmount,
					remainingOutstandingPrincipal);
			plans.add(plan);
			loanAmount = remainingOutstandingPrincipal;
			duration = duration - 1;
			startDate = startDate.plusMonths(1);
		}
		return plans;
	}

	private BorrowerPlan buildBorrowerPlanObject(double loanAmount, LocalDateTime startDate, double annuity,
			double interest, double principalAmount, double remainingOutstandingPrincipal) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);

		return BorrowerPlan.builder().borrowerPaymentAmount(annuity).initialOutstandingPrincipal(loanAmount)
				.date(startDate.format(formatter)).interest(interest).principal(principalAmount)
				.remainingOutstandingPrincipal(remainingOutstandingPrincipal).build();
	}
}
