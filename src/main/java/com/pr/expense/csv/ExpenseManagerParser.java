package com.pr.expense.csv;

import com.pr.commons.csv.CSVParser;
import com.pr.commons.csv.ParseResult;

import java.util.Set;

/**
 * @Author Rahul Gaur.
 * Created on 4/1/2017
 */
public class ExpenseManagerParser extends CSVParser<ActivityCsv>{

	public ParseResult<ActivityCsv> parse(String content) {
		return super.parse(content, ActivityCsv.class);
	}

	@Override
	protected void validateData(ActivityCsv activityCsv, Set<String> error) {

	}
}
