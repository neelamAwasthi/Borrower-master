package com.borrower.domain.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class BorrowerPlanRequest {
	@NotEmpty(message = "Loan amount is empty")
	private String loanAmount;
	@NotEmpty(message = "Nominial rate is empty")
	private String nominalRate;
	@NotNull(message = "Duration is null")
	private Integer duration;
	@NotEmpty(message = "Start date is empty")
	private String startDate;
	
	
}
