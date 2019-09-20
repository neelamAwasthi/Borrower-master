package com.borrower.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author neelam
 *
 */

@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class BorrowerPlan {
	private double borrowerPaymentAmount;
	private String date;
	double initialOutstandingPrincipal;
	double interest;
	double principal;
	double remainingOutstandingPrincipal;

}
