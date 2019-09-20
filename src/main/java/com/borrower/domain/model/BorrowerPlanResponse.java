package com.borrower.domain.model;

import java.util.List;

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
public class BorrowerPlanResponse {
	List<BorrowerPlan> borrowerPlan;
}
